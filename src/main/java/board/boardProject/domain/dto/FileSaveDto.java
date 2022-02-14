package board.boardProject.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class FileSaveDto {
    private String originalName;
    private String savedName;

    public FileSaveDto(String originalName, String savedName) {
        this.originalName = originalName;
        this.savedName = savedName;
    }
}
