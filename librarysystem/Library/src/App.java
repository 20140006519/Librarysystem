import java.util.ArrayList;
import java.util.List;


interface LibraryItem {
    void borrowItem();
    void returnItem();
    boolean isBorrowed();
}

class Book implements LibraryItem {
    private String title;
    private String author;
    private boolean borrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        if (!borrowed) {
            borrowed = true;
            System.out.println("Book: " + title + " by " + author + " has been borrowed.");
        } else {
            System.out.println("Book: " + title + " by " + author + " is already borrowed.");
        }
    }

    @Override
    public void returnItem() {
        if (borrowed) {
            borrowed = false;
            System.out.println("Book: " + title + " by " + author + " has been returned.");
        } else {
            System.out.println("Book: " + title + " by " + author + " is not currently borrowed.");
        }
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }

    @Override
    public String toString() {
        return "Book: " + title + " by " + author;
    }
}

class DVD implements LibraryItem {
    private String title;
    private String director;
    private boolean borrowed;

    public DVD(String title, String director) {
        this.title = title;
        this.director = director;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        if (!borrowed) {
            borrowed = true;
            System.out.println("DVD: " + title + " directed by " + director + " has been borrowed.");
        } else {
            System.out.println("DVD: " + title + " directed by " + director + " is already borrowed.");
        }
        System.out.println("----------------------------------------------------------------------------- ");
    }

    @Override
    public void returnItem() {
        if (borrowed) {
            borrowed = false;
            System.out.println("DVD: " + title + " directed by " + director + " has been returned.");
        } else {
            System.out.println("DVD: " + title + " directed by " + director + " is not currently borrowed.");
        }
        System.out.println("----------------------------------------------------------------------------- ");
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }

    @Override
    public String toString() {
        return "DVD: " + title + " directed by " + director;
    }
}


abstract class LibraryUser {
    protected List<LibraryItem> borrowedItems;

    public LibraryUser() {
        borrowedItems = new ArrayList<>();
    }

    public void borrowItem(LibraryItem item) {
        if (!item.isBorrowed()) {
            item.borrowItem();
            borrowedItems.add(item);
        } else {
            System.out.println("Item is already borrowed.");
        }
    }

    public void returnItem(LibraryItem item) {
        if (borrowedItems.contains(item)) {
            item.returnItem();
            borrowedItems.remove(item);
        } else {
            System.out.println("You have not borrowed this item.");
        }
    }

    public abstract void printItemsBorrowed();
}

// Student class extending LibraryUser
class Student extends LibraryUser {
    @Override
    public void printItemsBorrowed() {
        System.out.println("Student: Alice");
        System.out.println("Borrowed Items:");
        for (LibraryItem item : borrowedItems) {
            System.out.println("- " + item.toString());
        }

        System.out.println("----------------------------------------------------------------------------- ");
    
    }
}

// Teacher class extending LibraryUser
class Teacher extends LibraryUser {
    @Override
    public void printItemsBorrowed() {
        System.out.println("Teacher: Bob");
        System.out.println("Borrowed Items:");
        for (LibraryItem item : borrowedItems) {
            System.out.println("- " + item.toString());
        }
        System.out.println("----------------------------------------------------------------------------- ");
    }
}


public class App {
    public static void main(String[] args) {
        Book book1 = new Book("To Kill a Mockingbird", "Harper Lee");
        DVD dvd1 = new DVD("The Shawshank Redemption", "Frank Darabont");

        Student student = new Student();
        Teacher teacher = new Teacher();

        student.borrowItem(book1);
        teacher.borrowItem(dvd1);

        student.printItemsBorrowed();
        teacher.printItemsBorrowed();

        student.returnItem(book1);
        teacher.returnItem(dvd1);

        student.printItemsBorrowed();
        teacher.printItemsBorrowed();
    }
}