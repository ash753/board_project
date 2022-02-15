package board.boardProject.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentPrintDto {
    private Integer id;
    private String content;
    private String date;

    public CommentPrintDto(Integer id, String content, String date) {
        this.id = id;
        this.content = content;
        this.date = date;
    }
}
