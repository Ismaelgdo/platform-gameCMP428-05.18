
public class Adventurer extends Sprite
{
   static String[] action = {"lt","rt", "up", "dn", "ht"};

   public Adventurer(int x, int y, int width, int height)
   {
      super(x, y, "g_", action, 6, 15);

   }

}