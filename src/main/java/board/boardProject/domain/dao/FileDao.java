package board.boardProject.domain.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
public class FileDao {
    private Integer id;
    private String originalName;
    private String savedName;
    private Integer boardId;

    public FileDao() {
    }


    public FileDao(String originalFile, String savedName, Integer boardId) {
        this.originalName = originalFile;
        this.savedName = savedName;
        this.boardId = boardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileDao fileDao = (FileDao) o;
        return Objects.equals(getId(), fileDao.getId()) && Objects.equals(getOriginalName(), fileDao.getOriginalName()) && Objects.equals(getSavedName(), fileDao.getSavedName()) && Objects.equals(getBoardId(), fileDao.getBoardId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOriginalName(), getSavedName(), getBoardId());
    }
}
