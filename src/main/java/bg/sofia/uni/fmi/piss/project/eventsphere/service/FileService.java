package bg.sofia.uni.fmi.piss.project.eventsphere.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static bg.sofia.uni.fmi.piss.project.eventsphere.SecurityConstants.USER_DIR;

import java.net.Socket;
import java.util.concurrent.TimeUnit;
	
@Service
public class FileService {

    public ResponseEntity uploadFile(MultipartFile file, String username) {

		try {
			File tempFile = File.createTempFile("tmp_file", ".png");
			Path tempLocation = Paths.get(tempFile.getAbsolutePath());

			Files.copy(file.getInputStream(), tempLocation, StandardCopyOption.REPLACE_EXISTING);

			String uploadDir = USER_DIR + username;

			Path copyLocation = Paths.get(uploadDir + File.separator + "profile_pic.jpg");

			Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

			tempFile.delete();

		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("There was an error processing your picture. Please upload it again.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("Your picture is uploaded successfully!",
				HttpStatus.OK);
    }
}
