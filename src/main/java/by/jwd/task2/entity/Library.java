package by.jwd.task2.entity;

import java.io.Serializable;
import java.util.Arrays;

public class Library implements Serializable {

    private static final long serialVersionUID = 1L;

    private Book[] books;

    public Library() {}

    public Library(Book[] books) {
        this.books = books;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((books == null) ? 0 : books.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Library other = (Library) obj;
        if (books == null) {
            if (other.books != null)
                return false;
        } else if (!books.equals(other.books))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(this.getClass().getName());
        builder.append(" [books=");
        builder.append(Arrays.toString(books));
        builder.append("]");
        return builder.toString();
    }
}
