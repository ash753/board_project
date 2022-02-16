package board.boardProject.repository;

import board.boardProject.domain.dao.FileDao;
import board.boardProject.mapper.FileDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileRepository {

    @Autowired
    private FileDaoMapper fileDaoMapper;

    public FileDao save(FileDao fileDao){
        fileDaoMapper.save(fileDao);
        return fileDao;
    }

    public FileDao findById(Integer fileDaoId){
        return fileDaoMapper.findById(fileDaoId);
    }

    public List<FileDao> findByBoardId(Integer boardId){
        return fileDaoMapper.findByBoardId(boardId);
    }

    public void delete(Integer fileDaoId){
        fileDaoMapper.delete(fileDaoId);
    }

    public void deleteByBoardId(Integer boardId){
        fileDaoMapper.deleteByBoardId(boardId);
    }
}
