import java.awt.*;

public class Sprite extends Rect
{
   Animation[] animation;

   boolean selected = false;

   boolean moving = false;

   static final int LEFT   = 0;
   static final int RIGHT  = 1;
   static final int UP     = 2;
   static final int DOWN   = 3;
   static final int HIT   = 4;
   
  // static final
   
//   protected double dx, dy;
//   protected double gravity = .5;
   protected boolean jumping;
   //protected boolean falling = true;
  // protected double maxHeight = 60;
 //  protected 
   //  Image sliding =  Toolkit.getDefaultToolkit().createImage("../assets/g_dn_0.gif");
   
   float yvel = 0;
   float g = .5f;
   boolean ontheground = false;
   Circle circle = new Circle( 170, 500, 20, 0);
   Rect rectangle = new Rect(100, 200, 20, 20 );
   
   public int minx = 100;
   public int maxx = 600;
   
   Image slinding;
   
   boolean isSliding = false;

   
   
   
 //  static final int DOWN   = 3;
   
   //jump speed
//   private double jumpSpeed = 10;
//   private double currentJumpSpeed = jumpSpeed;
//   
//   //falling speed
//   private double maxFallSpeed = 30;
//   private double currentFallingSpeed = 0.1;
   
   
   


   int pose = RIGHT;

   
   
   public Sprite(int x, int y, String file, String[] action, int count, int duration)
   {
      super(x, y, 140, 205);
      Rect r = new Rect(50, 50, 100, 100);

      
      animation = new Animation[action.length];
    // count is number of frames, how long every image is shown
    //  animation[i] = new Animation(file + action[i]+"_", count, duration);
    // animation[i] = new Animation(file + action[i]+"_", count, duration);
    //  animation[i] = new Animation(file + action[i]+"_", count, duration);
    //  animation[i] = new Animation(file + action[i]+"_", count, duration);
    //  animation[i] = new Animation(file + action[i]+"_", count, duration);
      
      for(int i = 0; i < action.length; i++)

         animation[i] = new Animation(file + action[i]+"_", count, duration);
   }
   
   public void slide(int dy)
   {  

     //setCoords(x, 500, 200, 100);
    // System.out.println(x);
	  // setLocation(x, 500);
	 y += dy;
	 circle.y = dy;
	 //rectangle.y = (300);

	 moving = true;
	 isSliding = true;
	 //slinding = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ismae\\eclipse-workspace\\Game\\images\\g_dn_1.gif");
   	 pose = DOWN; 

   }
   public void moveLeftBy(int dx)
   {
      x -= dx;
      circle.x -= dx;

      moving = true;

      pose = LEFT;
      
   }
   
   public void moveRightBy(int dx)
   {
	  
	//     if (x <=600 ){
		  
	       x += dx;
	       circle.x +=dx;

		   moving = true;

		   pose = RIGHT; 
	  
	  // } else {
		   
		//   x += 0;
		  // circle.x += 0;
		   
		  // moving = true;
 
	  // }
     

   }
   
   public void startJump(boolean jumping) {
	   
	  if (jumping) {
	   yvel = -20f;
	   ontheground = false;
	   jumping = true;
	   


	  }
	  

	  
   }
   
   public void finishJump(boolean jumping) {
	   
	  
	   if(yvel < -6.0)
		   yvel = -6.0f;
	   

   }
   
   public void updateJump(boolean jumping) {
	   
	  yvel += g;
	  y += yvel;
	  circle.y += yvel;
	 // pose = UP;

	 if (y >= 270.0 && isSliding == false)
	 {
		 y = 270;
		 circle.y = 400;
		 yvel = 0.0f;
		 ontheground = true;
		
		// jumping = false;

	  } 
	 else if (isSliding == true) 
	 {
		 y = 330;
		 circle.y = 500;
		 yvel = 0.0f;
		 ontheground = true;
		// jumping = false;

	  } 
 

   }
   
   public void gotHit()
   {


      moving = true;

      pose = HIT;
      
   }
   
	   
 /* public void jump (double dy) {
	 
		  y -= dy;
		  
		  moving = true;
		  pose = UP;  
  } */

  /* 
   public void jump() {
	   
	   y -= currentJumpSpeed;
	   
	   currentJumpSpeed -= .1;
	   
	   if (currentJumpSpeed <= -2) {
		   currentJumpSpeed = jumpSpeed;
		   
	   }
	   
	   moving = true;
	   pose = UP;
	   
   }  */
  
   /*
   
   public void falling() {
	   y += currentFallingSpeed;
	   
	   if (currentFallingSpeed < maxFallSpeed) {
		   currentFallingSpeed += .5;
	   }
	 
	   if (falling = false)
		   currentFallingSpeed = .5;
	   
	   /*
	   if( y == ontheground) {
		   falling = false;
	   } else {
		   falling = true;
	   } */
	   
  // } */

/*
   
   public void updateJump(boolean Jump){
	   currentJumpSpeed += g;
	   y += currentJumpSpeed;
	   
	   if(y > 270) {
		   y = 270;
		   currentJumpSpeed = 0.0f;
		   ontheground = true;
		   
		   
	   }
	   
   }
   
   */
   
//   public void update() {
//		setx((int)(Game.WIDTH / 2 - x));
//		//tileMap.sety((int)(Game.HEIGHT / 2 - y));
//   }
   
//	public void setx(int i) {
//		
//		x = i;
//		if(x < minx) {
//			x = minx;
//		}
//		if(x > maxx) {
//			x = maxx;
//		}
//		
//	}


   public void draw(Graphics g)
   {
      if(moving)
         g.drawImage(animation[pose].nextImage(), (int)x, (int)y, null);
      else
         g.drawImage(animation[pose].stillImage(), (int)x, (int)y, null);

         moving = false;
         
        // super.draw(g);
//      g.setColor(Color.red);
//      if (selected) g.drawLine((int)x+8, (int)y+h, (int)x+w-5, (int)y+h);
//     ;

   }
}