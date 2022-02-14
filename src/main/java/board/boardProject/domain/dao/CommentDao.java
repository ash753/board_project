package board.boardProject.domain.dao;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter
public class CommentDao {
    private Integer id;
    private String content;
    private String date;
    private Integer boardId;

    public CommentDao() {
    }

    public CommentDao(String content, String date, Integer boardId) {
        this.content = content;
        this.date = date;
        this.boardId = boardId;
    }

    @Override
    public String toString() {
        return "CommentDao{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", boardId=" + boardId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDao that = (CommentDao) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getContent(), that.getContent()) && Objects.equals(getDate(), that.getDate()) && Objects.equals(getBoardId(), that.getBoardId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContent(), getDate(), getBoardId());
    }
}