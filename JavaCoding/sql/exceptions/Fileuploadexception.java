package exception;

@SuppressWarnings("serial")
public class FileUploadException extends Exception {
    public FileUploadException(String message) {
        super(message);
    }
}