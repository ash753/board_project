package board.boardProject.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class BoardPrintDto {
    private String title;
    private String content;
    private String date;
    private List<FileDownloadDto> fileList;
}
