import processing.core.PApplet;

public class Ball {
    
    PApplet parent;
    float xpos, ypos, size;
    float xdir, ydir;
    float baseXdir, xspeed, yspeed;
    
    public Ball() {
        
    }
    
    public Ball(PApplet p, int x, int y) {
        parent = p;
        xpos = x;
        ypos = y;
        size = 10;
        
        xspeed = 5;
        yspeed = 4;
        do {
            xdir = parent.random(0, 100) % 3;
        } while (xdir > 0.5 && xdir < 1.5);
        baseXdir = Math.abs(xdir);
        do {
            ydir = parent.random(0, 100) % 3;
        } while (ydir > 0.5 && ydir < 1.5);
        xdir -= 1;
        ydir -= 1;
    }
    
    public void draw() {
        parent.fill(255, 0, 0);
        parent.stroke(0);
        parent.ellipse(xpos, ypos, size, size);
    }
    
    public void move(Paddle p, Paddle a) {
        
        float tmp = xpos + xdir * xspeed;
        if (tmp > parent.width || tmp < 0 ) {
            xdir *= -1;
        }
        xpos += xdir * xspeed;
        
        // if the ball is inside of the player (bottom) paddle
        // set the ball on the paddle, then set ball direction to up
        if (this.xpos >= p.xpos && this.xpos <= p.xpos + p.length &&
                this.ypos >= p.ypos && this.ypos <= p.ypos + p.height) {
            this.ypos = p.ypos - 1;
            this.ydir = -1;
            
            // change xspeed based on where the paddle hits
            float percentOffCenter = (this.xpos - (p.xpos + p.length / 2)) / (p.length / 2);
            this.xdir = percentOffCenter * 2 * this.baseXdir;
            
        // same thing with ai (top) paddle, only just under the paddle
        // and moving down
        } else if (this.xpos >= a.xpos && this.xpos <= a.xpos + a.length &&
                    this.ypos >= a.ypos && this.ypos <= a.ypos + a.height) {
            this.ypos = a.ypos + a.height + 1;
            this.ydir = 1;
            
         // change xspeed based on where the paddle hits
            float percentOffCenter = (this.xpos - (a.xpos + a.length / 2)) / (a.length / 2);
            this.xdir = percentOffCenter * 2 * this.baseXdir;
        }
        ypos += ydir * yspeed;
    }
    

}