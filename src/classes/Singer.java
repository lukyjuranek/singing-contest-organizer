package classes;

import java.util.ArrayList;

public class Singer extends User {
    private String artisticName;
    private ArrayList<String> songTitles;
    private ArrayList<String> genres;
    private String country;
    private String dateOfRelease;
    private int receivedVotes;

    public Singer(String username, String password, String name, String surname, int id, String role, String artisticName, ArrayList<String> songTitles, ArrayList<String> genres, String country, String dateOfRelease, int receivedVotes) {
        super(username, password, name, surname, id, role);
        this.artisticName = artisticName;
        this.songTitles = songTitles;
        this.genres = genres;
        this.country = country;
        this.dateOfRelease = dateOfRelease;
        this.receivedVotes = receivedVotes;
    }

    public String getArtisticName() {
        return artisticName;
    }

    public void setArtisticName(String artisticName) {
        this.artisticName = artisticName;
    }

    public ArrayList<String> getSongTitles() {
        return songTitles;
    }

    public void setSongTitles(ArrayList<String> songTitles) {
        this.songTitles = songTitles;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(String dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public int getReceivedVotes() {
        return receivedVotes;
    }

    public void setReceivedVotes(int receivedVotes) {
        this.receivedVotes = receivedVotes;
    }
}
