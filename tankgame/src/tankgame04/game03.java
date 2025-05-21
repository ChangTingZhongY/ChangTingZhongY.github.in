package tankgame04;


import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class game03 extends JFrame {
    MyPanel map;
    public static void main(String[] args) {
        System.out.println("请输入选择 1:新游戏" +
                "\n"+      "         2、继续上局");

        game03 game01 = new game03();
    }

    public game03(){ Scanner scanner = new Scanner(System.in);
        String key =scanner.next();
        map=new MyPanel(key);
        Thread thread = new Thread(map);
        thread.start();
        this.add(map);
        this.setSize(1300,750);
        this.addKeyListener(map);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("存盘");

                Recorder.keeprecord();
                System.exit(0);
            }
        });
    }
}
