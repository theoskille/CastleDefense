import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;

public class StatBar{
   private final int WIDTH;
   private final int HEIGHT;
   private final int BOTTOM;
   private int health;
   private int level;
   private int mana;
   private int maxMana;
   private int speed;
   private int maxHealth;
   private int defense;
   private int vitality;
   private int exp;
   private int expToNext;
   
   public StatBar(){
      WIDTH=Game.WIDTH;
      HEIGHT=100;
      BOTTOM=Game.HEIGHT-130;
   }
   public void render(Graphics g){
      g.setColor(Color.PINK);
      g.fillRect(0,BOTTOM,Game.WIDTH,100);
      g.setColor(Color.BLACK);
      Font fnt1=new Font("arial",Font.BOLD,30);
      g.setFont(fnt1);
      g.drawString("health: "+health,20,BOTTOM+30);
      g.drawString("mana: "+mana,20,BOTTOM+60);
      g.drawString("exp "+exp+"/"+expToNext,400,BOTTOM+60);
      g.drawString("level: "+level,500,BOTTOM+30);
   }
   public void update(int health,int mana,int exp,int expToNext,int level){
      this.health=health;
      this.mana=mana;
      this.exp=exp;
      this.expToNext=expToNext;
      this.level=level;
   }
}