package board.boardProject.service;

import board.boardProject.domain.dao.BoardDao;
import board.boardProject.domain.dto.*;
import board.boardProject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImp implements BoardService{
    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public List<BoardListDto> getBoardList() {
        List<BoardListDto> boardListDtoList = new ArrayList<>();

        List<BoardDao> boardDaoList = boardRepository.getAll();
        for (BoardDao boardDao : boardDaoList) {
            boardListDtoList.add(new BoardListDto(boardDao.getId(), boardDao.getTitle(), boardDao.getDate()));
        }
        return boardListDtoList;
    }

    @Override
    @Transactional
    public BoardDao addBoard(BoardAddDto boardAddDto) throws IOException {
        String now = getCurrentTimeAsString();
        BoardDao boardDao = new BoardDao(boardAddDto.getTitle(), boardAddDto.getContent(), now);
        BoardDao savedBoardDao = boardRepository.save(boardDao);
        return savedBoardDao;
    }

    @Override
    public BoardPrintDto getBoardInfo(Integer boardId) {
        BoardDao findBoardDao = boardRepository.findById(boardId);
        return new BoardPrintDto(findBoardDao.getId(), findBoardDao.getTitle(), findBoardDao.getContent(), findBoardDao.getDate());
    }

    @Override
    public void editBoard(BoardEditDto boardEditDto, Integer boardId) {
        String now = getCurrentTimeAsString();
        BoardDao boardDao = new BoardDao(boardId, boardEditDto.getTitle(), boardEditDto.getContent(), now);
        boardRepository.edit(boardDao);
    }

    @Override
    public void deleteBoard(Integer boardId) {
        boardRepository.delete(boardId);
    }


    private String getCurrentTimeAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(Calendar.getInstance().getTime());
        return now;
    }
}
