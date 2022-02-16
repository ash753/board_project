package board.boardProject.service;

import board.boardProject.domain.dao.FileDao;
import board.boardProject.domain.dto.FileDownloadDto;
import board.boardProject.domain.dto.FileSaveDto;
import board.boardProject.filemanagement.FileManagement;
import board.boardProject.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FileServiceImp implements FileService{

    private final FileRepository fileRepository;
    private final FileManagement fileManagement;

    @Override
    public List<FileDao> storeFiles(FileSaveDto fileSaveDto, Integer boardId) throws IOException {
        List<MultipartFile> fileList = fileSaveDto.getFileList();
        List<FileDao> fileDaoList = fileManagement.storeFiles(fileList);

        for (FileDao fileDao : fileDaoList) {
            fileDao.setBoardId(boardId);
            fileRepository.save(fileDao);
        }
        return fileDaoList;
    }

    @Override
    public void changeFiles(FileSaveDto fileSaveDto, Integer boardId) throws IOException {
        deleteFileByBoardId(boardId);
        storeFiles(fileSaveDto, boardId);
    }

    @Override
    public List<FileDownloadDto> getFileInfoByBoardId(Integer boardId) {
        List<FileDownloadDto> result = new ArrayList<>();
        List<FileDao> findFileDaoList = fileRepository.findByBoardId(boardId);
        for (FileDao fileDao : findFileDaoList) {
            result.add(new FileDownloadDto(fileDao.getId(), fileDao.getOriginalName()));
        }
        return result;
    }

    @Override
    public Resource getFileById(Integer fileId) throws MalformedURLException {
        FileDao fileDao = fileRepository.findById(fileId);
        return new UrlResource("file:" + fileManagement.getFullPath(fileDao.getSavedName()));
    }

    @Override
    public String getOriginalFileName(Integer fileId) {
        FileDao fileDao = fileRepository.findById(fileId);
        return fileDao.getOriginalName();
    }

    @Override
    public void deleteFileByBoardId(Integer boardId) {
        List<FileDao> fileDaoList = fileRepository.findByBoardId(boardId);
        fileManagement.deleteFiles(fileDaoList);
        fileRepository.deleteByBoardId(boardId);
    }
}
