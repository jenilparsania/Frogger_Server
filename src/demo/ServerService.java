package demo;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServerService implements Runnable {


	//declare but not initialize the passed variables from
	// BankServer (we  need to use the originals)
	private Socket s;
	// variable passing in the gamePrepServer
	final int CLIENT_PORT = 5555;
	private Car cars[];
	private Car cars1[], cars2[];
	private Log logs[], logs1[], logs2[];
	private Frog frog;
	
	//variables to process our incoming socket data
	private Scanner in;
	private PrintWriter out;
	
	public ServerService() {
		
	}
	
	// implement score as of the class 
	
	public ServerService(Socket s , Frog frog, Car cars[], Car cars1[], Car cars2[], Log logs[],Log logs1[],Log logs2[]) {
		
		this.s = s;
		this.frog = frog;
		this.cars = cars;
		this.cars1 = cars1;
		this.cars2 = cars2;
		this.logs = logs;
		this.logs1 = logs1;
		this.logs2 = logs2;
		
	}
	
	
	@Override
	public void run() {
		
		try {
			in = new Scanner(s.getInputStream());
			
			out = new PrintWriter(s.getOutputStream());
			
			processRequest();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				s.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	private void processRequest() throws IOException {
		//need a loop to process the command tokens as they are 
		// parsed one at a time 
		while (true) {
			if(!in.hasNext()) return; // this is the line which stops us if there is not data left to parse
			
			// extract the first token (command)
			
			String command = in.next();    //in.next() gets String
			
			executeCommand(command);
			
			
			
		}
	}
	
	private void executeCommand(String command) throws   IOException {
		if(command.equals("MOVEFROG")) {
			//MOVEFROG UP
			//MOVEFROG DOWN
			//extract the string passed through socket
			String direction = in.next();
			
			//refer the frog moving in the game Prep project
			if(direction.equals("UP")) {
				int y = frog.getY();
				y -= GameProperties.CHARACTER_STEP;
				frog.setY(y);	
			}
			
			if(direction.equals("DOWN")) {
				int y = frog.getY();
				y+= GameProperties.CHARACTER_STEP;
				frog.setY(y);
			}
			
			if(direction.equals("LEFT")) {
				int x = frog.getX();
				x-= GameProperties.CHARACTER_STEP;
				frog.setX(x);
			}
			
			if(direction.equals("RIGHT")) {
				int x = frog.getX();
				x+= GameProperties.CHARACTER_STEP;
				frog.setX(x);
			}
			
			Socket s2 = new Socket("localhost", CLIENT_PORT);
			
			OutputStream outstream = s2.getOutputStream();
			PrintWriter out = new PrintWriter(outstream);

			
			String commandOut = "FROGPOSITION "+frog.getX() + "  " + frog.getY() + "\n";
			
			System.out.println("Sending: " + commandOut);
			out.println(commandOut);
			out.flush();
				
			s2.close();
			
			
			return ;
			
			
			
		}else if(command.equals("GETFROG")) {
			//open a socket to the client
			Socket s2 = new Socket("localhost", CLIENT_PORT);
			
			OutputStream outstream = s2.getOutputStream();
			PrintWriter out = new PrintWriter(outstream);

			
			String commandOut = "FROGPOSITION "+frog.getX() + "  " + frog.getY() + "\n";
			
			System.out.println("Sending: " + commandOut);
			out.println(commandOut);
			out.flush();
				
			s2.close();
			
			
			
			return ;
			
		}else if(command.equals("STARTGAME")) {
			// loop through and start cars and logs moving 
			for(int z = 0; z < cars.length;z++) {
				cars[z].startThread();
				cars1[z].startThread();
				cars2[z].startThread();
				logs[z].startThread();
				logs1[z].startThread();
				logs2[z].startThread();
				
			}
			
			
			return ;
				
		}else if(command.equals("STOPGAME")) {
			for(int z = 0; z < cars.length;z++) {
				cars[z].stopThread();
				cars1[z].stopThread();
				cars2[z].stopThread();
				logs[z].stopThread();
				logs1[z].stopThread();
				logs2[z].stopThread();
				
			}
			
		}
		else if(command.equals("GETCARS")) {
			// open a socket to the client , send cars coordinates
			// video : 40:00
			Socket s2 = new Socket("localhost", CLIENT_PORT);
			
			OutputStream outstream = s2.getOutputStream();
			PrintWriter out = new PrintWriter(outstream);

			
			String commandOut = "CARDATA "+cars[0].getX() + " " + cars[0].getY() + " "+cars[1].getX() + " " + cars[1].getY() + " " +cars[2].getX() + "  " + cars[2].getY() + "  "+ cars[3].getX() + "  "+cars[3].getY() + " "+
																   cars1[0].getX() +  " " + cars1[0].getY() + " "+cars1[1].getX() + " " + cars1[1].getY() + " " +cars1[2].getX() + "  " + cars1[2].getY() + "  "+ cars1[3].getX() + "  "+cars1[3].getY() + " "+
																   cars2[0].getX() +  " " + cars2[0].getY() + " "+cars2[1].getX() + " " + cars2[1].getY() + " " +cars2[2].getX() + "  " + cars2[2].getY() + "  "+ cars2[3].getX() + "  "+cars2[3].getY() + " "+ "\n";
			
			System.out.println("Sending: " + commandOut);
			out.println(commandOut);
			out.flush();
				
			s2.close();
			
			
			
			
			return ;
		
		}else if(command.equals("GETLOGS")) {
			//open a socket and send the logs  co-ordinates
			Socket s3 = new Socket("localhost",CLIENT_PORT);
			
			OutputStream outstream = s3.getOutputStream();
			PrintWriter out = new PrintWriter(outstream);
			
			String commandOut = "LOGDATA " + logs[0].getX() + " " + logs[0].getY() + " " + logs[1].getX() + " " + logs[1].getY() + " " +logs[2].getX() + " " + logs[2].getY() + " " + logs[3].getX() + " " + logs[3].getY() + " " + 
																	logs1[0].getX() + " " + logs1[0].getY() + " " + logs1[1].getX() + " " + logs1[1].getY() + " " +logs1[2].getX() + " " + logs1[2].getY() + " " + logs1[3].getX() + " " + logs1[3].getY() + " "+ 
																	logs2[0].getX() + " " + logs2[0].getY() + " " + logs2[1].getX() + " " + logs2[1].getY() + " " +logs2[2].getX() + " " + logs2[2].getY() + " " + logs2[3].getX() + " " + logs2[3].getY() + 
					"  \n" ;
			
			System.out.println("Sending : " + commandOut);
			out.println(commandOut);
			out.flush();
			s3.close();
			
			
			return;
		}
		
		else {
			// not a valid command
			System.out.println(command + "received");
			String outCommand = "INVALID";
			out.println(outCommand);
			out.flush();
			
			return ; 
		}
	}
	
	


}
