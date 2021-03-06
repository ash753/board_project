package board.boardProject.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class BoardPrintDto {
    private Integer id;
    private String title;
    private String content;
    private String date;

    public BoardPrintDto(Integer id, String title, String content, String date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }
}
