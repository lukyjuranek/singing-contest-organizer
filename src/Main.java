import classes.Song;
import classes.User;
import classes.Singer;
import classes.Voter;

import java.util.Collections;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {
    private static User[] users = {};
    private static int signedInUserId = -1;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Create some users for testing
        users = append(users, new Voter("Alice", "pass", "Alice", "Smith", 1, "voter", new ArrayList<>(Arrays.asList("The Weeknd", "Dua Lipa", "Drake", "Cardi B", "Post Malone", "Ariana Grande", "Billie Eilish", "Khalid", "Taylor Swift", "Bruno Mars")), new ArrayList<>(Arrays.asList("Blinding Lights", "Levitating", "God's Plan", "Bodak Yellow", "Circles", "7 Rings", "Bad Guy", "Talk", "Lover", "Uptown Funk")), 10, "Canada"));
        users = append(users, new Voter("Elena", "pass", "Elena", "Garcia", 2, "voter", new ArrayList<>(Arrays.asList("Shakira", "J Balvin", "Rosalia", "Bad Bunny", "Enrique Iglesias", "Marc Anthony", "Daddy Yankee", "Jennifer Lopez", "Nicky Jam", "Ricky Martin")), new ArrayList<>(Arrays.asList("Hips Don't Lie", "Mi Gente", "Malamente", "Mia", "Bailando", "Vivir Mi Vida", "Gasolina", "On The Floor", "El Perdon", "Livin' La Vida Loca")), 15, "Spain"));

        users = append(users, new User("admin", "admin", "name", "surname", 3, "admin"));
        users = append(users, new User("lukas", "pass", "name", "surname", 4, "singer"));
        users = append(users, new User("joe", "1234", "name", "surname", 5, "singer"));

        Song song1 = new Song(5, "Shape of You");
        Song song2 = new Song(10, "Uptown Funk");
        Song song3 = new Song(15, "Bohemian Rhapsody");
        Song song4 = new Song(20, "Billie Jean");

        users = append(users, new Singer("singer", "pass", "John", "Legend", 6, "singer", "Ed Sheeran", new ArrayList<>(Arrays.asList(song1, song2)), new ArrayList<>(Arrays.asList("Pop", "R&B")), "UK", "1991"));
        users = append(users, new Singer("singer2", "pass ", "Shawn", "Mendes", 7, "singer", "Queen", new ArrayList<>(Arrays.asList(song3, song4)) , new ArrayList<>(Arrays.asList("Rock", "Classic Rock")), "UK", "1970"));

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
        for (User user : users) {
            if (user.getId() == id) {
                return user.getUsername();
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
        System.out.print("""
                Menu:
                \t1. Show singers
                \t2. Show songs
                \t3. Show voters
                \t-----(Singer privileges required)-----
                \t4. Show votes
                \t5. Add song
                \t-----(Voter privileges required)------
                \t6. Vote song
                \t-----(Admin privileges required)------
                \t7. Manage Users
                \t--------------------------------------
                \t8. Sign out
                \t9. Quit
                """);
    }

    public static void showManageUsers() {
        System.out.print("""
                Manage Users:
                \t--------------------------------------
                \t1. Add User
                \t2. Update Data
                \t3. Remove User
                \t--------------------------------------
                \t4. Back to Menu
                \t5. Exit

                """);
        manageUsers();
    }

    public static void manageUsers() {
        /**
         * Asks the user to select an option from the manage users menu and executes the
         * corresponding method
         */
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        switch (option) {
            case 1 -> handleAdminOption("registerUser");
            case 2 -> handleAdminOption("updateUserData");
            case 3 -> handleAdminOption("removeUser");
            case 4 -> System.out.print("");
            case 5 -> quit();
            default -> System.out.println("Invalid admin command");
        }
    }

    public static void askForOptionAndExecute() {
        /** Asks the user to select an option and executes the corresponding method */
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        switch (option) {
            case 1 -> showSingers();
            case 2 -> showSongs();
            case 3 -> showVoters();
            case 4 -> handleSingerOption("showVotes");
            case 5 -> handleSingerOption("addSong");
            case 6 -> handleVoterOption("voteSong");
            case 7 -> showManageUsers();
            case 8 -> signOut();
            case 9 -> quit();
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

    public static void handleVoterOption(String option) {
        if (singedInUserRole().equals("voter")) {
            if (option.equals("voteSong")) {
                voteSong();
            } else {
                System.out.println("Invalid voter option");
            }
        } else {
            System.out.println("Voter privileges required");
        }
    }
    
    public static void handleSingerOption(String option) {
        if (singedInUserRole().equals("singer")) {
            switch (option) {
                case "showVotes" -> showVotes();
                case "addSong" -> addSongOrGenre();
                default -> System.out.println("Invalid singer option");
            }
        } else {
            System.out.println("Singer privileges required");
        }
    }


    public static String singedInUserRole() {
        User singedInUser = getUserObjectById(signedInUserId, users);
        return singedInUser.getRole();
    }

    public static void signOut(){
        signedInUserId = -1;
        System.out.println("Signed out");
    }

    public static void quit() {
        /** Exits the program */
        System.out.println("Exiting program");
        scanner.close();
        System.exit(0);
    }

    public static ArrayList<Singer> createListSingers() {
        /** Creates a list of singers */
        ArrayList<Singer> singers = new ArrayList<>(); // empty list of singers
        for (User user : users) {
            if (user instanceof Singer singer) {
                singers.add(singer);
            }
        }
        return singers;
    }

    public static ArrayList<Voter> createListVoter() {
        /** Creates a list of voters */
        ArrayList<Voter> voters = new ArrayList<>(); // empty list of voters
        for (User user : users) {
            if (user instanceof Voter voter) {
                // Cast the object to Singer type
                voters.add(voter);
            }
        }
        return voters;
    }

    public static void SortVotes(String option, ArrayList<Singer> singers){
        /** Sorts the votes in ascending or descending order, bubble sort */

        // Get the list of singers
        if (option.equals("ascending")) {
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
        } else if (option.equals("descending")) {
            // Sort votes in descending order using bubble sort
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
    }

    public static void partition(ArrayList<Voter> voters, int low, int high) {
        /** Partitions the list of voters by votes */
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        int pivot = voters.get(low + (high - low) / 2).getVotes();
        while (i <= j) { // Divide into two lists
            // If the current value from the left list is smaller than the pivot, vise versa
            while (voters.get(i).getVotes() < pivot) {
                i++;
            }
            while (voters.get(j).getVotes() > pivot) {
                j--;
            }
            if (i <= j) { // Swap elements to the correct side
                Voter temp = voters.get(i);
                voters.set(i, voters.get(j));
                voters.set(j, temp);
                i++;
                j--;
            }
        }
        if (low < j) // Recursion
            partition(voters, low, j);
        if (i < high)
            partition(voters, i, high);
    }

    public static void sortVotesGiven(String option, ArrayList<Voter> voters) {
        /** Sorts the votes in ascending or descending order, bubble sort */
        // Sort the list of voters by given votes using quick sort
        if (option.equals("ascending")) {
            partition(voters, 0, voters.size() - 1);
        } else if (option.equals("descending")) {
            partition(voters, 0, voters.size() - 1);
            Collections.reverse(voters);
        } else {
            System.out.println("Invalid option");
        }
    }

    public static void showSingers() {
        /** Prints the singers, sort by votes (ascending/descending) */
        // Get the list of singers
        ArrayList<Singer> singers = createListSingers();
        String option;
        System.out.print("Do you want the votes ordered in ascending or descending order? (a/d): ");
        option = scanner.nextLine();
        if (option.equals("a")) {
            // call the sort method
            SortVotes("ascending", singers);
        } else if (option.equals("d")) {
            // call the sort method
            SortVotes("descending", singers);
        } else {
            System.out.println("Invalid option");
        }
        // Print the singers
        for (Singer singer : singers) {
            System.out.println("\t" + singer.getUsername() + ", Votes: " + singer.getReceivedVotes());
        }
    }

    public static void showSongs() {
        /** Prints the songs */
        // Gets the list of singers
        ArrayList<Singer> singers = createListSingers();
        String option;
        System.out.print("Do you want the votes ordered in ascending or descending order? (a/d): ");
        option = scanner.nextLine();
        if (option.equals("a")) {
            // call the sort method
            SortVotes("ascending", singers);

        } else if (option.equals("d")) {
            // call the sort method
            SortVotes("descending", singers);
        } else {
            System.out.println("Invalid option");
        }
        // Sort songs in alphabetical order
        for (Singer singer : singers) {
            singer.sortSongsAlphabet("ascending");
        }
        for (Singer singer : singers) {
            System.out.println("\t" + singer.getArtisticName() + ", Songs: " + singer.showSongs() + " Total Votes: "
                    + singer.getReceivedVotes());
        }
    }

    public static void sortVotersAlphabetic(ArrayList<Voter> voters){
        /** sort voters in alphabetical order */
        for (int j = 0; j < voters.size() - 1; j++) {
            for (int i = 0; i < voters.size() - 1; i++) {
                // Compare the usernames by full alphanumerical value
                if (voters.get(i).getUsername().compareTo(voters.get(i + 1).getUsername()) > 0) {
                    Voter temp = voters.get(i);
                    voters.set(i, voters.get(i + 1));
                    voters.set(i + 1, temp);
                }
            }
        }
    }

    public static void showVoters() {
        /** Prints the voters, sort by votes (ascending/descending) */
        ArrayList<Voter> voters = createListVoter();
        String option;
        sortVotersAlphabetic(voters);

        // Print the singers in alphabetical order first
        for (Voter voter : voters)
            System.out.println("\t" + voter.getUsername() + ", Votes Given: " + voter.getVotes());
        System.out.println();

        System.out.print("Do you want the votes ordered in ascending or descending order? (a/d): ");
        option = scanner.nextLine();
        if (option.equals("a")) {
            // call the sort method (quick sort)
            sortVotesGiven("ascending", voters);
        } else if (option.equals("d")) {
            // call the sort method (quick sort)
            sortVotesGiven("descending", voters);
        } else {
            System.out.println("Invalid option");
        }
        // Print the singers after sorting
        for (Voter voter : voters)
            System.out.println("\t" + voter.getUsername() + ", Votes: " + voter.getVotes());
    }

    public static void showVotes() {
        /** Prints the votes for users songs */
        User singedInUser = getUserObjectById(signedInUserId, users);

        if (singedInUser instanceof Singer singer) {

            //ask for the order
            System.out.print("Do you want the votes ordered in ascending or descending order? (a/d): ");
            char option = scanner.nextLine().charAt(0);
            if (option == 'a') {
                // call the sort method
                singer.sortSongsAlphabet("ascending");
            } else if (option == 'd') {
                // call the sort method
                singer.sortSongsAlphabet("descending");
            } else {
                System.out.println("Invalid option");
            }
            System.out.println("Your songs: " + singer.showSongs());

        }
    }



    public static void addSongOrGenre() {
        /**
         * Adds a song or a genre
         * Shows the list
         */
        User singedInUser = getUserObjectById(signedInUserId, users);
        if (singedInUser instanceof Singer singer) {
            System.out.print("Do you want to add a song or a genre? (s/g): ");
            char option = scanner.nextLine().charAt(0);
            if (option == 's') {
                System.out.print("Enter the song name: ");
                String songName = scanner.nextLine();
                System.out.print("Enter the song votes: ");
                int songVotes = scanner.nextInt();
                Song song = new Song(songVotes, songName);
                singer.updateSongs(song);
                System.out.println("Song added!");
            } else if (option == 'g') {
                System.out.print("Enter the genre name: ");
                String genreName = scanner.nextLine();
                singer.updateGenres(genreName);
                System.out.println("Genre added successfully!");
            } else {
                System.out.println("Invalid option");
            }
        }
    }

    public static void voteSong() {
        /** Asks the user to vote for a song */
    
        boolean found = false;
        //print list of songs using the method showSongs
        ArrayList<Singer> singers = createListSingers();

        showSongs();
        //ask user to choose the song
        System.out.print("Choose the song to vote for: ");
        String song = scanner.nextLine();
        //ask the user to confirm the operation
        System.out.println("Are you sure you want to vote for this song? (y/n)");
        char option = scanner.nextLine().charAt(0);
        if (option == 'y') {
            //update the number of votes
            for (Singer singer : singers) {
                for (Song song1 : singer.getSongs()) {
                    if (song1.getSongName().equals(song)) {
                        song1.setVotes(song1.getVotes() + 1);
                         // update voters total votes
                        // singer.setReceivedVotes(singer.getReceivedVotes() + 1);
                        User userObjectById = getUserObjectById(signedInUserId, users);
                        if (userObjectById instanceof Voter voter) {
                            voter.setVotes(voter.getVotes() + 1);
                        };
                        found = true;
                        // song is found, break the loop
                        break;
                    }
                }
            }
        } else if (option == 'n') {
            //returns to the main menu
            return;
        } else {
            System.out.println("Invalid option");
        }

        // shows the list of songs with the updated number of votes
        if (!found){
            System.out.println("Song not found");
            return;
        }


        // prints the list of songs with the updated number of votes
        showSongs();
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
        // Prints the list of users with the username and the role
        for (User user : users) {
            System.out.println("Username: " + user.getUsername() + ", Role: " + user.getRole());
        }
    
        // Asks the administrator to select the user to update
        System.out.print("Select the user to update: ");
        String username = scanner.nextLine();
    
        // Finds the user to update
        User userToUpdate = getUserObjectById(getUserIdByUsername(username, users), users);

    
        if (userToUpdate == null) {
            System.out.println("User not found");
            return;
        }
    
        // Prompts for the new username and password
        System.out.print("Enter the new username: ");
        String newUsername = scanner.nextLine();
        System.out.print("Enter the new password: ");
        String newPassword = scanner.nextLine();
    
        // Prompts for the additional data based on the role of the user
        if (userToUpdate instanceof Singer singerToUpdate) {
            System.out.print("Enter the new artistic name: ");
            String newArtisticName = scanner.nextLine();
            System.out.print("Enter the new country: ");
            String newCountry = scanner.nextLine();
            System.out.print("Enter the new date of release: ");
            String newDateOfRelease = scanner.nextLine();
    
            // Updates the singer with the new data
            System.out.println("Are you sure you want to update the singer? (y/n)");
            char option = scanner.nextLine().charAt(0);
            if (option == 'y') {
                singerToUpdate.setUsername(newUsername);
                singerToUpdate.setPassword(newPassword);
                singerToUpdate.setArtisticName(newArtisticName);
                singerToUpdate.setCountry(newCountry);
                singerToUpdate.setDateOfRelease(newDateOfRelease);
                System.out.println("Singer updated successfully");
            } else if (option == 'n') {
                System.out.println("Update cancelled");
            } else {
                System.out.println("Invalid option");
            }
        } else if (userToUpdate instanceof Voter) {
            Voter voterToUpdate = (Voter) userToUpdate;
            System.out.print("Enter the new country: ");
            String newCountry = scanner.nextLine();
    
            // Updates the voter with the new data
            System.out.println("Are you sure you want to update the voter? (y/n)");
            char option = scanner.nextLine().charAt(0);
            if (option == 'y') {
                voterToUpdate.setUsername(newUsername);
                voterToUpdate.setPassword(newPassword);
                voterToUpdate.setCountry(newCountry);
                System.out.println("voter updated successfully");
            } else if (option == 'n') {
                System.out.println("Update cancelled");
            } else {
                System.out.println("Invalid option");
            }
        } else {
            // Updates the user with the new data
            System.out.println("Are you sure you want to update the user? (y/n)");
            char option = scanner.nextLine().charAt(0);
            if (option == 'y') {
                userToUpdate.setUsername(newUsername);
                userToUpdate.setPassword(newPassword);
                System.out.println("User updated successfully");
            } else if (option == 'n') {
                System.out.println("Update cancelled");
            } else {
                System.out.println("Invalid option");
            }
        }
    }

    public static void removeUser() {
        /** Lets you select the user and delete the user */
        String temp;
            System.out.print("Select the user to delete: ");
            temp = scanner.nextLine();

            User[] new_arr = new User[users.length - 1];
            boolean aux = false;

            for (int i = 0, k = 0; i < users.length - 1; i++, k++) {
                if (temp.equals(users[i].getUsername())) {
                    i++;
                }
                new_arr[k] = users[i];
            }
            // Check if the user was deleted
            for (int i = 0; i < users.length - 1; i++) {
                if (Arrays.equals(new_arr, users)) {
                    System.out.print("User was not able to be deleted");
                    aux = false;
                } else {
                    // If the user was deleted, it returns true
                    aux = true;
                }
            }

            if (aux) {
                users = new_arr;
                System.out.println("User deleted successfully");
            }
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
        System.out.print("How many songs will be entered? ");
        int songCount = Integer.parseInt(scanner.nextLine());
        ArrayList<Song> songs = new ArrayList<>();
        for (int i = 0 ;  i < songCount ; i++) {
            System.out.print("Enter a song title: ");
            String songTitle = scanner.nextLine();
            Song songObject = new Song(0, songTitle);
            songs.add(i, songObject);
        }

        System.out.print("Enter the genre(s): ");
        String genres = scanner.nextLine();
        // Split the genres by comma
        ArrayList<String> genresArray = new ArrayList<>(Arrays.asList(genres.split(",")));
        System.out.print("Enter the date of release of the last song: ");
        String dateOfRelease = scanner.nextLine();
        System.out.print("Enter the country: ");
        String country = scanner.nextLine();

        // Creates a new singer object
        users = append(users, new Singer(username, password,name, surname, id, "admin", artisticName, songs, genresArray, dateOfRelease, country));
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
        /** Return the original array with a new element appended to it
         * @param array
         * @param element
         * 
         * return array
          */
        T[] newArr = Arrays.copyOf(arr, arr.length + 1);
        newArr[newArr.length - 1] = element;
        return newArr;
    }

}
