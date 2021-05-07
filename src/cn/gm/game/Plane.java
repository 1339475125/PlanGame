package cn.gm.game;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @ClassName Plane
 * @Description 飞机
 * @Author edz
 * @Date 2021/5/6 5:27 下午
 * @Version 1.0
 **/

public class Plane extends GameObject{
    boolean left, up, right, down;
    int speed = 30;
    boolean live = true;

    public Plane(Image img, double x, double y) {
        super(img, x, y);
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = 30;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }

    public void drawSelf(Graphics g){
        if(live){
            g.drawImage(img, (int) x, (int) y, null);
            if(left){
                x-=speed;
            }
            if(right){
                x+=speed;
            }
            if(up){
                y-=speed;
            }
            if(down){
                y+=speed;
            }
        } else{
            System.out.println("死掉了");
        }

    }

    // 按键按下增加方向
    public void addDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
        }
    }

    // 按键按下增加方向
    public void minusDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
        }
    }
}
