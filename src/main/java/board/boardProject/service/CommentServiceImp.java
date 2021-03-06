package board.boardProject.service;

import board.boardProject.domain.dao.CommentDao;
import board.boardProject.domain.dto.CommentPrintDto;
import board.boardProject.domain.dto.CommentAddDto;
import board.boardProject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Service
@Transactional
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
    public CommentDao addComment(CommentAddDto commentAddDto, Integer boardId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String now = sdf.format(Calendar.getInstance().getTime());

        CommentDao commentDao = new CommentDao(commentAddDto.getContent(), now, boardId);
        return commentRepository.save(commentDao);
    }

    @Override
    public void deleteCommentByCommentId(Integer commentId) {
        commentRepository.deleteByCommentId(commentId);
    }
}
