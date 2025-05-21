package tankgame04;

public class Shot implements Runnable {
    private int x;
    private int y;
    private int directzi;
    private int speed = 3;
    private boolean isLive = true;

    @Override
    public void run() {
        while (isLive) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch (directzi) {
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
            }
//            System.out.println("x=" + x + "y=" + y);
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive)) {

                isLive = false;
                break;
            }
        }


    }

    public Shot(int x, int y, int directzi) {
        this.x = x;
        this.y = y;
        this.directzi = directzi;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirectzi() {
        return directzi;
    }

    public void setDirectzi(int directzi) {
        this.directzi = directzi;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }


}
