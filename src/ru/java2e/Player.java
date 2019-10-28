package ru.java2e;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    public static final int MAX_V=50;
    public static final int MAX_TOP=0;
    public static final int MAX_BOT=500;

    Image imgfrw = new ImageIcon("res/player.png").getImage();
    Image imgtop = new ImageIcon("res/playertop.png").getImage();
    Image imgbot = new ImageIcon("res/playerbot.png").getImage();
    Image img = imgfrw;

    int width = img.getWidth(null);
    int height = img.getHeight(null);

    int v = 0;
    int dv = 0;
    int s = 0;
    int dy = 0;

    int x = 50;
    int y = 150;
    int layer1 = 0;
    int layer2 = 1914;


    public void move() {
        s += v;
        v+=dv;
        if (v<=0) v=0;
        if (v>=MAX_V) v = MAX_V;
        y+=dy;
        if (y<=MAX_TOP) y = MAX_TOP;
        if (y>=MAX_BOT) y = MAX_BOT;

        if (layer2 - v <= 0){
            layer1 = 0;
            layer2 = 1152;
        } else{
            layer1 -=v;
            layer2 -=v;
        }
    }
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT){
            dv = +1;
        }
        if (key == KeyEvent.VK_LEFT){
            dv = -1;
        }

        if (key == KeyEvent.VK_DOWN){
           dy += 10;
           img = imgbot;

        }
        if (key == KeyEvent.VK_UP){
           dy -= 10;
           img=imgtop;
        }
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT){
            dv = 0;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN){
            dy = 0;
            img=imgfrw;
        }
    }
}
