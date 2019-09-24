import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;

public class Spell extends Entity{
   public static ArrayList<Spell> spells;
   
   public Spell(int x,int y,int speed,int width,int height){
      super(x,y,speed,0,speed,width,height);
      spells.add(this);
   }
   public int getDamage(){return 0;}
   public int getRecoil(){return 0;}
   public boolean lingers(){return false;}
}

