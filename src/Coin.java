import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Coin extends Rect {

	   Image image = Toolkit.getDefaultToolkit().getImage("coin.gif");
	   int x;
	   int y;
	   
		public Coin(int x, int y) {
			super(x, y, 40, 40);
			
//			 this.x = x;
//		     this.y = y;

		}
		
		public void draw(Graphics g) {
			//super.draw(g);
			g.drawImage(image, (int)super.x, (int)super.y, null);
			
		}
}
