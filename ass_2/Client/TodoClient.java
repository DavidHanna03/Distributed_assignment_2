import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class TodoClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            TodoService service = (TodoService) registry.lookup("TodoService");
            Scanner scanner = new Scanner(System.in);
            String command;

            System.out.println("Welcome to the Todo Client!");
            System.out.println("Commands: add, complete, delete, count, search, view, exit");

            while (true) {
                System.out.print("Enter command: ");
                command = scanner.nextLine();

                switch (command.toLowerCase()) {
                    case "add":
                        System.out.print("Enter task to add: ");
                        String newTask = scanner.nextLine();
                        service.addTask(newTask);
                        System.out.println("Task added.");
                        break;

                    case "complete":
                        System.out.print("Enter task to mark as complete: ");
                        String taskToComplete = scanner.nextLine();
                        if (service.markTaskComplete(taskToComplete)) {
                            System.out.println("Task marked as complete.");
                        } else {
                            System.out.println("Task not found.");
                        }
                        break;

                    case "delete":
                        System.out.print("Enter task to delete: ");
                        String taskToDelete = scanner.nextLine();
                        if (service.deleteTask(taskToDelete)) {
                            System.out.println("Task deleted.");
                        } else {
                            System.out.println("Task not found.");
                        }
                        break;

                    case "count":
                        int taskCount = service.getTaskCount();
                        System.out.println("Total tasks: " + taskCount);
                        break;

                    case "search":
                        System.out.print("Enter keyword to search: ");
                        String keyword = scanner.nextLine();
                        List<String> results = service.searchTasks(keyword);
                        System.out.println("Search Results:");
                        for (String task : results) {
                            System.out.println("- " + task);
                        }
                        break;

                    case "view":
                        List<String> allTasks = service.viewAllTasks();
                        System.out.println("All Tasks:");
                        for (String task : allTasks) {
                            System.out.println("- " + task);
                        }
                        break;

                    case "exit":
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid command. Try again.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
