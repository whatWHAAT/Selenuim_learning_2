import org.testng.annotations.Test;

public class Book {
    private String name;
    private int year;
    private String author;

    public Book (String title, int age, String whoWrote) {
        name = title;
        year = age;
        author = whoWrote;
    }

    static void bla() {
        Book book = new Book("Lotr", 5555, "Tolkien");
        System.out.println(book.author + book.year + book.name);
    }

    public static void main(String[] args) {
        bla();
    }
}
