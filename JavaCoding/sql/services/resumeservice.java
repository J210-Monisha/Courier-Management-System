package service;

import exception.FileSizeExceededException;
import exception.UnsupportedFileFormatException;

import java.io.File;
import java.io.FileNotFoundException;

public class ResumeService {
    public void uploadResume(String filePath) {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                throw new FileNotFoundException("Resume file not found: " + filePath);
            }
            if (!file.getName().endsWith(".pdf")) {
                throw new UnsupportedFileFormatException("Only PDF files are supported.");
            }
            if (file.length() > 5 * 1024 * 1024) { 
                throw new FileSizeExceededException("File size exceeds the 5MB limit.");
            }

            System.out.println("Resume uploaded successfully.");
        } catch (FileNotFoundException | UnsupportedFileFormatException | FileSizeExceededException e) {
            System.err.println("File upload error: " + e.getMessage());
        }
    }
}