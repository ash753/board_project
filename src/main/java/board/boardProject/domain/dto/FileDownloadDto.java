package board.boardProject.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class FileDownloadDto {
    private Integer id;
    private String originalName;

    public FileDownloadDto(Integer id, String originalName) {
        this.id = id;
        this.originalName = originalName;
    }
}
