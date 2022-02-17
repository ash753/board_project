package board.boardProject.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class CommentAddDto {
    @NotBlank
    private String content;

    public CommentAddDto() {
    }

    public CommentAddDto(String content) {
        this.content = content;
    }
}
