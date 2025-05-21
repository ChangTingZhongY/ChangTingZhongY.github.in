package tankgame04;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {
    MyTank ting;
    Vector<Node> nodes =new Vector<>();
    //    DiTank ting2;
//    DiTank ting3;
//    DiTank ting4;
    Vector<DiTank> diTanks = new Vector<DiTank>(3);
    Vector<Bomb> bombs = new Vector<>();
    int diTankssize = 3;

    Image image1 = null;
//    Image image2 = null;
//    Image image3 = null;

    public MyPanel(String key) {

        ting = new MyTank(400, 400);
        ting.setSpeed(5);
        switch (key){
            case "1": for (int i = 0; i < diTankssize; i++) {
                DiTank diTank = new DiTank((531 + 100 * i), 331);
                diTank.setDirect(2);

                Shot shot = new Shot(diTank.getX(), diTank.getY(), diTank.getDirect());
                diTank.shots.add(shot);
                new Thread(shot).start();
                diTanks.add(diTank);
                Thread dis = new Thread(diTank);
                dis.start();
            }
                Recorder.setDiTanks(diTanks);
                image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/微信图片_20240129124653.jpg"));
                break;
            case "2":nodes=Recorder.getnodesallditankesgame();
                for (int i = 0; i < nodes.size(); i++) {
                Node node = nodes.get(i);
                DiTank diTank = new DiTank(node.getX(), node.getY());
                diTank.setDirect(node.getDirect());

                Shot shot = new Shot(diTank.getX(), diTank.getY(), diTank.getDirect());
                diTank.shots.add(shot);
                new Thread(shot).start();
                diTanks.add(diTank);
                Thread dis = new Thread(diTank);
                dis.start();
            }
                Recorder.setDiTanks(diTanks);
                image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/微信图片_20240129124653.jpg"));
                break;
            default:
                System.out.println("输入有误");



        }

//        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/微信图片_20240129124653.jpg"));
//        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/微信图片_20240129124653.jpg"));
//        ting2 = new DiTank(100, 1);
//        ting3 = new DiTank(300, 1);
//        ting4 = new DiTank(500, 1);
//        diTanks.add(ting2);
//        diTanks.add(ting3);
//        diTanks.add(ting4);
//        ting2 = new MyTank(200, 100);
    }

    public void showinfo(Graphics g) {
        //记录玩家总成绩
        g.setColor(Color.black);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("累计击毁敌方坦克:", 1020, 30);
        drawTank(1040, 80, g, 0, 1);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString(Recorder.getAllditankesnum() + "", 1080, 85);
        g.setColor(Color.black);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        showinfo(g);
        g.fillRect(0, 0, 1000, 750);
        fangchongdie();
        for (int i = 0; i < ting.shots.size(); i++) {
            Shot shot = ting.shots.get(i);

            if (shot != null && shot.isLive()) {
                g.setColor(Color.cyan);
                g.fillOval(shot.getX(), shot.getY(), 1, 1);
            } else {
                ting.shots.remove(i);
            }
        }
//        if (ting==null){ }
//
//            switch (ting.getDirect()) {
//                case 0:
//                    g.fillOval(ting.getShot().getX() , ting.getShot().getY() , 1, 1);
//                    break;
//                case 1:
//                    g.fillOval(ting.getShot().getX(), ting.getShot().getY() , 1, 1);
//                    break;
//                case 2:
//                    g.fillOval(ting.getShot().getX() , ting.getShot().getY(), 1, 1);
//                    break;
//                case 3:
//                    g.fillOval(ting.getShot().getX() , ting.getShot().getY() , 1, 1);
//                    break;
//            }


        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.life > 0) {
                g.drawImage(image1, bomb.x - 20, bomb.y - 30, 60, 60, this);
            }
//            else if (bomb.life>3) {
//                g.drawImage(image2,bomb.x,bomb.y,60,60,this);
//            }else {
//                g.drawImage(image3,bomb.x,bomb.y,60,60,this);
//            }bomb.lifeDown();

            bomb.lifeDown();

            if (bomb.life == 0) {
                bombs.remove(bomb);
            }
        }
        if (ting != null && ting.isLive()) {
            drawTank(ting.getX(), ting.getY(), g, ting.getDirect(), 0);

        }
