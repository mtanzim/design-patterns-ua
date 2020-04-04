import java.util.ArrayList;

public class Channel implements Subject {

    private ArrayList<Observer> observers = new ArrayList<Observer>();
    private String channelName;
    private String status;

    public Channel(String name, String initialStatus) {
        this.channelName = name;
        this.status = initialStatus;
    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
    }

    public String getStatus() {
        return this.status;
    }

    public void notifyObservers() {
        String template = "Channel %s update: %s";
        String update = String.format(template, this.channelName, this.status);
        for (Observer o : observers) {
            o.update(update);
        }

    }

}