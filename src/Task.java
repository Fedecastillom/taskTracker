import java.time.LocalDateTime;

public class Task {
    private static int lastId = 0;
    private int id;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(String description) {
        this.description = description;
        id = ++lastId;
        status = "todo";
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public void markAsDone() {
        status = "done";
        updatedAt = LocalDateTime.now();
    }

    public void markInProgress() {
        status = "in-progress";
        updatedAt = LocalDateTime.now();
    }

    public void updateDescription(String description) {
        this.description = description;
        updatedAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String toString() {
        return "id: " + id
                + ", description: " + description
                + ", status: " + status
                + ", created At: " + createdAt
                + ", updated At: " + updatedAt;
    }

    public String toJson() {
        return "{\"id\":\"" + id
                + "\", \"description\":\"" + description.strip()
                + "\", \"status\":\"" + status
                + "\", \"createdAt\":\"" + createdAt
                + "\", \"updatedAt\":\"" + updatedAt + "\"}";
    }

    public static Task fromJson(String json) {
        json = json.replace("\"", "").replace("{", "").replace("}", "");
        String[] jsonTask = json.split(",");

        int id = Integer.parseInt(jsonTask[0].split(":")[1].strip());
        String description = jsonTask[1].split((":"))[1].strip();
        String status = jsonTask[2].split((":"))[1].strip();
        String createdAt = jsonTask[3].split(("[a-z]:"))[1].strip();
        String updatedAt = jsonTask[4].split(("[a-z]:"))[1].strip();

        Task task = new Task(description);
        task.id = id;
        task.status = status;
        task.createdAt = LocalDateTime.parse(createdAt);
        task.updatedAt = LocalDateTime.parse(updatedAt);

        if (id > lastId) {
            lastId = id;
        }

        return task;
    }
}
