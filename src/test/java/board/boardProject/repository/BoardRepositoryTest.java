package board.boardProject.repository;

import board.boardProject.domain.dao.BoardDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void saveTest() throws Exception{
        //given
        String now = getCurrentTimeAsString();
        BoardDao board1 = new BoardDao("스프링1", "안녕하세요", now);
        BoardDao board2 = new BoardDao("스프링2", "안녕하세요", now);

        //when
        BoardDao saveBoard1 = boardRepository.save(board1);
        BoardDao saveBoard2 = boardRepository.save(board2);
        List<BoardDao> boardList = boardRepository.getAll();
        List<BoardDao> findList = new ArrayList<>();
        for (BoardDao boardDao : boardList) {
            if (boardDao.getId().equals(saveBoard1.getId()) || boardDao.getId().equals(saveBoard2.getId())) {
                findList.add(boardDao);
            }
        }

        //then
        assertThat(saveBoard1).isEqualTo(board1);
        assertThat(saveBoard2).isEqualTo(board2);
        assertThat(findList.size()).isEqualTo(2);
    }
    @Test
    public void editTest() throws Exception{
        //given
        String now = getCurrentTimeAsString();
        BoardDao board1 = new BoardDao("스프링1", "안녕하세요", now);
        BoardDao board2 = new BoardDao("스프링2", "안녕하세요", now);

        //when
        BoardDao saveBoard1 = boardRepository.save(board1);
        saveBoard1.setContent("바뀐 본문");
        boardRepository.edit(saveBoard1);

        //then
        assertThat(boardRepository.findById(saveBoard1.getId())).isEqualTo(saveBoard1);
    }
    @Test
    public void deleteTest() throws Exception{
        //given
        String now = getCurrentTimeAsString();
        BoardDao board1 = new BoardDao("스프링1", "안녕하세요", now);
        BoardDao board2 = new BoardDao("스프링2", "안녕하세요", now);

        //when
        boardRepository.save(board1);
        boardRepository.save(board2);
        boardRepository.delete(board1.getId());

        //then
        assertThat(boardRepository.getAll()).doesNotContain(board1);
     }

    private String getCurrentTimeAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(Calendar.getInstance().getTime());
        return now;
    }
}