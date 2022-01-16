package TestingVarCode;

/* https://www.youtube.com/watch?v=OI-TFbHQhtA&list=PLWfJeLqH0KUUicazPYhTdnZydkC10h6wB&index=8
 How to code a GUI in Java by choobtorials 12/01/2022
 */

import Quiz.Program;
import Quiz.Highscore;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    private JFrame frame;
    private JLabel label;
    private JButton button1, button2;
    private JTextArea ta;
    private int width;
    private int height;

    public GUI(int w, int h) {
        frame = new JFrame();
        label = new JLabel("CSDC BB 2024 FH Campus Wien");
        button1 = new JButton("Play Game");
        button2 = new JButton("Show Highscore");
        ta = new JTextArea("Team Bermuda \n Projekt fuer Programmieren 1 \n Carmen\nSergiu \n Nina \nRaphi \n Laura");
        width = w;
        height = h;
    }

    public void setUpGUI() {
        Container cp = frame.getContentPane();
        BorderLayout bl = new BorderLayout();
        cp.setLayout(bl);
        frame.setSize(width, height);
        frame.setTitle("Quizmaker - Bermuda");

        cp.add(label, BorderLayout.NORTH);
        cp.add(button1, BorderLayout.WEST);
        cp.add(ta, BorderLayout.CENTER);
        cp.add(button2, BorderLayout.EAST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setUpButtonListeners() {

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Object o = ae.getSource();

                if(o == button2){
                    Highscore hs = new Highscore();
                    hs.printHighscore();
                } else if(o == button1){
               /*
                    Program pg = new Program();
                    main?
                    pg.checkInput();
                    pg.checkAnswer();
                */

                    System.out.println("Game is running");
                }
            }
        };
        button1.addActionListener(buttonListener);
        button2.addActionListener(buttonListener);
    }
}