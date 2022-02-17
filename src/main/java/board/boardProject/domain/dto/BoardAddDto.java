package board.boardProject.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter @Setter
public class BoardAddDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private List<MultipartFile> fileList;

    public BoardAddDto() {
    }

    public BoardAddDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
