import classes.User;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static User[] users = {};
    private static int signedInUserId = -1;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        users = append(users, new User("admin", "admin", 0, "admin"));
        users = append(users, new User("lukas", "pass", 5, "singer"));
        users = append(users, new User("joe", "1234", 76, "singer"));

        System.out.println("\nWelcome to singing contest organizer !\n");

        while (true) {
            if (signedInUserId != -1) {
                // if the user is already signed in, show the menu
                showMenu();
                askForOptionAndExecute();

            } else {
                // if the user is not signed in, check the username and password
                System.out.println("Please authenticate");
                // Ask the user to enter the username
                System.out.print("\tUsername: ");
                String username = scanner.nextLine();
                // Ask the user to enter the password
                System.out.print("\tPassword: ");
                String password = scanner.nextLine();
                // Check the username and password
                if (checkUsernameAndPassword(username, password, users)) {
                    // if the username and password are correct, show the menu
                    signedInUserId = getUserIdByUsername(username, users);

                } else {
                    // if the username and password are incorrect, show an error message
                    System.out.println("Incorrect username or password");
                }
                ;
            }
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
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user.getId();
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

    public static User getUserObjectById(int id, User[] users) {
        /**
         * Returns the whole user object that corresponds to the user id
         * 
         * @param id
         * @return user
         */
        // Loop through the users array and return the username with the given id
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public static boolean checkUsernameAndPassword(String username, String password, User[] users) {
        /**
         * Checks the username and password
         *
         * @param username
         * @param password
         *
         * return boolean
         */
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user.checkPassword(password);
            }
        }
        return false;

    }

    public static void showMenu() {
        /** Prints the menu based on the user role and prints singed in user */
        System.out.println("\nSigned in as: " + getUsernameById(signedInUserId, users) + "(id: " + signedInUserId + ")");
        System.out.println("Menu:");
        System.out.println("\t1. Show singers");
        System.out.println("\t2. Show songs");
        System.out.println("\t3. Show voters");
        System.out.println("\t4. Show votes");
        System.out.println("\t5. Add song");
        System.out.println("\t-----(Admin privileges required)-----");
        System.out.println("\t6. Add user");
        System.out.println("\t7. Update data");
        System.out.println("\t8. Remove user");
        System.out.println("\t-------------------------------------");
        System.out.println("\t9. Sign out");
        System.out.println("\t10. Quit");
    }

    public static void askForOptionAndExecute() {
        //
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        switch (option) {
            case 1 -> showSingers();
            case 2 -> showSongs();
            case 3 -> showVoters();
            case 4 -> showVotes();
            case 5 -> addSongOrGenre();
            case 6 -> handleAdminOption("registerUser");
            case 7 -> handleAdminOption("updateUserData");
            case 8 -> handleAdminOption("removeUser");
            case 9 -> signOut();
            case 10 -> quit();
            default -> System.out.println("No option selected");
        }
    }

    public static void handleAdminOption(String option) {
        if (singedInUserRole().equals("admin")) {
            switch (option) {
                case "registerUser" -> registerUser();
                case "updateUserData" -> updateUserData();
                case "removeUser" -> removeUser();
                default -> System.out.println("Invalid admin option");
            }
        } else {
            System.out.println("Admin privileges required");
        }
    }


    public static String singedInUserRole() {
        /** Returns the signedin user role
         * 
         * return String
         */
        User singedInUser = getUserObjectById(signedInUserId, users);
        return singedInUser.getRole();
    }

    public static void signOut(){
        /** Sings out the user */
        signedInUserId = -1;
        System.out.println("Signed out");
    }

    public static void quit() {
        /** Exits the program */
        System.out.println("Exiting program");
        scanner.close();
        System.exit(0);
    }

    public static void showSingers() {
        /** Prints the singers */
    }

    public static void showSongs() {
        /** Prints the songs */
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

    public static void removeUser() {
        /** Lets you select the user and delete the user */
    }

    public static void registerSinger() {
        /** Registers the singer */
    }

    public static void registerVoter() {
        /** Registers the voter */
    }

    public static <T> T[] append(T[] arr, T element) {
        /** Return the origianl array with a new element appended to it
         * param array
         * param element
         * 
         * return array
          */
        T[] newArr = Arrays.copyOf(arr, arr.length + 1);
        newArr[newArr.length - 1] = element;
        return newArr;
    }

}