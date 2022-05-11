import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {


    int Ent = 10;
    // initializing array values to be empty with a length of 8 values 
    int[] intCircleY = new int[Ent];
    int[] intCircleX = new int[Ent];
    boolean[] blnBallHideStatus = new boolean[Ent];

    // initializing posiiton of hero 
    int intHeroX = 400;
    int intHeroY = 700;

    // initializes counters
    int intCounter = 3;
    int intGameStart = 0;
    int intGameEndCount;
    
    // initializing booleans
    boolean blnUpPressed = false;
    boolean blnDownPressed = false;
    boolean blnLeftPressed = false;
    boolean blnRightPressed = false;

    // initializes the loading of images
    PImage hero;
    PImage villain;
    PImage Heart;
    PImage End;   
    PImage Win; 
    PImage Open;
    PImage boink;
    PImage Back;
	
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

    boink = loadImage("boink.png");
    boink.resize(100,100);

    End = loadImage("End.png");
    End.resize(800,800);

    Win = loadImage("Win.png");
    Win.resize(800,800);

    Open = loadImage("Open.png");
    Open.resize(800,800);

    Back = loadImage("Back.png");
    Back.resize(800,800);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    // sets backround 
    image(Back, 0, 0);

    // fills arrays with random values for X and Y positions 
    for (int i = 0; i < intCircleX.length; i++){
      intCircleX[i] = (int)random(0,750);
        intCircleY[i] = (int)random(0,200);
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    // replaces backround each time draw method is ran 
    image(Back, 0, 0);

    if (mousePressed){
      intGameStart++;
      }

    if (intGameStart == 0){
      image(Open, 0, 0);
    }else{

    //triggers if key is pressed, and checks which akey is pressed and which direction to move the hero in 
    if (keyPressed){
      if (blnUpPressed) {
        intHeroY-=3;
      }
      if (blnDownPressed) {
        intHeroY+=3;
      }
      if (blnLeftPressed) {
        intHeroX-=3;
      }
      if (blnRightPressed) {
        intHeroX+=3;
      }
    }
    // produces image of hero on the screen 
      image(hero,intHeroX,intHeroY);
    

      // for loop to operate the villain entities 
      for (int i = 0; i < intCircleX.length; i++){

        // if down or up arrows are pressed, the speed of the villains changes 
          if (keyPressed){
            if (keyCode == DOWN){
              intCircleY[i]++;
            }else if (keyCode == UP){
              intCircleY[i]--; 
            }
        }
        intCircleY[i]+= 2;

        // if villain entities get off the screen they are sent back to the top 
        if (intCircleY[i] > 775){
        intCircleX[i] = (int)random(0,750);
        intCircleY[i] = (int)random(0,100);
        }

        // if hero moves off of screen it gets recentered 
        if (intHeroX < 0 || intHeroX > 700 || intHeroY > 750){
        intHeroX = 400;
        intHeroY = 700;
        }
        // if hero moves to top of screen, it gets recentered to middle and gains an extra life 
        if (intHeroY < 0){
          intHeroX = 400;
          intHeroY = 700;
          intCounter++;
        }

        // if mouse is pressed on one of the villain entities boolean array will be set to true 
        if (mousePressed){
          if ((intCircleX[i] < mouseX && intCircleX[i] + 50 > mouseX) && (intCircleY[i] < mouseY && intCircleY[i] + 50 > mouseY)){
            blnBallHideStatus[i] = true;
            image(boink, mouseX - 100, mouseY - 50);
          }
          }

          // if boolean array value is false, entitiy will be printed onto screen, if array is true, it will not 
        if (blnBallHideStatus[i] == false){
          image(villain, intCircleX[i], intCircleY[i]);
          if ((intCircleX[i] - 100 < intHeroX  && intCircleX[i] + 50 > intHeroX) && (intCircleY[i] - 100 < intHeroY && intCircleY[i] + 50 > intHeroY)){
            intCircleY[i] = 0;
            intCounter--;
          } 
        }

        // if counter reaches lower than 0 game is cut and end screen is shown to press to play again 
        if (intCounter <= 0){
          image(End, 0, 0);
          fill(0);
          rect(65,450,300,250);
          textSize(75);
          fill(255);
          text(" PLAY\nAGAIN", 85, 550);
          if (mousePressed){
            if((65 < mouseX && mouseX < 365) && (450 < mouseY && mouseY < 700)){
              intCounter = 3;
              intHeroX = 400;
              intHeroY = 700;
              for (int y = 0; y < intCircleY.length; y++){
                intCircleY[y] = 0;
                blnBallHideStatus[y] = false;
                intCircleY[y] = (int)random(0,200);
                intCircleX[y] = (int)random(0,750);
              }
            }
          }
        }
      }

      // prints a heart on screen as many times as the counter indicates 
      for (int x = 0; x < intCounter; x++){
        image(Heart, x * 80, 700);
      }


      // checks if all ballhide statuses are set to true 
      for (int x = 0; x < intCircleX.length; x++){
        if (blnBallHideStatus[x]){
          intGameEndCount ++;
        }
      }
      // if all all ball hide statuses are true, end screen will be shown 
      if (intGameEndCount == intCircleX.length){
        image(Win, 0, 0);
        if (key == ' '){
          intCounter = 3;
            intHeroX = 400;
            intHeroY = 700;
              for (int y = 0; y < intCircleY.length; y++){
                intCircleY[y] = 0;
                blnBallHideStatus[y] = false;
                intCircleY[y] = (int)random(0,200);
                intCircleX[y] = (int)random(0,750);
              }
        }
      }
    }
    intGameEndCount = 0;
  }

      // method to run each time keys are pressed to check what key is pressed and operate on boolean values 
      public void keyPressed() {
        if (key == 'w') {
          blnUpPressed = true;
        }
        else if (key == 's') {
          blnDownPressed = true;
        }
        else if (key == 'a') {
          blnLeftPressed = true;
        }
        else if (key == 'd') {
          blnRightPressed = true;
        }
      }
      
        // method to run each time keys are released to check what key is released and operate on boolean values 
      public void keyReleased() {
        if (key == 'w') {
          blnUpPressed = false;
        }
        else if (key == 's') {
          blnDownPressed = false;
        }
        else if (key == 'a') {
          blnLeftPressed = false;
        }
        else if (key == 'd') {
          blnRightPressed = false;
        }
      }
}