package board.boardProject.filemanagement;

import board.boardProject.domain.dao.BoardDao;
import board.boardProject.domain.dao.FileDao;
import board.boardProject.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class FileManagementTest {
    @Autowired
    private FileManagement fileManagement;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    void storeAndDeleteTest() throws IOException {
        //given
        String now = getCurrentTimeAsString();
        BoardDao board1 = new BoardDao("스프링1", "안녕하세요", now);
        String fullPath = fileManagement.getFullPath("file.txt");
        BoardDao saveBoard1 = boardRepository.save(board1);

        List<MultipartFile> inputFileList = new ArrayList<>();
        inputFileList.add(new MockMultipartFile("file1.txt", "file1.txt","text/plain", "hello".getBytes(StandardCharsets.UTF_8)));
        inputFileList.add(new MockMultipartFile("file2.txt", "file2.txt","text/plain","hello".getBytes(StandardCharsets.UTF_8)));

        //when
        List<FileDao> fileDaoList = fileManagement.storeFiles(saveBoard1.getId(),inputFileList);

        //then
        for (FileDao fileDao : fileDaoList) {
            String content = Files.readString(Paths.get( fileManagement.getFullPath(fileDao.getSavedName())));
            assertThat(content).isEqualTo("hello");
        }
        fileManagement.deleteFiles(fileDaoList);
    }
    private String getCurrentTimeAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(Calendar.getInstance().getTime());
        return now;
    }
}