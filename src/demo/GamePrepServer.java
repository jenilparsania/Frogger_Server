package demo;
/*  { SERVER code has no labels }*/

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;



public class GamePrepServer  {

	
	
	public static void main(String[] args) throws IOException {
		 Frog frog;
		 Car car;
		 Log log;
		
		 Car[] cars;  
		 Car[] cars1;
		 Car[] cars2;
		
		 Log[] logs ;
		 Log[] logs1;
		 Log[] logs2;
		 
		 
		 
		 frog = new Frog(GameProperties.x_left,GameProperties.y_left,39,40,"frog1-copy.png");

			


			// frog setup
			frog.setX(GameProperties.x_left);
			frog.setY(GameProperties.y_left);
			frog.setWidth(40);
			frog.setHeight(39);
			frog.setImage("frog1-copy.png");
		
			// the car array code is in here 
			
			cars = new Car[4];
			cars1= new Car[4];
			cars2 = new Car[4];
			logs = new Log[4];
			logs2 = new Log[4];
			logs1 = new Log[4];
			
			
			
			cars[0] = new Car(175,491,40,100,"car-3.png");
			cars[1] = new Car(385,491,40,100,"car-3.png");
			cars[2] = new Car(615,491,40,100,"car-3.png");
			cars[3] = new Car(7,491,40,100,"car-3.png");
			
			cars1[0] = new Car(7,407,40,100,"car-3.png");
			cars1[1] = new Car(175,407,40,100,"car-3.png");
			cars1[2] = new Car(385,407,40,100,"car-3.png");
			cars1[3] = new Car(615,407,40,100,"car-3.png");
			
			cars2[0] = new Car(7,323,40,100,"car-3.png");
			cars2[1] = new Car(175,323,40,100,"car-3.png");
			cars2[2] = new Car(385,323,40,100,"car-3.png");
			cars2[3] = new Car(615,323,40,100,"car-3.png");
			
			// log = new Log(7,160,120,250,"log-delete.png");
						// logs is the middle row 
			logs[0] = new Log(7,113,110,120,"log-big.png");
			logs[1] = new Log(217,113,110,120,"log-big.png");
			logs[2] = new Log(427,113,110,120,"log-big.png");
			logs[3] = new Log(637,113,110,120,"log-big.png");
						
						
			logs2[0] = new Log(7,29,110,120,"log-big.png");
			logs2[1] = new Log(217,29,110,120,"log-big.png");
			logs2[2] = new Log(427,29,110,120,"log-big.png");
			logs2[3] = new Log(637,29,110,120,"log-big.png");
						
			logs1[0] = new Log(7,197,110,120,"log-big.png");
			logs1[1] = new Log(217,197,110,120,"log-big.png");
			logs1[2] = new Log(427,197,110,120,"log-big.png");
			logs1[3] = new Log(637,197,110,120,"log-big.png");
			
					 
			for(int i=0;i<cars.length;i++) {
				cars[i].setFrog(frog);
				
				
		        cars[i].display();

		         cars[i].setLogs(logs);
			 	 cars[i].setLogs1(logs1);
			 	 cars[i].setLogs2(logs2);
//		         cars[i].setGamePrep(this);	
		   
		         // for the car1 array 
		         cars1[i].setFrog(frog);
				 
				 
			 
			     cars1[i].display();

			 	 cars1[i].setLogs(logs);
			 	 cars1[i].setLogs1(logs1);
			 	 cars2[i].setLogs2(logs2);
		
//			     cars1[i].setGamePrep(this);	

			      cars2[i].setFrog(frog);
				  

			      cars2[i].display();

			 	  cars2[i].setLogs(logs);
			 	  cars2[i].setLogs1(logs1);
			 	  cars2[i].setLogs2(logs2);
			
//			     cars2[i].setGamePrep(this);	
		     
			}
			
			
			
		

			for(int i=0;i<logs.length;i++) {
				logs[i].setFrog(frog);
				logs[i].setCars(cars);
				logs[i].setCars1(cars1);
				logs[i].setCars2(cars2);
				
//				logs[i].setGamePrep(this);
				
				
				
				logs[i].display();
				logs[i].setDirection(false);
				
				
				// for logs1 array 
				
				logs1[i].setFrog(frog);
				logs1[i].setCars(cars);
				logs1[i].setCars1(cars1);
				logs1[i].setCars2(cars2);
				
//				logs1[i].setGamePrep(this);
				
				
				logs1[i].display();
				logs1[i].setDirection(true);
				
		
				//for logs2 array
				
				logs2[i].setFrog(frog);
				logs2[i].setCars(cars);
				logs2[i].setCars1(cars1);
				logs2[i].setCars2(cars2);
			
//				logs2[i].setGamePrep(this);
				
				logs2[i].display();
				logs2[i].setDirection(true);
				
			}
			
			
			
			/*
			 void stopAllLogs() {
				for(int i=0; i<logs.length;i++) {
					logs[i].stopThread();
					logs1[i].stopThread();
					logs2[i].stopThread();
				}
			}*/
			
			
			final int SOCKET_PORT = 5556;
			ServerSocket server = new ServerSocket(SOCKET_PORT);
			
			
			Thread t1 = new Thread(new Runnable() {
				public void run() {
		    		synchronized(this) {
		    			while (true) {
		    				try {
		    				System.out.println("Server is listening for the connection inside t1");
		    		    	Socket s = server.accept();
		    		    	System.out.println("Client Connected");
		    		    	// pass each and everything in the server service as we are going to manipulate everything (24:00 in the video)
		    		    	ServerService myService = new ServerService(s,frog,cars,cars1,cars2,logs,logs1,logs2);
		    		    	// video 22:30
		    		    	Thread t = new Thread(myService);
		    		    	t.start();
		    		    	
		    				}catch(Exception e) {
		    					e.printStackTrace();
		    				}
		    		    	
		    		}
		    			
		    	}
		    	}
				
				
			});
			t1.start();
			// this was the masterpiece I was missing 
			
			

	}
	/////  STOPPED ALL THE CARS 
	////////////// AND THE LOGS 
	
