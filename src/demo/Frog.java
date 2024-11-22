package demo;

import javax.swing.JLabel;

public class Frog extends Sprite {
	
//	private Boolean hasPassedCars;
	private Boolean hasReachedLogs;
	private JLabel frogLabel;
	
	
	public void setFrogLabel(JLabel temp) {
		frogLabel = temp;
	}
	
	
	public Frog() {
		super();
		
	}
	
	public Frog(int x,int y,int height, int width,String image) {
		super(x,y,height,width,image);
		
	}

}
