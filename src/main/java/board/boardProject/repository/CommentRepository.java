package board.boardProject.repository;

import board.boardProject.domain.dao.CommentDao;
import board.boardProject.repository.mapper.CommentDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {

    @Autowired
    private CommentDaoMapper commentDaoMapper;

    public CommentDao save(CommentDao commentDao){
        commentDaoMapper.save(commentDao);
        return commentDao;
    }
    public CommentDao findByCommentId(Integer commentId){
        return commentDaoMapper.findByCommentId(commentId);
    }

    public List<CommentDao> findByBoardId(Integer boardId){
        return commentDaoMapper.findByBoardId(boardId);
    }

    public boolean edit(CommentDao commentDao){
        int affectedNumber = commentDaoMapper.edit(commentDao);
        if(affectedNumber>0) return true;
        else return false;
    }

    public boolean deleteByCommentId(Integer commentId){
        int affectedNumber = commentDaoMapper.deleteByCommentId(commentId);
        if(affectedNumber>0) return true;
        else return false;
    }

    public boolean deleteByBoardId(Integer boardId){
        int affectedNumber = commentDaoMapper.deleteByBoardId(boardId);
        if(affectedNumber>0) return true;
        else return false;
    }

    public boolean deleteAll(){
        int affectedNumber = commentDaoMapper.deleteAll();
        if(affectedNumber>0) return true;
        else return false;
    }
}