	 void stopAllCars(Car cars[] , Car cars1[], Car cars2[] ,int len ) {  // ask about it  
	
		
		
		for(int i=0; i < len;i++) {
			cars[i].stopThread();
			cars1[i].stopThread();
			cars2[i].stopThread();
//			gameEndsLose();
			
		}
	}
	 
	 
	 void stopAllLogs(Log logs[], Log logs1[], Log logs2[], int len) {
			for(int i=0; i<logs.length;i++) {
				logs[i].stopThread();
				logs1[i].stopThread();
				logs2[i].stopThread();
			}
			
	 }
	
	/*
	public void gameEndsLose() {  
		int newMarks = Integer.parseInt( score.getText())-50;
		score.setText(Integer.toString(newMarks));

	}*/

	
	
	

	
	
	
	// method to execute the database connection 
	public  void setupDatabase() {
		
		Connection conn = null;
		/*
		try {
			
			// load the database driver
			Class.forName("org.sqlite.JDBC");
			System.out.println("Driver Loaded");
			
			//create a connection string and connect to database
			String dbURL = "jdbc:sqlite:frogger.db";
			conn = DriverManager.getConnection(dbURL);
			
			//if succesfull
			if(conn != null) { /*
				System.out.println("connected to database");
				
				// Showing the meta-data for database
				DatabaseMetaData db = (DatabaseMetaData) conn.getMetaData();
				System.out.println("Driver Name: "+ db.getDriverName());
				System.out.println("Driver Version: "+ db.getDriverVersion());
				System.out.println("Product Name: "+ db.getDatabaseProductName());
				System.out.println("Product Version: "+ db.getDatabaseProductVersion());
				
				// create a table using prepared - statement 
				String sqlCreateTable = "CREATE TABLE IF NOT EXISTS GAME" 
						+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ "NAME TEXT NOT NULL," + "SCORE  INT NOT NULL) ";
				
				try (PreparedStatement pstmtCreateTable = conn.prepareStatement(sqlCreateTable)) {
					pstmtCreateTable.executeUpdate();
					System.out.println("Table Successfully Created");
				}
				
				String sqlInsert = "INSERT INTO GAME (NAME , SCORE) VALUES (? , ? )";
				try(PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert)){
					
					/*
					 * 
					String playerName;
					
					playerName = JOptionPane.showInputDialog(null,"what is your name ? ");
					
					
					
					//execute calls to prepared statement 
					if(playerName == null) {
						System.out.println("No record inserted, please restart the game  ");
						
						
					}else {
						pstmtInsert.setString(1,playerName);
						pstmtInsert.setInt(2,Integer.parseInt(score.getText()));
						pstmtInsert.executeUpdate();
						System.out.println("Record Inserted"); 
						
						
					} 
					
					
					
					
					
					
				}
				
				
			}
		
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	
		
		
		*/
	}
	
	

}
