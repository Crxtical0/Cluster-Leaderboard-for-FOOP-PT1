// Zack Ethan Ang && Ethan Jayden Jadulang
/*
Test Case 1: Valid Event and Score Recording
Input:
  - Add Event: Basketball
  - Record Score: Manresa, Basketball, 25
Expected Output:
  - Event successfully registered.
  - Score successfully recorded.
  - Manresa's total score becomes 25.00.
  
Test Case 2: Duplicate Event Registration
Input:
  - Add Event: Basketball
  - Add Event: Basketball
Expected Output:
  - First input: Event successfully registered.
  - Second input: "Event already Registered."


Test Case 3: Negative Score Validation
Input:
  - Record Score: Manresa, Basketball, -10
Expected Output:
  - "Negative Scores are not allowed."
  - Score is not recorded.


Test Case 4: Invalid Cluster Name
Input:
  - Record Score: Hogwarts, Basketball, 20
Expected Output:
  - "Invalid Cluster."
  - Score is not recorded.


Test Case 5: Unregistered Event
Input:
  - Record Score: Manresa, Swimming, 15
Expected Output:
   - "Invalid event. Please add the event first."
   - Score is not recorded.
 */

import java.util.Scanner;

public class clusterLeaderboard {
    static final String CLUSTER_NAMES[] = { "Manresa", "Pamplona", "Jerusalem", "Montserrat", "Paris", "Barcelona",
            "Salamanca", "Rome" };
    static final int MAX_CLUSTERS = 8;
    static final int MAX_EVENTS = 20;
    static String[] eventNames = new String[MAX_EVENTS];
    static double[] totalClusterScore = new double[MAX_CLUSTERS];
    static final int MAX_SCORES = 160;
    static double clusterEventScore[] = new double[MAX_SCORES];
    static String clusterEventNameScored[] = new String[MAX_SCORES];
    static String clusterNameScored[] = new String[MAX_SCORES];
    static int scoredCount = 0;
    static int eventCount = 0;
    static Scanner sc = new Scanner(System.in);
    static String sortedClusterNames[] = new String[MAX_CLUSTERS];
    static double sortedFinalScores[] = new double[MAX_CLUSTERS];

    public static void main(String[] args) {
        int choice;
        String eventName;
        String cluster;
        do {
            choice = displayMenu();

            switch (choice) {
                case 1:
                    showLeaderboard();
                    break;
                case 2:
                    System.out.println("What is the Event Name?");
                    eventName = sc.nextLine();
                    addEvent(eventName);
                    break;
                case 3:
                    System.out.println("Which cluster?");
                    cluster = sc.nextLine();
                    if (!isValidName(cluster, CLUSTER_NAMES)) {
                        System.out.println("Invalid Cluster.");
                        break;
                    }
                    if (eventCount == 0) {
                        System.out.println("No Events Registered.");
                        break;
                    }
                    showEvents();
                    System.out.println("Which event?");
                    eventName = sc.nextLine();
                    if (!isValidName(eventName, eventNames)) {
                        System.out.println("Invalid event. Please add the event first.");
                        break;
                    }
                    System.out.println("How many points?");
                    double score = sc.nextDouble();
                    sc.nextLine();
                    recordScore(cluster, eventName, score);
                    break;
                case 4:
                    System.out.println("Which cluster?");
                    cluster = sc.nextLine();
                    clusterSummary(cluster);
                    break;
                case 5:
                    showEvents();
                    break;
                case 6:
                    sortLeaderboard();
                    System.out.println("======================================================");
                    System.out.println("Thank you for using the Cluster Leaderboard System!");
                    String highestCluster = "No Recorded Score";
                    String lowestCluster = "No Recorded Score";
                    if (scoredCount > 0) {
                        highestCluster = sortedClusterNames[0];
                        lowestCluster = sortedClusterNames[MAX_CLUSTERS - 1];
                    }
                    System.out.printf(
                            "Events Registered: %d%nScores Recorded: %d%nHighest Cluster: %s%nLowest Cluster: %s%n",
                            eventCount, scoredCount, highestCluster, lowestCluster);
                    System.out.println("======================================================");
                    break;
                default:
                    System.out.println("Invalid Output. Please try again.");
                    System.out.println();
            }
        } while (choice != 6);
    }

