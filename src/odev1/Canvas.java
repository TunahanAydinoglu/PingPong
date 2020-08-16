package odev1;

import javax.swing.JFrame;

public class Canvas extends JFrame{

	static int width = 600;
	static int height = 450;
	private ThreadAnimation ta;
	private PaintSurface ps; 

	public Canvas(){
		setSize(width,height);
		ps = new PaintSurface();
		super.add(ps);

		ta = new ThreadAnimation(this);
		ta.start();

		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public static void main(String args[]){

		new Canvas();
	}
}

