package board.boardProject.service;

import board.boardProject.domain.dto.FileDownloadDto;
import board.boardProject.domain.dto.FileSaveDto;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface FileService {
    void storeFiles(FileSaveDto fileSaveDto, Integer boardId) throws IOException;
    void changeFiles(FileSaveDto fileSaveDto, Integer boardId) throws IOException;
    List<FileDownloadDto> getFileInfoByBoardId(Integer boardId);
    Resource getFileById(Integer fileId) throws MalformedURLException;
    String getOriginalFileName(Integer fileId);
    void deleteFileByBoardId(Integer boardId);
}
