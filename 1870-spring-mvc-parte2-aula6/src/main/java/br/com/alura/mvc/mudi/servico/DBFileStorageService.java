package br.com.alura.mvc.mudi.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

import br.com.alura.mvc.mudi.exception.FileStorageException;
import br.com.alura.mvc.mudi.exception.MyFileNotFoundException;
import br.com.alura.mvc.mudi.model.DBFile;
import br.com.alura.mvc.mudi.model.Segredo;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.DBFileRepository;
import br.com.alura.mvc.mudi.repository.SegredoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;

@Service
public class DBFileStorageService {
	
	@Autowired
	private DBFileRepository dbFileRepository;
	
	@Autowired
	private UserRepository userRepository;

	
    public DBFile storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            
          //Busca usuario logado na aplicação
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userRepository.findByUsername(username);
			//dbFile.setUser(user);

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes(), user);
         
            

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DBFile getFile(String fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

}
