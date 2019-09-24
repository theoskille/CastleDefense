import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
public class Menu{
   public Rectangle playButton;
   public Rectangle quitButton;
   
   public Menu(){
      playButton=new Rectangle(Game.WIDTH/2-120,150,200,50);
      quitButton=new Rectangle(Game.WIDTH/2-120,250,200,50);
   }
   
   public void render(Graphics g){
   Graphics2D g2d=(Graphics2D) g;
   g.setColor(Color.WHITE);
   g.fillRect(0,0,Game.WIDTH,Game.HEIGHT);
   Font fnt0=new Font("arial",Font.BOLD,50);
   g.setFont(fnt0);
   g.setColor(Color.BLACK);
   g.drawString("Valley of Spores",Game.WIDTH/4,100);
   Font fnt1=new Font("arial",Font.BOLD,30);
   g.setFont(fnt1);
   g.drawString("play",playButton.x+70,playButton.y+30);
   g.drawString("quit",quitButton.x+70,quitButton.y+30);
   g2d.draw(playButton);
   g2d.draw(quitButton);
   }
}