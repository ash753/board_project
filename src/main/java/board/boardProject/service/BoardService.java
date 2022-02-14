package board.boardProject.service;

import board.boardProject.domain.dto.BoardAddDto;
import board.boardProject.domain.dto.BoardListDto;
import board.boardProject.domain.dto.BoardPrintDto;
import board.boardProject.domain.dto.FileDownloadDto;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    List<BoardListDto> getBoardList();

    void addBoard(BoardAddDto boardAddDto) throws IOException;

}
