package board.boardProject;

import board.boardProject.domain.dto.BoardAddDto;
import board.boardProject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class initData {
    private final BoardService boardService;

    @PostConstruct
    public void init() throws IOException {
        BoardAddDto boardAddDto = new BoardAddDto("Spring 게시판 입니다", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Laborum quis pariatur ex, quae officiis repellendus aliquam, explicabo delectus voluptatibus nostrum sit atque obcaecati iste eaque omnis saepe temporibus excepturi in earum soluta! Eveniet inventore deleniti, quia sint autem temporibus nesciunt perferendis saepe magnam natus neque maiores consequatur eius eum voluptatum, alias vitae repellendus quos, blanditiis quibusdam repudiandae. Rem maiores autem quasi blanditiis, officia deleniti. Reiciendis veniam perspiciatis facere quia ipsam neque asperiores, nesciunt, maiores voluptatem repellat hic itaque impedit enim magni minus dolorem id ullam laborum eligendi voluptatum dicta incidunt odit? Hic vitae impedit sit veniam tempore earum, harum dolorem!");
        boardService.addBoard(boardAddDto);
    }
}
