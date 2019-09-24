import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class Chomp extends Enemy{
   private BufferedImage image;
   private final int speed;
   private int turn;
   public Chomp(int x,int y){
      super(x,y,-1,32,32,100,25,15);
      speed=-1;
      Random r=new Random();
      turn=r.nextInt(240);
      try{
      image = ImageIO.read(new File("C:\\Users\\theos\\Documents\\SourceCode\\CastleDefense\\res\\chomp_sprite.png"));
      }catch(IOException e){
         System.out.println("no image");
      }

   }
   public void draw(Graphics g){
     g.drawImage(image,getX(),getY(),null);
   }
   public void move(){
      setX(getX()+speed);
      if(turn<=120){
         setY(getY()-Math.abs(speed));
         turn++;
      }else if(turn>120){
         setY(getY()+Math.abs(speed));
         turn++;
         if(turn>=240)
            turn=0;
      }
      
   }
}