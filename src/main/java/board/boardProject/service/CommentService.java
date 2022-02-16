package board.boardProject.service;

import board.boardProject.domain.dao.CommentDao;
import board.boardProject.domain.dto.CommentPrintDto;
import board.boardProject.domain.dto.CommentAddDto;

import java.util.List;


public interface CommentService {
    List<CommentPrintDto> getCommentListByBoardId(Integer boardId);

    void deleteCommentByBoardId(Integer boardId);

    CommentDao addComment(CommentAddDto commentAddDto, Integer boardId);

    void deleteCommentByCommentId(Integer commentId);
}
