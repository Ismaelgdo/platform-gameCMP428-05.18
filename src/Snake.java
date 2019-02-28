import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Snake extends Rect {

	
    Image image = Toolkit.getDefaultToolkit().getImage("snake2.gif");

	   
	public Snake(int x, int y) {
		super(x, y, 200, 40);
		// TODO Auto-generated constructor stub
	}

	public void moveTowards(Adventurer hero)
	   {
//	    int dx = (int) (hero.x - this.x);
//	    int dy = (int) (hero.y - this.y);
//	    
//	    int d = (dx * dx + dy * dy);
//	      
//
//	    if ( d > 1200)

	    x -= 12;
	    
	   }
	
	public void draw(Graphics g) {
		//super.draw(g);
		g.drawImage(image, (int)super.x, (int)super.y, null);
		
	}
	

}
