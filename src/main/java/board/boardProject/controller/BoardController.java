package board.boardProject.controller;

import board.boardProject.domain.dto.BoardAddDto;
import board.boardProject.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/boards")
    public String boardsList(Model model) {
        model.addAttribute("boards", boardService.getBoardList());
        return "boardList";
    }

    @GetMapping("/boards/new")
    public String createBoardForm(@ModelAttribute("boardForm") BoardAddDto boardAddDto) {
        return "createBoardForm";
    }

    @PostMapping("boards/new")
    public String createBoard(@ModelAttribute BoardAddDto boardAddDto) throws IOException {
        log.info("boardAddDto.fileList.size()={}",boardAddDto.getFileList().size());
        boardService.addBoard(boardAddDto);
        return "redirect:/boards";
    }
}
