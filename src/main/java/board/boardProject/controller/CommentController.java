package board.boardProject.controller;

import board.boardProject.domain.dto.CommentAddDto;
import board.boardProject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/boards/{boardId}/comments")
    public String addComments(@PathVariable Integer boardId,
                              @Validated @ModelAttribute CommentAddDto commentAddDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentAddDto", bindingResult);
            redirectAttributes.addFlashAttribute("commentAddDto", commentAddDto);
            return "redirect:/boards/{boardId}";
        }
        commentService.addComment(commentAddDto, boardId);
        return "redirect:/boards/{boardId}";
    }

    @DeleteMapping("boards/{boardId}/comments/{commentId}")
    public String deleteComments(@PathVariable Integer commentId){
        commentService.deleteCommentByCommentId(commentId);
        return "redirect:/boards/{boardId}";
    }
}