//        if (!ting.isLive()||diTanks.isEmpty()){ting=null;}
        for (int i = 0; i < diTanks.size(); i++) {
            DiTank diTank = diTanks.get(i);
            if (diTank.isLive()) {
                drawTank(diTank.getX(), diTank.getY(), g, diTank.getDirect(), 1);
                for (int j = 0; j < diTank.shots.size(); j++) {
                    Shot shot = diTank.shots.get(j);
                    if (shot.isLive() == true) {
                        g.fillOval(shot.getX(), shot.getY(), 1, 1);
//                        switch (diTanks.get(i).getDirect()) {
//                            case 0:
//                                g.fillOval(shot.getX() - 5, shot.getY() - 40, 10, 10);
//                                break;
//                            case 1:
//                                g.fillOval(shot.getX() + 30, shot.getY() - 5, 10, 10);
//                                break;
//                            case 2:
//                                g.fillOval(shot.getX() - 5, shot.getY() + 30, 10, 10);
//                                break;
//                            case 3:
//                                g.fillOval(shot.getX() - 40, shot.getY() - 5, 10, 10);
//                                break;
//                        }
                    } else {
                        diTank.shots.remove(shot);
                    }
                }
            }
        }
//        drawTank(ting2.getX(), ting2.getY(), g, ting2.getDirect(), 1);
//        drawTank(ting3.getX(), ting3.getY(), g, ting3.getDirect(), 1);
//        drawTank(ting4.getX(), ting4.getY(), g, ting4.getDirect(), 1);
//        drawTank(ting2.getX(),ting2.getY(),g,0,0);
//        drawTank(ting2.getX(),ting2.getY(),g,0,0);
    }
