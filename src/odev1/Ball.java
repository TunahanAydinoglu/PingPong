package odev1;
import java.awt.geom.Ellipse2D;


class Ball extends Ellipse2D.Float{
	public int x_speed, y_speed;
	private int d;

	public Ball(int diameter) {
		super((int)(Math.random() * (Canvas.width - 20) + 1),
				0, diameter, diameter);
		this.d = diameter;
		
		this.x_speed = (int)(Math.random() * 4 + 5);
		this.y_speed = (int)(Math.random() * 4 + 5);
	}
	public void move() {
		if (super.x < 0 || super.x > Canvas.width - d)
			x_speed = -x_speed;

		if (super.y < 0 || super.y > Canvas.height - d)
			y_speed = -y_speed;
		
		super.x += x_speed;
		super.y += y_speed;
	}
}