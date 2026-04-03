package org.example;
import java.util.*;

public class Main {
    private static UserDAO userDAO = new UserDAO();
    private static Scanner scanner = new Scanner(System.in);  //must be static

    public static void main(String[] args) {
        System.out.println("=== Mini-User Administration System ===\n");
        boolean running = true;
        while (running) {
            showMenu();
            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    viewAllUsers();
                    break;
                case 3:
                    viewUserByID();
                    break;
                case 4:
                    updateUser();
                    break;
                case 5:
                    deleteUser();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exit System. Bye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    public static void showMenu() {
        System.out.println("1. Create User");
        System.out.println("2. View All Users");
        System.out.println("3. View User by ID");
        System.out.println("4. Update User");
        System.out.println("5. Delete User");
        System.out.println("6. Exit");
    }
    public static void createUser() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        User user = new User(username, email, password);
        boolean status = userDAO.addUser(user);
        if (status) {
            System.out.println("User created successfully!");
        }
        else {
            System.out.println("User creation failed!");
        }
    }

    public static void viewAllUsers() {
        System.out.println("All Users: /n");
        List<User> users = userDAO.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found!");
        }
        else {
            for (User user : users) {
                System.out.println(user.toString());  //has overwritten
            }
        }
    }
    public static void viewUserByID() {
        System.out.println("User by ID: /n");
        int id = scanner.nextInt();
        User user = userDAO.getUserByID(id);  //getUserByID return an User object, here user is a ref variable stored obj
        if (user == null) {
            System.out.println("User not found!");
        }
        else {
            System.out.println(user.toString());
        }
    }
    public static void updateUser() {
        System.out.println("\n--- Update User ---");
        int id = getIntInput("Enter User ID to update: ");

        User user = userDAO.getUserByID(id);

        if (user != null) {
            System.out.println("Current data: " + user);
            System.out.print("New Username (press Enter to keep current): ");
            String username = scanner.nextLine();
            System.out.print("New Email (press Enter to keep current): ");
            String email = scanner.nextLine();
            System.out.print("New Password (press Enter to keep current): ");
            String password = scanner.nextLine();

            if (!username.isEmpty()) user.setUsername(username);
            if (!email.isEmpty()) user.setEmail(email);
            if (!password.isEmpty()) user.setPassword(password);

            if (userDAO.updateUser(user)) {
                System.out.println("User updated successfully!");
            } else {
                System.out.println("Failed to update user.");
            }
        } else {
            System.out.println("User not found.");
        }
    }
    public static void deleteUser() {
        System.out.println("Enter ID: ");
        int id = scanner.nextInt();
        boolean status = userDAO.deleteUser(id);
        if (status) {
            System.out.println("User deleted successfully!");
        }
        else {
            System.out.println("User delete failed!");
        }
    }
    public static int getIntInput(String message) {
        System.out.println(message);
        while (!scanner.hasNextInt()) {
            scanner.next();  // Clear invalid input
            System.out.println("Invalid input. " + message);
        }
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline after reading int
        return choice;
    }
}
