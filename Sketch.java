import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

    // initializing array values to be empty with a length of 8 values 
    int[] CircleY = new int[8];
    int[] CircleX = new int[8];
    boolean[] ballHideStatus = new boolean[8];

    // initializing posiiton of hero 
    int heroX = 400;
    int heroY = 700;

    // initializes counter for heros lives 
    int counter = 3;
    int GameStart = 0;
    
    // initializing movemement booleans for hero
    boolean upPressed = false;
    boolean downPressed = false;
    boolean leftPressed = false;
    boolean rightPressed = false;

    boolean GameOver;

    PImage hero;
    PImage villain;
    PImage Heart;
    PImage End;   
    PImage Win; 
    PImage Open;
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(800, 800);

    // loads and resizes images to be used later 
    hero = loadImage("hero.png");
    hero.resize(100,100);

    villain = loadImage("villain.png");
    villain.resize(50,50);

    Heart = loadImage("Heart.png");
    Heart.resize(100,100);

    End = loadImage("End.png");
    End.resize(800,800);

    Win = loadImage("Win.png");
    Win.resize(800,800);

    Open = loadImage("Open.png");
    Open.resize(800,800);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    // sets backround 
    background(210, 255, 173);

    // fills arrays with random values for X and Y positions 
    for (int i = 0; i < CircleX.length; i++){
        CircleX[i] = (int)random(0,750);
        CircleY[i] = (int)random(0,200);
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    // replaces backround each time draw method is ran 
    background(210, 255, 173);

    if (mousePressed){
      GameStart++;
    }

    if (GameStart == 0){
      image(Open, 0, 0);
    }else{

    //triggers if key is pressed, and checks which akey is pressed and which direction to move the hero in 
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
    // produces image of hero on the screen 
      image(hero,heroX,heroY);
    

      // for loop to operate the villain entities 
      for (int i = 0; i < CircleX.length; i++){

        // if down or up arrows are pressed, the speed of the villains changes 
          if (keyPressed){
            if (keyCode == DOWN){
              CircleY[i]++;
            }else if (keyCode == UP){
                CircleY[i]--; 
            }
        }
        CircleY[i]+= 2;

        // if villain entities get off the screen they are sent back to the top 
        if (CircleY[i] > 775){
        CircleX[i] = (int)random(0,750);
        CircleY[i] = (int)random(0,100);
        }

        // if mouse is pressed on one of the villain entities boolean array will be set to true 
        if (mousePressed){
          if ((CircleX[i] < mouseX && CircleX[i] + 50 > mouseX) && (CircleY[i] < mouseY && CircleY[i] + 50 > mouseY)){
            ballHideStatus[i] = true;
          }
          }

          // if boolean array value is false, entitiy will be printed onto screen, if array is true, it will not 
        if (ballHideStatus[i] == false){
          image(villain, CircleX[i], CircleY[i]);
          if ((CircleX[i] - 100 < heroX  && CircleX[i] + 50 > heroX) && (CircleY[i] - 100 < heroY && CircleY[i] + 50 > heroY)){
            CircleY[i] = 0;
            counter--;
          } 
        }

        // if counter reaches lower than 0 game is cut and end screen is shown 
        if (counter <= 0){
          image(End, 0, 0);
          fill(0);
          rect(65,450,300,250);
          textSize(75);
          fill(255);
          text(" PLAY\nAGAIN", 85, 550);
          if (mousePressed){
            if((65 < mouseX && mouseX < 365) && (450 < mouseY && mouseY < 700)){
              counter = 3;
              heroX = 400;
              heroY = 700;
              for (int y = 0; y < CircleY.length; y++){
                CircleY[y] = 0;
                ballHideStatus[y] = false;
                CircleY[y] = (int)random(0,200);
                CircleX[y] = (int)random(0,750);
              }
            }
          }
        }
      }

      // prints a heart on screen as many times as the counter indicates 
      for (int x = 0; x < counter; x++){
        image(Heart, x * 80, 700);
      }

      if (ballHideStatus[0] && ballHideStatus[1] && ballHideStatus[2] && ballHideStatus[3] && ballHideStatus[4] && ballHideStatus[5] && ballHideStatus[6] && ballHideStatus[7]){
        image(Win, 0, 0);
        if (key == ' '){
            counter = 3;
              heroX = 400;
              heroY = 700;
              for (int y = 0; y < CircleY.length; y++){
                CircleY[y] = 0;
                ballHideStatus[y] = false;
                CircleY[y] = (int)random(0,200);
                CircleX[y] = (int)random(0,750);
              }
        }
      }
    }
  }

      // method to run each time keys are pressed to check what key is pressed and operate on boolean values 
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
      
        // method to run each time keys are released to check what key is released and operate on boolean values 
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