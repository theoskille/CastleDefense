import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;

public class Entity{
  
   private int x;
   private int y;
   private int vx;
   private int vy;
   private int speed;
   private int height;
   private int width;
   
    public Entity(int x,int y,int vx,int vy,int speed,int w,int h){
      this.x=x;
      this.y=y;
      this.vx=vx;
      this.vy=vy;
      this.speed=speed;
      height=h;
      width=w;
     
      
   }
   public void setVelocityX(int vx){
      this.vx=vx;
   }
   public void setVelocityY(int vy){
      this.vy=vy;
   }
   public void setX(int x){
      this.x=x;
   }
   public void setY(int y){
      this.y=y;
   }
   public int getSpeed(){return speed;}
   public int getX(){return x;}
   public int getY(){return y;}
   public int getVX(){return vx;}
   public int getVY(){return vy;}
   public Rectangle getHitBox(){return new Rectangle(x,y,width,height);}
   
    public void move(){
      x+=vx;
      y+=vy;
   }
   public void draw(Graphics g){return;}
   
   
}