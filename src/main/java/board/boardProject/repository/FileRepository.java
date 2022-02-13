package board.boardProject.repository;

import board.boardProject.domain.dao.BoardDao;
import board.boardProject.domain.dao.FileDao;
import board.boardProject.repository.mapper.FileDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
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

    public boolean delete(Integer fileDaoId){
        int affectedNumber = fileDaoMapper.delete(fileDaoId);
        if(affectedNumber>0)return true;
        else return false;
    }

    public boolean deleteByBoardId(Integer boardId){
        int affectedNumber = fileDaoMapper.deleteByBoardId(boardId);
        if(affectedNumber>0) return true;
        else return false;
    }

    public boolean deleteAll(){
        int affectedNumber = fileDaoMapper.deleteAll();
        if(affectedNumber>0)return true;
        else return false;
    }
}
