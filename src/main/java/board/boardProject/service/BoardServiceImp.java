package board.boardProject.service;

import board.boardProject.domain.dao.BoardDao;
import board.boardProject.domain.dao.FileDao;
import board.boardProject.domain.dto.*;
import board.boardProject.filestore.FileStore;
import board.boardProject.repository.BoardRepository;
import board.boardProject.repository.CommentRepository;
import board.boardProject.repository.FileRepository;
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
    private final FileRepository fileRepository;
    private final CommentRepository commentRepository;
    private final FileStore fileStore;

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
    public void addBoard(BoardAddDto boardAddDto) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(Calendar.getInstance().getTime());

        BoardDao boardDao = new BoardDao(boardAddDto.getTitle(), boardAddDto.getContent(), now);
        BoardDao savedBoardDao = boardRepository.save(boardDao);

        List<FileSaveDto> fileSaveDtoList = fileStore.storeFiles(boardAddDto.getFileList());
        for (FileSaveDto fileSaveDto : fileSaveDtoList) {
            fileRepository.save(new FileDao(fileSaveDto.getOriginalName(), fileSaveDto.getSavedName(), savedBoardDao.getId()));
        }
    }
}
