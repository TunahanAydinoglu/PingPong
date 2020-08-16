package odev1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;


public class PaintSurface extends JComponent {
	int paddle_x = 0;
	int paddle_y = 360;
	int score = 0;
	float english = 1.0f;
	Ball ball;

	Color[] color = {
			Color.RED, Color.ORANGE,
			Color.MAGENTA, Color.ORANGE,
			Color.CYAN, Color.BLUE
	};

	int colorIndex;

	public PaintSurface(){
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				if (e.getX() - 30 - paddle_x > 5)
					english = 1.5f;
				else if (e.getX() - 30 - paddle_x < -5)
					english = -1.5f;
				else
					english = 1.0f;
				paddle_x = e.getX() - 30;
			}
		} );
		ball = new Ball(20);
	}

	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		Shape paddle = new Rectangle2D.Float( 
				paddle_x, paddle_y, 120, 8);
		g2.setColor(color[colorIndex % 6]); 

		if (ball.intersects(paddle_x, paddle_y, 120, 8)
				&& ball.y_speed > 0) {
			ball.y_speed = -ball.y_speed;
			ball.x_speed = (int)(ball.x_speed * english);
			if (english != 1.0f)
				colorIndex++;
			score += Math.abs(ball.x_speed * 10);
		}

		if (ball.getY() + ball.getHeight()
				>= Canvas.height) {
			ball = new Ball(20);
			score -= 10;
			colorIndex = 0;
		}

		ball.move();
		g2.fill(ball);
		g2.setColor(Color.GREEN);
		g2.fill(paddle);
		g2.drawString("Score:"  + score, 250, 20); 
	}
}
