import java.util.ArrayList;

public class Playlist implements IComponent {

    public String playlistName;
    public ArrayList<IComponent> playlist = new ArrayList<IComponent>();

    public Playlist(String playlistName) {
        this.playlistName = playlistName;
    }

    // Your code goes here!
    public void play() {
        for (IComponent song : playlist) {
            song.play();
        }
    }

    public void setPlaybackSpeed(float speed) {
        // set speed
        for (IComponent song : playlist) {
            song.setPlaybackSpeed(speed);
        }
    }

    public String getName() {
        return playlistName;
    }

    public void add(IComponent song) {
        playlist.add(song);
    }

    public void remove(IComponent song) {
        playlist.remove(song);
    }

}