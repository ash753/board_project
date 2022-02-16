package board.boardProject.repository;

import board.boardProject.domain.dao.BoardDao;
import board.boardProject.domain.dao.CommentDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentRepositoryTest {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    CommentRepository commentRepository;

    @Test
    public void saveTest() throws Exception{
        //given
        String now = getCurrentTimeAsString();
        BoardDao board1 = new BoardDao("스프링1", "안녕하세요", now);
        BoardDao board2 = new BoardDao("스프링2", "안녕하세요", now);
        boardRepository.save(board1);
        boardRepository.save(board2);

        CommentDao comment1 = new CommentDao("댓글입니다1", now, board1.getId());
        CommentDao comment2 = new CommentDao("댓글입니다2", now, board1.getId());
        CommentDao comment3 = new CommentDao("댓글입니다3", now, board2.getId());

        commentRepository.save(comment1);
        commentRepository.save(comment2);
        commentRepository.save(comment3);

        //when
        List<CommentDao> findCommentList = commentRepository.findByBoardId(board1.getId());

        //then
        assertThat(findCommentList).contains(comment1, comment2);
     }

    @Test
     public void editTest() throws Exception{
         //given
         String now = getCurrentTimeAsString();
         BoardDao board1 = new BoardDao("스프링1", "안녕하세요", now);
         BoardDao board2 = new BoardDao("스프링2", "안녕하세요", now);
         boardRepository.save(board1);
         boardRepository.save(board2);

         CommentDao comment1 = new CommentDao("댓글입니다1", now, board1.getId());
         CommentDao comment2 = new CommentDao("댓글입니다2", now, board1.getId());
         CommentDao comment3 = new CommentDao("댓글입니다3", now, board2.getId());

         commentRepository.save(comment1);
         commentRepository.save(comment2);
         commentRepository.save(comment3);

         //when
         now = getCurrentTimeAsString();
         comment1.setContent("수정된 댓글입니다.");
         comment1.setDate(now);
         commentRepository.edit(comment1);
         CommentDao findComment = commentRepository.findByCommentId(comment1.getId());

         //then
         assertThat(findComment).isEqualTo(comment1);
     }

     @Test
     public void deleteByCommentIdTest() throws Exception{
         //given
         String now = getCurrentTimeAsString();
         BoardDao board1 = new BoardDao("스프링1", "안녕하세요", now);
         BoardDao board2 = new BoardDao("스프링2", "안녕하세요", now);
         boardRepository.save(board1);
         boardRepository.save(board2);

         CommentDao comment1 = new CommentDao("댓글입니다1", now, board1.getId());
         CommentDao comment2 = new CommentDao("댓글입니다2", now, board1.getId());
         CommentDao comment3 = new CommentDao("댓글입니다3", now, board2.getId());

         commentRepository.save(comment1);
         commentRepository.save(comment2);
         commentRepository.save(comment3);
         //when
         commentRepository.deleteByCommentId(comment2.getId());

         //then
         List<CommentDao> findCommentList = commentRepository.findByBoardId(board1.getId());
         assertThat(findCommentList).doesNotContain(comment2);
      }

    @Test
    public void deleteByBoardIdTest() throws Exception{
        //given
        String now = getCurrentTimeAsString();
        BoardDao board1 = new BoardDao("스프링1", "안녕하세요", now);
        BoardDao board2 = new BoardDao("스프링2", "안녕하세요", now);
        boardRepository.save(board1);
        boardRepository.save(board2);

        CommentDao comment1 = new CommentDao("댓글입니다1", now, board1.getId());
        CommentDao comment2 = new CommentDao("댓글입니다2", now, board1.getId());
        CommentDao comment3 = new CommentDao("댓글입니다3", now, board2.getId());

        commentRepository.save(comment1);
        commentRepository.save(comment2);
        commentRepository.save(comment3);

        //when
        commentRepository.deleteByBoardId(board1.getId());

        //then
        List<CommentDao> findCommentList = commentRepository.findByBoardId(board1.getId());
        assertThat(findCommentList.size()).isEqualTo(0);
    }

    private String getCurrentTimeAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(Calendar.getInstance().getTime());
        return now;
    }
}