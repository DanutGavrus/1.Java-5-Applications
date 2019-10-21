import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainWindow extends JFrame {
    private Polinom polinom1 = new Polinom();
    private Polinom polinom2 = new Polinom();
    private Polinom result = new Polinom();
    private String t = "";

    public MainWindow() {
        JLabel jlabel1 = new JLabel("Polynomial1:");
        JTextField jTextField1 = new JTextField("3x^2 +2x + 1");
        JLabel jlabel2 = new JLabel("Polynomial2:");
        JTextField jTextField2 = new JTextField("4x^2-1");
        JLabel jlabel3 = new JLabel("Result:");
        JTextField jTextField3 = new JTextField();

        jlabel1.setBounds(250, 40, 80, 20);
        jTextField1.setBounds(329, 40, 280, 20);
        jTextField1.setForeground(Color.GRAY);
        jTextField1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextField1.getText().equals("3x^2 +2x + 1")) {
                    jTextField1.setText("");
                    jTextField1.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (jTextField1.getText().isEmpty()) {
                    jTextField1.setForeground(Color.GRAY);
                    jTextField1.setText("3x^2 +2x + 1");
                }
            }
        });
        jlabel2.setBounds(250, 80, 80, 20);
        jTextField2.setBounds(329, 80, 280, 20);
        jTextField2.setForeground(Color.GRAY);
        jTextField2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextField2.getText().equals("4x^2-1")) {
                    jTextField2.setText("");
                    jTextField2.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (jTextField2.getText().isEmpty()) {
                    jTextField2.setForeground(Color.GRAY);
                    jTextField2.setText("4x^2-1");
                }
            }
        });
        jlabel3.setBounds(250, 120, 80, 20);
        jTextField3.setBounds(329, 120, 280, 20);
        jTextField3.setEditable(false);

        JButton add = new JButton("+");
        add.setBounds(250, 180, 60, 60);
        add.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                UpdatePolinom1(jTextField1);
                UpdatePolinom2(jTextField2);
                result = Operations.add2Polinoms(polinom1, polinom2);
                UpdateResultJTextField(jTextField3);
            }
        });

        JButton substract = new JButton("-");
        substract.setBounds(350, 180, 60, 60);
        substract.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                UpdatePolinom1(jTextField1);
                UpdatePolinom2(jTextField2);
                result = Operations.substract2Polinoms(polinom1, polinom2);
                UpdateResultJTextField(jTextField3);

            }
        });

        JButton multiply = new JButton("*");
        multiply.setBounds(450, 180, 60, 60);
        multiply.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                UpdatePolinom1(jTextField1);
                UpdatePolinom2(jTextField2);
                result = Operations.multiply2Polinoms(polinom1, polinom2);
                UpdateResultJTextField(jTextField3);
            }
        });

        JButton divide = new JButton("/");
        divide.setBounds(550, 180, 60, 60);
        divide.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                UpdatePolinom1(jTextField1);
                UpdatePolinom2(jTextField2);
                Polinom[] results = new Polinom[2];
                results = Operations.divide2Polinoms(polinom1, polinom2);
                if (results == null) {
                    t = "Division with 0 FORBIDDEN !";
                    jTextField3.setText(t);
                }
                else {
                    result = results[0];
                    UpdateResultJTextField(jTextField3);
                    String aux = t;
                    result = results[1];
                    UpdateResultJTextField(jTextField3);
                    t = aux + " remainder " + t;
                    jTextField3.setText(t);
                }
            }
        });

        JButton integrate = new JButton("Integrate Pol1");
        integrate.setBounds(250, 280, 160, 60);
        integrate.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                UpdatePolinom1(jTextField1);
                UpdatePolinom2(jTextField2);
                result = Operations.integratePolinom1(polinom1);
                UpdateResultJTextField(jTextField3);
            }
        });

        JButton derivate = new JButton("Derivate Pol1");
        derivate.setBounds(450, 280, 160, 60);
        derivate.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                UpdatePolinom1(jTextField1);
                UpdatePolinom2(jTextField2);
                result = Operations.derivatePolinom1(polinom1);
                UpdateResultJTextField(jTextField3);
            }
        });

        setLayout(null);
        setSize(858, 480);
        setTitle("Polynomial Operations");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(jlabel1);
        add(jTextField1);
        add(jlabel2);
        add(jTextField2);
        add(jlabel3);
        add(jTextField3);
        add(add);
        add(substract);
        add(multiply);
        add(divide);
        add(integrate);
        add(derivate);
        jTextField3.requestFocusInWindow();
    }

    private void UpdatePolinom1(JTextField jTextField) {
        Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        Matcher matcher = pattern.matcher(jTextField.getText());
        Monom monom;
        boolean coeffFound, flag = false;
        int i, number = 0;

        polinom1.purge();
        while (matcher.find()) {
            coeffFound = false;
            i = 0;
            monom = new Monom(0, 0);
            while(i < matcher.group(1).length()){
                if (matcher.group(1).charAt(i) == 'x') {
                    if (i - 1 < 0) {
                        number = 1;
                        monom.setCoeff(number);
                        coeffFound = true;;
                    }
                    else if (matcher.group(1).charAt(i - 1) == ' ' || matcher.group(1).charAt(i - 1) == '+' || matcher.group(1).charAt(i - 1) == '-') {
                        if (matcher.group(1).charAt(i - 1) == '-') number = -1;
                        else number = 1;
                        monom.setCoeff(number);
                        coeffFound = true;;
                    }
                    if (i + 1 >= matcher.group(1).length()) {
                        number = 1;
                        monom.setPower(number);
                    }
                    else if (matcher.group(1).charAt(i + 1) == ' ' || matcher.group(1).charAt(i + 1) == '+' || matcher.group(1).charAt(i + 1) == '-') {
                        number = 1;
                        monom.setPower(number);
                    }
                    i++;
                }
                while (i < matcher.group(1).length() && matcher.group(1).charAt(i) != '+' && matcher.group(1).charAt(i) != '-' && matcher.group(1).charAt(i) != ' ' && matcher.group(1).charAt(i) != 'x' && matcher.group(1).charAt(i) != '^') {
                    number = number*10 + Character.getNumericValue(matcher.group(1).charAt(i));
                    if (((i-1) >= 0 && matcher.group(1).charAt(i-1) == '-') || ((i-2) >= 0 && matcher.group(1).charAt(i-2) == '-')) {
                        flag = true; // flag which checks if we had a -
                    }
                    i++;
                }
                if (i < matcher.group(1).length() && matcher.group(1).charAt(i) == 'x') i--;
                if (i == 0 && matcher.group(1).charAt(i) == '-') flag = true;
                if (flag == true) {
                    number *= -1;
                    flag = false;
                }
                if (!coeffFound && number != 0) {
                    monom.setCoeff((float)number);
                    number = 0;
                    coeffFound = true;
                }
                else if(number != 0){
                    monom.setPower(number);
                    number = 0;
                }
                i++;
            }
            polinom1.addMonom(monom);
        }
        polinom1.sortMonoms();
    }

    private void UpdatePolinom2(JTextField jTextField) {
        Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        Matcher matcher = pattern.matcher(jTextField.getText());
        Monom monom;
        boolean coeffFound, flag = false;
        int i, number = 0;

        polinom2.purge();
        while (matcher.find()) {
            coeffFound = false;
            i = 0;
            monom = new Monom(0, 0);
            while(i < matcher.group(1).length()){
                if (matcher.group(1).charAt(i) == 'x') {
                    if (i - 1 < 0) {
                        number = 1;
                        monom.setCoeff(number);
                        coeffFound = true;
                    }
                    else if (matcher.group(1).charAt(i - 1) == ' ' || matcher.group(1).charAt(i - 1) == '+' || matcher.group(1).charAt(i - 1) == '-') {
                        if (matcher.group(1).charAt(i - 1) == '-') number = -1;
                        else number = 1;
                        monom.setCoeff(number);
                        coeffFound = true;;
                    }
                    if (i + 1 >= matcher.group(1).length()) {
                        number = 1;
                        monom.setPower(number);
                    }
                    else if (matcher.group(1).charAt(i + 1) == ' ' || matcher.group(1).charAt(i + 1) == '+' || matcher.group(1).charAt(i + 1) == '-') {
                        number = 1;
                        monom.setPower(number);
                    }
                    i++;
                }
                while (i < matcher.group(1).length() && matcher.group(1).charAt(i) != '+' && matcher.group(1).charAt(i) != '-' && matcher.group(1).charAt(i) != ' ' && matcher.group(1).charAt(i) != 'x' && matcher.group(1).charAt(i) != '^') {
                    number = number*10 + Character.getNumericValue(matcher.group(1).charAt(i));
                    if (((i-1) >= 0 && matcher.group(1).charAt(i-1) == '-') || ((i-2) >= 0 && matcher.group(1).charAt(i-2) == '-')) {
                        flag = true; // flag which checks if we had a -
                    }
                    i++;
                }
                if (i < matcher.group(1).length() && matcher.group(1).charAt(i) == 'x') i--;
                if (flag == true) {
                    number *= -1;
                    flag = false;
                }
                if (!coeffFound && number != 0) {
                    monom.setCoeff((float)number);
                    number = 0;
                    coeffFound = true;
                }
                else if(number != 0){
                    monom.setPower(number);
                    number = 0;
                }
                i++;
            }
            polinom2.addMonom(monom);
        }
        polinom2.sortMonoms();
    }

    public void UpdateResultJTextField(JTextField jTextField3) {
        boolean first = true, zero = true;
        t = "";
        for (Monom m: result.getMonoms()) {
            m.setCoeff((float) Math.round(m.getCoeff() * 100) / 100);
            if (first) {
                if (m.getCoeff() != 0) {
                    zero = false;
                    if (m.getPower() == 1) {
                        t = t + m.getCoeff() + 'x';
                    } else if (m.getPower() == 0) {
                        t = t + m.getCoeff();
                    } else t = t + m.getCoeff() + "x^" + m.getPower();
                    first = false;
                }
            }
            else {
                if (m.getCoeff() != 0) {
                    zero = false;
                    if (m.getCoeff() < 0) {
                        if (m.getCoeff() == -1) {
                            if (m.getPower() == 1) {
                                t = t + " -x";
                            } else if (m.getPower() == 0) {
                                t = t + " -1";
                            }
                            else {
                                t = t + " -x^" + m.getPower();
                            }
                        }
                        else {
                            if (m.getPower() == 1) {
                                t = t + ' ' + m.getCoeff() + 'x';
                            } else if (m.getPower() == 0) {
                                t = t + ' ' + m.getCoeff();
                            } else {
                                t = t + " " + m.getCoeff() + "x^" + m.getPower();
                            }
                        }
                    }
                    else {
                        if (m.getCoeff() == 1) {
                            if (m.getPower() == 1) {
                                t = t + " +x";
                            } else if (m.getPower() == 0) {
                                t = t + " +1";
                            }
                            else {
                                t = t + " +x^" + m.getPower();
                            }
                        }
                        else {
                            if (m.getPower() == 1) {
                                t = t + " +" + m.getCoeff() + 'x';
                            } else if (m.getPower() == 0) {
                                t = t + " +" + m.getCoeff();
                            }
                            else {
                                t = t + " +" + m.getCoeff() + "x^" + m.getPower();
                            }
                        }
                    }
                }
            }
        }
        if (zero) t = "0";
        jTextField3.setText(t);
    }
}