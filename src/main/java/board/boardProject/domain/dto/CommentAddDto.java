package board.boardProject.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentAddDto {
    private String content;

    public CommentAddDto() {
    }

    public CommentAddDto(String content) {
        this.content = content;
    }
}
