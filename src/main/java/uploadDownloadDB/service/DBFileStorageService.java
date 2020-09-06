package uploadDownloadDB.service;

import uploadDownloadDB.exception.FileStorageException;
import uploadDownloadDB.exception.MyFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uploadDownloadDB.model.DBFile;
import uploadDownloadDB.properties.FileStorageProperties;
import uploadDownloadDB.repository.DBFileRepository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class DBFileStorageService {

    @Autowired
    private DBFileRepository dbFileRepository;


    public DBFile storeFile(MultipartFile file) {
        //normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        //copy file to target location (Replacing existing file with the same name)
        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Invalid path name");
            }
            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
            return dbFileRepository.save(dbFile);
        } catch (IOException e) {
            throw new FileStorageException("Could not store file " + fileName + " .Please try again", e);
        }
    }


    public DBFile getFile (String fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(()->new MyFileNotFoundException("File not found with id "+fileId));
    }
}