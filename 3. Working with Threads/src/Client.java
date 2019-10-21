public class Client implements Runnable {
    private int serviceTime;

    public Client(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public void run() {
        processCommand();
    }

    private void processCommand() {
        if(Thread.currentThread().getName().charAt(14) == '1') { // queue 1
            Main.queues.get(0).addClient(1);
            Main.simulationOutData[2] += serviceTime;
        } else if (Thread.currentThread().getName().charAt(14) == '2') { // queue 2
            Main.queues.get(1).addClient(2);
            Main.simulationOutData[5] += serviceTime;
        } else if (Thread.currentThread().getName().charAt(14) == '3') { // queue 3
            Main.queues.get(2).addClient(3);
            Main.simulationOutData[8] += serviceTime;
        }
        try {
            Thread.sleep(serviceTime * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(Thread.currentThread().getName().charAt(14) == '1') { // queue 1
            Main.queues.get(0).removeClient(1);
        } else if (Thread.currentThread().getName().charAt(14) == '2') { // queue 2
            Main.queues.get(1).removeClient(2);
        } else if (Thread.currentThread().getName().charAt(14) == '3') { // queue 3
            Main.queues.get(2).removeClient(3);
        }
        Main.peopleInQueues--;
        MainWindow.UpdateRepresentation(null, 0, 2);
    }
}
