package board.boardProject.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter @Setter
public class BoardEditDto {
    private String title;
    private String content;
    private List<MultipartFile> fileList;

    public BoardEditDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