//    画出坦克的方法

    /**
     * x
     * y
     * g画笔
     * direction坦克方向
     * type坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direction, int type) {
//        颜色
        switch (type) {
            case 0://自己的
                g.setColor(Color.cyan);
                break;
            case 1://敌人的
                g.setColor(Color.yellow);
                break;
            default:
                break;

        }
        switch (direction) {//0向上1向右2向下3向左
            case 0:
                g.fill3DRect(x - 20, y - 30, 10, 60, false);//坦克左轮子
                g.fill3DRect(x + 10, y - 30, 10, 60, false);//坦克右轮子
                g.fill3DRect(x - 10, y - 20, 20, 40, false);//坦克身体
                g.fillOval(x - 10, y - 10, 20, 20);//坦克圆
                g.drawLine(x, y, x, y - 30);//坦克炮台
                break;
            case 1:
                g.fill3DRect(x - 30, y - 20, 60, 10, false);
                g.fill3DRect(x - 30, y + 10, 60, 10, false);
                g.fill3DRect(x - 20, y - 10, 40, 20, false);
                g.fillOval(x - 10, y - 10, 20, 20);//
                g.drawLine(x, y, x + 30, y);//
                break;
            case 2:
                g.fill3DRect(x - 20, y - 30, 10, 60, false);//坦克左轮子
                g.fill3DRect(x + 10, y - 30, 10, 60, false);//坦克右轮子
                g.fill3DRect(x - 10, y - 20, 20, 40, false);//坦克身体
                g.fillOval(x - 10, y - 10, 20, 20);//坦克圆
                g.drawLine(x, y, x, y + 30);//坦克炮台
                break;
            case 3:
                g.fill3DRect(x - 30, y - 20, 60, 10, false);
                g.fill3DRect(x - 30, y + 10, 60, 10, false);
                g.fill3DRect(x - 20, y - 10, 40, 20, false);
                g.fillOval(x - 10, y - 10, 20, 20);//
                g.drawLine(x, y, x - 30, y);//
                break;

            default:
                System.out.println("暂时无处理");
        }
    }

    public void hitTank2() {
        for (int j = 0; j < ting.shots.size(); j++) {
            Shot shot = ting.shots.get(j);
            if (shot != null && shot.isLive()) {
                for (int i = 0; i < diTanks.size(); i++) {
                    DiTank diTank = diTanks.get(i);
                    hitTank(shot, diTank);
                }
            }
        }
    }

    public void hitTank(Shot s, DiTank diTank) {
        switch (diTank.getDirect()) {
            case 0:
            case 2:
                if (s.getX() > diTank.getX() - 20 && s.getX() < diTank.getX() + 20
                        && s.getY() > diTank.getY() - 30 && s.getY() < diTank.getY() + 30) {
                    s.setLive(false);
                    diTank.setLive(false);
                    Bomb bomb = new Bomb(diTank.getX(), diTank.getY());
                    bombs.add(bomb);
                    diTanks.remove(diTank);
                    Recorder.addAllditankesnum();
                }
                break;
            case 1:
            case 3:
                if (s.getX() > diTank.getX() - 30 && s.getX() < diTank.getX() + 30
                        && s.getY() > diTank.getY() - 20 && s.getY() < diTank.getY() + 20) {
                    s.setLive(false);
                    diTank.setLive(false);
                    Bomb bomb = new Bomb(diTank.getX(), diTank.getY());
                    bombs.add(bomb);
                    diTanks.remove(diTank);
                    Recorder.addAllditankesnum();
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S) {
//            ting.setY(ting.getY()+10);
            ting.setDirect(2);
            ting.MoveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
//            ting.setY(ting.getY()-10);
            ting.setDirect(0);
            ting.MoveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
//            ting.setX(ting.getX()-10);
            ting.setDirect(3);
            ting.MoveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
//            ting.setX(ting.getX()+10);
            ting.setDirect(1);
            ting.MoveRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
//            if (ting.getShot().isLive()){
//            if (ting.getShot() == null || !ting.getShot().isLive()) {
//                ting.shotshot();
//            }
            ting.shotshot();
        }
//        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            hitTank2();
            hitme();
            this.repaint();
        }


    }

    public void fangchongdie() {//自己防重叠
        for (int i = 0; i < diTanks.size(); i++) {
            DiTank diTank1 = diTanks.get(i);
            for (int j = 0; j < diTanks.size(); j++) {
                DiTank diTank2 = diTanks.get(j);
                if (!(diTank1 == diTank2)) {
                    if (diTank1.getX() > diTank2.getX() - 70 && diTank1.getY() < diTank2.getY() + 70 &&
                            diTank1.getY() > diTank2.getY() - 70 && diTank1.getX() < diTank2.getX() + 70
                    ) {
                        if (diTank2.getDirect() == 0) {
                            diTank1.setDirect(2);
                        } else if (diTank2.getDirect() == 1) {
                            diTank1.setDirect(3);
                        } else if (diTank2.getDirect() == 2) {
                            diTank1.setDirect(0);
                        } else {
                            diTank1.setDirect(1);
                        }
                    }
                } else {
                    continue;
                }
            }
//            if (diTank.getX()<ting.getX()-60||diTank.getX()>ting.getX()+60||diTank.getY()<ting.getY()-60
//            ||diTank.getY()>ting.getY()+60){
//
//            }
        }
    }

    public void hitme() {
        for (int i = 0; i < diTanks.size(); i++) {
            DiTank diTank = diTanks.get(i);
            for (int j = 0; j < diTank.shots.size(); j++) {
                Shot shot = diTank.shots.get(j);
                if (ting.isLive() && shot.isLive()) {
                    switch (ting.getDirect()) {
                        case 0:
                        case 2:
                            if (shot.getX() > ting.getX() - 20 && shot.getX() < ting.getX() + 20
                                    && shot.getY() > ting.getY() - 30 && shot.getY() < ting.getY() + 30) {
                                shot.setLive(false);
                                ting.setLive(false);
                                Bomb bomb = new Bomb(diTank.getX(), diTank.getY());
                                bombs.add(bomb);
//                                diTanks.remove(diTank);diTank
                            }
                            break;
                        case 1:
                        case 3:
                            if (shot.getX() > ting.getX() - 30 && shot.getX() < ting.getX() + 30
                                    && shot.getY() > ting.getY() - 20 && shot.getY() < ting.getY() + 20) {
                                shot.setLive(false);
                                ting.setLive(false);
                                Bomb bomb = new Bomb(diTank.getX(), diTank.getY());
                                bombs.add(bomb);
//                                diTanks.remove(diTank);
                            }
                            break;
                    }
                }
            }
        }
    }
}
