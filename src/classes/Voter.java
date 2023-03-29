package classes;

import java.util.ArrayList;

public class Voter extends User {
    private ArrayList<String> favouriteSingers;
    private ArrayList<String> favouriteSongs;
    private int votes;
    private String country;

    public Voter(String username, String password, String name, String surname, int id, String role, ArrayList<String> favouriteSingers, ArrayList<String> favouriteSongs, int votes, String country) {
        super(username, password, name, surname, id, role);
        this.favouriteSingers = favouriteSingers;
        this.favouriteSongs = favouriteSongs;
        this.votes = votes;
        this.country = country;
    }

    public ArrayList<String> getFavouriteSingers() {
        return favouriteSingers;
    }

    public void setFavouriteSingers(ArrayList<String> favouriteSingers) {
        this.favouriteSingers = favouriteSingers;
    }

    public ArrayList<String> getFavouriteSongs() {
        return favouriteSongs;
    }

    public void setFavouriteSongs(ArrayList<String> favouriteSongs) {
        this.favouriteSongs = favouriteSongs;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
