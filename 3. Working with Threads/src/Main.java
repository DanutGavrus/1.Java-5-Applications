import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static int[] inputData = {3, 30, 1, 3, 2, 4};
    public static int[] simulationOutData = new int[10];
    private static Random rand = new Random();
    public static List<Queue> queues;
    public static int peopleInQueues = 0;
    private static int maxPeopleInQueue = 0;
    private static int totalNrOfClients = 0;

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow(inputData);
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        queues = new ArrayList<Queue>(inputData[0]);
        for (int i = 0; i < inputData[0]; i++) {
            Queue queue = new Queue();
            queues.add(queue);
        }
        ExecutorService executor = Executors.newFixedThreadPool(inputData[0]);
        MyTimer simulationTimer = new MyTimer(0, 1000, inputData[1]);
        while (!simulationTimer.Stopped) {
            int timeBetweenClients = rand.nextInt(inputData[3] - inputData[2] + 1) + inputData[2];
            try {
                Thread.sleep(timeBetweenClients * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            peopleInQueues++;
            totalNrOfClients++;
            CheckForPeakHour(simulationTimer);
            MainWindow.UpdateRepresentation(null, 0, 2);
            int serviceTime = rand.nextInt(inputData[5] - inputData[4] + 1) + inputData[4];
            Runnable client = new Client(serviceTime);
            executor.execute(client);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        MainWindow.UpdateSimulationInterval(simulationOutData);
    }

    public static void CheckForPeakHour(MyTimer simulationTimer) {
        if (peopleInQueues > maxPeopleInQueue) {
            maxPeopleInQueue = peopleInQueues;
            simulationOutData[0] = simulationTimer.k;
        }
    }
}