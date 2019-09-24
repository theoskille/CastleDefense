import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
public class GameOverMenu{
   public Rectangle mainMenuButton;
   
   public GameOverMenu(){
      mainMenuButton=new Rectangle(Game.WIDTH/2-120,150,200,50);
   }
   
   public void render(Graphics g){
   Graphics2D g2d=(Graphics2D) g;
   g.setColor(Color.WHITE);
   g.fillRect(0,0,Game.WIDTH,Game.HEIGHT);
   Font fnt0=new Font("arial",Font.BOLD,50);
   g.setFont(fnt0);
   g.setColor(Color.BLACK);
   g.drawString("Game Over",Game.WIDTH/2-150,100);
   g.setColor(Color.BLACK);
   Font fnt1=new Font("arial",Font.BOLD,30);
   g.setFont(fnt1);
   g.drawString("Main Menu",mainMenuButton.x+25,mainMenuButton.y+30);
   g2d.draw(mainMenuButton);
   }
}