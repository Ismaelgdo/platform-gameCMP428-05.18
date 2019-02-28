import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class goal extends Rect {

	   Image image = Toolkit.getDefaultToolkit().getImage("dog.png");
	   
	   public goal(int x, int y) {
			super(x, y, 64, 64);

		}
		
		public void draw(Graphics g) {
			//super.draw(g);
			g.drawImage(image, (int)super.x, (int)super.y, 120, 120, null);
			
		}
}