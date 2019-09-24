import javax.swing.JPanel;

/**
 * GameLoop is an extended JPanel that updates the display at a constant rate.
 * It includes a implementation of a game loop based on the "Constant Game Speed with Maximum FPS"
 * loop shown on this page: http://www.koonsolo.com/news/dewitters-gameloop/ 
 *
 * This game loop calls the abstract update() method 60 times per second by default (can be changed via setTicksPerSecond() method).
 * The game loop calls repaint() at most once per update() call (ie only changes visuals when the things to be drawn changes),
 * although the rate may be less due to hardware performance.
 */
@SuppressWarnings("serial")
public abstract class GameLoop extends JPanel implements Runnable
{
	
	private int framesPerSecond;
	private int ticksPerSecond;
	private double nsPerTick;
	private int tickCount;
	private boolean gameRunning;
	private Thread gameThread;
	private boolean outputOn;
	private boolean isPaused;
	
	/**
	 * Creates a new GameLoop, which calls the default JPanel constructor.
	 */
	public GameLoop()
	{
		framesPerSecond = 0;
		ticksPerSecond = 0;
		nsPerTick = 1000000000.0 / 60;
		outputOn = true;
	}
	
	/**
	 * Sets the number of times per second that the GameLoop calls update().
	 * @param tps The number of times per second the loop should tick.
	 */
	public void setTicksPerSecond(int tps)
	{
		nsPerTick = 1000000000.0 / tps;
	}
	
	/**
	 * Sets whether a frame and update counter are printed to the console.
	 * @param on True if the loop should print information, else false.
	 */
	public void setOutput(boolean on)
	{
		outputOn = on;
	}
	
	/**
	 * Initiates a new Thread and begins the GameLoop's loop.
	 */
	public void begin()
	{
		gameRunning = true;
		isPaused = false;
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	/**
	 * Ends the GameLoop's loop.
	 */
	public void end()
	{
		gameRunning = false;
	}
	
	/**
	 * Sets whether the GameLoop is paused.
	 * @param paused True to pause the loop, else false.
	 */
	public void setPaused(boolean paused)
	{
		isPaused = paused;
	}
	
	/**
	 * The loop of the GameLoop.
	 * Should not be called directly, rather should be invoked by a call to begin().
	 */
	public void run()
	{
		int frames = 0;
		long firstTime = System.nanoTime();
		long lastTickTime = firstTime;
		boolean renderNext = false;
		tickCount = 0;
		
		/* this game loop regulates the number of times the game updates and repaints per second.
		 * this prevents the game from running faster in different sized windows or faster computers
	  	 * the game should perform at a significantly different speed only on terrible pieces of trash computers
		 * also, once the gameThread is started, this loop runs until end() is called; the only thing that changes when a game is paused is
		 * 		whether or not the program enters the if statement's body.
		 */
		while (gameRunning)
		{
			if (!isPaused)
			{
				long currTime = System.nanoTime();
				while (currTime - lastTickTime >= nsPerTick)	// if nsPerTick has passed
				{
					lastTickTime += nsPerTick;
					tick();		// change the actual game stuff (calculations, etc)
					renderNext = true;
				}
				if (renderNext)	// pointless to repaint when the game does not change/update
				{
					frames++;
					repaint();
					renderNext = false;
				}				
				// for displaying frames/ticks per second:
				if (currTime - firstTime >= 1000000000)	// 1000000000 nanoseconds in 1 second
				{
					firstTime = currTime;
					framesPerSecond = frames;
					ticksPerSecond = tickCount;
					if (outputOn)
						System.out.println("ticks: " + tickCount + "; fps: " + frames);
					frames = 0;
					tickCount = 0;
				}
			}
		}
	}
	
	/**
	 * Calls update() and advances the loop a single tick.
	 * tick() is called by the loop a default of 60 times per second.
	 * This can be set using the setTicksPerSecond(int tps) method.
	 */
	public void tick()
	{
		tickCount++;
		update();
	}
	
	/**
	 * Recalculates program information to be displayed.
	 * update() is called by the loop.
	 */
	public abstract void update();
	
	/**
	 * Returns the number of ticks from the previous second.
	 * @return The number of ticks from the previous second.
	 */
	public int getTickCount()
	{
		return tickCount;
	}
	
	/**
	 * Returns the number of frames from the previous second.
	 * @return The number of frames from the previous second.
	 */
	public int getFPS()
	{
		return framesPerSecond;
	}
	
	/**
	 * Returns the number of times the loop calls tick() per second.
	 * @return The number of times the loop calls tick() per second.
	 */
	public int getTPS()
	{
		return ticksPerSecond;
	}
}