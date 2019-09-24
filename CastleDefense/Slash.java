import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;

public class Slash extends Spell{
   
   private BufferedImage image;
   private static int fireRate;
   private static int fireRateCounter;
   private int damage;
   private int speed;
   private int width;
   private int height;
   private int duration;
   private int recoil;
   
   public Slash(int x,int y){
      super(x+32,y,0,20,60);
      fireRate=60;
      damage=50;
      speed=0;
      width=20;
      height=60;
      duration=15;
      recoil=20;
      try{
      image = ImageIO.read(new File("C:\\Users\\theos\\Documents\\SourceCode\\CastleDefense\\res\\slash_sprite.png"));
      }catch(IOException e){
         System.out.println("no image");
      }
      
   }
   public void draw(Graphics g){
      g.drawImage(image,getX(),getY(),null);
   }
   public String toString(){
      return "slash";
   }
   public int getDamage(){return damage;}
   public int getRecoil(){return recoil;}
   public void move(){
      if(duration==0)
         Spell.spells.remove(this);
      duration--;
   }
   public boolean lingers(){return true;}
   
   public static boolean canFire(){
      if(fireRateCounter<=0){
         fireRateCounter=fireRate;
         return true;
      }
      fireRateCounter--;
      return false;
   }
   
}