package classes;

public class Song {
    private int votes;
    private String songName;
    public Song(int votes, String songName){
        this.votes = votes;
        this.songName = songName;
    }
    public int getVotes() {return votes;}
    public String getSongName(){return songName;}


}
