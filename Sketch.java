import org.w3c.dom.Text;
import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
	
    int[] CircleY = new int[8];
    int[] CircleX = new int[8];
    boolean[] ballHideStatus = new boolean[8];

    int heroX = 400;
    int heroY = 700;
    
    boolean upPressed = false;
    boolean downPressed = false;
    boolean leftPressed = false;
    boolean rightPressed = false;

    int counter = 3;

    PImage hero;
    PImage villain;
    PImage Heart;

    
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(800, 800);

    hero = loadImage("hero.png");
    hero.resize(100,100);

    villain = loadImage("villain.png");
    villain.resize(50,50);

    Heart = loadImage("Heart.png");
    Heart.resize(100,100);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(210, 255, 173);

    for (int i = 0; i < CircleX.length; i++){
        CircleX[i] = (int)random(0,800);
    }
    for (int i = 0; i < CircleY.length; i++){
        CircleY[i] = (int)random(0,200);
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    
    background(210, 255, 173);

    if (keyPressed){
      if (upPressed) {
        heroY-=3;
      }
      if (downPressed) {
        heroY+=3;
      }
      if (leftPressed) {
        heroX-=3;
      }
      if (rightPressed) {
        heroX+=3;
      }
    }
      image(hero,heroX,heroY);
    

      for (int i = 0; i < CircleX.length; i++){

          if (keyPressed){
            if (keyCode == DOWN){
              CircleY[i]++;
            }else if (keyCode == UP){
                CircleY[i]--; 
            }
        }
        CircleY[i]+= 2;

        if (CircleY[i] > 775){
            CircleY[i] = 0;
        }

        if (mousePressed){
          if ((CircleX[i] < mouseX && CircleX[i] + 50 > mouseX) && (CircleY[i] < mouseY && CircleY[i] + 50 > mouseY)){
            ballHideStatus[i] = true;
          }
          }

        if (ballHideStatus[i] == false){
          image(villain, CircleX[i], CircleY[i]);
          if ((CircleX[i] - 100 < heroX  && CircleX[i] + 50 > heroX) && (CircleY[i] - 100 < heroY && CircleY[i] + 50 > heroY)){
            CircleY[i] = 0;
            counter--;
          }
        }


        if (counter <= 0){
          background(255);
        }
      }

      for (int x = 1; x <= counter; x++){
        image(Heart, x * 80, 700);
      }

      }

      public void keyPressed() {
        if (key == 'w') {
          upPressed = true;
        }
        else if (key == 's') {
          downPressed = true;
        }
        else if (key == 'a') {
          leftPressed = true;
        }
        else if (key == 'd') {
          rightPressed = true;
        }
      }
      
      public void keyReleased() {
        if (key == 'w') {
          upPressed = false;
        }
        else if (key == 's') {
          downPressed = false;
        }
        else if (key == 'a') {
          leftPressed = false;
        }
        else if (key == 'd') {
          rightPressed = false;
        }
      }


}