    public static int displayMenu() {
        // shows the menu and starting screen of the user
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

    public static void sortLeaderboard() {
        // sorts the clusters from most points to least points
        // clusters with no registered scores will have their order based on the final
        // constants

        // puts the cluster names and their score in a copy to be sorted without editing
        // the original array
        for (int k = 0; k < MAX_CLUSTERS; k++) {
            sortedClusterNames[k] = CLUSTER_NAMES[k];
            sortedFinalScores[k] = totalClusterScore[k];
        }

        // uses bubble sort to sort the copied array for cluster names and respective
        // scores from highest to lowest
        for (int i = 0; i < MAX_CLUSTERS - 1; i++) {
            for (int j = 0; j < MAX_CLUSTERS - i - 1; j++) {
                if (sortedFinalScores[j] < sortedFinalScores[j + 1]) {
                    double tempScore = sortedFinalScores[j];
                    sortedFinalScores[j] = sortedFinalScores[j + 1];
                    sortedFinalScores[j + 1] = tempScore;
                    String tempName = sortedClusterNames[j];
                    sortedClusterNames[j] = sortedClusterNames[j + 1];
                    sortedClusterNames[j + 1] = tempName;
                }
            }
        }
    }

    public static void showLeaderboard() {
        sortLeaderboard();
        // prints the leaderboard from top 1 to top 8
        System.out.println("==========LEADERBOARD==========");
        for (int l = 0; l < MAX_CLUSTERS; l++) {
            System.out.printf("%d. %-10s - %8.2f%n", l + 1, sortedClusterNames[l], sortedFinalScores[l]);
        }
        System.out.println("===============================");
    }

    public static void addEvent(String eventName) {
        // validates the entries and adds valid events
        if (eventCount >= MAX_EVENTS) {
            System.out.println("Max Events Reached.");
        } else if(eventName.equals("")) {
            System.out.println("Please input an event.");
        } else if (isValidName(eventName, eventNames)) {
            System.out.println("Event already Registered.");
        } else {
            eventNames[eventCount] = eventName;
            eventCount++;
            System.out.println("Event successfully registered!");
        }
    }

    public static void recordScore(String cluster, String eventName, double score) {
        // validate the entries
        if (!isValidName(cluster, CLUSTER_NAMES)) {
            System.out.println("Invalid cluster.");
            return;
        }

        if (!isValidName(eventName, eventNames)) {
            System.out.println("Event not registered.");
            return;
        }

        if (eventCount == 0) {
            System.out.println("No registered events");
            return;
        }

        if (scoredCount >= MAX_SCORES) {
            System.out.println("Maximum number of scores reached.");
            return;
        }
        if (score < 0) {
            System.out.println("Negative Scores are not allowed.");
            return;
        }
        // records the scores of the corresponding cluster
        clusterNameScored[scoredCount] = cluster;
        clusterEventNameScored[scoredCount] = eventName;
        clusterEventScore[scoredCount] = score;

        // adds the score to the total of the matching cluster
        for (int i = 0; i < MAX_CLUSTERS; i++) {
            if (cluster.equalsIgnoreCase(CLUSTER_NAMES[i])) {
                totalClusterScore[i] = totalClusterScore[i] + score;
                break;
            }
        }
        System.out.println("Score successfully recorded!");
        // increases the recorded score count to increase the index for the next
        // recorded score (for the array to match)
        scoredCount++;
    }

    public static void showEvents() {
        // prints the registered events
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

    public static boolean isValidName(String name, String[] nameArray) {
        // checks if the name of an event or cluster is valid
        for (int i = 0; i < nameArray.length; i++) {
            if (nameArray[i] != null && nameArray[i].equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static void clusterSummary(String cluster) {
        // validates entries
        if (!isValidName(cluster, CLUSTER_NAMES)) {
            System.out.println("Invalid cluster.");
            return;
        }
        // prints the recorded event names with a recorded score from the chosen cluster
        for (int i = 0; i < scoredCount; i++) {
            if (cluster.equalsIgnoreCase(clusterNameScored[i])) {
                System.out.printf("Event Name: %s - %.2f%n", clusterEventNameScored[i], clusterEventScore[i]);
            }
        }
        // shows the total points of a cluster
        for (int j = 0; j < MAX_CLUSTERS; j++) {
            if (CLUSTER_NAMES[j].equalsIgnoreCase(cluster)) {
                System.out.printf("Total Scored Points: %.2f%n", totalClusterScore[j]);
                break;
            }
        }
    }
}