package cn.gm.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * @ClassName GameUtil
 * @Description 工具类
 * @Author edz
 * @Date 2021/5/6 4:34 下午
 * @Version 1.0
 **/
public class GameUtil {
    // 工具类最好将构造器私有化
    private GameUtil(){

    }

    /**
     * 指定图片路径的对象
     * @param path
     * @return
     */
    public static Image getImage(String path){
        BufferedImage bi = null;
        try{
            URL u = GameUtil.class.getClassLoader().getResource(path);
            bi = ImageIO.read(u);
        }catch(IOException e){
            e.printStackTrace();
        }
        return bi;
    }

    public static void main(String[] args) {
        getImage("images/boom/1.gif");
    }
}
