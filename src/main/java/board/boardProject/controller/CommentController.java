package board.boardProject.controller;

import board.boardProject.domain.dto.BoardPrintDto;
import board.boardProject.domain.dto.CommentAddDto;
import board.boardProject.domain.dto.CommentPrintDto;
import board.boardProject.domain.dto.FileDownloadDto;
import board.boardProject.service.BoardService;
import board.boardProject.service.CommentService;
import board.boardProject.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final BoardService boardService;
    private final FileService fileService;
    private final CommentService commentService;

    @PostMapping("/boards/{boardId}/comments")
    public String addComments(@PathVariable Integer boardId,
                              @Validated @ModelAttribute CommentAddDto commentSaveDto,
                              BindingResult bindingResult,
                              Model model){
        if(bindingResult.hasErrors()){
            BoardPrintDto boardPrintDto = boardService.getBoardInfo(boardId);
            List<CommentPrintDto> commentPrintDtoList = commentService.getCommentListByBoardId(boardId);
            List<FileDownloadDto> fileDownloadDtoList = fileService.getFileInfoByBoardId(boardId);

            model.addAttribute("board", boardPrintDto);
            model.addAttribute("comments", commentPrintDtoList);
            model.addAttribute("files", fileDownloadDtoList);

            String nlString = System.getProperty("line.separator").toString();
            model.addAttribute("nlString", nlString);
            return "board";
        }
        commentService.addComment(commentSaveDto, boardId);
        return "redirect:/boards/{boardId}";
    }

    @DeleteMapping("boards/{boardId}/comments/{commentId}")
    public String deleteComments(@PathVariable Integer commentId){
        commentService.deleteCommentByCommentId(commentId);
        return "redirect:/boards/{boardId}";
    }
}
