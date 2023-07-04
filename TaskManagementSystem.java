import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    private String name;
    private String description;
    private LocalDate deadline;

    public Task(String name, String description, LocalDate deadline) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}

class TaskManager {
    private List<Task> tasks;
    private Scanner sc;

    public TaskManager() {
        tasks = new ArrayList<>();
        sc = new Scanner(System.in);
    }

    public void addTask() {
        System.out.print("Enter task name: ");
        String name = sc.nextLine();
        System.out.print("Enter task description: ");
        String description = sc.nextLine();
        System.out.print("Enter task deadline (yyyy-MM-dd): ");
        String deadlineStr = sc.nextLine();
        LocalDate deadline = LocalDate.parse(deadlineStr, DateTimeFormatter.ISO_DATE);
        Task task = new Task(name, description, deadline);
        tasks.add(task);
        System.out.println("Task added successfully!");
    }

    public void updateTask() {
        System.out.print("Enter task name to update: ");
        String nameToUpdate = sc.nextLine();
        Task taskToUpdate = findTaskByName(nameToUpdate);
        if (taskToUpdate != null) {
            System.out.print("Enter new task name: ");
            String newName = sc.nextLine();
            System.out.print("Enter new task description: ");
            String newDescription = sc.nextLine();
            System.out.print("Enter new task deadline (yyyy-MM-dd): ");
            String newDeadlineStr = sc.nextLine();
            LocalDate newDeadline = LocalDate.parse(newDeadlineStr, DateTimeFormatter.ISO_DATE);
            taskToUpdate.setName(newName);
            taskToUpdate.setDescription(newDescription);
            taskToUpdate.setDeadline(newDeadline);
            System.out.println("Task updated successfully!");
        } else {
            System.out.println("Task not found with the given name.");
        }
    }

    public void deleteTask() {
        System.out.print("Enter task name to delete: ");
        String nameToDelete = sc.nextLine();
        Task taskToDelete = findTaskByName(nameToDelete);
        if (taskToDelete != null) {
            tasks.remove(taskToDelete);
            System.out.println("Task deleted successfully!");
        } else {
            System.out.println("Task not found with the given name.");
        }
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("Tasks:");
            for (Task task : tasks) {
                System.out.println("Name: " + task.getName());
                System.out.println("Description: " + task.getDescription());
                System.out.println("Deadline: " + task.getDeadline());
                System.out.println();
            }
        }
    }

    private Task findTaskByName(String name) {
        for (Task task : tasks) {
            if (task.getName().equalsIgnoreCase(name)) {
                return task;
            }
        }
        return null;
    }
}

public class TaskManagementSystem {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("_________TASK MANAGEMENT SYSTEM_________");
            System.out.println("1. Add Task");
            System.out.println("2. Update Task");
            System.out.println("3. Delete Task");
            System.out.println("4. Display Tasks");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    taskManager.addTask();
                    break;
                case 2:
                    taskManager.updateTask();
                    break;
                case 3:
                    taskManager.deleteTask();
                    break;
                case 4:
                    taskManager.displayTasks();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        }
    }
}
