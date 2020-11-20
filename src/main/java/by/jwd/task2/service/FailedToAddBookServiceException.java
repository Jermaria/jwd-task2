package by.jwd.task2.service;

public class FailedToAddBookServiceException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public FailedToAddBookServiceException() {}
    
    public FailedToAddBookServiceException(Exception e) {       
        super(e);
    }
    
    public FailedToAddBookServiceException(String message, Exception e) {
        super(message, e);
    }
    
    public FailedToAddBookServiceException(String message) {
        super(message);
    }

}
