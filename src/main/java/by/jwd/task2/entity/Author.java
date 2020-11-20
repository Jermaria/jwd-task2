package by.jwd.task2.entity;

import java.io.Serializable;

import by.jwd.task2.validation.ValidationException;

public class Author implements Serializable {

    private static final long serialVersionUID = 1L;
	
    private String name;
    private String surname;
	
    public Author() {}

    public Author(String name, String surname) throws ValidationException {   
        if (!name.isBlank() && !surname.isBlank()) { 
            this.name = name;
            this.surname = surname;        
         } else {
            throw new ValidationException("name or surname is invalid");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ValidationException {   
        if (!name.isBlank()) {
            this.name = name;
        } else { 
            throw new ValidationException("invalid name input");
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws ValidationException {
        if (!surname.isBlank()) {  
            this.surname = surname;       
        } else {         
            throw new ValidationException("invalid surname input");
        }
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
        Author other = (Author) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (surname == null) {
            if (other.surname != null)
                return false;
        } else if (!surname.equals(other.surname))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(this.getClass().getName());
        builder.append(" [name=");
        builder.append(name);
        builder.append(", surname=");
        builder.append(surname);
        builder.append("]");
        return builder.toString();
    }

}
