package ru.java2e;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame f = new JFrame("Game");
        Road r = new Road();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(r.width, r.height);
        f.add(new Road());
        f.setVisible(true);

    }
}