package board.boardProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class viewControll {
    @GetMapping("board")
    public String board(){
        return "board";
    }
}
