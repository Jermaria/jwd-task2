package by.jwd.task2.controller;

import java.math.BigDecimal;

import by.jwd.task2.entity.Author;
import by.jwd.task2.entity.Book;
import by.jwd.task2.entity.Cover;
import by.jwd.task2.entity.Library;
import by.jwd.task2.entity.Publisher;
import by.jwd.task2.service.FailedToAddBookServiceException;
import by.jwd.task2.service.LibraryService;
import by.jwd.task2.validation.ValidationException;


public class Main {

    public static void main(String[] args) {
        
        Library belkniga = new Library();
        ReportPrinter printer = ReportPrinter.getInstance();

        try {
            
            Author orwell = new Author("Джордж", "Оруэлл");
            Author darwin = new Author("Чарльз", "Дарвин");
            Author palahniuk = new Author("Чак", "Паланик");
            
            Publisher ast = new Publisher("АСТ", "РФ");
            Publisher azbuka = new Publisher("Азбука", "РФ");
            
            Author[] book1Authors = new Author[] {orwell};
            Author[] book2Authors = new Author[] {darwin};
            Author[] book3Authors = new Author[] {palahniuk};
            
            Book book1 = new Book("Скотный двор", book1Authors, ast, 2017, 368, 
                    BigDecimal.valueOf(15.80), Cover.SOFT);
            
            Book book2 = new Book("Происхождение видов", book2Authors, azbuka, 2018, 704, 
                    BigDecimal.valueOf(12), Cover.SOFT);

            Book book3 = new Book("Бойцовский клуб", book3Authors, ast, 2017, 218, 
                    BigDecimal.valueOf(10), Cover.SOFT);

            LibraryService service = LibraryService.getInstance();

            service.addBookToLibrary(belkniga, book1);
            service.addBookToLibrary(belkniga, book2);
            service.addBookToLibrary(belkniga, book3);
            
            printer.printFoundBooks(service.fetchBooksPublishedAfter(belkniga, 2018));  
        } catch (ValidationException e) {         
            printer.printInvalidInputMessage();         
        } catch (FailedToAddBookServiceException e) {        
            printer.printNoBookDataMessage();
        }
    }
}
