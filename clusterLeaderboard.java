import java.util.Scanner;

public class clusterLeaderboard {
    static String[] eventNames = new String[20];
    static int eventCount = 0;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        
    }

    public static int displayMenu() {
        System.out.println("========CLUSTER LEADERBOARD========");
        System.out.println("1. View Current Leaderboard (1)");
        System.out.println("2. Add Event Name (2)");
        System.out.println("3. Record Competition Score (3)");
        System.out.println("4. View Cluster Summary (4)");
        System.out.println("5. Exit (5)");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        return choice;
    }

    public static void addEvent(String eventName) {
        eventNames[eventCount] = eventName;
        eventCount++;
    }
}
