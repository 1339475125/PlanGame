package cn.gm.game;

import java.awt.*;

/**
 * @ClassName Explode
 * @Description TODO
 * @Author edz
 * @Date 2021/5/6 7:05 下午
 * @Version 1.0
 **/
public class Explode {

    double x, y;
    static Image[] images = new Image[16];
    static {
        for(int i=0; i< 16; i++){
            images[i] = GameUtil.getImage("images/boom/" +(i+1)+".gif");
            images[i].getWidth(null);
        }
    }

    int count;

    public void draw(Graphics g){
        if(count <= 15){
            g.drawImage(images[count], (int)x, (int)y, null);
            count++;
        }
    }

    public Explode(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Explode() {
    }

    public static void main(String[] args) {
        new Explode();
    }
}
