package board.boardProject.service;

import board.boardProject.domain.dao.CommentDao;
import board.boardProject.domain.dto.CommentPrintDto;
import board.boardProject.domain.dto.CommentAddDto;
import board.boardProject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {
    private final CommentRepository commentRepository;
    @Override
    public List<CommentPrintDto> getCommentListByBoardId(Integer boardId) {
        List<CommentPrintDto> result = new ArrayList<>();
        List<CommentDao> findCommentDaoList = commentRepository.findByBoardId(boardId);
        for (CommentDao commentDao : findCommentDaoList) {
            result.add(new CommentPrintDto(commentDao.getId(), commentDao.getContent(), commentDao.getDate()));
        }
        return result;
    }

    @Override
    public void deleteCommentByBoardId(Integer boardId) {
        commentRepository.deleteByBoardId(boardId);
    }

    @Override
    public void addComment(CommentAddDto commentSaveDto, Integer boardId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(Calendar.getInstance().getTime());

        CommentDao commentDao = new CommentDao(commentSaveDto.getContent(), now, boardId);
        commentRepository.save(commentDao);
    }

    @Override
    public void deleteCommentByCommentId(Integer commentId) {
        commentRepository.deleteByCommentId(commentId);
    }
}
