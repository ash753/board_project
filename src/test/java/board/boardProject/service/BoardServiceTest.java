package board.boardProject.service;

import board.boardProject.domain.dao.BoardDao;
import board.boardProject.domain.dto.BoardAddDto;
import board.boardProject.domain.dto.BoardEditDto;
import board.boardProject.domain.dto.BoardListDto;
import board.boardProject.domain.dto.BoardPrintDto;
import board.boardProject.exception.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceTest {
    @Autowired
    private BoardService boardService;



    @Test
    void getBoardList() throws IOException {
        //given
        BoardAddDto boardAddDto1 = new BoardAddDto("제목1", "내용1");
        BoardAddDto boardAddDto2 = new BoardAddDto("제목2", "내용2");
        boardService.addBoard(boardAddDto1);
        boardService.addBoard(boardAddDto2);

        //when
        List<BoardListDto> boardList = boardService.getBoardList();
        List<String> result = new ArrayList<>();
        for (BoardListDto boardListDto : boardList) {
            result.add(boardListDto.getTitle());
        }

        //then
        assertThat(result).contains(boardAddDto1.getTitle(), boardAddDto2.getTitle());
    }

    @Test
    void addBoard() throws IOException {
        //given
        BoardAddDto boardAddDto = new BoardAddDto("제목 3", "내용3");

        //when
        BoardDao boardDao = boardService.addBoard(boardAddDto);

        //then
        assertThat(boardDao.getTitle()).isEqualTo(boardAddDto.getTitle());
        assertThat(boardDao.getContent()).isEqualTo(boardAddDto.getContent());
    }

    @Test
    void getBoardInfo() throws IOException {
        //given
        BoardAddDto boardAddDto = new BoardAddDto("제목 4", "내용4");
        BoardDao boardDao = boardService.addBoard(boardAddDto);

        //when
        BoardPrintDto boardInfo = boardService.getBoardInfo(boardDao.getId());

        //then
        assertThat(boardInfo.getTitle()).isEqualTo(boardAddDto.getTitle());
        assertThat(boardInfo.getContent()).isEqualTo(boardAddDto.getContent());
    }

    @Test
    void editBoard() throws IOException {
        //given
        BoardAddDto boardAddDto = new BoardAddDto("제목 5", "내용5");
        BoardDao boardDao = boardService.addBoard(boardAddDto);
        BoardEditDto boardEditDto = new BoardEditDto("제목 6", "제목 6");

        //when
        boardService.editBoard(boardEditDto, boardDao.getId());
        BoardPrintDto boardPrintDto = boardService.getBoardInfo(boardDao.getId());

        //then
        assertThat(boardPrintDto.getTitle()).isEqualTo(boardEditDto.getTitle());
        assertThat(boardPrintDto.getContent()).isEqualTo(boardEditDto.getContent());
    }

    @Test
    void deleteBoard() throws IOException {
        //given
        BoardAddDto boardAddDto = new BoardAddDto("제목 7", "내용7");
        BoardDao boardDao = boardService.addBoard(boardAddDto);

        //when
        boardService.deleteBoard(boardDao.getId());

        //then
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class,()->{
            boardService.getBoardInfo(boardDao.getId());
        });

    }
}