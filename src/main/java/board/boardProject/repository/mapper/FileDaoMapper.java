package board.boardProject.repository.mapper;

import board.boardProject.domain.dao.FileDao;
import board.boardProject.repository.FileRepository;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileDaoMapper {

    @Insert("INSERT INTO file(original_name, saved_name, board_id) VALUES(#{file.originalName}, #{file.savedName}, #{file.boardId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(@Param("file") FileDao fileDao);

    @Select("SELECT * FROM file WHERE file_id=#{fileId}")
    @Results(id="FileMap", value={
            @Result(property = "id", column = "file_id"),
            @Result(property = "originalName", column = "original_name"),
            @Result(property = "savedName", column = "saved_name"),
            @Result(property = "boardId", column = "board_id")
    })
    FileDao findById(@Param("fileId") Integer fileDaoId);

    @Select("SELECT * FROM file")
    @ResultMap("FileMap")
    List<FileDao> getAll();


    @Select("SELECT * FROM file WHERE board_id=#{boardId}")
    @ResultMap("FileMap")
    List<FileDao> findByBoardId(@Param("boardId") Integer boardId);

    @Update("UPDATE file SET file_id=#{file.id}, original_name=#{file.originalName}, saved_name=#{file.savedName}, board_id=#{boardId} WHERE file_id=#{file.id}")
    int edit(@Param("file") FileDao fileDao);

    @Delete("DELETE FROM file WHERE file_id=#{id}")
    int delete(@Param("id") Integer fileDaoId);

    @Delete("DELETE FROM file WHERE board_id=#{boardId}")
    int deleteByBoardId(@Param("boardId") Integer boardId);

    @Delete("DELETE FROM file")
    int deleteAll();
}
