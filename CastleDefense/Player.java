import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Player extends Entity{

   private BufferedImage image1;
   private BufferedImage image2;
   private int invulnerableCounter;
   private int invulnerableTime;
   private boolean invulnerable;
   private int health;
   private int level;
   private int exp;
   private int expToNext;
   private int mana;
   private int maxMana;
   private int speed;
   private int maxHealth;
   private int defense;
   private int vitality;
   private int vitalityCounter;
   private int wisdom;
   private int wisdomCounter;
   private int attributePoints;
   private boolean walk;
   private int walkCounter;
   
   public Player(int x,int y){
      super(x,y,0,0,2,25,64);
      level=1;
      speed=2;
      mana=100;
      maxMana=100;
      health=100;
      maxHealth=100;
      defense=0;
      vitality=1;
      vitalityCounter=120;
      exp=0;
      expToNext=100;
      wisdom=1;
      wisdomCounter=30;
      invulnerableCounter=0;
      invulnerableTime=60;
      invulnerable=false;
      walk=false;
      walkCounter=0;
      attributePoints=0;
      

      try{
      image1 = ImageIO.read(new File("C:\\Users\\theos\\Documents\\SourceCode\\CastleDefense\\res\\player_sprite.png"));
      image2= ImageIO.read(new File("C:\\Users\\theos\\Documents\\SourceCode\\CastleDefense\\res\\player_sprite_2.png"));

      }catch(IOException e){
         System.out.println("no image");
      }
   }
   
   public void draw(Graphics g){
     if(invulnerable){
         if(invulnerableCounter%10==0)
            g.drawImage(image1,getX(),getY(),null);
     }else if(walk){
      System.out.println("walk"+walkCounter);
         if(walkCounter%10==0)
            g.drawImage(image1,getX(),getY(),null);
         else{
            g.drawImage(image2,getX(),getY(),null);
            walkCounter++;
         }
     }else
         g.drawImage(image1,getX(),getY(),null);
     
   }
   public boolean destroy(){
      if(health<=0){
         Game.state=Game.STATE.GAMEOVER;
         return true;
      }
      return false;
   }
   public void damage(){
      if(!invulnerable){
            for(int i=Enemy.enemies.size()-1;i>=0;i--)
               if(Enemy.enemies.get(i).getHitBox().intersects(getHitBox())){
                  System.out.println("damaged");
                  health-=Enemy.enemies.get(i).getDamage();
                  System.out.println(health);
                  System.out.println("size "+Enemy.enemies.size());     
                  invulnerable=true;
               }  
      }
      if(invulnerableCounter==invulnerableTime){
         invulnerable=false;
         invulnerableCounter=0;
      }
      if(invulnerable)
         invulnerableCounter++;
   }
   
   public int getHealth(){return health;}
   public int getMaxHealth(){return maxHealth;}
   public int getMaxMana(){return maxMana;}
   public int getVitality(){return vitality;}
   public int getWisdom(){return wisdom;}
   public int getSpeed(){return speed;}
   public int getAttributePoints(){return attributePoints;}
   public BufferedImage getPlayerSprite(){return image1;}
   
   public void setMaxHealth(int maxHealth){this.maxHealth=maxHealth;}
   public void setMaxMana(int maxMana){this.maxMana=maxMana;}
   public void setVitality(int vitality){this.vitality=vitality;}
   public void setWisdom(int wisdom){this.wisdom=wisdom;}
   public void setSpeed(int speed){this.speed=speed;}
   public void decAttributePoints(){attributePoints--;}
   
   public void setWalk(boolean var){walk=var;}
   public void resetHealth(){health=maxHealth;}
   public int getMana(){return mana;}
   public int getExp(){return exp;}
   public int getExpToNext(){return expToNext;}
   public void setExp(int incExp){exp+=incExp;}
   public void decMana(int dec){mana-=dec;}
   public int getLevel(){return level;}
   public void update(){
      if(exp>=expToNext){
         level++;
         exp=0;
         attributePoints+=3;
      }
      if(health<maxHealth && vitalityCounter<=0){
         health+=vitality;
         vitalityCounter=120;
      }
      vitalityCounter--;
      if(mana<maxMana && wisdomCounter<=0){
         mana+=wisdom;
         wisdomCounter=30;
      }
      wisdomCounter--;
      
   }
   

   public String toString(){
      return "Player";
   }
   
}