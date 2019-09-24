import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class Shroom extends Enemy{
   private BufferedImage image;
   private final int speed;
   private int stall;
   public Shroom(int x,int y){
      super(x,y,-1,32,32,100,25,10);
      speed=-1;
      Random r=new Random();
      stall=r.nextInt(140);
      try{
      image = ImageIO.read(new File("C:\\Users\\theos\\Documents\\SourceCode\\CastleDefense\\res\\shroom_sprite.png"));
      }catch(IOException e){
         System.out.println("no image");
      }

   }
   public void draw(Graphics g){
     g.drawImage(image,getX(),getY(),null);
   }
   public void move(){
      if(stall<=60){
         stall++;
         return;
      }
      setX(getX()+speed);
      stall++;
      if(stall>=140)
         stall=0;
      
         
   }
}