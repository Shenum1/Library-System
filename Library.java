import java.io.*;
import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;
    private ArrayList<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public void borrowBook(User user, int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                if (!book.isBorrowed()) {
                    book.borrowItem();
                    System.out.println(user.getName() + " borrowed " + book.getTitle());
                    return;
                } else {
                    System.out.println("Book is already borrowed.");
                    return;
                }
            }
        }
        System.out.println("Book not found.");
    }

    public void returnBook(User user, int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                if (book.isBorrowed()) {
                    book.returnItem();
                    System.out.println(user.getName() + " returned " + book.getTitle());
                    return;
                } else {
                    System.out.println("Book is not borrowed.");
                    return;
                }
            }
        }
        System.out.println("Book not found.");
    }

    public void listBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    // Save library state to a file
    public void saveToFile(String BookHub) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(BookHub))) {
            out.writeObject(books);
            out.writeObject(users);
        }
    }

    // Load library state from a file
    @SuppressWarnings("unchecked")
    public void loadFromFile(String BookHub) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(BookHub))) {
            books = (ArrayList<Book>) in.readObject();
            users = (ArrayList<User>) in.readObject();
        }
    }
}
