package by.jwd.task2.validation;

import java.util.Calendar;

import by.jwd.task2.entity.Book;

public class BookValidator {
    
    private static final BookValidator INSTANCE = new BookValidator();
    
    private BookValidator() {}

    public static BookValidator getInstance() {
        return INSTANCE;
    }

    public boolean isBookDataPresent(Book book) {       
        boolean isPresent = (book.getTitle() != null && !book.getTitle().isBlank()
                             && book.getAuthors().length != 0
                             && book.getPublisher().getName() != null 
                             && book.getPublisher().getCountry() != null
                             && book.getYear() > 0
                             && book.getYear() <= Calendar.getInstance().get(Calendar.YEAR)
                             && book.getPagesNum() > 0
                             && book.getPrice().signum() != -1);
        return isPresent;
    }
    
    public boolean isBookArrayInitialized(Book[] array) {     
        boolean isInitialized = (array != null);
        return isInitialized;
    }
}
