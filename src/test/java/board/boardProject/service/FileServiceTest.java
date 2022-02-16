package board.boardProject.service;

import board.boardProject.domain.dao.BoardDao;
import board.boardProject.domain.dao.FileDao;
import board.boardProject.domain.dto.BoardAddDto;
import board.boardProject.domain.dto.FileDownloadDto;
import board.boardProject.domain.dto.FileSaveDto;
import board.boardProject.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class FileServiceTest {
    @Autowired
    private BoardService boardService;

    @Autowired
    private FileService fileService;

    private final String fileDir = "D:/Desktop/portfolio/boardProject/src/main/resources/static/file/";
    private static List<BoardDao> boardDaoList = new ArrayList<>();

    @BeforeEach
    void boardSetting() throws IOException {
        boardDaoList.add(boardService.addBoard(new BoardAddDto("제목1", "내용1")));
        boardDaoList.add(boardService.addBoard(new BoardAddDto("제목2", "내용2")));
    }
    @AfterEach
    void boardDelete(){
        boardService.deleteBoard(boardDaoList.get(0).getId());
        boardService.deleteBoard(boardDaoList.get(1).getId());
        fileService.deleteFileByBoardId(boardDaoList.get(0).getId());
        fileService.deleteFileByBoardId(boardDaoList.get(1).getId());
        boardDaoList.clear();
    }

    @Test
    void storeFiles() throws IOException {
        //given
        List<MultipartFile> inputFileList1 = new ArrayList<>();
        inputFileList1.add(new MockMultipartFile("file1.txt", "file1.txt","text/plain", "hello1".getBytes(StandardCharsets.UTF_8)));
        inputFileList1.add(new MockMultipartFile("file2.txt", "file2.txt","text/plain","hello2".getBytes(StandardCharsets.UTF_8)));

        List<MultipartFile> inputFileList2 = new ArrayList<>();
        inputFileList2.add(new MockMultipartFile("file3.txt", "file3.txt","text/plain", "hello3".getBytes(StandardCharsets.UTF_8)));
        inputFileList2.add(new MockMultipartFile("file4.txt", "file4.txt","text/plain","hello4".getBytes(StandardCharsets.UTF_8)));

        FileSaveDto fileSaveDto1 = new FileSaveDto(inputFileList1);
        FileSaveDto fileSaveDto2 = new FileSaveDto(inputFileList2);

        //when
        List<FileDao> fileDaoList1 = fileService.storeFiles(fileSaveDto1, boardDaoList.get(0).getId());
        List<FileDao> fileDaoList2 = fileService.storeFiles(fileSaveDto2, boardDaoList.get(1).getId());



        String originalFileName = fileService.getOriginalFileName(fileDaoList1.get(0).getId());

        List<FileDownloadDto> fileDownloadDtoList = fileService.getFileInfoByBoardId(fileDaoList2.get(0).getBoardId());
        List<String> originalNameList = new ArrayList<>();
        for (FileDownloadDto fileDownloadDto : fileDownloadDtoList) {
            originalNameList.add(fileDownloadDto.getOriginalName());
        }

        Resource findResource = fileService.getFileById(fileDaoList1.get(1).getId());
        String storedFileName = findResource.getFilename();
        String content = Files.readString(Paths.get(fileDir + fileDaoList1.get(1).getSavedName()));



        //then
        assertThat(originalFileName).isEqualTo("file1.txt");
        assertThat(originalNameList).contains("file3.txt", "file4.txt");

        assertThat(storedFileName).isEqualTo(fileDaoList1.get(1).getSavedName());
        assertThat(content).isEqualTo("hello2");

        fileService.deleteFileByBoardId(boardDaoList.get(0).getId());
        fileService.deleteFileByBoardId(boardDaoList.get(1).getId());

    }

    @Test
    void changeFiles() throws IOException {
        //given
        List<MultipartFile> inputFileList1 = new ArrayList<>();
        inputFileList1.add(new MockMultipartFile("file1.txt", "file1.txt","text/plain", "hello1".getBytes(StandardCharsets.UTF_8)));
        inputFileList1.add(new MockMultipartFile("file2.txt", "file2.txt","text/plain","hello2".getBytes(StandardCharsets.UTF_8)));
        FileSaveDto fileSaveDto1 = new FileSaveDto(inputFileList1);
        List<FileDao> fileDaoList1 = fileService.storeFiles(fileSaveDto1, boardDaoList.get(1).getId());


        List<MultipartFile> inputFileList2 = new ArrayList<>();
        inputFileList2.add(new MockMultipartFile("file3.txt", "file3.txt","text/plain", "hello3".getBytes(StandardCharsets.UTF_8)));
        inputFileList2.add(new MockMultipartFile("file4.txt", "file4.txt","text/plain","hello4".getBytes(StandardCharsets.UTF_8)));
        FileSaveDto fileSaveDto2 = new FileSaveDto(inputFileList2);

        //when
        fileService.changeFiles(fileSaveDto2, boardDaoList.get(1).getId());
        List<FileDownloadDto> findFileDownloadDtoList1 = fileService.getFileInfoByBoardId(boardDaoList.get(1).getId());


        List<FileDownloadDto> fileDownloadDtoList = fileService.getFileInfoByBoardId(boardDaoList.get(1).getId());
        List<String> originalNameList = new ArrayList<>();
        for (FileDownloadDto fileDownloadDto : fileDownloadDtoList) {
            originalNameList.add(fileDownloadDto.getOriginalName());
        }

        //then
        assertThat(originalNameList).contains("file3.txt", "file4.txt");

        fileService.deleteFileByBoardId(boardDaoList.get(0).getId());
        fileService.deleteFileByBoardId(boardDaoList.get(1).getId());
    }
}