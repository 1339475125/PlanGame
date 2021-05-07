package cn.gm.game;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;


/**
 * @ClassName MyGameFrame
 * @Description 飞机游戏主窗口
 * @Author edz
 * @Date 2021/5/6 4:18 下午
 * @Version 1.0
 **/
public class MyGameFrame extends Frame {

    Image feiji = GameUtil.getImage("images/feiji.png");
    Image bj = GameUtil.getImage("images/bj.jpeg");
    Plane plane = new Plane(feiji, 250, 250);
//    Shell shell = new Shell();
    Shell[] shells = new Shell[50];
    Explode bao;
    Date startTime = new Date();
    Date endTime;
    int period;

    @Override
    public void paint(Graphics g) { // 自动被调用， g是画笔
        super.paint(g);
        g.drawImage(bj, 0, 0, null);
        Color c = g.getColor();
        plane.drawSelf(g);
        // 画50次炮弹
        for(int i=0; i<shells.length; i++) {
            shells[i].draw(g);
            // 碰撞检测
            boolean peng = shells[i].getRect().intersects(plane.getRect());
            if (peng) {
                plane.live = false;
                if (bao == null) {
                    bao = new Explode();
                    endTime = new Date();
                    period = (int)(endTime.getTime() -startTime.getTime()) / 1000;
                }
                bao.draw(g);
            }
            if(!plane.live){
                g.setColor(Color.red);
                Font f = new Font("宋体", Font.BOLD, 50);
                g.setFont(f);
                g.drawString("时间：" + period + "秒", (int) plane.x, (int)plane.y);
            }
        }
        g.setColor(c);
    }

    // 反复重画窗口
    class PaintThread extends Thread{

        @Override
        public void run(){
            while (true){
                repaint();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class KeyMonitor extends KeyAdapter{

        // 按下
        @Override
        public void keyPressed(KeyEvent e) {
            plane.addDirection(e);
        }

        // 抬起
        @Override
        public void keyReleased(KeyEvent e) {
            plane.minusDirection(e);
        }
    }


    /**
     * 游戏初始化窗口
     *
     */


    public void launchFrame(){
        this.setTitle("飞机游戏");
        this.setVisible(true);
        this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        this.setLocation(300, 300);
        this.addWindowListener(new WindowAdapter() {
            /**
             * 关闭窗口
             * @param e
             */

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                System.exit(0);
            }
        });

        new PaintThread().start(); //启动重画线程
        addKeyListener(new KeyMonitor());

        // 初始化炮弹
        for(int i=0; i<shells.length; i++){
            shells[i] = new Shell();
        }
    }

    public  static void main(String[] args){
        MyGameFrame f = new MyGameFrame();
        f.launchFrame();
    }

    // 双缓冲
    private Image offScreenImage = null;

    public void update(Graphics g){
        if(offScreenImage == null)
            offScreenImage = this.createImage(
                    Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }
}
