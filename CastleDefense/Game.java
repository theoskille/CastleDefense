import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import java.io.*;


public class Game extends GameLoop implements KeyListener,MouseListener{
   public static final int HEIGHT;
   public static final int WIDTH;
   private Scanner levelDataScan;
   private Menu menu;
   private GameOverMenu gameOverMenu;
   private PauseMenu pauseMenu;
   private Player player;
   private StatBar statBar;
   public static enum STATE{
      MENU,
      GAME,
      GAMEOVER,
      PAUSE
   };
   public static STATE state=STATE.MENU;
   
   public static void main(String[] args){
      Game g=new Game();
   }
   static{
      HEIGHT=600;
      WIDTH=800;
   }
   
   public Game(){
      
      player=new Player(100,100);
      menu=new Menu();
      gameOverMenu=new GameOverMenu();
      pauseMenu=new PauseMenu(player);
      statBar=new StatBar();
      JFrame frame=new JFrame("Valley of Spores");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      frame.setResizable(false);
      frame.setVisible(true);
      frame.getContentPane().add(this);
      frame.addKeyListener(this);
      frame.addMouseListener(this);
      frame.pack();
      frame.setLocationRelativeTo(null);
      Spell.spells=new ArrayList<Spell>();
      Enemy.enemies=new ArrayList<Enemy>();
      this.begin();
      System.out.println(Enemy.enemies.size());
      
      
   }
   public int sx(){
      Random r=new Random();
      return r.nextInt(100)+WIDTH;
   }
   public int sy(){
      Random r=new Random();
      return r.nextInt(HEIGHT-290)+64;
   }
   public void update(){
      if(state==STATE.GAME){
         for(int i=Spell.spells.size()-1;i>=0;i--){
            Spell.spells.get(i).move();
         }
         for(int i=Enemy.enemies.size()-1;i>=0;i--){
            Enemy.enemies.get(i).move();
            Enemy.enemies.get(i).destroy();
         }
         player.move();
         player.damage();
         player.update();
         statBar.update(player.getHealth(),player.getMana(),player.getExp(),player.getExpToNext(),player.getLevel());
         if(!player.destroy()){
            checkCollisions();
            if(Enemy.enemies.size()==0){
               pauseMenu.incLevel();
               state=STATE.PAUSE;
               
            }
         }
      }
   }
   public void paint(Graphics g){
      if(state==STATE.GAME){
         super.paint(g);
         for(Spell s:Spell.spells)
            s.draw(g);
         for(Enemy e:Enemy.enemies)
            e.draw(g);
         player.draw(g);
         statBar.render(g);
      }else if(state==STATE.MENU){
         menu.render(g);
      }else if(state==STATE.GAMEOVER){
         gameOverMenu.render(g);
      }else if(state==STATE.PAUSE){
         pauseMenu.render(g);
      }
   }
   public void checkCollisions(){
      for(int i=Spell.spells.size()-1;i>=0;i--)
         for(int j=Enemy.enemies.size()-1;j>=0;j--)
            if(Spell.spells.get(i).getHitBox().intersects(Enemy.enemies.get(j).getHitBox())){
               Enemy.enemies.get(j).damage(Spell.spells.get(i).getDamage(),Spell.spells.get(i).getRecoil());
               if(!Spell.spells.get(i).lingers())
                  Spell.spells.remove(i);
               player.setExp(Enemy.enemies.get(j).getExp());
               return;
            }
   }
   public void reset(){
      player.resetHealth();
      pauseMenu.resetLevel();
      for(int i=Enemy.enemies.size()-1;i>=0;i--)
         Enemy.enemies.remove(i);
      for(int i=Spell.spells.size()-1;i>=0;i--)
         Spell.spells.remove(i);
      try{
         levelDataScan=new Scanner(new File("level_data.txt"));
      }catch(FileNotFoundException e){
         System.out.println("level data not found");
         return;
      }

   }
   public void spawnEnemies(){
      while(levelDataScan.hasNext()){
         String enemy="";
         if(levelDataScan.hasNext())
            enemy=levelDataScan.next();
         System.out.println(enemy);
         if(enemy.equals("end")){
            System.out.println("amount of enemies "+Enemy.enemies.size()); 
            return;
         }
         int count=0;
         if(levelDataScan.hasNext())
             count=Integer.parseInt(levelDataScan.next());
         System.out.println("count "+count);
         for(int i=0;i<count;i++){
            if(enemy.equals("shroom"))
               new Shroom(sx(),sy());
            if(enemy.equals("chomp"))
               new Chomp(sx(),sy());
         }
      } 
      System.out.println("amount of enemies "+Enemy.enemies.size());     
   }
   
   public void keyPressed(KeyEvent e){
      if(state==STATE.GAME){
         int code=e.getKeyCode();
         if(code==KeyEvent.VK_UP)
            player.setVelocityY(-player.getSpeed());
         if(code==KeyEvent.VK_DOWN)
            player.setVelocityY(player.getSpeed());
         if(code==KeyEvent.VK_LEFT)
            player.setVelocityX(-player.getSpeed());
         if(code==KeyEvent.VK_RIGHT)
            player.setVelocityX(player.getSpeed());
         player.setWalk(true);
      }
        
   }
   public void keyReleased(KeyEvent e){
      int code=e.getKeyCode();
      if(code==KeyEvent.VK_UP)
         player.setVelocityY(0);
      if(code==KeyEvent.VK_DOWN)
         player.setVelocityY(0);
      if(code==KeyEvent.VK_LEFT)
         player.setVelocityX(0);
      if(code==KeyEvent.VK_RIGHT)
         player.setVelocityX(0);
      if(code==KeyEvent.VK_Z){
         player.setX(player.getX()+10);
         new Slash(player.getX(),player.getY());
         
      }
      if(code==KeyEvent.VK_X){
         player.decMana(20);
         new FireBall(player.getX(),player.getY());
      }
      
   }
   public void mousePressed(MouseEvent e){
      int mx=e.getX();
      int my=e.getY();
      if(state==STATE.MENU){
         if(mx>=Game.WIDTH/2-120 && mx<=Game.WIDTH/2-120+200 && my>=175 && my<=225){
            reset();
            state=STATE.PAUSE;
            
         }
         if(mx>=Game.WIDTH/2-120 && mx<=Game.WIDTH/2-120+200 && my>=275 && my<=335)
            System.exit(1);
      }else if(state==STATE.GAMEOVER){
         if(mx>=Game.WIDTH/2-120 && mx<=Game.WIDTH/2-120+200 && my>=175 && my<=225)
            state=STATE.MENU;
      }else if(state==STATE.PAUSE){
         if(mx>=Game.WIDTH/2-110 && mx<=Game.WIDTH/2+90 && my>=400 && my<=450){
            for(int i=Spell.spells.size()-1;i>=0;i--)
               Spell.spells.remove(i);
            levelDataScan.nextLine();
            spawnEnemies();
            state=STATE.GAME;
         }
      }
   }
   public void mouseClicked(MouseEvent e){}
   public void mouseEntered(MouseEvent e){}
   public void mouseExited(MouseEvent e){}
   public void mouseReleased(MouseEvent e){}
   public void keyTyped(KeyEvent e){}
}
