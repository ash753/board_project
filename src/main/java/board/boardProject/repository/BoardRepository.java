package board.boardProject.repository;

import board.boardProject.domain.dao.BoardDao;
import board.boardProject.mapper.BoardDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepository {

    @Autowired
    private BoardDaoMapper boardDaoMapper;

    public BoardDao save(BoardDao boardDao){
        boardDaoMapper.save(boardDao);
        return boardDao;
    }

    public List<BoardDao> getAll(){
        return boardDaoMapper.getAll();
    }

    public BoardDao findById(Integer boardId){
        return boardDaoMapper.findById(boardId);
    }

    public boolean edit(BoardDao boardDao){
        int affectedNumber = boardDaoMapper.edit(boardDao);
        if(affectedNumber==1)return true;
        else return false;
    }
    public boolean delete(Integer boardId){
        int affectedNumber = boardDaoMapper.delete(boardId);
        if(affectedNumber==1)return true;
        else return false;
    }

    public void deleteAll(){
        boardDaoMapper.deleteAll();
    }

}
