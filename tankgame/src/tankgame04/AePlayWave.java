package tankgame04;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AePlayWave extends Thread{
    private String filename;
    public AePlayWave(String wavfile){
        filename=wavfile;
    }
    @Override
    public void run() {
        File soundFile = new File(filename);
        AudioInputStream audioInputStream=null;
        try {
            audioInputStream= AudioSystem.getAudioInputStream(soundFile);
        }
         catch (Exception e) {
           e.printStackTrace();
           return;
        }
        AudioFormat format=audioInputStream.getFormat();
        SourceDataLine auline=null;
        DataLine.Info info =new DataLine.Info(SourceDataLine.class,format);

        try { auline=(SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (Exception e) {
       e.printStackTrace();
       return;
        }
        auline.start();
        int nBytesRead=0;
        byte[] abData=new byte[512];

            try {while (nBytesRead!=-1){
                nBytesRead=audioInputStream.read(abData,0,abData.length);
            if (nBytesRead>=0){
                auline.write(abData,0,abData.length);
            }
            }} catch (IOException e) {
                e.printStackTrace();
                return;
            }finally {
                auline.drain();
                auline.close();
            }
        }

}
