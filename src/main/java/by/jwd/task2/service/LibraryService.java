package by.jwd.task2.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import by.jwd.task2.entity.Author;
import by.jwd.task2.entity.Book;
import by.jwd.task2.entity.Library;
import by.jwd.task2.entity.Publisher;
import by.jwd.task2.validation.BookValidator;

public class LibraryService {
    
    private static final LibraryService INSTANCE = new LibraryService();
    private static final int INITIAL_BOOK_ARRAY_SIZE = 4;
    
    private final BookValidator validator = BookValidator.getInstance();
    
    private LibraryService() {}

    public static LibraryService getInstance() {
        return INSTANCE;
    }
    
    public synchronized void addBookToLibrary(Library library, Book book) throws FailedToAddBookServiceException {
        
        if (validator.isBookDataPresent(book)) {
        
            if (!validator.isBookArrayInitialized(library.getBooks())) { 
                initLibraryBooks(library);     
            } else if (isBookArrayFull(library.getBooks())) {
                library.setBooks(extendArray(library.getBooks()));
            }
            
            int bookId = generateNewBookId(library);
            book.setId(bookId);
            library.getBooks()[bookId-1] = book;
        } else {    
            throw new FailedToAddBookServiceException("Fields of book are null");
        }
    }
    
    public void initLibraryBooks(Library library) {      
        Book[] books = new Book[INITIAL_BOOK_ARRAY_SIZE];
        library.setBooks(books);
    }
 
    public boolean isBookArrayFull(Book[] array) {      
        boolean isFull = (array[array.length - 1] != null);
        return isFull;
    }
    
    public Book[] extendArray(Book[] array) {   
        Book[] extended = new Book[array.length + INITIAL_BOOK_ARRAY_SIZE];
        
        int index = 0;
        while (index < array.length) {        
            extended[index] = array[index];
            index++;
        }
        return extended;
    }
    
    public int generateNewBookId(Library library) {    
        int index = 0; 
        while (library.getBooks()[index] != null) {            
            index++;
        }
        int id = index + 1;
        return id;
    }
    
    
    public Optional<Book[]> fetchBooksByAuthor(Library library, Author author) {
        Book[] result = Arrays.asList(library.getBooks()).stream()
                       .filter(book -> book != null && Arrays.asList(book.getAuthors()).stream()
                       .anyMatch(a -> author.equals(a)))
                       .toArray(Book[] :: new);

        Optional<Book[]> booksOptional = 
                    (result.length != 0) ? Optional.of(result) : Optional.empty();

        return booksOptional;
    }
    
    public long calculateBooksFoundByAuthor(Library library, Author author) {  
        long resultNum = Arrays.asList(library.getBooks()).stream()
                .filter(book -> book != null && Arrays.asList(book.getAuthors()).stream()
                .anyMatch(a -> author == a))
                .count(); 
        return resultNum; 
    }
    
    public Optional<Book[]> fetchBooksByPublisher(Library library, Publisher publisher) {
        Optional<Book[]> booksOptional;
        
        if (validator.isBookArrayInitialized(library.getBooks())) {       
            Book[]result = Arrays.asList(library.getBooks()).stream()
                    .filter(book -> book != null && publisher.equals(book.getPublisher()))
                    .toArray(Book[] :: new);

            booksOptional = (result.length != 0) ? Optional.of(result) : Optional.empty();       
        } else {    
            booksOptional = Optional.empty();
        }  
        return booksOptional;     
    }
    
    public Optional<Book[]> fetchBooksPublishedAfter(Library library, int year) {
        Book[] result = Arrays.asList(library.getBooks()).stream()
                .filter(book -> book != null && year <= book.getYear())
                .toArray(Book[] :: new);
       
        Optional<Book[]> booksOptional = 
                (result.length != 0) ? Optional.of(result) : Optional.empty();
                
        return booksOptional;
    }
    
    public int countPagesTotal(Library library) {
        int sum = Arrays.asList(library.getBooks()).stream()
                 .filter(book -> book != null)
                 .collect(Collectors.summingInt(Book::getPagesNum));     
        return sum;  
    }
    
    public Optional<Integer> defineOldestBookYear(Library library) {        
        Optional<Integer> yearOptional;
        
        if (validator.isBookArrayInitialized(library.getBooks())) {
            yearOptional = Arrays.asList(library.getBooks()).stream()
                              .filter(book -> book != null)
                              .min(Comparator.comparingInt(Book::getYear))
                              .map(Book::getYear);  
        } else {     
            yearOptional = Optional.empty();
        }  
        return yearOptional;
    }
    
    public Optional<BigDecimal> defineCheapestBookPrice(Library library) {
        Optional<BigDecimal> priceOptional;
        
        if (validator.isBookArrayInitialized(library.getBooks())) {
            priceOptional = Arrays.asList(library.getBooks()).stream()
                    .filter(book -> book != null)
                    .map(Book::getPrice)
                    .min(Comparator.naturalOrder());    
        } else {
            priceOptional = Optional.empty();
        }
        return priceOptional;
    }

}
