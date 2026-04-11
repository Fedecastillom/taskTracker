public class Task {
    private static int lastID = 0;
    private final int ID;
    private String description;
    private String status;
    private String createdAt;
    private String UpdatedAt;

    public Task(String description) {
        this.ID = ++lastID;
        this.description = description;
        this.status = "To do";
    }
}
