public class TaskCLIApp {
    static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        String command = args[0];

        /*
        *  Mejorar el listado de las tareas
        *  Arreglar que cuando esta vacio el archivo no funciona
        *  Arreglar que cuando se pone una coma (,) en la descripcion no funciona
        *  Mas println (delete, update, add, etc) pero fuera de las clases como en TaskManager:18
        */

        switch (command) {
            case "add":
                if (validateArgs(args, 2)) {
                    taskManager.addTask(args[1]);
                }
                break;

            case "update":
                if (validateArgs(args, 3)) {
                    taskManager.updateTask(args[1], args[2]);
                }
                break;

            case "delete":
                if (validateArgs(args, 2)) {
                    taskManager.deleteTask(args[1]);
                }
                break;

            case "list":
                if (args.length < 2) {
                    System.out.println(taskManager.getTasks("all"));
                } else {
                    System.out.println(taskManager.getTasks(args[1]));
                }
                break;

            case "mark-done":
                if (validateArgs(args, 2)) {
                    taskManager.markDone(args[1]);
                }
                break;

            case "mark-in-progress":
                if (validateArgs(args, 2)) {
                    taskManager.markInProgress(args[1]);
                }
                break;

            case "help":
                printHelp();
                return;
        }
        taskManager.saveTasks();
    }

    private static boolean validateArgs(String[] args, int expected) {
        if (args.length != expected) {
            System.out.println("Invalid number of arguments. Expected: " + (expected - 1));
            return false;
        }
        return true;
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
