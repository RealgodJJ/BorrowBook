package reagodjj.example.com.borrowbook;

public class Book {
    private String bookName;
    private int bookForAge;
    private boolean history;
    private boolean suspense;
    private boolean literary;
    private int picture;

    public Book(String bookName, int bookForAge, boolean history, boolean suspense, boolean literary, int picture) {
        this.bookName = bookName;
        this.bookForAge = bookForAge;
        this.history = history;
        this.suspense = suspense;
        this.literary = literary;
        this.picture = picture;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookForAge() {
        return bookForAge;
    }

    public void setBookForAge(int bookForAge) {
        this.bookForAge = bookForAge;
    }

    public boolean isHistory() {
        return history;
    }

    public void setHistory(boolean history) {
        this.history = history;
    }

    public boolean isSuspense() {
        return suspense;
    }

    public void setSuspense(boolean suspense) {
        this.suspense = suspense;
    }

    public boolean isLiterary() {
        return literary;
    }

    public void setLiterary(boolean literary) {
        this.literary = literary;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
