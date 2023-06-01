package net.aarruda.crud.configure;

import java.io.*;
import java.nio.file.*;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	public static void saveFile(String uploadDir, String fileName,
			MultipartFile multipartFile) throws IOException {
		Path uploadPath = Paths.get(uploadDir);
		if (fileName != "") {
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}

			try (InputStream inputStream = multipartFile.getInputStream()) {
				Path filePath = uploadPath.resolve(fileName);
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException ioe) {
				throw new IOException("Erro ao guardar a imagem: " + fileName, ioe);
			}
		}
	}
}
