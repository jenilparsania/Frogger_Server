package demo;



public class Log  extends Sprite implements Runnable{
	private Boolean moving;
	private Boolean direction;
	private Thread t;
	private Boolean hasPassedCars;
	
	private Car car;
	private Car cars[];
	private Car cars1[];
	private Car cars2[];
	
	
	private Frog frog;
	private GamePrepServer gamePrep;
	public void setGamePrep(GamePrepServer gamePrep) {
		this.gamePrep = gamePrep;
	}
	
	
	
	
	public void setFrog(Frog temp) {
		frog = temp;
	}
	
	public Boolean getMoving() {
		return moving;
	}
	
	public void setMoving(Boolean moving) {
		this.moving = moving;
	}
	
	
	public void setCar(Car temp) { 
		this.car = temp;
	}
	
	
	public void setCars(Car[] temp) { //could be used for array
		this.cars = temp;
	}
	
	public void setCars1(Car[] temp) { //could be used for array
		this.cars1 = temp;
	}
	
	public void setCars2(Car[] temp) { //could be used for array
		this.cars2 = temp;
	}
	
	
	
	public Boolean getDirection() {
		return this.direction;
	}
	
	public void setDirection(boolean temp) { 
		this.direction = temp;
	}
	
	
	public Log() {
		super();
		this.moving = false;
	}
	
	public Log(int x, int y,int height, int width, String image) {
		super(x,y,height,width,image);
		this.moving = false;
		
		
	}
	/*
	private GamePrepServer gamePrep;
	public void setGamePrep(GamePrepServer gamePrep) {
		this.gamePrep = gamePrep;
	}*/
	
	
	public void startThread() {
		// run will  be triggered
		System.out.println("Current Moving logs : "+this.moving);
		
		if(!this.moving) {
			this.moving = true;
			
			this.setImage("log-big.png");
			
			
			
			frog.setImage("frog1-copy.png");
			
			
			
			System.out.println("Starting thread");
			t = new Thread(this,"Log Thread");
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
//			car.setMoving(false);
			for(int i=0; i < cars.length;i++) {
				cars[i].setMoving(false);
				cars1[i].setMoving(false);
				cars2[i].setMoving(false);
				
			}
			
		}
	}
	
	
	public void stopAllCars() {
		for(int i=0; i<cars.length;i++) {
			cars[i].stopThread();
			cars1[i].stopThread();
			cars2[i].stopThread();
		}
	}
	
	/*
	public void stopAllLogs() {
		for(int i=0; i<logs.length;i++) {
			logs[i].stopThread();
			logs1[i].stopThread();
			logs2[i].stopThread();
		}
		
 }*/

	
	public void run() {
		
		System.out.println("run triggered");
		/**/
		while(this.moving) {
			int x = this.x;
			
			if(!this.getDirection()) {
				//going other way 
				x -= GameProperties.CHARACTER_STEP;
				
				if(x<= -1*this.width) {
					x =GameProperties.SCREEN_WIDTH;
				}
				
				this.setX(x);
				System.out.println("log x: \"+this.r.x+\",  log y : \"+ this.r.y+ \" , log w : \"+this.r.width+ \" ,log h : \" +this.r.height");
				
				
				
				this.detechCollision();
				
			}else { 
				// regular movement 
				
				x += GameProperties.CHARACTER_STEP;
				
				if(x>= GameProperties.SCREEN_WIDTH) {
					x = -1 * this.width;
					
					
				}
				
				this.setX(x);
				System.out.println("log x: "+this.r.x+",  log y : "+ this.r.y+ " , log w : "+this.r.width+ " ,log h : " +this.r.height);
				
				
				
				this.detechCollision();
				System.out.println("x,y: "+ this.x + " " + this.y);
			}
			

			
			try {
				Thread.sleep(200);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		System.out.println("Thread Stopped");
		
	}
	
	
	private void detechCollision() {
	
		if(frog.getY() <= GameProperties.y_safe) {

			
			if(this.r.intersects(frog.getRectangle())) {
				
				
				int frogx = frog.getX();
				
				if(!this.direction) {
					frogx -= GameProperties.CHARACTER_STEP;
					frog.setX(frogx);
					
				}else {
					frogx += GameProperties.CHARACTER_STEP;
					frog.setX(frogx);
					
				}
				
				if(frogx <= 0) {
					this.stopAllCars();
					cars1[0].stopAllLogs();
					this.startAgain();
//					gamePrep.gameEndsLose();
					
				}else if(frogx >= GameProperties.x_right) {
					this.stopAllCars();
					cars1[0].stopAllLogs();
//					gamePrep.gameEndsLose();
					this.startAgain();
				}
				
				frog.setX(frogx);

				
				
				
				
//				
			}
//			
//			if(!collided) {
//				
//					this.stopThread();
//					gamePrep.stopAllCars();
//					gamePrep.stopAllLogs();
//					this.startAgain();
//				
//			}
//			
//					
			}
		}
			
	}

