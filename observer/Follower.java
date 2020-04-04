
public class Follower implements Observer {
    public String followerName;

    public Follower(String name) {
        this.followerName = name;
    }

    public void update(String update) {
        // notify followers
    }

    public void play() {
        // play content
    }

}