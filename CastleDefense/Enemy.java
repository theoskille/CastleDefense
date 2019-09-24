import java.awt.Graphics;
import java.util.ArrayList;

public class Enemy extends Entity{
   private int health;
   private int damage;
   private int expGiven;
   public static ArrayList<Enemy> enemies;
   public Enemy(int x,int y,int speed,int h,int w,int health,int damage,int expGiven){
      super(x,y,0,0,speed,h,w);
      enemies.add(this);
      this.health=health;
      this.damage=damage;
      this.expGiven=expGiven;
      
   }
   
   public void move(){
      setX(getX()+getSpeed());
   }
   public void draw(Graphics g){
      g.fillRect(getX(),getY(),32,32);
   }
   public String toString(){
      return "Enemy";
   }
   public void damage(int damage,int recoil){
      health-=damage;
      setX(getX()+recoil);
   }
   public void destroy(){
      if(health<=0)
         enemies.remove(this);
   }
   public int getDamage(){
      return damage;
   }
   public int getExp(){return expGiven;}
}