import processing.core.PApplet;

public class Paddle {

    PApplet parent;
    boolean player;
    float xpos, ypos, length, height;
    final int baseSpeed = 5;
    int speed;

    public Paddle(PApplet p, boolean isPlayer, int x, int y, int len) {
        parent = p;
        player = isPlayer;
        xpos = x;
        ypos = y;
        length = len;
        height = 7;
        speed = baseSpeed;
    }

    public void draw() {
        parent.fill(0,255,0);
        parent.noStroke();
        parent.rect(xpos, ypos, length, height);
    }

    public void move(Ball b) {
        if (player) {
            if (parent.key == PApplet.CODED) {
                if (parent.keyCode == PApplet.LEFT &&
                        this.xpos > 0) {
                    this.xpos -= speed;
                } else if (parent.keyCode == PApplet.RIGHT &&
                        this.xpos < parent.width - this.length) {
                    this.xpos += speed;
                }
            }
        } else {

            if (b.xpos < this.xpos + length / 2 - 2 &&
                    this.xpos > 0) {
                this.xpos -= speed;
            } else if (b.xpos > this.xpos - length / 2 + 2&&
                    this.xpos < parent.width - this.length) {
                this.xpos += speed;
            }
        }

        // make sure paddle is still on screen
        if (this.xpos < 0) {
            this.xpos = 0;
        } else if (this.xpos > parent.width) {
            this.xpos = parent.width - this.length;
        }
    }


}
