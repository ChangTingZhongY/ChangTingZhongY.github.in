package tankgame04;

import java.io.*;
import java.nio.Buffer;
import java.util.Vector;

//记录相关信息
public class Recorder {
    private static int allditankesnum = 0;//击毁敌方坦克

    //i/o
//    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "src\\myRecord.txt";
    private static Vector<DiTank> diTanks = null;

    public static void setDiTanks(Vector<DiTank> diTanks) {
        Recorder.diTanks = diTanks;
    }

    private static Vector<Node> nodes = new Vector<>();

    public static Vector<Node> getnodesallditankesgame() {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            allditankesnum = Integer.parseInt(br.readLine());

            String line = null;
            while ((line = br.readLine()) != null) {
                String[] xyd = line.split(" ");
//                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return nodes;
    }

    public static void keeprecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allditankesnum + "\r\n");
            for (int i = 0; i < diTanks.size(); i++) {
                DiTank diTank = diTanks.get(i);
                if (diTank.isLive()) {
                    String recorder = diTank.getX() + " " + diTank.getY() + " " + diTank.getDirect();
                    bw.write(recorder);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static int getAllditankesnum() {
        return allditankesnum;
    }

    public static void setAllditankesnum(int allditankesnum) {
        Recorder.allditankesnum = allditankesnum;
    }

    public static void addAllditankesnum() {
        Recorder.allditankesnum++;
    }
}
