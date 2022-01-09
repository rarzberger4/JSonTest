package TestingVarCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//https://introcs.cs.princeton.edu/java/15inout/GUI.java.html (09.01.2022)

    public class GUI implements ActionListener {

        private JFrame frame;
        private JPanel panel;
        private JLabel label, label1, label2;
        private JTextField input;

        public GUI(){

            frame = new JFrame();
            panel = new JPanel();

            JButton button = new JButton("Antwort 1");
            button.addActionListener(this);
            JButton button1 = new JButton("Antwort 2");
            JButton button2 = new JButton("Antwort 3");
            JButton button3 = new JButton("Antwort 4");
            JButton button4 = new JButton("View Highscore");

            input = new JTextField(10);

            label = new JLabel("QUIZMAKER BERMUDA");
            label1 = new JLabel(" Die Frage lautet: Hier eine Beispielfrage");
            label2 = new JLabel("Dein Name:");

            panel.setBorder(BorderFactory.createEmptyBorder(60,60,20,60));
            panel.setLayout(new GridLayout(0,1));
            panel.add(label);
            panel.add(label2);
            panel.add(input);
            panel.add(label1);
            panel.add(button);
            panel.add(button1);
            panel.add(button2);
            panel.add(button3);
            panel.add(button4);



            frame.add(panel, BorderLayout.CENTER);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Our GUI");
            frame.pack();
            frame.setVisible(true);
        }

        public static void main(String[] args){
            new GUI();
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
