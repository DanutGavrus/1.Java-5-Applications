import java.util.*;

public class MyTimer {
    public int k = 0;
    private Timer timer;
    public boolean Stopped;

    public MyTimer(int delay, int period, final int duration) {
        // creating timer task, timer
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                k++;
                if (k == duration) {
                    Stopped = true;
                    k = 0;
                    timer.cancel();
                }
                if (Main.peopleInQueues > 3) {
                    if (Main.queues.get(0).isEmpty() == 0) Main.simulationOutData[1]++;
                    if (Main.queues.get(1).isEmpty() == 0) Main.simulationOutData[4]++;
                    if (Main.queues.get(2).isEmpty() == 0) Main.simulationOutData[7]++;
                }
                if (Main.queues.get(0).isEmpty() == 1) Main.simulationOutData[3]++;
                if (Main.queues.get(1).isEmpty() == 1) Main.simulationOutData[6]++;
                if (Main.queues.get(2).isEmpty() == 1) Main.simulationOutData[9]++;
            }
        };
        timer = new Timer();

        // scheduling the task at interval
        timer.schedule(task, delay, period);
    }
}
