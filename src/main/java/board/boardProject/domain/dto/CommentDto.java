package board.boardProject.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentDto {
    private String content;
    private String date;
    private Integer boardId;
}
