package SystemsLog;

import java.nio.file.*;
import java.io.IOException;
import java.util.zip.*;

public class LogManager {

   
    public void createLogFile(String filePathString) throws IOException {
        Path filePath = Paths.get(filePathString);
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
    }

    public void moveLogFile(String sourcePathString, String targetPathString) throws IOException {
        Path sourcePath = Paths.get(sourcePathString);
        Path targetPath = Paths.get(targetPathString);
        Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
    }

    public void deleteLogFile(String filePathString) throws IOException {
        Path filePath = Paths.get(filePathString);
        Files.delete(filePath);
    }

    public void archiveLogFile(String sourcePathString, String archivePathString) throws IOException {
        Path sourcePath = Paths.get(sourcePathString);
        Path archivePath = Paths.get(archivePathString);

       
        if (Files.notExists(archivePath.getParent())) {
            Files.createDirectories(archivePath.getParent());
        }

        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(archivePath))) {

            zos.putNextEntry(new ZipEntry(sourcePath.getFileName().toString()));

            byte[] bytes = Files.readAllBytes(sourcePath);
            zos.write(bytes, 0, bytes.length);

            zos.closeEntry();
        }
    }
}