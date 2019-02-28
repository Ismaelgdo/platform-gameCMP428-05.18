import java.applet.Applet;
import java.awt.event.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.awt.*;

public class Game extends Applet implements KeyListener, Runnable, MouseListener, MouseMotionListener
{
    Image    off_screen;
    Graphics off_g;

    static final double g = .5;
    protected double maxDY =  10;
    double dy = 15;
    
    int lives = 5;
    int score;
    int counter = 0;

    Adventurer s = new Adventurer(100, -20, 170, 220);
    
    //private TileMap tileMap;
    
    Thread t;
    
    
   //=--------------------------------------------------------Lines/Bounds ---------------------------------=//

   Line groundLimit = new Line(0,480,1000,480);
   Line topLimit = new Line(0,20,1000,20);
   
   //=--------------------------------------------------------Background ---------------------------------=//

   //ImageLayer menuBackground     = new ImageLayer("menuBackground.png", 0, 0, 1); 
   
   
   ImageLayer front     = new ImageLayer("front.png", 0, 0, 10);
   ImageLayer trees2     = new ImageLayer("trees2.png", 0, 0, 20);
   ImageLayer background     = new ImageLayer("background.png", 0, 0, 100);
   ImageLayer foreground     = new ImageLayer("foreground.png", 0, 0, 3);
   ImageLayer fog     = new ImageLayer("fog.png", 0, 0, 80);
   
   Image mainTitle = Toolkit.getDefaultToolkit().getImage("mainScreen.png");
   Image playerSelection = Toolkit.getDefaultToolkit().getImage("CharacterSelection.png");
   Image stats = Toolkit.getDefaultToolkit().getImage("stats-bar.png");
   Image winning = Toolkit.getDefaultToolkit().getImage("winning.png");
   Image game_over = Toolkit.getDefaultToolkit().getImage("gameOver.png");
   
   //=--------------------------------------------------------Enemies ---------------------------------=//
   
   //Enemy wasp = new Enemy(1500, 240, "wasp-left.gif");
  // Image waspenemy = Toolkit.getDefaultToolkit().getImage("wasp-left.gif");
   
   //=--------------------------------------------------------coins / lives ---------------------------------=//
   
   Image heart = Toolkit.getDefaultToolkit().getImage("heart.png");
   
   Coin testcoin = new Coin( 500, 250);
   
   goal doggo   = new goal(7000, 350);
   
   //Enemy doggo = new Enemy(2000, 300, "dog.png");

   Coin[] coins = new Coin[15];
   
   Enemy[] bugs = new Enemy[7];
   Snake[] snake = new Snake[4];
   
   //int[] yvalues = {300, 100};
   
   //=--------------------------------------------------------Sound ------------------------------------------=//
   
   Sound snakeSound = new Sound("C:\\Users\\ismae\\eclipse-workspace\\Game\\Sounds\\Explosion.wav");
   Sound jungle     = new Sound("C:\\Users\\ismae\\eclipse-workspace\\Game\\Sounds\\Jungle.wav");
   //Michael Robinson Homingstar
   
   Sound coinSound  = new Sound("C:\\Users\\ismae\\eclipse-workspace\\Game\\Sounds\\key_pickup.wav");
   Sound menuMusic  = new Sound("C:\\Users\\ismae\\eclipse-workspace\\Game\\Sounds\\menu.wav");
   Sound hit  = new Sound("C:\\Users\\ismae\\eclipse-workspace\\Game\\Sounds\\hit.wav");
   Sound click  = new Sound("C:\\Users\\ismae\\eclipse-workspace\\Game\\Sounds\\buttonClick.wav");

   Sound applause  = new Sound("C:\\Users\\ismae\\eclipse-workspace\\Game\\Sounds\\applause.wav");

   //=-----------------------------------------------------Key Booleans ---------------------------------=//
   
   boolean lt_Pressed = false;
   boolean rt_Pressed = false;
   boolean up_Pressed = false;
   boolean dn_Pressed = false;
   boolean isjumping = false;
   
   
   //=-----------------------------------------------------Game States ---------------------------------=//
   
   final int WIDTH = 1000;
   final int HEIGHT = 635;
   
   
   final int mainScreen = 0;
   final int playerSelect = 1;
   final int levelScreen1 = 2;
   final int levelScreen2 = 3;
   final int winingState = 4;
   final int gameOver = 5;
   
   int GameState = mainScreen;
   
