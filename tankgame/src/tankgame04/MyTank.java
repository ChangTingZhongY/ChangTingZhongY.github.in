package tankgame04;

import java.util.Vector;

public class MyTank extends Tank {
    private Shot shot = null;
    Vector<Shot> shots = new Vector<>();
    private boolean isLive = true;

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public Shot getShot() {
        return shot;
    }

    public void setShot(Shot shot) {
        this.shot = shot;
    }

    public void shotshot() {
        if (shots.size() == 5) {
            return;
        }
        switch (getDirect()) {
            case 0:
                shot = new Shot(getX(), getY() - 30, 0);
                break;
            case 1:
                shot = new Shot(getX() + 30, getY(), 1);
                break;
            case 2:
                shot = new Shot(getX(), getY() + 30, 2);
                break;
            case 3:
                shot = new Shot(getX() - 30, getY(), 3);
                break;
        }
        shots.add(shot);
        new Thread(shot).start();
//        new Thread(shot).start();
    }

    public MyTank(int x, int y) {
        super(x, y);
    }
}
