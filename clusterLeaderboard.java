import java.util.Scanner;

public class clusterLeaderboard {
    static String[] eventNames = new String[20];
    static int eventCount = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            choice = displayMenu();

            switch (choice) {
                case 1:
                    break;
                case 2:
                    System.out.println("What is the Event Name?");
                    String eventName = sc.nextLine();
                    addEvent(eventName);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    showEvents();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Invalid Output. Please try again.");
                    System.out.println();
            }
        } while (choice != 6);
    }

    public static int displayMenu() {
        System.out.println("========CLUSTER LEADERBOARD========");
        System.out.println("1. View Current Leaderboard (1)");
        System.out.println("2. Add Event Name (2)");
        System.out.println("3. Record Competition Score (3)");
        System.out.println("4. View Cluster Summary (4)");
        System.out.println("5. Show Registered Events (5)");
        System.out.println("6. Exit (6)");
        System.out.println("===================================");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }

    public static void addEvent(String eventName) {
        if (eventCount > 20) {
            System.out.println("Max Events Reached.");
        } else {
            eventNames[eventCount] = eventName;
            eventCount++;
        }
    }

    public static void showEvents() {
        if (eventCount == 0) {
            System.out.println("No Events Registered.");
        } else {
            System.out.println("=====REGISTERED EVENTS=====");
            for (int i = 0; i < eventCount; i++) {
                System.out.println((i + 1) + ". " + eventNames[i]);
            }
            System.out.println("===========================");
        }
    }
}
