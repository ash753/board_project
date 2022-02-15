package board.boardProject.service;

import board.boardProject.domain.dao.BoardDao;
import board.boardProject.domain.dto.BoardAddDto;
import board.boardProject.domain.dto.BoardEditDto;
import board.boardProject.domain.dto.BoardListDto;
import board.boardProject.domain.dto.BoardPrintDto;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    List<BoardListDto> getBoardList();

    BoardDao addBoard(BoardAddDto boardAddDto) throws IOException;

    BoardPrintDto getBoardInfo(Integer boardId);

    void editBoard(BoardEditDto boardEditDto, Integer boardId);

    void deleteBoard(Integer boardId);
}
