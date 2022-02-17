package board.boardProject.controller;

import board.boardProject.domain.dao.BoardDao;
import board.boardProject.domain.dto.*;
import board.boardProject.service.BoardService;
import board.boardProject.service.CommentService;
import board.boardProject.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;
    private final FileService fileService;

    @GetMapping("/boards")
    public String boardsList(Model model) {
        model.addAttribute("boards", boardService.getBoardList());
        return "boardList";
    }

    @GetMapping("/boards/{boardId}")
    public String board(@PathVariable Integer boardId, Model model) {

        BoardPrintDto boardPrintDto = boardService.getBoardInfo(boardId);
        List<CommentPrintDto> commentPrintDtoList = commentService.getCommentListByBoardId(boardId);
        List<FileDownloadDto> fileDownloadDtoList = fileService.getFileInfoByBoardId(boardId);

        model.addAttribute("board", boardPrintDto);
        model.addAttribute("comments", commentPrintDtoList);
        model.addAttribute("files", fileDownloadDtoList);
        model.addAttribute("commentAddDto", new CommentAddDto());

        return "board";
    }

    @GetMapping("/boards/newForm")
    public String createBoardForm(@ModelAttribute("boardForm") BoardAddDto boardAddDto) {
        return "createBoardForm";
    }

    @PostMapping("/boards")
    public String createBoard(@Validated @ModelAttribute("boardForm") BoardAddDto boardAddDto,
                              BindingResult bindingResult) throws IOException {

        if(bindingResult.hasErrors()){
            return "createBoardForm";
        }

        BoardDao boardDao = boardService.addBoard(boardAddDto);
        FileSaveDto fileSaveDto = new FileSaveDto(boardAddDto.getFileList());
        fileService.storeFiles(fileSaveDto, boardDao.getId());
        return "redirect:/boards/"+boardDao.getId();
    }

    @GetMapping("/boards/{boardId}/editForm")
    public String boardEditForm(@PathVariable("boardId") Integer boardId ,Model model) {
        BoardPrintDto boardPrintDto = boardService.getBoardInfo(boardId);
        model.addAttribute("boardEditDto", new BoardEditDto(boardPrintDto.getTitle() ,boardPrintDto.getContent()));
        return "editBoard";
    }

    @PutMapping("/boards/{boardId}")
    public String editBoard(@PathVariable("boardId") Integer boardId,
                            @Validated @ModelAttribute("boardEditDto") BoardEditDto boardEditDto,
                            BindingResult bindingResult) throws IOException {

        if(bindingResult.hasErrors()){
            return "editBoard";
        }

        FileSaveDto fileSaveDto = new FileSaveDto(boardEditDto.getFileList());
        boardService.editBoard(boardEditDto, boardId);
        fileService.changeFiles(fileSaveDto, boardId);
        return "redirect:/boards/{boardId}";
    }

    @DeleteMapping("/boards/{boardId}")
    public String boardDelete(@PathVariable("boardId") Integer boardId){
        fileService.deleteFileByBoardId(boardId);
        commentService.deleteCommentByBoardId(boardId);
        boardService.deleteBoard(boardId);
        return "redirect:/boards";
    }
}
