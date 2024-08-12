public class LibraryItem {
    protected int id;
    protected String title;
    protected boolean isBorrowed;

    public LibraryItem(int id, String title) {
        this.id = id;
        this.title = title;
        this.isBorrowed = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowItem() {
        if (!isBorrowed) {
            isBorrowed = true;
        } else {
            System.out.println("Item is already borrowed.");
        }
    }

    public void returnItem() {
        if (isBorrowed) {
            isBorrowed = false;
        } else {
            System.out.println("Item is not borrowed.");
        }
    }

    @Override
    public String toString() {
        return "LibraryItem [id=" + id + ", title=" + title + ", isBorrowed=" + isBorrowed + "]";
    }
}
