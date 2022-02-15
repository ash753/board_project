package board.boardProject.service;

import board.boardProject.domain.dto.FileSaveDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    void storeFiles(List<MultipartFile> fileList, Integer boardId) throws IOException;
}
