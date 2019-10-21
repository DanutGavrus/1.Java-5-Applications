import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

import static javax.swing.JOptionPane.getRootFrame;
import static javax.swing.JOptionPane.showMessageDialog;

public class Functions {
    private static PrintWriter printWriter;
    public static boolean fileMissing;

    // Receives the txt file and returns an array of type String representing each line
    public static ArrayList<String> getInputLines(String fileName) {
        ArrayList<String> lines = new ArrayList();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(e -> lines.add(e));
        } catch (IOException e) {
            fileMissing = true;
            showMessageDialog(getRootFrame(), "ERROR: THE FILE ACTIVITIES.txt IS MISSING ! Make sure to put it where the application was started and try again.");
            e.printStackTrace();
        }
        return lines;
    }

    // Receives each line of the input and returns a list of MonitoredData
    public static ArrayList<MonitoredData> getMonitoredDataList(ArrayList<String> lines) {
        ArrayList<MonitoredData> monitoredDataList = new ArrayList();
        lines.stream().forEach(e -> {
            String[] parts = e.split("\t\t");
            String startTime = parts[0];
            String endTime = parts[1];
            String activity = parts[2].replace("\t", "");
            MonitoredData monitoredData = new MonitoredData(startTime, endTime, activity);
            monitoredDataList.add(monitoredData);
        });
        return monitoredDataList;
    }

    // Receives the list of MonitoredData and returns a list of actions
    public static ArrayList<String> getActionsList(ArrayList<MonitoredData> monitoredDataList) {
        ArrayList<String> actionsList = new ArrayList<>();
        monitoredDataList.stream().forEach(e -> {
            if (!actionsList.contains(e.getActivity()))
                actionsList.add(e.getActivity());
        });
        return actionsList;
    }

    // Receives an integer and creates a txt file with it`s info
    public static void createTask1File(int nrOfDiffDays) {
        try {
            printWriter = new PrintWriter("task1.txt", "UTF-8");
            printWriter.println("The number of different days is: " + nrOfDiffDays);
            printWriter.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    // Receives a HashMap<String, Integer> and creates a txt file with it`s info
    public static void createTask2File(HashMap<String, Integer> diffActions) {
        try {
            printWriter = new PrintWriter("task2.txt", "UTF-8");
            printWriter.println("The map of type <String, Integer> that maps to each distinct action the number of occurrences:\n");
            diffActions.entrySet().stream().forEach(e -> printWriter.println(e.getKey() + ": " + e.getValue()));
            printWriter.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    // Receives a HashMap<Integer, HashMap<String, Integer>> and creates a txt file with it`s info
    public static void createTask3File(HashMap<Integer, HashMap<String, Integer>> diffActionsEachDay) {
        try {
            printWriter = new PrintWriter("task3.txt", "UTF-8");
            printWriter.println("The data structure of type Map<Integer, Map<String, Integer>> that contains the activity count for each day of the log:\n");
            diffActionsEachDay.entrySet().stream().forEach(e -> {
                printWriter.println("Day " + e.getKey() + ": ");
                HashMap<String, Integer> aux = e.getValue();
                aux.entrySet().stream().forEach(e2 -> printWriter.println(e2.getKey() + ": " + e2.getValue()));
            });
            printWriter.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    // Receives a HashMap<String, String> and creates a txt file with it`s info
    public static void createTask4File(HashMap<String, String> actionsDuration) {
        try {
            printWriter = new PrintWriter("task4.txt", "UTF-8");
            printWriter.println("The data structure of type Map<String, DateTime> that maps for each activity the total duration computed over the monitoring period, >10 hours are filtered out:\n");
            actionsDuration.entrySet().stream().forEach(e -> printWriter.println(e.getKey() + ": " + e.getValue()));
            printWriter.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    // Receives an ArrayList<String> and creates a txt file with it`s info
    public static void createTask5File(ArrayList<String> actionsLessThan5) {
        try {
            printWriter = new PrintWriter("task5.txt", "UTF-8");
            printWriter.println("The filtered activities that have 90% of the monitoring samples with duration less than 5 minutes:\n");
            actionsLessThan5.stream().forEach(e -> printWriter.println(e));
            printWriter.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
