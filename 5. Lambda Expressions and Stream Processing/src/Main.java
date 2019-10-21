import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import static javax.swing.JOptionPane.getRootFrame;
import static javax.swing.JOptionPane.showMessageDialog;

interface CreateString {
    String processString(String str1, String str2, String str3);
}

public class Main {
    private static ArrayList<MonitoredData> monitoredDataList;

    private static ArrayList<Integer> diffDays;
    private static ArrayList<String> actionsList;
    private static DateTimeFormatter dateTimeFormatter;
    private static LocalDateTime localDateTime;

    private static int task2Count;
    private static int task3Count;
    private static long task4Seconds;
    private static int task5Samples, task5SamplesLessThan5;

    public static void main(String[] args) {
        String fileName = "Activities.txt";
        ArrayList<String> lines = Functions.getInputLines(fileName);
        monitoredDataList = Functions.getMonitoredDataList(lines);
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (!Functions.fileMissing) {
            showMessageDialog(getRootFrame(), "This application completes 5 tasks based on the content of the file 'Activities.txt' using Lambda Expressions and Stream Processing:\n\n" +
                    "-1)Which is the number of different days that appear in the monitored data ?\n" +
                    "-2)Create a map of type <String, Integer> that maps to each distinct action the number of occurrences;\n" +
                    "-3)Create a data structure Map<Integer, Map<String, Integer>> that contains the activity count for each day of the log;\n" +
                    "-4)Create a data structure Map<String, DateTime> that maps for each activity the total duration computed over the monitoring period. Filter out if >10 hours;\n" +
                    "-5)Filter the activities that have 90% of the monitoring samples with duration less than 5 minutes.");
            doTask1();
            doTask2();
            doTask3();
            doTask4();
            doTask5();
            showMessageDialog(getRootFrame(),"The answers are placed in 5 .txt files created where the application was started.");
        }
    }

    private static void doTask1() {
        diffDays  = new ArrayList<>();
        int nrOfDiffDays;
        monitoredDataList.stream().forEach(e -> {
            localDateTime = LocalDateTime.parse(e.getStartTime(), dateTimeFormatter);
            if (!diffDays.contains(localDateTime.getDayOfMonth()))
                diffDays.add(localDateTime.getDayOfMonth());
        });
        if (diffDays.size() == 1) nrOfDiffDays = 0;
        else nrOfDiffDays = diffDays.size();
        Functions.createTask1File(nrOfDiffDays);
    }

    private static void doTask2() {
        HashMap<String, Integer> diffActions = new HashMap<>();
        actionsList = Functions.getActionsList(monitoredDataList);
        actionsList.stream().forEach(e -> {
            task2Count = 0;
            monitoredDataList.stream().forEach(e2 -> { if (e2.getActivity().equals(e)) task2Count++; });
            diffActions.put(e, task2Count);
        });
        Functions.createTask2File(diffActions);
    }

    private static void doTask3() {
        HashMap<Integer, HashMap<String, Integer>> diffActionsEachDay = new HashMap<>();
        diffDays.stream().forEach(e -> {
            HashMap<String, Integer> diffActions = new HashMap<>();
            actionsList.stream().forEach(e2 -> {
                task3Count = 0;
                monitoredDataList.stream().forEach(e3 -> {
                    localDateTime = LocalDateTime.parse(e3.getStartTime(), dateTimeFormatter);
                    if (localDateTime.getDayOfMonth() == e && e3.getActivity().equals(e2)) task3Count ++;
                });
                diffActions.put(e2, task3Count);
            });
            diffActionsEachDay.put(e, diffActions);
        });
        Functions.createTask3File(diffActionsEachDay);
    }

    private static void doTask4() {
        HashMap<String, String> actionsDuration = new HashMap<>();
        actionsList.stream().forEach(e -> {
            task4Seconds = 0;
            CreateString actionDuration = (str1, str2, str3) -> str1 + " hours " + str2 + " minutes and " + str3 + " seconds.";
            monitoredDataList.stream().forEach(e2 -> {
                Duration duration;
                if (e.equals(e2.getActivity())) {
                    LocalDateTime localDateTimeAux = LocalDateTime.parse(e2.getEndTime(), dateTimeFormatter);
                    localDateTime = LocalDateTime.parse(e2.getStartTime(), dateTimeFormatter);
                    duration = Duration.between(localDateTime, localDateTimeAux);
                    task4Seconds += duration.getSeconds();
                }
            });
            if (task4Seconds / 3600 <= 10) {
                String str1, str2, str3;
                str1 = "" + task4Seconds / 3600;
                task4Seconds = task4Seconds % 3600;
                str2 = "" + task4Seconds / 60;
                task4Seconds = task4Seconds % 60;
                str3 = "" + task4Seconds;
                actionsDuration.put(e, actionDuration.processString(str1, str2, str3));
            }
        });
        Functions.createTask4File(actionsDuration);
    }

    private static void doTask5() {
        ArrayList<String> actionsLessThan5 = new ArrayList<>();
        actionsList.stream().forEach(e -> {
            task5Samples = 0;
            task5SamplesLessThan5 = 0;
            monitoredDataList.stream().forEach( e2 -> {
                Duration duration;
                if (e.equals(e2.getActivity())) {
                    LocalDateTime localDateTimeAux = LocalDateTime.parse(e2.getEndTime(), dateTimeFormatter);
                    task5Samples++;
                    localDateTime = LocalDateTime.parse(e2.getStartTime(), dateTimeFormatter);
                    duration = Duration.between(localDateTime, localDateTimeAux);
                    if (duration.getSeconds() / 60 < 5) {
                        task5SamplesLessThan5++;
                    }
                }
            });
            float x = (float)task5SamplesLessThan5 / task5Samples;
            if (x >= 0.9) actionsLessThan5.add(e);
        });
        Functions.createTask5File(actionsLessThan5);
    }
}
