package TestingVarCode;

/* https://www.youtube.com/watch?v=OI-TFbHQhtA&list=PLWfJeLqH0KUUicazPYhTdnZydkC10h6wB&index=8
 How to code a GUI in Java by choobtorials 12/01/2022
 */

import Quiz.Program;
import Quiz.Highscore;

import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    private JFrame frame;
    private JLabel label;
    private JButton button1, button2;
    private int width;
    private int height;

    public GUI(int w, int h) {
        frame = new JFrame();
        label = new JLabel("The Quizmaker by Team Bermuda");
        button1 = new JButton("Play Game");
        button2 = new JButton("Show Highscore");
        width = w;
        height = h;
    }

    public void setUpGUI() {
        Container cp = frame.getContentPane();
        FlowLayout flow = new FlowLayout();
        cp.setLayout(flow);
        frame.setSize(width, height);
        frame.setTitle("Quizmaker - Bermuda");

        cp.add(label);
        cp.add(button1);
        cp.add(button2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setUpButtonListeners() {
        ActionListener buttonListener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               /*
               Program pg = new Program();
               pg.checkInput();
               pg.checkAnswer();
                */

                System.out.println("Game is running");

            }
        };

        ActionListener buttonListener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                Highscore hs = new Highscore();
                hs.printHighscore();
            }
        };
        button1.addActionListener(buttonListener1);
        button2.addActionListener(buttonListener2);
    }
}