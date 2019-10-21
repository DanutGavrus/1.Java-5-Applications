import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import static javax.swing.JOptionPane.getRootFrame;
import static javax.swing.JOptionPane.showMessageDialog;

public class MainWindow extends JFrame {
    private static JPanel InputDataPanel = new JPanel();
    private static JPanel SimIntPanel = new JPanel();
    private static JPanel QEvolPanel = new JPanel();
    public static int counter1 = 0, counter2 = 0, counter3 = 0;
    public static JTextField totalNrOfPeopleJT;

    private static JTextField PeakHourTF = new JTextField();
    private static JTextField Q1AvgWaitTimeTF = new JTextField();
    private static JTextField Q1ServiceTimeTF = new JTextField();
    private static JTextField Q1EmptyTimeTF = new JTextField();
    private static JTextField Q2AvgWaitTimeTF = new JTextField();
    private static JTextField Q2ServiceTimeTF = new JTextField();
    private static JTextField Q2EmptyTimeTF = new JTextField();
    private static JTextField Q3AvgWaitTimeTF = new JTextField();
    private static JTextField Q3ServiceTimeTF = new JTextField();
    private static JTextField Q3EmptyTimeTF = new JTextField();

    public MainWindow(final int[] inputData) {
        InputDataPanel.setBounds(40, 40, 400, 600);
        SimIntPanel.setBounds(480, 40, 400, 600);
        QEvolPanel.setBounds(920, 40, 400, 600);

        InputDataPanel.setLayout(null);
        SimIntPanel.setLayout(null);
        QEvolPanel.setLayout(null);

        // InputDataPanel Labels and TextFields:
        JLabel InputDataLabel = new JLabel ("Input Data");
        JLabel NrOfQueues = new JLabel ("Number of Queues: ");
        JLabel SimulationInterval = new JLabel ("Simulation Interval: ");
        JLabel ArrTimeBetwCustomers = new JLabel ("Arriving Time Between Customers ");
        JLabel ArrTimeMin = new JLabel ("Min time: ");
        JLabel ArrTimeMax = new JLabel ("Max time: ");
        JLabel ServiceTime = new JLabel ("Time Staying in Queue ");
        JLabel ServiceTimeMin = new JLabel ("Min time: ");
        JLabel ServiceTimeMax = new JLabel ("Max time: ");

        InputDataLabel.setBounds(100, 40, 400, 20);
        NrOfQueues.setBounds(40, 110, 120, 20);
        SimulationInterval.setBounds(40, 150, 120, 20);
        ArrTimeBetwCustomers.setBounds(40, 210, 400, 20);
        ArrTimeMin.setBounds(40, 230, 80, 20);
        ArrTimeMax.setBounds(40, 270, 80, 20);
        ServiceTime.setBounds(40,320, 400, 20);
        ServiceTimeMin.setBounds(40, 340, 80, 20);
        ServiceTimeMax.setBounds(40, 380, 80, 20);

        InputDataPanel.add(InputDataLabel);
        InputDataPanel.add(NrOfQueues);
        InputDataPanel.add(SimulationInterval);
        InputDataPanel.add(ArrTimeBetwCustomers);
        InputDataPanel.add(ArrTimeMin);
        InputDataPanel.add(ArrTimeMax);
        InputDataPanel.add(ServiceTime);
        InputDataPanel.add(ServiceTimeMin);
        InputDataPanel.add(ServiceTimeMax);

        final JTextField NrOfQueuesJT = new JTextField("3");
        final JTextField SimIntJT = new JTextField("30");
        final JTextField ArrTimeMinJT = new JTextField("1");
        final JTextField ArrTimeMaxJT = new JTextField("3");
        final JTextField ServTimeMinJT = new JTextField("2");
        final JTextField ServTimeMaxJT = new JTextField("4");

        NrOfQueuesJT.setBounds(160, 110, 100, 20);
        SimIntJT.setBounds(160, 150, 100, 20);
        ArrTimeMinJT.setBounds(100, 230, 100, 20);
        ArrTimeMaxJT.setBounds(100, 270, 100, 20);
        ServTimeMinJT.setBounds(100, 340, 100, 20);
        ServTimeMaxJT.setBounds(100, 380, 100, 20);

        InputDataPanel.add(NrOfQueuesJT);
        InputDataPanel.add(SimIntJT);
        InputDataPanel.add(ArrTimeMinJT);
        InputDataPanel.add(ArrTimeMaxJT);
        InputDataPanel.add(ServTimeMinJT);
        InputDataPanel.add(ServTimeMaxJT);

        // Focus Listeners for text fields
        NrOfQueuesJT.setForeground(Color.GRAY);
        NrOfQueuesJT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (NrOfQueuesJT.getText().equals("3")) {
                    NrOfQueuesJT.setText("");
                    NrOfQueuesJT.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (NrOfQueuesJT.getText().isEmpty()) {
                    NrOfQueuesJT.setForeground(Color.GRAY);
                    NrOfQueuesJT.setText("3");
                }
            }
        });
        SimIntJT.setForeground(Color.GRAY);
        SimIntJT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (SimIntJT.getText().equals("30")) {
                    SimIntJT.setText("");
                    SimIntJT.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (SimIntJT.getText().isEmpty()) {
                    SimIntJT.setForeground(Color.GRAY);
                    SimIntJT.setText("30");
                }
            }
        });
        ArrTimeMinJT.setForeground(Color.GRAY);
        ArrTimeMinJT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ArrTimeMinJT.getText().equals("1")) {
                    ArrTimeMinJT.setText("");
                    ArrTimeMinJT.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (ArrTimeMinJT.getText().isEmpty()) {
                    ArrTimeMinJT.setForeground(Color.GRAY);
                    ArrTimeMinJT.setText("1");
                }
            }
        });
        ArrTimeMaxJT.setForeground(Color.GRAY);
        ArrTimeMaxJT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ArrTimeMaxJT.getText().equals("3")) {
                    ArrTimeMaxJT.setText("");
                    ArrTimeMaxJT.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (ArrTimeMaxJT.getText().isEmpty()) {
                    ArrTimeMaxJT.setForeground(Color.GRAY);
                    ArrTimeMaxJT.setText("3");
                }
            }
        });
        ServTimeMinJT.setForeground(Color.GRAY);
        ServTimeMinJT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ServTimeMinJT.getText().equals("2")) {
                    ServTimeMinJT.setText("");
                    ServTimeMinJT.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (ServTimeMinJT.getText().isEmpty()) {
                    ServTimeMinJT.setForeground(Color.GRAY);
                    ServTimeMinJT.setText("2");
                }
            }
        });
        ServTimeMaxJT.setForeground(Color.GRAY);
        ServTimeMaxJT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ServTimeMaxJT.getText().equals("4")) {
                    ServTimeMaxJT.setText("");
                    ServTimeMaxJT.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (ServTimeMaxJT.getText().isEmpty()) {
                    ServTimeMaxJT.setForeground(Color.GRAY);
                    ServTimeMaxJT.setText("4");
                }
            }
        });

        // SimulationIntervalPanel Labels and TextFields:
        JLabel SimIntLabel = new JLabel ("Output for the simulation interval: ");
        JLabel PeakHourLabel = new JLabel ("Peak Hour: ");
        JLabel Q1Label = new JLabel ("Q1 ");
        JLabel Q1AvgWaitTime = new JLabel ("Average waiting time: ");
        JLabel Q1ServiceTime = new JLabel ("Service time: ");
        JLabel Q1EmptyQueueTime = new JLabel ("Empty queue time: ");
        JLabel Q2Label = new JLabel ("Q2 ");
        JLabel Q2AvgWaitTime = new JLabel ("Average waiting time: ");
        JLabel Q2ServiceTime = new JLabel ("Service time: ");
        JLabel Q2EmptyQueueTime = new JLabel ("Empty queue time: ");
        JLabel Q3Label = new JLabel ("Q3 ");
        JLabel Q3AvgWaitTime = new JLabel ("Average waiting time: ");
        JLabel Q3ServiceTime = new JLabel ("Service time: ");
        JLabel Q3EmptyQueueTime = new JLabel ("Empty queue time: ");

        SimIntLabel.setBounds(100, 40, 200, 20);
        PeakHourLabel.setBounds(40, 100, 100, 20);
        Q1Label.setBounds(40, 140, 60, 20);
        Q1AvgWaitTime.setBounds(40, 170, 140, 20);
        Q1ServiceTime.setBounds(40, 200, 140, 20);
        Q1EmptyQueueTime.setBounds(40, 230, 140, 20);
        Q2Label.setBounds(40, 270, 60, 20);
        Q2AvgWaitTime.setBounds(40, 300, 140, 20);
        Q2ServiceTime.setBounds(40, 330, 140, 20);
        Q2EmptyQueueTime.setBounds(40, 360, 140, 20);
        Q3Label.setBounds(40, 400, 60, 20);
        Q3AvgWaitTime.setBounds(40, 430, 140, 20);
        Q3ServiceTime.setBounds(40, 460, 140, 20);
        Q3EmptyQueueTime.setBounds(40, 490, 140, 20);

        SimIntPanel.add(SimIntLabel);
        SimIntPanel.add(PeakHourLabel);
        SimIntPanel.add(Q1Label);
        SimIntPanel.add(Q1AvgWaitTime);
        SimIntPanel.add(Q1ServiceTime);
        SimIntPanel.add(Q1EmptyQueueTime);
        SimIntPanel.add(Q2Label);
        SimIntPanel.add(Q2AvgWaitTime);
        SimIntPanel.add(Q2ServiceTime);
        SimIntPanel.add(Q2EmptyQueueTime);
        SimIntPanel.add(Q3Label);
        SimIntPanel.add(Q3AvgWaitTime);
        SimIntPanel.add(Q3ServiceTime);
        SimIntPanel.add(Q3EmptyQueueTime);

        PeakHourTF = new JTextField();
        Q1AvgWaitTimeTF = new JTextField();
        Q1ServiceTimeTF = new JTextField();
        Q1EmptyTimeTF = new JTextField();
        Q2AvgWaitTimeTF = new JTextField();
        Q2ServiceTimeTF = new JTextField();
        Q2EmptyTimeTF = new JTextField();
        Q3AvgWaitTimeTF = new JTextField();
        Q3ServiceTimeTF = new JTextField();
        Q3EmptyTimeTF = new JTextField();

        PeakHourTF.setBounds(180, 100, 100, 20);
        Q1AvgWaitTimeTF.setBounds(180, 170, 100, 20);
        Q1ServiceTimeTF.setBounds(180, 200, 100, 20);
        Q1EmptyTimeTF.setBounds(180, 230, 100, 20);
        Q2AvgWaitTimeTF.setBounds(180, 300, 100, 20);
        Q2ServiceTimeTF.setBounds(180, 330, 100, 20);
        Q2EmptyTimeTF.setBounds(180, 360, 100, 20);
        Q3AvgWaitTimeTF.setBounds(180, 430, 100, 20);
        Q3ServiceTimeTF.setBounds(180, 460, 100, 20);
        Q3EmptyTimeTF.setBounds(180, 490, 100, 20);

        PeakHourTF.setEditable(false);
        Q1AvgWaitTimeTF.setEditable(false);
        Q1ServiceTimeTF.setEditable(false);
        Q1EmptyTimeTF.setEditable(false);
        Q2AvgWaitTimeTF.setEditable(false);
        Q2ServiceTimeTF.setEditable(false);
        Q2EmptyTimeTF.setEditable(false);
        Q3AvgWaitTimeTF.setEditable(false);
        Q3ServiceTimeTF.setEditable(false);
        Q3EmptyTimeTF.setEditable(false);

        SimIntPanel.add(PeakHourTF);
        SimIntPanel.add(Q1AvgWaitTimeTF);
        SimIntPanel.add(Q1ServiceTimeTF);
        SimIntPanel.add(Q1EmptyTimeTF);
        SimIntPanel.add(Q2AvgWaitTimeTF);
        SimIntPanel.add(Q2ServiceTimeTF);
        SimIntPanel.add(Q2EmptyTimeTF);
        SimIntPanel.add(Q3AvgWaitTimeTF);
        SimIntPanel.add(Q3ServiceTimeTF);
        SimIntPanel.add(Q3EmptyTimeTF);

        // QEvolPanel Labels and TextFields:
        JLabel QEvolLabel = new JLabel ("Queues Evolution: ");
        JLabel Q1EvolLabel = new JLabel("Q1");
        JLabel Q2EvolLabel = new JLabel("Q2");
        JLabel Q3EvolLabel = new JLabel ("Q3");


        QEvolLabel.setBounds(140, 40, 200, 20);
        Q1EvolLabel.setBounds(60, 100, 40, 20);
        Q2EvolLabel.setBounds(180, 100, 40, 20);
        Q3EvolLabel.setBounds(300, 100, 40, 20);


        QEvolPanel.add(QEvolLabel);
        QEvolPanel.add(Q1EvolLabel);
        QEvolPanel.add(Q2EvolLabel);
        QEvolPanel.add(Q3EvolLabel);

        // Properties of the UI
        setLayout(null);
        setSize(1320, 720);
        setTitle("Working with Threads");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(InputDataPanel);
        add(SimIntPanel);
        add(QEvolPanel);

        JLabel totalNrOfPeopleJL = new JLabel ("Total number of people: ");
        totalNrOfPeopleJT = new JTextField();
        totalNrOfPeopleJT.setEditable(false);
        totalNrOfPeopleJT.setText("" + Main.peopleInQueues);
        totalNrOfPeopleJL.setBounds(60, 180, 200, 20);
        totalNrOfPeopleJT.setBounds(200, 180, 60, 20);
        QEvolPanel.add(totalNrOfPeopleJL);
        QEvolPanel.add(totalNrOfPeopleJT);

        JButton start = new JButton("Save Input.");
        start.setBounds(40, 430, 160, 80);
        InputDataPanel.add(start);
        start.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                inputData[0] = Integer.parseInt(NrOfQueuesJT.getText());
                inputData[1] = Integer.parseInt(SimIntJT.getText());
                inputData[2] = Integer.parseInt(ArrTimeMinJT.getText());
                inputData[3] = Integer.parseInt(ArrTimeMaxJT.getText());
                inputData[4] = Integer.parseInt(ServTimeMinJT.getText());
                inputData[5] = Integer.parseInt(ServTimeMaxJT.getText());
            }
        });
        repaint();
        showMessageDialog(getRootFrame(), "Feel free to change any input data and press 'Save Input' before the simulation starts!");
        showMessageDialog(getRootFrame(), "Simulations auto starts in 3 seconds after pressing OK!");
        SimIntLabel.requestFocusInWindow();
    }

    public static void UpdateRepresentation(ArrayList<JLabel> clients, int index, int function) {
        if (function == 0 ) { // add
            if (index == 1) {
                clients.get(0).setBounds(64, 140 + 40 * counter1, 40, 20);
                counter1++;
            }
            else if (index == 2)  {
                clients.get(0).setBounds(194, 140 + 40 * counter2, 40, 20);
                counter2++;
            }
            else {
                clients.get(0).setBounds(304, 140 + 40 * counter3, 40, 20);
                counter3++;
            }
            clients.get(0).setText(("*"));
            QEvolPanel.add(clients.get(0));
            QEvolPanel.repaint();
        }
        if (function == 1) { // remove
            if (index == 1) {
                counter1--;
            }
            if (index == 2) {
                counter2--;
            }
            if (index == 3) {
                counter3--;
            }
            clients.get(0).setText("");
            QEvolPanel.repaint();
        }
        if (function == 2) { // queue status
            totalNrOfPeopleJT.setText(" ");
            totalNrOfPeopleJT.setText(" " + Main.peopleInQueues);
            QEvolPanel.repaint();
        }
    }

    public static void UpdateSimulationInterval(int[] simulationOutData) {
        float[] qAvgWaitTime = new float[3];
        PeakHourTF.setText(" " + simulationOutData[0]);
        qAvgWaitTime[0] = (float)simulationOutData[3] / 3;
        qAvgWaitTime[1] = (float)simulationOutData[6] / 2;
        qAvgWaitTime[2] = (float)simulationOutData[9] / 3;
        Q1AvgWaitTimeTF.setText(" " + qAvgWaitTime[0]);
        Q2AvgWaitTimeTF.setText(" " + qAvgWaitTime[1]);
        Q3AvgWaitTimeTF.setText(" " + qAvgWaitTime[2]);
        Q1EmptyTimeTF.setText(" " + simulationOutData[3]);
        Q2EmptyTimeTF.setText(" " + simulationOutData[6]);
        Q3EmptyTimeTF.setText(" " + simulationOutData[9]);
        Q1ServiceTimeTF.setText(" " + simulationOutData[2]);
        Q2ServiceTimeTF.setText(" " + simulationOutData[5]);
        Q3ServiceTimeTF.setText(" " + simulationOutData[8]);
        SimIntPanel.repaint();
    }
}