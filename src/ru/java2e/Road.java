package ru.java2e;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Road extends JPanel implements ActionListener, Runnable {

    Image img = new ImageIcon("res/road.png").getImage();
    int width = img.getWidth(null);
    int height = img.getHeight(null);


    Player p = new Player();
    HeatBars h = new HeatBars();
    Thread enemiesFactory = new Thread(this);
    List <Enemy> enemies = new ArrayList<Enemy>();
    Timer mainTimer = new Timer(20, this);
    public Road(){
        mainTimer.start();
        enemiesFactory.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }

    @Override
    public void run() {
        while (true){
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(2000));
                enemies.add(new Enemy(2000, random.nextInt(500), random.nextInt(60), this));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class MyKeyAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            p.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            p.keyReleased(e);
        }
    }

    public void paint(Graphics g){
        g = (Graphics2D) g;
        g.drawImage(img,  p.layer1,0, null);
        g.drawImage(img,  p.layer2,0, null);
        g.drawImage(p.img, p.x, p.y, null);

        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()){
            Enemy e = i.next();
            if (e.x >=2400 || e.x<=-2400){
                i.remove();
            } else
                e.move();
                g.drawImage(e.img, e.x, e.y, null);
        }

        String speed = String.valueOf((p.v)*3);

        g.setColor(Color.WHITE);
        Font font = new Font("Times New Roman", Font.ITALIC, 25);
        g.setFont(font);

        g.drawString(speed, 100, 600);
    }
    public void actionPerformed (ActionEvent e){
        p.move();
        repaint();
        testCollision();
    }
    public void testCollision(){
        Iterator<Enemy> i = enemies.iterator();
        while(i.hasNext()){
            Enemy e = i.next();
            if(h.getRect(p.x, p.y, p.width, p.height).intersects(h.getRect(e.x, e.y, e.width, e.height))){
                JOptionPane.showMessageDialog(null, "Crushed ;(");
                System.exit(1);
            }
        }
    }
}
