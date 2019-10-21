import javax.swing.*;
import java.util.ArrayList;

public class Queue{
    private ArrayList<JLabel> clients;
    private int timeBetweenClients;

    public Queue() {
        clients = new ArrayList<JLabel>();
        timeBetweenClients = 0;
    }

    public void addClient(int index) {
        clients.add(new JLabel("*"));
        MainWindow.UpdateRepresentation(clients, index, 0);
    }

    public void removeClient(int index) {
        MainWindow.UpdateRepresentation(clients, index, 1);
    }

    public int isEmpty() {
        if (clients.size() == 0) return 1;
        else return 0;
    }

    public void setTimeBetweenClients(int timeBetweenClients) {
        this.timeBetweenClients = timeBetweenClients;
    }

    public int getTimeBetweenClients() {
        return timeBetweenClients;
    }

    public ArrayList<JLabel> getClients() {
        return clients;
    }

    public void setClients(ArrayList<JLabel> clients) {
        this.clients = clients;
    }
}
