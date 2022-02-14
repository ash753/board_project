package board.boardProject.mapper;

import board.boardProject.domain.dao.CommentDao;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentDaoMapper {
    @Insert("INSERT INTO comment(content, date, board_id) VALUES(#{comment.content}, #{comment.date}, #{comment.boardId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(@Param("comment") CommentDao commentDao);

    @Select("SELECT * FROM comment WHERE board_id = #{boardId}")
    @Results(id="CommentMap", value = {
            @Result(property = "id", column = "comment_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "date", column = "date"),
            @Result(property = "boardId", column = "board_id")
    })
    List<CommentDao> findByBoardId(@Param("boardId") Integer boardId);

    @Select("SELECT * FROM comment WHERE comment_id=#{id}")
    @ResultMap("CommentMap")
    CommentDao findByCommentId(@Param("id") Integer commentId);

    @Update("UPDATE comment SET comment_id =#{comment.id}, content=#{comment.content}, date=#{comment.date}, board_id=#{comment.boardId} WHERE comment_id=#{comment.id}")
    int edit(@Param("comment") CommentDao commentDao);

    @Delete("DELETE FROM comment WHERE comment_id=#{id}")
    int deleteByCommentId(@Param("id") Integer commentId);

    @Delete("DELETE FROM comment WHERE board_id=#{boardId}")
    int deleteByBoardId(@Param("boardId") Integer boardId);

    @Delete("DELETE FROM comment")
    int deleteAll();
}
