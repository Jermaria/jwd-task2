package by.jwd.task2.controller;

import java.math.BigDecimal;
import java.util.Optional;

import by.jwd.task2.entity.Author;
import by.jwd.task2.entity.Book;

public class ReportPrinter {

    private static final ReportPrinter INSTANCE = new ReportPrinter();
    private static final String NO_BOOKS_MESSAGE = "Sorry, no books found";
    private static final String INVALID_INPUT_MESSAGE = "Invalid expression";
    private static final String EMPTY_LIBRARY_MESSAGE = "Sorry, no books in the library";
    private static final String NO_BOOK_DATA_MESSAGE = "Forgot to provide book data?";
    private static final String COMMA = ", ";
    private static final String TITLE = "Title: ";
    private static final String AUTHOR = "Author(s): ";
    private static final String PUBLISHER = "Publisher: ";
    private static final String YEAR = "Published in: ";
    private static final String PAGES = "Number of pages: ";
    private static final String PRICE = "Price: ";
    private static final String DIVIDING_LINE = "---------------------";

    private ReportPrinter() {}

    public static ReportPrinter getInstance() {
        return INSTANCE;
    }

    public void printFoundBooks(Optional<Book[]> result) {
        if (result.isPresent()) {
            Book[] books = result.get();
            for (Book b : books) {
                printBookData(b);
            }
        } else {
            printNoBooksMessage();
        }
    }

    public void printBookData(Book b) {
        System.out.println(TITLE + b.getTitle());
        System.out.print(AUTHOR);
        printAuthors(b.getAuthors());
        System.out.println(PUBLISHER + b.getPublisher().getName() + COMMA + b.getPublisher().getCountry());
        System.out.println(YEAR + b.getYear());
        System.out.println(PAGES + b.getPagesNum());
        System.out.println(PRICE + b.getPrice());
        System.out.println(DIVIDING_LINE);
    }

    public void printAuthors(Author[] authors) {
        for (Author author : authors) {
            System.out.print(author.getName() + " " + author.getSurname() + COMMA);
        }
        System.out.println();
    }

    public void printNoBooksMessage() {
        System.out.println(NO_BOOKS_MESSAGE);
    }

    public void printInvalidInputMessage() {
        System.out.println(INVALID_INPUT_MESSAGE);
    }

    public void printYearOptional(Optional<Integer> year) {
        String result = (year.isPresent() ? year.get().toString() : EMPTY_LIBRARY_MESSAGE);
        System.out.println(result);
    }

    public void printPriceOptional(Optional<BigDecimal> price) {
        String result = (price.isPresent() ? price.get().toString() : EMPTY_LIBRARY_MESSAGE);
        System.out.println(result);
    }

    public void printNoBookDataMessage() {
        System.out.println(NO_BOOK_DATA_MESSAGE);
    }
}
