import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final Path FILE_PATH = Path.of("tasks.json");
    private List<Task> tasks;

    public TaskManager() {
        tasks = loadTasks();
    }

    public void addTask(String description) {
        Task new_task = new Task(description);
        tasks.add(new_task);
    }

    public void updateTask(String id, String new_description) {
        Task task = findTask(id);
        task.updateDescription(new_description);
    }

    public void deleteTask(String id) {
        Task task = findTask(id);
        tasks.remove(task);
    }

    public void markDone(String id) {
        Task task = findTask(id);
        task.markAsDone();
    }

    public void markInProgress(String id) {
        Task task = findTask(id);
        task.markInProgress();
    }

    public Task findTask(String id) {
        int parsedId = Integer.parseInt(id);

        for (Task task : tasks) {
            if (parsedId == task.getId()) {
                return task;
            }
        }

        throw new IllegalArgumentException("Task with ID " + id + " not found!");
    }

    public List<Task> getTasks(String type) {
        List<Task> tasksList = new ArrayList<>();

        for (Task task : tasks) {
            if (type.equals("all") || task.getStatus().equals(type)) {
                tasksList.add(task);
            }
        }

        return tasksList;
    }

    private List<Task> loadTasks() {
        List<Task> stored_tasks = new ArrayList<>();

        if (!Files.exists(FILE_PATH)) {
            return stored_tasks;
        }

        try {
            String jsonContent = Files.readString(FILE_PATH);

            if (jsonContent.replaceAll("\\s", "").equals("[]")) {
                return stored_tasks;
            }

            String[] taskList = jsonContent.replace("[", "").replace("]", "").split("},");

            for (String taskJson : taskList) {

                if (!taskJson.endsWith("}")) {
                    taskJson = taskJson + "}";
                }

                stored_tasks.add(Task.fromJson(taskJson));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return stored_tasks;
    }

    public void saveTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");

        for (int i = 0; i < tasks.size(); i++) {
            sb.append(tasks.get(i).toJson());

            if (i < tasks.size() - 1) {
                sb.append(",\n");
            }
        }

        sb.append("\n]");

        String jsonContent = sb.toString();

        try {
            Files.writeString(FILE_PATH, jsonContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
