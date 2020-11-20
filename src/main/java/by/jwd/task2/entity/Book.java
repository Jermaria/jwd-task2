package by.jwd.task2.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import by.jwd.task2.validation.ValidationException;

public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private Author[] authors;
    private Publisher publisher;
    private int year;
    private int pagesNum;
    private BigDecimal price;
    private Cover cover;

    public Book() {}

    public Book(String title, Author[] authors, Publisher publisher, int year, int pagesNum, BigDecimal price,
                Cover cover) throws ValidationException {

        if (!(title.isBlank() || authors.length == 0 || publisher.getName() == null || publisher.getCountry() == null
                || year < 1 || year > Calendar.getInstance().get(Calendar.YEAR) || pagesNum < 1)
                || price.signum() == -1) {

            this.title = title;
            this.authors = authors;
            this.publisher = publisher;
            this.year = year;
            this.pagesNum = pagesNum;
            this.price = price;
            this.cover = cover;
        } else {
            throw new ValidationException("invalid title, author, publisher, year," + "pagesNum or price");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws ValidationException {
        if (!title.isBlank()) {
            this.title = title;
        } else {
            throw new ValidationException("invalid title");
        }
    }

    public Author[] getAuthors() {
        return authors;
    }

    public void setAuthors(Author[] authors) throws ValidationException {
        if (authors.length != 0) {
            this.authors = authors;
        } else {
            throw new ValidationException("no author provided");
        }
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) throws ValidationException {
        if (publisher.getName() != null && publisher.getCountry() != null) {
            this.publisher = publisher;
        } else {
            throw new ValidationException("lack publisher data: name or country");
        }
  }

    public int getYear() {
        return year;
    }

    public void setYear(int year) throws ValidationException {
        if (year > 0 && year < Calendar.getInstance().get(Calendar.YEAR)) {
            this.year = year;
        } else {
            throw new ValidationException("invalid year" + year);
        }
    }

    public int getPagesNum() {
        return pagesNum;
    }

    public void setPagesNum(int pagesNum) throws ValidationException {
        if (pagesNum > 0) {
            this.pagesNum = pagesNum;
        } else {
            throw new ValidationException("invalid number of pages" + pagesNum);
        }
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) throws ValidationException {
        if (price.signum() != -1) {
            this.price = price;
        } else {
            throw new ValidationException("invalid price" + price);
        }
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(authors);
        result = prime * result + ((cover == null) ? 0 : cover.hashCode());
        result = prime * result + id;
        result = prime * result + pagesNum;
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + year;
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
        Book other = (Book) obj;
        if (!Arrays.equals(authors, other.authors))
            return false;
        if (cover != other.cover)
            return false;
        if (id != other.id)
            return false;
        if (pagesNum != other.pagesNum)
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (publisher == null) {
            if (other.publisher != null)
                return false;
        } else if (!publisher.equals(other.publisher))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (year != other.year)
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(this.getClass().getName());
        builder.append(" [id=");
        builder.append(id);
        builder.append(", title=");
        builder.append(title);
        builder.append(", authors=");
        builder.append(Arrays.toString(authors));
        builder.append(", publisher=");
        builder.append(publisher);
        builder.append(", year=");
        builder.append(year);
        builder.append(", pagesNum=");
        builder.append(pagesNum);
        builder.append(", price=");
        builder.append(price);
        builder.append(", cover=");
        builder.append(cover);
        builder.append("]");
        return builder.toString();
    }
}
