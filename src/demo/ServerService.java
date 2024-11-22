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
	final int CLIENT_PORT = 5656;
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
			
			
			
			return ;
			
		}else if(command.equals("GETCARS")) {
			// open a socket to the client , send cars coordinates
			// video : 40:00
			
			
			return ;
		
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
