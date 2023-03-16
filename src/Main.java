import classes.User;
import java.util.Scanner;
import java.util.Arrays;


public class Main {
    private static User[] users = {};
    private static int signedInUserId = -1;
    public static void main(String[] args) {
        users = append(users, new User("admin","admin", 0, "admin"));
        users = append(users, new User("lukas","pass", 5, "singer"));
        users = append(users, new User("joe","1234", 76, "singer"));
        // append new user object to the array

        // intialize one user object with name lukas password hello and id 5
        // users[1] = new User("lukas","passs", 5)
        
        // array of songs
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWelcome to singing contest organizer\n");

        // Create a main loop that will run until the user exits the program
        // The loop should ask the user to enter the password
        // If the password is correct, it should show the menu
        // If the password is incorrect, it should ask the user to enter the password
        // again
        while (true) {
            if(signedInUserId != -1) {
                // if the user is already signed in, show the menu
                showMenu();
                askForOption();

            } else {
                // if the user is not signed in, check the username and password
                System.out.println("Please authenticate");
                // Ask the user to enter the username
                System.out.print("\tUsername:");
                String username = scanner.nextLine();
                // Ask the user to enter the password
                System.out.print("\tPassword:");
                String password = scanner.nextLine();
                // Check the username and password
                if(checkPassword(username, password, users)){
                    // if the username and password are correct, show the menu
                    signedInUserId = getUserIdByUsername(username, users);
                } else {
                    // if the username and password are incorrect, show an error message
                    System.out.println("Incorrect username or password");
                };
            }

            // if the username and password are correct, show the menu until the user exits
            // the program or signs out
        }
    }

    public static int getUserIdByUsername(String username, User[] users) {
        /**
         * Returns the user id that corresponds to the username
         * 
         * @param username
         * @return id
         */
        // Loop through the users array and return the user id with the given username
        for (int i = 0; i < users.length; i++) {
            if (users[i].getUsername().equals(username)) {
                return users[i].getId();
            }
        }
        return -1;
    }

    public static String getUsernameById(int id, User[] users) {
        /**
         * Returns the username that corresponds to the user id
         * 
         * @param id
         * @return username
         */
        // Loop through the users array and return the username with the given id
        for (int i = 0; i < users.length; i++) {
            if (users[i].getId() == id) {
                return users[i].getUsername();
            }
        }
        return "";
    }



    public static boolean checkPassword(String username, String password, User[] users) {
        /**
         * Checks the password
         * 
         * @param password
         */
        for (int i = 0; i < users.length; i++) {
            if (users[i].getUsername().equals(username)) {
                if(users[i].checkPassword(password)){
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;

    }

    public static void showMenu() {
        /** Prints the menu based on the user role and prints singed in user */
        // Print singed in user
        System.out.println("\nSigned in as: " + getUsernameById(signedInUserId, users) + "(id: " + signedInUserId + ")");
        System.out.println("Menu:");
        System.out.println("\t1. ...");
        System.out.println("\t2. ...");
        System.out.println("\t3. ...");
        System.out.println("\t4. ...");
    }

    public static void askForOption() {
        /** Asks the user to enter the option */
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the option:");
        int option = scanner.nextInt();
    }

    public static void showSingers() {
        /** Prints the singers */
    }

    public static void showVoters() {
        /** Prints the voters */
    }

    public static void showVotes() {
        /** Prints the votes */
    }

    public static void addSongOrGenre() {
        /**
         * Adds a song or a genre
         * Shows the list
         */
    }

    public static void voteSong() {
        /** Asks the user to vote for a song */
    }

    public static void registerUser() {
        /** Registers the user */
    }

    public static void updateUserData() {
        /** Lets you select the user and update the data */
    }

    public static void deleteUser() {
        /** Lets you select the user and delete the user */
    }

    public static void registerSinger() {
        /** Registers the singer */
    }

    public static void registerVoter() {
        /** Registers the voter */
    }

    public static <T> T[] append(T[] arr, T element) {
        T[] newArr = Arrays.copyOf(arr, arr.length + 1);
        newArr[newArr.length - 1] = element;
        return newArr;
    }

}