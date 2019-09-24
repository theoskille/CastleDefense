import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;

public class FireBall extends Spell{
   
   private BufferedImage image;
   private static int fireRate;
   private static int fireRateCounter;
   private int damage;
   private int speed;
   private int width;
   private int height;
   
   
   
   
   public FireBall(int x,int y){
      super(x+25,y,2,20,20);
      fireRate=120;
      damage=50;
      speed=2;
      width=20;
      height=20;
      try{
      image = ImageIO.read(new File("C:\\Users\\theos\\Documents\\SourceCode\\CastleDefense\\res\\fireball_sprite.png"));
      }catch(IOException e){
         System.out.println("no image");
      }
      
   }
   public void draw(Graphics g){
      g.drawImage(image,getX(),getY(),null);
   }
   public String toString(){
      return "FireBall";
   }
   public int getDamage(){return damage;}
   public static boolean canFire(){
      if(fireRateCounter==0){
         fireRateCounter=fireRate;
         return true;
      }
      fireRateCounter--;
      return false;
   }
}