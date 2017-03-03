import processing.core.PApplet;

public class Pong extends PApplet {
    
    final int bg = 0;
    final int paddleLen = 60;
    boolean newGame;
    Paddle player, ai;
    Ball ball;
    int playerScore, aiScore;
    int d;
    
    public void settings() {
        size(400, 400);
        playerScore = 0;
        aiScore = 0;
    }
    
    public void setup() {
        player = new Paddle(this, true, (width - paddleLen) / 2, height - 30, paddleLen);
        ai = new Paddle(this, false, (width - paddleLen) / 2, 30, paddleLen);
        ball = new Ball(this, width / 2, height / 2);
        newGame = true;
        d = 3;
    }
    
    public void draw() {
        
        
        
        background(bg);
        textSize(12);
        text(playerScore, 20, 20);
        text(aiScore, width - 20, 20);
        
        player.move(ball);
        ai.move(ball);
        
        ball.move(player, ai);
        
        player.draw();
        ai.draw();
        ball.draw();
        
        isBallOffScreen();
        
        if (newGame) {
            textSize(32);
            fill(255, 255, 255);
            text(d--, width / 2, height / 2);
            delay(1000);
            if (d == -1)
                newGame = false;
        }
    }
    
    public void isBallOffScreen() {
        
        if (ball.ypos < 0) {
            playerScore++;
            setup();
        } else if (ball.ypos > height) {
            aiScore++;
            setup();
        }
        
    }
    
    public void keyPressed() {
        if (key == CODED) {
            if (keyCode == LEFT || keyCode == RIGHT) {
                player.speed = player.baseSpeed;
            }
        }
        
    }
    
    public void keyReleased() {
        if (key == CODED) {
            if (keyCode == LEFT || keyCode == RIGHT) {
                player.speed = 0;
            }
        } else {
            if (key == 'q' || key == 'Q') {
                exit();
            }
        }
    }
    
    public static void main(String[] args) {
        PApplet.main("Pong");
    }

}
