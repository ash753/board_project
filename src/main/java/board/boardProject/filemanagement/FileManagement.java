package board.boardProject.filemanagement;

import board.boardProject.domain.dao.FileDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Transactional
public class FileManagement {
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename){
        return fileDir+filename;
    }

    public List<FileDao> storeFiles(List<MultipartFile> multipartFileList) throws IOException {
        List<FileDao> resultList = new ArrayList<>();

        if(multipartFileList==null) return null;
        for (MultipartFile multipartFile : multipartFileList) {
            if (!multipartFile.isEmpty()) {
                FileDao fileDao = storeFile(multipartFile);
                resultList.add(fileDao);
            }
        }
        return resultList;
    }

    public FileDao storeFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()) return null;

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new FileDao(originalFilename, storeFileName);
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

    public void deleteFiles(List<FileDao> fileDaoList) {
        for (FileDao fileDao : fileDaoList) {
            String fullPath = getFullPath(fileDao.getSavedName());
            File file = new File(fullPath);
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
