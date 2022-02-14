package board.boardProject.filestore;

import board.boardProject.domain.dao.FileDao;
import board.boardProject.domain.dto.FileSaveDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename){
        return fileDir+filename;
    }

    public List<FileSaveDto> storeFiles(List<MultipartFile> multipartFileList) throws IOException {
        List<FileSaveDto> resultList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFileList) {
            if (!multipartFile.isEmpty()) {
                FileSaveDto fileSaveDto = storeFile(multipartFile);
                resultList.add(fileSaveDto);
            }
        }
        return resultList;
    }

    public FileSaveDto storeFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()) return null;

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new FileSaveDto(originalFilename, storeFileName);
    }

    private String createStoreFileName(String originalFileName){
        String ext = extractExt(originalFileName);
        String uuid = UUID.randomUUID() + "";
        return uuid+"."+ext;
    }

    private String extractExt(String originalFilename){
        int pos = originalFilename.indexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
