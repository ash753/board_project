package board.boardProject.service;

import board.boardProject.domain.dao.FileDao;
import board.boardProject.domain.dto.FileSaveDto;
import board.boardProject.filestore.FileStore;
import board.boardProject.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImp implements FileService{
    private final FileRepository fileRepository;
    private final FileStore fileStore;

    @Override
    public void storeFiles(List<MultipartFile> fileList, Integer boardId) throws IOException {
        List<FileSaveDto> fileSaveDtoList = fileStore.storeFiles(fileList);
        for (FileSaveDto fileSaveDto : fileSaveDtoList) {
            fileRepository.save(new FileDao(fileSaveDto.getOriginalName(), fileSaveDto.getSavedName(), boardId));
        }
    }
}
