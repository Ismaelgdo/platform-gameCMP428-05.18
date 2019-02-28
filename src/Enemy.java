import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Enemy extends Rect {
	
   Image image;
   int x;
   int y;
   
	public Enemy(int x, int y, String location) {
		super(x, y, 50, 50);
		
//		 this.x = x;
//	     this.y = y;
		
		image = Toolkit.getDefaultToolkit().getImage(location);
		// TODO Auto-generated constructor stub
	}
	

	
	public void draw(Graphics g) {
		
		g.drawImage(image, (int)super.x, (int)super.y, null);
		
	}

}
