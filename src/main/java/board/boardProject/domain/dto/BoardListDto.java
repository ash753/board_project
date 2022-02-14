package board.boardProject.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardListDto {
    private Integer id;
    private String title;
    private String date;

    public BoardListDto(Integer id, String title, String date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }
}