   boolean snakeBite = false;
   
   //=-----------------------------------------------------Rect and Button Images -------------------------------------=//
 
   Rect startButton = new Rect(230, 500,  310, 80);
   Rect stanleyRect = new Rect(170, 250,  180, 250);
   Rect salleyRect  = new Rect(610, 250,  180, 250);
   Rect menu        = new Rect(840, 550, 150, 60);
   
   Image playButton =  Toolkit.getDefaultToolkit().getImage("playButton.png");
   Image scoreImage =  Toolkit.getDefaultToolkit().getImage("coinsCounter.png");
   Image hourglass =  Toolkit.getDefaultToolkit().getImage("hourglass.png");
   Image menuButton =  Toolkit.getDefaultToolkit().getImage("menu.png");

   boolean overlap = false;

   int mx;
   int my;

   //=-----------------------------------------------------Init -----------------  ---------------------------------------=//
   
   public void init()
   {
      off_screen = this.createImage(WIDTH, HEIGHT);
      off_g      = off_screen.getGraphics();

      requestFocus();
      addKeyListener(this);

      addMouseListener(this);
      addMouseMotionListener(this);
      
      jungle.loop();
      
      //tileMap = new TileMap("testmap.txt", 64);
      
      for(int i = 0; i < coins.length; i++)

          coins[i] = new Coin(650 + i*400, getRandom(300, 100, 300));
      
      for(int i = 0; i < bugs.length; i++)

          bugs[i] = new Enemy(1100 + i*2000, getRandom(100, 250, 80), getRandomEnemy("wasp-left.gif", "bug-left.gif" ));
      
      
      for(int i = 0; i < snake.length; i++)

    	  snake[i] = new Snake(4000 + i*7000, 420);
      
      t = new Thread(this);

      t.start();
   }

