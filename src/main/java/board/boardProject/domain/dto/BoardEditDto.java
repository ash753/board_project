package board.boardProject.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter @Setter
public class BoardEditDto {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private List<MultipartFile> fileList;

    public BoardEditDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
