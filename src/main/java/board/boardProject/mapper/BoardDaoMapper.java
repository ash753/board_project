package board.boardProject.mapper;

import board.boardProject.domain.dao.BoardDao;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardDaoMapper {
    @Insert("INSERT INTO board(title, content, date) VALUES( #{board.title}, #{board.content}, #{board.date})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(@Param("board") BoardDao boardDao);

    @Select("SELECT * FROM board")
    @Results(id="BoardMap", value={
            @Result(property = "id", column = "board_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "date", column = "date")
    })
    List<BoardDao> getAll();

    @Select("SELECT * FROM board WHERE board_id=#{id}")
    @ResultMap("BoardMap")
    BoardDao findById(@Param("id") Integer boardId);

    @Update("UPDATE board SET board_id=#{board.id}, title=#{board.title}, content=#{board.content}, date=#{board.date} WHERE board_id=#{board.id}")
    int edit(@Param("board") BoardDao boardDao);

    @Delete("DELETE FROM board WHERE board_id=#{id}")
    int delete(@Param("id") Integer boardId);

    @Delete("DELETE FROM board")
    int deleteAll();
}
