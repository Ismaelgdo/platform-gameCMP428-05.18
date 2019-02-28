import java.awt.*;

public class Circle
{

   double x;
   double y;
   double r;

   int A;

   public Circle(double x, double y, double r, int A)
   {
      this.x = x;
      this.y = y;
      this.r = r;
      this.A = A;
      
   }


   public void moveBy(int dx, int dy)
   {
      x += dx;
      y += dy;
   }

   public void moveForwardBy(int distance)
  {
     x += distance * Lookup.cos[A];
     y += distance * Lookup.sin[A];
  }

  public void rotateLeftBy(int dA)
  {
     A -= dA;

     if(A < 0)     A += 360;
  }

  public void rotateRightBy(int dA)
  {
     A += dA;

     if(A >= 360)  A-= 360;
  }



   public void draw(Graphics g)
   {
      g.drawOval((int)(x-r), (int)(y-r), (int)(2*r), (int)(2*r));

      double cosA = Lookup.cos[A];
      double sinA = Lookup.sin[A];

      g.drawLine((int)x, (int)y, (int)(x + r*cosA), (int)(y + r*sinA));
   }

}




