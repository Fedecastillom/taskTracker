import java.time.LocalDateTime;

public class Task {
    private static int lastID = 0;
    private final int ID;
    private String description;
    private String status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(String description) {
        this.description = description;
        ID = ++lastID;
        status = "To do";
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public void markAsDone() {
        status = "Done";
        updatedAt = LocalDateTime.now();
    }

    public void markInProgress() {
        status = "In Progress";
        updatedAt = LocalDateTime.now();
    }

    public void updateDescription(String description) {
        this.description = description;
        updatedAt = LocalDateTime.now();
    }

    public int getID() {
        return ID;
    }

    public String getStatus() {
        return status;
    }

    public String toString() {
        return "id: " + ID + ", description: " + description + ", status: " + status + ", created At: " + createdAt + ", updated At: " + updatedAt;
    }
}
