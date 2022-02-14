package board.boardProject.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class FileDownloadDto {
    private String originalName;
    private MultipartFile multipartFile;

    public FileDownloadDto(String originalName, MultipartFile multipartFile) {
        this.originalName = originalName;
        this.multipartFile = multipartFile;
    }
}
