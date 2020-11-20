package by.jwd.task2.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import java.math.BigDecimal;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import by.jwd.task2.entity.Author;
import by.jwd.task2.entity.Book;
import by.jwd.task2.entity.Cover;
import by.jwd.task2.entity.Library;
import by.jwd.task2.entity.Publisher;
import by.jwd.task2.service.FailedToAddBookServiceException;
import by.jwd.task2.service.LibraryService;
import by.jwd.task2.validation.ValidationException;

public class LibraryServiceTest {

    private static final LibraryService SERVICE = LibraryService.getInstance();

    private Library belkniga;

    @BeforeClass
    public void provideBookArray() throws ValidationException {
        Author orwell = new Author("Джордж", "Оруэлл");
        Author darwin = new Author("Чарльз", "Дарвин");

        Publisher ast = new Publisher("АСТ", "РФ");
        Publisher azbuka = new Publisher("Азбука", "РФ");

        Author[] book1Authors = new Author[] { orwell };
        Author[] book2Authors = new Author[] { darwin };

        Book[] books = new Book[] { new Book("Скотный двор", book1Authors, ast,
                                            2017, 368, BigDecimal.valueOf(15.80), Cover.SOFT),
                                    new Book("Происхождение видов", book2Authors, azbuka, 
                                            2018, 704, BigDecimal.valueOf(12), Cover.SOFT) };
        belkniga = new Library();
        belkniga.setBooks(books);
    }

    @Test
    public void addBookToLibraryTest() {
        try {
            Author palahniuk = new Author("Чак", "Паланик");
            Publisher ast = new Publisher("АСТ", "РФ");
            Author[] bookAuthors = new Author[] { palahniuk };

            SERVICE.addBookToLibrary(belkniga,
                    new Book("Бойцовский клуб", bookAuthors, ast, 2017, 218, BigDecimal.valueOf(10), Cover.SOFT));
            int actual = belkniga.getBooks().length;
            int expected = 6;

            assertEquals(actual, expected);
        } catch (ValidationException | FailedToAddBookServiceException e) {
            fail();
        }
    }

    @Test
    public void countPagesTotalTest() {
        int actual = SERVICE.countPagesTotal(belkniga);
        int expected = 1290;

        assertEquals(actual, expected);
    }

    @Test
    public void defineCheepestBookTest() {
        BigDecimal actual = SERVICE.defineCheapestBookPrice(belkniga).get();
        BigDecimal expected = BigDecimal.valueOf(10);

        assertEquals(actual, expected);
    }

    @Test
    public void defineOldestBookYearTest() {
        int actual = SERVICE.defineOldestBookYear(belkniga).get();
        int expected = 2017;

        assertEquals(actual, expected);
    }

    @Test
    public void fetchBooksByAuthorTest() {
        try {
            Author orwell = new Author("Джордж", "Оруэлл");
            Author[] bookAuthors = new Author[] { orwell };
            Publisher ast = new Publisher("АСТ", "РФ");

            Book[] actual = SERVICE.fetchBooksByAuthor(belkniga, orwell).get();
            Book[] expected = new Book[] {
                    new Book("Скотный двор", bookAuthors, ast, 2017, 368, BigDecimal.valueOf(15.80), Cover.SOFT) };

            assertEquals(actual, expected);
        } catch (ValidationException e) {
            fail();
        }
    }

    @Test
    public void fetchBooksByPublisherTest() {
        try {
            Author darwin = new Author("Чарльз", "Дарвин");
            Author[] bookAuthors = new Author[] { darwin };
            Publisher azbuka = new Publisher("Азбука", "РФ");

            Book[] actual = SERVICE.fetchBooksByPublisher(belkniga, azbuka).get();
            Book[] expected = new Book[] { new Book("Происхождение видов", bookAuthors, azbuka, 2018, 704,
                    BigDecimal.valueOf(12), Cover.SOFT) };
            assertEquals(actual, expected);
        } catch (ValidationException e) {
            fail();
        }
    }
    
    @Test
    public void fetchBooksPublishedAfter() {
        try {
            Author darwin = new Author("Чарльз", "Дарвин");
            Author[] bookAuthors = new Author[] { darwin };
            Publisher azbuka = new Publisher("Азбука", "РФ");

            Book[] actual = SERVICE.fetchBooksPublishedAfter(belkniga, 2018).get();
            Book[] expected = new Book[] { new Book("Происхождение видов", bookAuthors, azbuka, 2018, 704,
                                                    BigDecimal.valueOf(12), Cover.SOFT) };
            assertEquals(actual, expected);
        } catch (ValidationException e) {
            fail();
        }
    }
    
    @Test(expectedExceptions = FailedToAddBookServiceException.class)
    public void FailedToAddBookServiceExceptionTest() throws FailedToAddBookServiceException {       
        SERVICE.addBookToLibrary(belkniga, new Book());
    }
    
    
}
