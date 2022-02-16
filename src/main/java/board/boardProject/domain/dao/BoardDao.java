package board.boardProject.domain.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter
public class BoardDao {
    private Integer id;
    private String title;
    private String content;
    private String date;

    public BoardDao() {
    }

    public BoardDao(String title, String content, String date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public BoardDao(Integer id, String title, String content, String date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDao boardDao = (BoardDao) o;
        return Objects.equals(getId(), boardDao.getId()) && Objects.equals(getTitle(), boardDao.getTitle()) && Objects.equals(getContent(), boardDao.getContent()) && Objects.equals(getDate(), boardDao.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getContent(), getDate());
    }
}
