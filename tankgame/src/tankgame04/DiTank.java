package tankgame04;

import java.util.Vector;

public class DiTank extends Tank implements Runnable {
    Vector<Shot> shots = new Vector<>();
    private boolean isLive = true;

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public DiTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true) {
            if (isLive && shots.size() < 2) {
                Shot s = null;
                switch (getDirect()) {
                    case 0:
                        s = new Shot(getX(), getY() - 30, 0);
                        break;
                    case 1:
                        s = new Shot(getX() + 30, getY(), 1);
                        break;
                    case 2:
                        s = new Shot(getX(), getY() + 30, 2);
                        break;
                    case 3:
                        s = new Shot(getX() - 30, getY(), 3);
                        break;
                }
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                shots.add(s);
                new Thread(s).start();
            }
            switch (getDirect()) {
                case 0:
                    for (int i = 0; i < 10; i++) {
                        MoveUp();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    break;
                case 1:
                    for (int i = 0; i < 10; i++) {
                        MoveRight();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 10; i++) {
                        MoveDown();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 10; i++) {
                        MoveLeft();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            setDirect((int) (Math.random() * 4));
            if (isLive == false) {
                break;
            }
        }
    }
}
