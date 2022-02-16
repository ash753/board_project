package board.boardProject.repository;

import board.boardProject.domain.dao.BoardDao;
import board.boardProject.domain.dao.FileDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class FileRepositoryTest {
    @Autowired
    private  FileRepository fileRepository;
    @Autowired
    private  BoardRepository boardRepository;

    @Test
    public void saveTest() throws Exception{
        //given
        String now = getCurrentTimeAsString();
        BoardDao board1 = new BoardDao("스프링1", "안녕하세요", now);
        boardRepository.save(board1);

        FileDao fileDao1 = new FileDao("text.txt", "saved1.txt", board1.getId());
        FileDao fileDao2 = new FileDao("text.txt", "saved2.txt", board1.getId());

        //when
        fileRepository.save(fileDao1);
        fileRepository.save(fileDao2);
        FileDao findFileDao1 = fileRepository.findById(fileDao1.getId());
        List<FileDao> findFileDaoList2 = fileRepository.findByBoardId(board1.getId());

        //then
        assertThat(findFileDao1).isEqualTo(fileDao1);
        assertThat(findFileDaoList2).contains(fileDao1, fileDao2);
    }

    @Test
     public void deleteTest() throws Exception{
        //given
        String now = getCurrentTimeAsString();
        BoardDao board1 = new BoardDao("스프링1", "안녕하세요", now);
        boardRepository.save(board1);

        FileDao fileDao1 = new FileDao("text.txt", "saved1.txt", board1.getId());
        FileDao fileDao2 = new FileDao("text.txt", "saved2.txt", board1.getId());

        //when
        fileRepository.save(fileDao1);
        fileRepository.save(fileDao2);
        fileRepository.delete(fileDao1.getId());

        //then
        assertThat(fileRepository.findByBoardId(board1.getId())).doesNotContain(fileDao1);
      }

      @Test
        public void deleteByBoardIdTest() throws Exception{
            //given
            String now = getCurrentTimeAsString();
            BoardDao board1 = new BoardDao("스프링1", "안녕하세요", now);
            boardRepository.save(board1);

            FileDao fileDao1 = new FileDao("text.txt", "saved1.txt", board1.getId());
            FileDao fileDao2 = new FileDao("text.txt", "saved2.txt", board1.getId());

            //when
            fileRepository.save(fileDao1);
            fileRepository.save(fileDao2);
            fileRepository.deleteByBoardId(board1.getId());

            //then
            assertThat(fileRepository.findByBoardId(board1.getId()).size()).isEqualTo(0);
        }

    private String getCurrentTimeAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(Calendar.getInstance().getTime());
        return now;
    }
}