package tankgame04;

public class Tank {
    private int x;
    private int y;
    private int direct;
    private int speed = 1;


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
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

    public void MoveUp() {
        if (y - 30 > 0) {
            y -= speed;
        }
    }

    public void MoveDown() {
        if (y + 70 < 750) {
            y += speed;
        }
    }

    public void MoveRight() {
        if (x + 50 < 1000) {
            x += speed;
        }
    }

    public void MoveLeft() {
        if (x - 30 > 0) {
            x -= speed;
        }
    }
}
