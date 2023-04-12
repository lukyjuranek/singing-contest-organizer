package classes;

import java.util.ArrayList;

public class Singer extends User {
    private String artisticName;
    private ArrayList<Song> songs;
    private ArrayList<String> genres;
    private String country;
    private String dateOfRelease;
    private int receivedVotes;

    public Singer(String username, String password, String name, String surname, int id, String role, String artisticName, ArrayList<Song> songs, ArrayList<String> genres, String country, String dateOfRelease) {
        super(username, password, name, surname, id, role);
        this.artisticName = artisticName;
        this.songs = songs;
        this.genres = genres;
        this.country = country;
        this.dateOfRelease = dateOfRelease;
        this.receivedVotes = 0;
        // sum of all votes of all songs
        for (Song song : songs)
            this.receivedVotes += song.getVotes();
    }

    public void sortSongsAlphabet(String order){
        // sort songs in alphabetical order
        if (order.equals("ascending")) {
            for (int i = 0; i < songs.size(); i++) {
                for (int j = i + 1; j < songs.size(); j++) {
                    if (songs.get(i).getSongName().compareTo(songs.get(j).getSongName()) > 0) {
                        // swap the song objects by name
                        Song temp = songs.get(i);
                        songs.set(i, songs.get(j));
                        songs.set(j, temp);
                    }
                }
            }
        } else if (order.equals("descending")){
            for (int i = 0; i < songs.size(); i++) {
                for (int j = i + 1; j < songs.size(); j++) {
                    if (songs.get(i).getSongName().compareTo(songs.get(j).getSongName()) < 0) {
                        // swap the song objects by name
                        Song temp = songs.get(i);
                        songs.set(i, songs.get(j));
                        songs.set(j, temp);
                    }
                }
            }
        }
    }

    public String getArtisticName() {
        return artisticName;
    }

    public void setArtisticName(String artisticName) {
        this.artisticName = artisticName;
    }

    public ArrayList<Song> getSongTitles() {
        return songs;
    }
    public String showSongs() {
        StringBuilder toShow;
        toShow = new StringBuilder();
        for (Song song : songs)
            toShow.append(song.getSongName()).append("(").append(song.getVotes()).append(")").append(", ");
        toShow.delete(toShow.length() - 2, toShow.length()); // delete last ", "
        toShow.append(".");
        return toShow.toString();
    }

    public void setSongTitles(ArrayList<Song> songs) {
        this.songs = songs;
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

    // get songs
    public ArrayList<Song> getSongs(){
        return songs;
    }
}