   public void run()
   {

       
      while(true)
      {

    	  
			if(GameState == levelScreen1) {	
				
				 for(int i = 0; i < snake.length; i++)
				 snake[i].moveTowards(s);
			
				 if(lt_Pressed)
	               {
	                 
	                if (s.x <=100 ) {
	                	   
	                   s.x = 100;
	                   s.moveLeftBy(0);
	                	   
	               } else {
	            	   
		 	      	for(int i = 0; i < bugs.length; i++)
		 		      	bugs[i].moveBy(4, 0);
		 	      	
		 	      	for(int i = 0; i < coins.length; i++)
		 		      	coins[i].moveBy(4, 0);
		 	      	
	            	   
	            	 s.moveLeftBy(2);
	                 background.moveRightBy(30);
	                 fog.moveRightBy(30);
	                 trees2.moveRightBy(30);
	                 front.moveRightBy(30);
	                 foreground.moveRightBy(30);

	                 
	                  }
	               }
				 
	               if(rt_Pressed)
	               {  
	            	
	            	doggo.moveBy(-4, 0);
	            	
	 	      		for(int i = 0; i < bugs.length; i++)
	 		      		 bugs[i].moveBy(-4, 0);
	 	      		
	 	      		for(int i = 0; i < coins.length; i++)
	 		      		 coins[i].moveBy(-4, 0);
	 	      		
	 		      			 
	            	 if (s.x >= 600) {
	            		 
	            		s.moveRightBy(0);
	                    background.moveLeftBy(30);
	                    fog.moveLeftBy(30);
	                    trees2.moveLeftBy(30);
	                    front.moveLeftBy(30);
	                    foreground.moveLeftBy(30);
	                    
	            	 }
	            	 else {
	            	 s.moveRightBy(2); 
	                 background.moveLeftBy(30);
	                 fog.moveLeftBy(30);
	                 trees2.moveLeftBy(30);
	                 front.moveLeftBy(30);
	                 foreground.moveLeftBy(30);
	            	 }
	               }
	               
	               
	               if (dn_Pressed)
	               {
	            	 s.slide(10);
	            	// s.y = 500;
	            	   
	               }
	               
	      		 
	      		 if ( s.overlaps(testcoin)) {
	      			 testcoin.moveBy(-1000, -1000);
	      		 }
	      		 
	      		for(int i = 0; i < coins.length; i++)
	      		 if ( s.overlaps(coins[i])) {
	      			coinSound.play();
	      			coins[i].moveBy(-1000, -1000);
	      			score += 5;
	      		 }
	      		
	      		
	      		for(int i = 0; i < bugs.length; i++)
		      		 if ( s.overlaps(bugs[i])) {
		      			hit.play();
		      			try {
		      				t.sleep(100);
		      				s.gotHit();
		      				
		      				
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		      			bugs[i].moveBy(-1000, -1000);
		      			lives--;

		      			//s.gotHit();
		      		 }
	      		

	      		for(int i = 0; i < snake.length; i++)
		      		 if ( s.overlaps(snake[i])) {      			
		      			snakeSound.play();
		      			s.gotHit();
		      			counter += 1;
		      			
		      			if(counter >= 15) {
		      				lives -= 1;
		      			}
			      		
		      		 } 

	      		resetCounter();
	      		
	      		/*
	      		new Timer().schedule(new TimerTask() {          
	      		    public void run() {
	      		    	for(int i = 0; i < snake.length; i++)
	   		      		if ( s.overlaps(snake[i])) {
			      			snakeSound.play();
			      			s.gotHit();
			      			
	   		      		}
	      		    	lives-= 1;
	      		        // this code will be executed after 2 seconds       
	      		    }
	      		}, 1000); */
	      		

	      		
	      		 
	      		
	      		if ( s.overlaps(doggo)) {
	      			applause.play();
	      			try {
	      				t.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	      			GameState = winingState;
	      		 }
	               
	      		if(lives <= 0) {
	      			try {
	      				t.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	      			GameState = gameOver;
	      		}

	            s.updateJump(isjumping);
	            

	            
			}
             

      
       //    double d = groundLimit.distanceTo(s.x, s.y);

          //  if(s.circle.r < d)
           // s.moveBy((int)((s.circle.r-d)*groundLimit.xN), (int)((s.circle.r-d)*groundLimit.yN) );
            
            
            double d2 = topLimit.distanceTo(s.x, s.y);
            
            if(s.circle.r > d2)
            s.moveBy((int)(-(s.circle.r-d2)*topLimit.xN), (int)((s.circle.r-d2)*topLimit.yN) );
                                            

         repaint();

         try
         {
             t.sleep(15);
             
         }
         catch(Exception x){}
         

      }
      
      

   }
   
  

   public void update(Graphics g)
   {
      off_g.clearRect(0, 0, WIDTH, HEIGHT);

      paint(off_g);

      g.drawImage(off_screen, 0, 0, null);
     // tileMap.update();
      
      
      
   }
   
   public void resetCounter(){
	   
	   if(counter >= 16)
	   counter = 0;
	   
	   
   }
   
   public void restart() {

		lives = 5;
		//s.x = -100;
		score = 0;

		//new Game();
		
        
   }
   

   



   public void paint(Graphics g)
   {
  
	    
	    this.setSize(WIDTH, HEIGHT);
	    g.setFont(new Font("monaco", Font.PLAIN, 32));
	    
	    if (GameState == mainScreen) {
	    g.drawImage(mainTitle, 0, 0, null);
	    g.drawImage(playButton, (int) startButton.x, (int) startButton.y, null);
	    //startButton.draw(g);
	    g.setColor(Color.WHITE);
	    	
	    }
	    
	    else if (GameState == playerSelect) {
	    
	    	g.drawImage(playerSelection, 0, 0, null);
	    	//stanleyRect.draw(g);
	    	//salleyRect.draw(g);
	    	
	    }
	    
	    else if ((GameState == levelScreen1) || (GameState == levelScreen2)) {
	    	
	    	
	    	
	         background.draw(g);
	         fog.draw(g);
	         trees2.draw(g);
	       //  tileMap.draw(g);
	         front.draw(g);
	         
	         
	         //groundLimit.draw(g);
	         //topLimit.draw(g);
	        // g.drawImage(waspenemy, 1000, 200, null);
	         //s.circle.draw(g);
	         //s.rectangle.draw(g);
	         foreground.draw(g);
	        // wasp.draw(g);
	       //  testcoin.draw(g);
	       //  coins.draw(g);
	         coins[1].draw(g);

	         for(int i = 0; i < coins.length; i++)
	        	 coins[i].draw(g); 
	         
	         for(int i = 0; i < bugs.length; i++)
	        	 bugs[i].draw(g); 
	         
	         //for(int i = 0; i < bugs.length; i++)
	        	// bugs[i].draw(g); 
	         
	         for(int i = 0; i < snake.length; i++)
	        	 snake[i].draw(g);
	         
	         doggo.draw(g);
	        // for(int i = 0; i > )
	         s.draw(g);
	         
	         g.drawImage(stats, 0, 563, null);
	         g.drawImage(scoreImage, 60, 570, null);
	         g.drawImage(hourglass, 400, 580, null);
	        // g.drawImage(menuButton,(int)menu.x, (int)menu.y , null);
	       //  menu.draw(g);
	         
			for(int i = lives; i > 0; i--)	{
				g.drawImage(heart , 950-(50*i),  585, 35, 25, this);
			}
			
			//String score_str = Integer.toString(score);
			g.drawString(" " + score, 150, 610);
	         
	         

	         
	    	
	    }
	   
	    else if (GameState == levelScreen1) {

	         s.draw(g);
	         for(int i = 0; i > coins.length; i++) {
	        	coins[i].draw(g);
	         }

	    }
	     
	    else if (GameState == winingState) {
	    	g.drawImage(winning, 0, 0, null);
	    	g.drawImage(menuButton, (int) menu.x, (int) menu.y, 150, 60, null);
	    	
	    }
	    
	    else if (GameState == gameOver) {
	    	
	    	g.drawImage(game_over, 0, 0, null);
	    	//menu.draw(g);
	    	g.drawImage(menuButton, (int) menu.x, (int) menu.y, 130, 60, null);
	    	
	    }
	    



   }
   
   public static int getRandom(int val1, int val2, int val3) {
	   
	    int[] yVal = new int[]{val1, val2, val3};
	   
	    int rnd = (int)(Math.random()*yVal.length);
	    return yVal[rnd];
	}
   
   public static String getRandomEnemy(String val1, String val2) {
	   
	    String[] enemies = new String[]{val1, val2};
	   
	    double rnd = (Math.random()*enemies.length);
	    return enemies[(int) rnd];
	}
  
   
   //=-----------------------------------------------------Mouse Events ---------------------------------=//
   public void snakeBite() {
	   
       if(snakeBite = true) {
       	
       	lives -= 1;
       	
       }
     //  snakeBite = false;
   }
   
   public void mousePressed(MouseEvent e)
   {
		int mx = e.getX();
		int my = e.getY();
		
		if(GameState == mainScreen) {
			if(startButton.inRect(mx, my)){
				click.play();
				GameState = playerSelect;
				
			}
		}
		
		else if (GameState == playerSelect) {
			if (stanleyRect.inRect(mx, my)){
				click.play();
				GameState = levelScreen1;
				
			}
		}
		
		else if ((GameState == gameOver) ||  (GameState == winingState))  {
			if (menu.inRect(mx, my)){
				click.play();
				GameState = mainScreen;
				restart(); 

				
				
			}
		}
   }

   public void mouseMoved(MouseEvent e)
   {
	   
   }

   public void mouseDragged(MouseEvent e)
   {
	   
   }

   public void mouseReleased(MouseEvent e)
   {
   }

   public void mouseClicked(MouseEvent e)
   {
   }

   public void mouseEntered(MouseEvent e)
   {
   }

   public void mouseExited(MouseEvent e)
   {
	   
   }

   //=-----------------------------------------------------Key Events ---------------------------------=//

   public void keyPressed(KeyEvent e)
   {
      int code = e.getKeyCode();

      if(code == e.VK_LEFT)     lt_Pressed = true;
      if(code == e.VK_RIGHT)    rt_Pressed = true;
     
      if(code == e.VK_UP)     
      {
    	  if (s.y >= 270) {
        	  s.startJump(isjumping = true);
        	  up_Pressed = true;  
    	  }

      }
      if(code == e.VK_DOWN){
    	  
    	if ( s.y > 200 ) {
    	  dn_Pressed = true;
    	  s.isSliding = true;
    	  }
    	  
      }

   }

   public void keyReleased(KeyEvent e)
   {
      int code = e.getKeyCode();

      if(code == e.VK_LEFT)     lt_Pressed = false;
      if(code == e.VK_RIGHT)    rt_Pressed = false;
      if(code == e.VK_UP)  
      {
    	   s.finishJump(isjumping = false);
    	   up_Pressed= false;

      }
      if(code == e.VK_DOWN)    
      {
    	  dn_Pressed = false;
    	  s.isSliding = false;
    	 /// System.out.println(s.x);
      }

   }

   public void keyTyped(KeyEvent e) {   }


}