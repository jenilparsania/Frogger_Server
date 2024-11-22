package demo;


public class Car extends Sprite implements Runnable {
	// would have to look that the Boolean variables like: 
	// visible and moving 
	private Boolean moving;
	private Thread t;
	
	
	
	private Frog frog;
	private Log log;
	
	private Log logs[];
	private Log logs1[];
	private Log logs2[];
	
	
	
	
	public void setFrog(Frog temp) {
		frog = temp;
	}
	
	
	
	public void setLog(Log temp) {
		log = temp;
	}
	
	
	
	
	public void setLogs(Log[] temp) {
		this.logs = temp;
	}
	
	public void setLogs1(Log[] temp) {
		this.logs1 = temp;
	}
	
	public void setLogs2(Log[] temp) {
		this.logs2 = temp;
	}
	public Boolean getMoving() {
		return moving;
	}
	
	public void setMoving(Boolean moving) {
		this.moving = moving;
	}
	
	public Car() {
		super();
		this.moving = false;
		
	}
	
	public Car(int x, int y,int height, int width, String image) {
		super(x,y,height,width,image);
		this.moving = false;
		
	}
	
	private GamePrepServer gamePrep;
	public void setGamePrep(GamePrepServer gamePrep) {
		this.gamePrep = gamePrep;
	}
	
	public void startThread() {
		// run will be triggered 
		System.out.println("Current moving cars : "+ this.moving);
		
		
		if(!this.moving) {
			this.moving = true;
			
			this.setImage("car-3.png");
			
			
			
			frog.setImage("frog1-copy.png");
			
			
			
			System.out.println("Starting thread");
			t = new Thread(this,"Car Thread");
			t.start();   // it automatically calls the run method 
			
			
		}
		
	}
	public void startAgain() { // as the thread is stopped frog should move again to initial phase
		frog.setX(GameProperties.x_left);
		frog.setY(GameProperties.y_left);
		
		
		
	}
	
	public void stopThread() {
		if(this.moving) {
			this.moving = false;
			log.setMoving(false);
			
			this.startAgain();
		}
	}
	
	public void stopAllLogs() {
		for(int i=0; i<logs.length;i++) {
			logs[i].stopThread();
			logs1[i].stopThread();
			logs2[i].stopThread();
		}
		
 }
	
	@Override
	public void run() {
		System.out.println("run triggered");
		while(this.moving) {
			int x = this.x;
			x += GameProperties.CHARACTER_STEP;
			
			if(x>= GameProperties.SCREEN_WIDTH) {
				x = -1 * this.width;
			}
			
			this.setX(x);
			System.out.println("carx: "+this.r.x+",  car y : "+ this.r.y+ " , car w : "+this.r.width+ " ,car h : " +this.r.height);
			
			
			
			this.detechCollision();
			System.out.println("x,y: "+ this.x + " " + this.y);
			
			try {
				Thread.sleep(200);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		System.out.println("Thread Stopped");
		
	}
	
	private void detechCollision() {
		if(this.r.intersects(frog.getRectangle())) {
			System.out.println("BOOM!");
			this.stopThread();
			logs1[0].stopAllCars();
			this.stopAllLogs();
//			gamePrep.gameEndsLose();
			
		}
	}



}
