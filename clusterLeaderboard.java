import java.util.Scanner;

public class clusterLeaderboard {
    static String[] eventNames = new String[20];
    static double[] totalClusterScore = new double[8];
    static double clusterEventScore[] = new double[160];
    static String clusterEventNameScored[] = new String[160];
    static String clusterNameScored[] = new String[160];
    static int scoredCount = 0;
    static int eventCount = 0;
    static Scanner sc = new Scanner(System.in);
    
    static final String CLUSTER_NAMES[] = {"Manresa", "Pamplona", "Jerusalem", "Montserrat", "Paris", "Barcelona", "Salamanca", "Rome"};
    public static void main(String[] args) {
        int choice;
        String eventName;
        String cluster;
        do {
            choice = displayMenu();

            switch (choice) {
                case 1:
                    break;
                case 2:
                    System.out.println("What is the Event Name?");
                    eventName = sc.nextLine();
                    addEvent(eventName);
                    break;
                case 3:
                    System.out.println("Which cluster?");
                    cluster = sc.nextLine();
                    System.out.println("Which event?");
                    eventName = sc.nextLine();
                    System.out.println("How many points?");
                    double score = sc.nextDouble();
                    recordScore(cluster, eventName, score);
                    System.out.println(clusterNameScored[0] + clusterEventNameScored[0] + clusterEventScore[0]);
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

    public static void recordScore(String cluster, String eventName, double score) {
        
        if (score < 0) {
            System.out.println("Negative Scores are not allowed.");
            return;
        }

        clusterNameScored[scoredCount] = cluster;
        clusterEventNameScored[scoredCount] = eventName;
        clusterEventScore[scoredCount] = score;
        
        for (int i = 0; i < 8; i++) {
            if (cluster == CLUSTER_NAMES[i]) {
                totalClusterScore[i] = totalClusterScore[i] + score;
                break;
            }
        }
        System.out.println("Score successfully recorded!");
        scoredCount++;
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
