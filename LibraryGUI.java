import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LibraryGUI extends JFrame {
    private Library library;
    private JTextArea displayArea;
    private JTextField titleField, authorField, userField, bookIdField;

    public LibraryGUI() {
        library = new Library();
        initialize();
    }

    private void initialize() {
        setTitle("Library Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        add(inputPanel, BorderLayout.NORTH);

        JPanel bookPanel = new JPanel(new FlowLayout());
        inputPanel.add(bookPanel);
        bookPanel.add(new JLabel("Title:"));
        titleField = new JTextField(10);
        bookPanel.add(titleField);
        bookPanel.add(new JLabel("Author:"));
        authorField = new JTextField(10);
        bookPanel.add(authorField);
        JButton addBookButton = new JButton("Add Book");
        bookPanel.add(addBookButton);

        JPanel userPanel = new JPanel(new FlowLayout());
        inputPanel.add(userPanel);
        userPanel.add(new JLabel("User:"));
        userField = new JTextField(10);
        userPanel.add(userField);
        userPanel.add(new JLabel("Book ID:"));
        bookIdField = new JTextField(5);
        userPanel.add(bookIdField);
        JButton borrowButton = new JButton("Borrow Book");
        userPanel.add(borrowButton);
        JButton returnButton = new JButton("Return Book");
        userPanel.add(returnButton);

        JPanel controlPanel = new JPanel(new FlowLayout());
        add(controlPanel, BorderLayout.SOUTH);
        JButton listBooksButton = new JButton("List Books");
        controlPanel.add(listBooksButton);
        JButton saveButton = new JButton("Save Library");
        controlPanel.add(saveButton);
        JButton loadButton = new JButton("Load Library");
        controlPanel.add(loadButton);

        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        borrowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                borrowBook();
            }
        });

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnBook();
            }
        });

        listBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listBooks();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveLibrary();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadLibrary();
            }
        });
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        if (!title.isEmpty() && !author.isEmpty()) {
            int bookId = library.getBooks().size() + 1;
            Book book = new Book(bookId, title, author);
            library.addBook(book);
            displayArea.append("Added Book: " + book + "\n");
        } else {
            displayArea.append("Title and Author cannot be empty\n");
        }
    }

    private void borrowBook() {
        String userName = userField.getText();
        int bookId;
        try {
            bookId = Integer.parseInt(bookIdField.getText());
        } catch (NumberFormatException e) {
            displayArea.append("Invalid Book ID\n");
            return;
        }

        if (!userName.isEmpty()) {
            User user = new User(userName, library.getUsers().size() + 1);
            library.registerUser(user);
            library.borrowBook(user, bookId);
            displayArea.append(user.getName() + " borrowed Book ID " + bookId + "\n");
        } else {
            displayArea.append("User name cannot be empty\n");
        }
    }

    private void returnBook() {
        String userName = userField.getText();
        int bookId;
        try {
            bookId = Integer.parseInt(bookIdField.getText());
        } catch (NumberFormatException e) {
            displayArea.append("Invalid Book ID\n");
            return;
        }

        if (!userName.isEmpty()) {
            User user = new User(userName, library.getUsers().size() + 1);
            library.returnBook(user, bookId);
            displayArea.append(user.getName() + " returned Book ID " + bookId + "\n");
        } else {
            displayArea.append("User name cannot be empty\n");
        }
    }

    private void listBooks() {
        displayArea.setText("");
        for (Book book : library.getBooks()) {
            displayArea.append(book + "\n");
        }
    }

    private void saveLibrary() {
        try {
            library.saveToFile("library.dat");
            displayArea.append("Library saved successfully\n");
        } catch (IOException e) {
            displayArea.append("Error saving library: " + e.getMessage() + "\n");
        }
    }

    private void loadLibrary() {
        try {
            library.loadFromFile("library.dat");
            displayArea.append("Library loaded successfully\n");
            listBooks();
        } catch (IOException | ClassNotFoundException e) {
            displayArea.append("Error loading library: " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LibraryGUI().setVisible(true);
            }
        });
    }
}
