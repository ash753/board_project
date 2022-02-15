package board.boardProject.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter @Setter
public class FileSaveDto {
    private List<MultipartFile> fileList;

    public FileSaveDto(List<MultipartFile> fileList) {
        this.fileList = fileList;
    }
}
