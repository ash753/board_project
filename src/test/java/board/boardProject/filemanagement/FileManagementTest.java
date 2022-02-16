package board.boardProject.filemanagement;

import board.boardProject.domain.dao.FileDao;
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
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class FileManagementTest {
    @Autowired
    private FileManagement fileManagement;

    @Test
    void storeAndDeleteTest() throws IOException {
        //given
        String fullPath = fileManagement.getFullPath("file.txt");

        List<MultipartFile> inputFileList = new ArrayList<>();
        inputFileList.add(new MockMultipartFile("file1.txt", "file1.txt","text/plain", "hello".getBytes(StandardCharsets.UTF_8)));
        inputFileList.add(new MockMultipartFile("file2.txt", "file2.txt","text/plain","hello".getBytes(StandardCharsets.UTF_8)));

        //when
        List<FileDao> fileDaoList = fileManagement.storeFiles(inputFileList);

        //then
        for (FileDao fileDao : fileDaoList) {
            String content = Files.readString(Paths.get( fileManagement.getFullPath(fileDao.getSavedName())));
            assertThat(content).isEqualTo("hello");
        }
        fileManagement.deleteFiles(fileDaoList);
    }
}