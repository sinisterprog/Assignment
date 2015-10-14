package com.mikes.filehandling;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class FileHandler {
	private final static String FILE_DESTINATION = "uploadedFiles\\";
	private final static Logger logger = Logger.getLogger(FileHandler.class);

	public static String saveFile(MultipartFile file, Integer Id) {
		new File(FILE_DESTINATION).mkdir();
		File fileDir = new File(FILE_DESTINATION + "\\" + Id.toString());
		fileDir.mkdir();
		String name = null;
		try {
			name = file.getOriginalFilename();
			byte[] bytes = file.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File(FILE_DESTINATION + name)));
			stream.write(bytes);
			stream.close();
			logger.info("Saved image file " + name + ".");
		} catch (Exception e) {
			logger.error("Failed to save image file " + name + "."
					+ e.getMessage());
		}
		return name;
	}

	public static void deleteFile(String photo_id) {
		if (photo_id == null) {
			return;
		}
		  
		String fileToDelete=FILE_DESTINATION + "\\" + photo_id;
		File f = new File(fileToDelete);
		if (f.exists()) {
			if (f.delete() == false) {
				logger.error("Failed to delete file " + fileToDelete);
			} else {
				logger.info("Successfully deleted file " + fileToDelete);
			}
		}
	}
}
