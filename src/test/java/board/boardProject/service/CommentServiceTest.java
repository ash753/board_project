package board.boardProject.service;

import board.boardProject.domain.dao.BoardDao;
import board.boardProject.domain.dao.CommentDao;
import board.boardProject.domain.dto.BoardAddDto;
import board.boardProject.domain.dto.CommentAddDto;
import board.boardProject.domain.dto.CommentPrintDto;
import board.boardProject.repository.CommentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommentServiceTest {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BoardService boardService;

    private BoardDao boardDao;

    @BeforeAll
    void boardSetting() throws IOException {
        boardDao = boardService.addBoard(new BoardAddDto("제목", "내용"));
    }
    @BeforeEach
    void before(){
        commentService.deleteCommentByBoardId(boardDao.getId());
    }

    @Test
    void getCommentListByBoardId() {
        //given
        CommentAddDto commentAddDto1 = new CommentAddDto("댓글 내용");
        CommentAddDto commentAddDto2 = new CommentAddDto("댓글 내용2");
        CommentDao commentDao1 = commentService.addComment(commentAddDto1, boardDao.getId());
        CommentDao commentDao2 = commentService.addComment(commentAddDto2, boardDao.getId());

        //when
        List<CommentPrintDto> commentList = commentService.getCommentListByBoardId(boardDao.getId());
        List<String> commentContents = new ArrayList<>();
        for (CommentPrintDto commentPrintDto : commentList) {
            commentContents.add(commentPrintDto.getContent());
        }

        //then
        assertThat(commentContents).contains("댓글 내용", "댓글 내용2");
    }

    @Test
    void deleteCommentByBoardId() {
        //given
        CommentAddDto commentAddDto1 = new CommentAddDto("댓글 내용");
        CommentAddDto commentAddDto2 = new CommentAddDto("댓글 내용2");
        CommentDao commentDao1 = commentService.addComment(commentAddDto1, boardDao.getId());
        CommentDao commentDao2 = commentService.addComment(commentAddDto2, boardDao.getId());

        //when
        commentService.deleteCommentByBoardId(boardDao.getId());
        List<CommentPrintDto> commentList = commentService.getCommentListByBoardId(boardDao.getId());

        //then
        assertThat(commentList.size()).isEqualTo(0);
    }

    @Test
    void addComment() {
        //given
        CommentAddDto commentAddDto1 = new CommentAddDto("댓글 내용");
        CommentDao commentDao1 = commentService.addComment(commentAddDto1, boardDao.getId());

        //when
        List<CommentPrintDto> commentList = commentService.getCommentListByBoardId(boardDao.getId());
        List<String> commentContents = new ArrayList<>();
        for (CommentPrintDto commentPrintDto : commentList) {
            commentContents.add(commentPrintDto.getContent());
        }

        //then
        assertThat(commentContents).contains("댓글 내용");
    }

    @Test
    void deleteCommentByCommentId() {
        //given
        CommentAddDto commentAddDto1 = new CommentAddDto("댓글 내용");
        CommentDao commentDao1 = commentService.addComment(commentAddDto1, boardDao.getId());

        //when
        commentService.deleteCommentByCommentId(commentDao1.getId());
        List<CommentPrintDto> commentListByBoardId = commentService.getCommentListByBoardId(boardDao.getId());

        //then
        assertThat(commentListByBoardId.size()).isEqualTo(0);
    }
}