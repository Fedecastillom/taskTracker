import java.util.List;

public class TaskCLIApp {
    static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        if (args.length == 0 || args[0].equals("help")) {
            printHelp();
            return;
        }

        String command = args[0];

        switch (command) {
            case "add":
                if (validateArgs(args, 2)) {
                    Task task = taskManager.addTask(args[1]);
                    printAction("added", String.valueOf(task.getId()));
                }
                break;

            case "update":
                if (validateArgs(args, 3)) {
                    taskManager.updateTask(args[1], args[2]);
                    printAction("updated", args[1]);
                }
                break;

            case "delete":
                if (validateArgs(args, 2)) {
                    taskManager.deleteTask(args[1]);
                    printAction("deleted", args[1]);
                }
                break;

            case "mark-done":
                if (validateArgs(args, 2)) {
                    taskManager.markDone(args[1]);
                    printAction("marked done", args[1]);
                }
                break;

            case "mark-in-progress":
                if (validateArgs(args, 2)) {
                    taskManager.markInProgress(args[1]);
                    printAction("marked in-progress", args[1]);
                }
                break;

            case "list":
                List<Task> tasks = taskManager.getTasks(args.length < 2? "all" : args[1]);

                for (Task task : tasks) {
                    System.out.printf("[%d] (%s) %s%n",
                            task.getId(),
                            task.getStatus(),
                            task.getDescription()
                    );
                }
                break;

            default:
                printHelp();
                return;
        }
        taskManager.saveTasks();
    }

    private static boolean validateArgs(String[] args, int expected) {
        if (args.length != expected) {
            System.out.println("Invalid number of arguments. Expected: " + (expected));
            return false;
        }
        return true;
    }

    private static void printAction(String action, String id) {
        System.out.println("✔ Task (ID " + id + ") " + action);
    }

    public static void printHelp() {
        System.out.println("""
            Task CLI Usage:
            
            # Adding a new task
            task-cli add "description"
            
            # Updating and deleting tasks
            task-cli update <id> "new description"
            task-cli delete <id>
            
            # Marking tasks
            task-cli mark-in-progress <id>
            task-cli mark-done <id>
            
            # Listing tasks
            task-cli list
            task-cli list done
            task-cli list todo
            task-cli list in-progress
            """);
    }
}
