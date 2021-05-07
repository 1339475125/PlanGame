package cn.gm.game;

import java.awt.*;

/**
 * @ClassName GameObject
 * @Description 游戏根类
 * @Author edz
 * @Date 2021/5/6 5:21 下午
 * @Version 1.0
 **/
public class GameObject {
    Image img;
    double x, y;
    int speed;
    int width, height;

    public void drawSelf(Graphics g){
        g.drawImage(img, (int) x, (int) y, null);
    }

    public GameObject(Image img, double x, double y, int speed, int width, int height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public GameObject(Image img, double x, double y) {
        this.img = img;
        this.x = x;
        this.y = y;
    }

    public GameObject(){

    }

    // 返回矩形地址
    public Rectangle getRect(){
        return new Rectangle((int)x, (int)y, width,height);
    }

}
