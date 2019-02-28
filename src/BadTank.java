public class BadTank extends Tank
{
   public BadTank(double x, double y, int A)
   {
      super(x, y, A);
   }

   public void shoot(Rect shell, Tank tank)
   {
      double ux = Lookup.cos[A];
      double uy = Lookup.sin[A];

      double nx = -uy;
      double ny = ux;

      double vx = tank.x - x;
      double vy = tank.y - y;

      double d = vx*nx + vy*ny;


      if((d < 5) && (d > -5))
      {
         super.shoot(shell);
      }
   }


   public void moveTowards(Tank tank)
   {
      turnTowards(tank);

      double ux = Lookup.cos[A];
      double uy = Lookup.sin[A];

      double nx = -uy;
      double ny = ux;

      double vx = tank.x - x;
      double vy = tank.y - y;

      double d = vx*ux + vy*uy;


      if ( d > 250)

         this.moveForwardBy(4);
   }



   public void turnTowards(Tank tank)
   {
      double ux = Lookup.cos[A];
      double uy = Lookup.sin[A];

      double nx = -uy;
      double ny = ux;

      double vx = tank.x - x;
      double vy = tank.y - y;

      double d = vx*nx + vy*ny;

      if(d > 5)
      {
          A += 3;
          if (A >= 360)  A -= 360;
      }
      if(d < -5)
      {
         A -= 3;
         if(A < 0)  A += 360;
      }

   }

}