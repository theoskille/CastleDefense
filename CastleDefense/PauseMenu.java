import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
public class PauseMenu{
   public Rectangle startButton;
   private Rectangle incMaxHealthButton;
   private Rectangle incMaxManaButton;
   private Rectangle incVitalityButton;
   private Rectangle incWisdomButton;
   private Rectangle incSpeedButton;
   private int level;
   private Player player;
   private int statsY;
   private int statsYOffset;
   private int statsX;
   
   public PauseMenu(Player player){
      startButton=new Rectangle(Game.WIDTH/2-115,375,200,50);
      level=1;
      this.player=player;
      statsY=175;
      statsYOffset=25;
      statsX=Game.WIDTH/2+20;
      incMaxHealthButton=new Rectangle(statsX+180,statsY+10,20,20);
      incMaxManaButton=new Rectangle(statsX+180,statsY+statsYOffset+10,20,20);
      incWisdomButton=new Rectangle(statsX+180,statsY+statsYOffset*2+10,20,20);
      incVitalityButton=new Rectangle(statsX+180,statsY+statsYOffset*3+10,20,20);
      incSpeedButton=new Rectangle(statsX+180,statsY+statsYOffset*4+10,20,20);
   }
   
   public void render(Graphics g){
   Graphics2D g2d=(Graphics2D) g;
   g.setColor(Color.PINK);
   g.fillRect(Game.WIDTH/8+40,Game.HEIGHT/8-20,Game.WIDTH/2+100,Game.HEIGHT/2+100);
   Font fnt0=new Font("arial",Font.BOLD,50);
   g.setFont(fnt0);
   g.setColor(Color.WHITE);
   g.fillRect(Game.WIDTH/2-150,165,100,100);
   g.drawImage(player.getPlayerSprite(),Game.WIDTH/2-125,180,null);
   g.setColor(Color.BLACK);
   g.drawString("Level "+level,Game.WIDTH/2-100,110);
   Font fnt1=new Font("arial",Font.BOLD,20);
   Font fnt2=new Font("arial",Font.BOLD,30);
   g.setFont(fnt2);
   g.drawString("Stats:",statsX,150);
   g.drawString("Character:",statsX-175,150);
   g.setFont(fnt1);
   g.drawString("Player Level: "+player.getLevel(),statsX,statsY);
   g.drawString("Max Health: "+player.getMaxHealth(),statsX,statsY+statsYOffset);
   g.drawString("Max Mana: "+player.getMaxMana(),statsX,statsY+statsYOffset*2);
   g.drawString("Vitality: "+player.getVitality(),statsX,statsY+statsYOffset*3);
   g.drawString("Widom: "+player.getWisdom(),statsX,statsY+statsYOffset*4);
   g.drawString("Speed: "+player.getSpeed(),statsX,statsY+statsYOffset*5);
   
   g.drawString("Attribute Points: "+player.getAttributePoints(),Game.WIDTH/2-100,350);
   
   g.setFont(fnt2);
   g.drawString("Start",startButton.x+60,startButton.y+30);
   g2d.draw(startButton);
   g2d.draw(incMaxHealthButton);
   g2d.draw(incMaxManaButton);
   g2d.draw(incWisdomButton);
   g2d.draw(incVitalityButton);
   g2d.draw(incSpeedButton);
   }
   public void incLevel(){level++;}
   public void resetLevel(){level=1;}
}