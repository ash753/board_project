package board.boardProject.repository;

import board.boardProject.domain.dao.CommentDao;
import board.boardProject.mapper.CommentDaoMapper;
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

    public void edit(CommentDao commentDao){
        commentDaoMapper.edit(commentDao);
    }

    public void deleteByCommentId(Integer commentId){
        commentDaoMapper.deleteByCommentId(commentId);
    }

    public void deleteByBoardId(Integer boardId){
        commentDaoMapper.deleteByBoardId(boardId);
    }
}
