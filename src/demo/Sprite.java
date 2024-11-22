package demo;

import java.awt.Rectangle;

public class Sprite {
	protected int x,y;
	protected int height,width;
	protected String image;
	protected Rectangle r;
	
	public Sprite() {
		super();
		r = new Rectangle(this.x,this.y,this.width,this.height);
		
	}
	public Sprite(int x,int y, int height, int width,String image) {
		super();
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.image = image;
		r = new Rectangle(this.x,this.y,this.width,this.height);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		this.r.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		this.r.y=y;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Rectangle getRectangle() {
		return r;
	}
	
	public void display() {
		System.out.println("x,y:"+this.x + " , "+ this.y);
		System.out.println("width,height:"+this.width + " , "+ this.height);
		System.out.println("image: "+ this.image);
		
		
	}
	
	

}
