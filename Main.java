public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Adding books to the library
        Book book1 = new Book(1, "The Catcher in the Rye", "J.D. Salinger");
        Book book2 = new Book(2, "To Kill a Mockingbird", "Harper Lee");
        library.addBook(book1);
        library.addBook(book2);

        // Registering users
        User user1 = new User("Alice", 101);
        User user2 = new User("Bob", 102);
        library.registerUser(user1);
        library.registerUser(user2);

        // Borrowing and returning books
        library.borrowBook(user1, 1);
        library.returnBook(user1, 1);

        // Listing all books
        library.listBooks();
    }
}
