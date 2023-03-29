import classes.User;
import classes.Singer;
import classes.Voter;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {
    private static User[] users = {};
    private static int signedInUserId = -1;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        users = append(users, new User("admin", "admin", "name", "surname", 1, "admin"));
        users = append(users, new User("lukas", "pass", "name", "surname", 2, "singer"));
        users = append(users, new User("joe", "1234", "name", "surname", 3, "singer"));
        users = append(users, new User("Adele", "1", "name", "surname", 4, "singer"));
        users = append(users, new User("Beyonce", "2", "name", "surname", 5, "singer"));
        users = append(users, new User("Ed Sheeran", "3", "name", "surname", 6, "singer"));
        users = append(users, new User("Taylor Swift", "4", "name", "surname", 7, "singer"));
        users = append(users, new User("Bruno Mars", "5", "name", "surname", 8, "singer"));
        users = append(users, new User("Rihanna", "6", "name", "surname", 9, "singer"));
        users = append(users, new User("Justin Bieber", "7", "name", "surname", 10, "singer"));
        users = append(users, new User("Katy Perry", "8", "name", "surname", 11, "singer"));
        users = append(users, new User("Ariana Grande", "9", "name", "surname", 12, "singer"));
        users = append(users, new User("Shawn Mendes", "10", "name", "surname", 13, "singer"));
        users = append(users, new Singer("Elton John", "10", "name", "surname", 13, "singer", "Artisticnname", new ArrayList<String>(Arrays.asList("Song1", "Song2")), new ArrayList<String>(Arrays.asList("Pop", "Rock")), "Spain", "2089", 150));
        users = append(users, new Singer("Shawn Mendes", "10", "name", "surname", 13, "singer", "Artisticnname", new ArrayList<String>(Arrays.asList("Song1", "Song2")), new ArrayList<String>(Arrays.asList("Pop", "Rock")), "Spain", "2089", 190));
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
        System.out.print("Menu:\n" +
                "\t1. Show singers\n" +
                "\t2. Show songs\n" +
                "\t3. Show voters\n" +
                "\t4. Show votes\n" +
                "\t5. Add song\n" +
                "\t-----(Admin privileges required)-----\n" +
                "\t6. Add user\n" +
                "\t7. Update data\n" +
                "\t8. Remove user\n" +
                "\t-------------------------------------\n" +
                "\t9. Sign out\n" +
                "\t10. Quit\n\n");
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
        /** Prints the singers, sort by votes (ascending/descending) */
        //empty list of singers
        ArrayList<Singer> singers = new ArrayList<>(); // empty list of singers
        for (User user : users) {
            if (user instanceof Singer singer) {
                // Cast the object to Singer type
                singers.add(singer);
            }
        }
        char option;
        System.out.print("Do you want the votes ordered in ascending or descending order? (a/d): ");
        option = scanner.nextLine().charAt(0);
        if (option == 'a') {
            // Sort votes in ascending order using bubble sort
            for (int j = 0; j < singers.size() - 1; j++) {
                for (int i = 0; i < singers.size() - 1; i++) {
                    if (singers.get(i).getReceivedVotes() > singers.get(i + 1).getReceivedVotes()) {
                        Singer temp = singers.get(i);
                        singers.set(i, singers.get(i + 1));
                        singers.set(i + 1, temp);
                    }
                }
            }
        } else if (option == 'd') {
            // Sort the votes in descending order using bubble sort
            for (int j = 0; j < singers.size() - 1; j++) {
                for (int i = 0; i < singers.size() - 1; i++) {
                    if (singers.get(i).getReceivedVotes() < singers.get(i + 1).getReceivedVotes()) {
                        Singer temp = singers.get(i);
                        singers.set(i, singers.get(i + 1));
                        singers.set(i + 1, temp);
                    }
                }
            }
        } else {
            System.out.println("Invalid option");
        }
        // Print the singers
        for (Singer singer : singers)
            System.out.print("{" + singer.getUsername() + ", Votes: " + singer.getReceivedVotes() + "} ");
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
        /** Registers new user */
        String role;
        while (true) {
            System.out.print("Select the role of the user (singer/voter/admin): ");
            role = scanner.nextLine();
            switch (role) {
                case "singer":
                    registerSinger();
                    return;
                case "voter":
                    registerVoter();
                    return;
                case "admin":
                    registerAdmin();
                    return;
                default:
                    System.out.println("Invalid role");
            }
        }
    }

    public static void updateUserData() {
        /** Lets you select the user and update the data */
    }

    public static void removeUser() {
        /** Lets you select the user and delete the user */
    }

    public static void registerSinger() {
        /** Registers the singer */
        // Ask for username, name, surname, password
        System.out.print("Enter the username: ");
        String username = scanner.nextLine();
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter the password: ");
        String password = scanner.nextLine();
        // Creates a random id and if it already exists, create a new one
        int id = (int) (Math.random() * 1000);
        while (getUserObjectById(id, users) != null) {
            id = (int) (Math.random() * 1000);
        }

        // Asks singer specific data
        System.out.print("Enter the artistic name: ");
        String artisticName = scanner.nextLine();
        System.out.print("Enter the song title(s): ");
        String songTitles = scanner.nextLine();
        // Split the song titles by comma
        ArrayList<String> songTitlesArray = new ArrayList<>(Arrays.asList(songTitles.split(",")));
        System.out.print("Enter the genre(s): ");
        String genres = scanner.nextLine();
        // Split the genres by comma
        ArrayList<String> genresArray = new ArrayList<>(Arrays.asList(genres.split(",")));
        System.out.print("Enter the date of release of the last song: ");
        String dateOfRelease = scanner.nextLine();
        System.out.print("Enter the country: ");
        String country = scanner.nextLine();
        System.out.print("Enter the number of votes received: ");
        int receivedVotes = scanner.nextInt();

        // Creates a new singer object
        users = append(users, new Singer(username, password,name, surname, id, "admin", artisticName, songTitlesArray, genresArray, dateOfRelease, country, receivedVotes));
        System.out.println("Singer registered successfully");
    }

    public static void registerVoter() {
        /** Registers the voter */
        // Ask for username, name, surname, password
        System.out.print("Enter the username: ");
        String username = scanner.nextLine();
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter the password: ");
        String password = scanner.nextLine();
        // Creates a random id and if it already exists, create a new one
        int id = (int) (Math.random() * 1000);
        while (getUserObjectById(id, users) != null) {
            id = (int) (Math.random() * 1000);
        }

        // Asks voter specific data,favouriteSingers, favouriteSongs, votes, country
        System.out.print("Enter the favourite singer(s) seperated by commas: ");
        String favouriteSingers = scanner.nextLine();
        // Split the favourite singers by comma
        ArrayList<String> favouriteSingersArray = new ArrayList<>(Arrays.asList(favouriteSingers.split(",")));
        System.out.print("Enter the favourite song(s) seperated by commas: ");
        String favouriteSongs = scanner.nextLine();
        // Split the favourite songs by comma
        ArrayList<String> favouriteSongsArray = new ArrayList<>(Arrays.asList(favouriteSongs.split(",")));
        System.out.print("Enter the number of votes: ");
        int votes = scanner.nextInt();
        System.out.print("Enter the country: ");
        String country = scanner.nextLine();


        // Creates a new singer object
        users = append(users, new Voter(username, password,name, surname, id, "admin", favouriteSingersArray, favouriteSongsArray, votes, country));
        System.out.println("Voter registered successfully");

    }

    public static void registerAdmin() {
        /** Registers the admin */
        // Asks for username, name, surname, password
        System.out.print("Enter the username: ");
        String username = scanner.nextLine();
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter the password: ");
        String password = scanner.nextLine();
        // Creates a random id and if it already exists, create a new one
        int id = (int) (Math.random() * 1000);
        while (getUserObjectById(id, users) != null) {
            id = (int) (Math.random() * 1000);
        }

        // Creates a new user object
        users = append(users, new User(username, password,name, surname, id, "admin"));
        System.out.println("Singer registered successfully");
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