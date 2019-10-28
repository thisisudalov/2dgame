package ru.java2e;

import javax.swing.*;
import java.awt.*;

public class Enemy {
    int x;
    int y;
    int v;

    Image img = new ImageIcon("res/enemy.png").getImage();
    int width = img.getWidth(null);
    int height = img.getHeight(null);
    Road road;

    public Enemy(int x, int y, int v, Road road){
        this.x = x;
        this.y = y;
        this.v = v;
        this.road = road;
    }
    public void   move(){
        x = x - road.p.v + v;
    }
}
