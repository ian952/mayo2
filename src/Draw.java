import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.thalmic.myo.*;
import com.thalmic.myo.enums.PoseType;

public class Draw extends JComponent{
	Image image;
	//this is gonna be your image that you draw on
	Graphics2D graphics2D;
	//this is what we'll be using to draw on
	int currentX, currentY, oldX, oldY;
	
	//these are gonna hold our mouse coordinates

	public void thick () {
		main.font += 10;
	}
	
	public void thin () {
		if (main.font - 10 >= 10) main.font -= 10;
		else main.font = 1;
	}
	
	//Now for the constructors
	public Draw(){
		setDoubleBuffered(false);

		hubLoop.hub.addListener(new AbstractDeviceListener() {  
		    @Override
		    public void onPose(Myo myo, long timestamp, Pose pose) {
		    	//System.out.println("not retarded");
		        switch (pose.getType()) {
		        	case WAVE_IN:
		        		thin();
		        		break;
		        	case WAVE_OUT:
		        		thick();
		        		break;
		        	case FINGERS_SPREAD:
		        		
		        		break;
		        	default:
		        		break;
		        }
		    }
		});
		
		ButtonPanel.erase.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	//graphics2D.setStroke(new BasicStroke(main.font + 10));
	        	graphics2D.setPaint(Color.white);
	        	repaint();
	        }
	    });
		
		ButtonPanel.clear.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	           clear();
	        }
	    });
		
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				oldX = e.getX();
				oldY = e.getY();
			}
		});
		
		
		//if the mouse is pressed it sets the oldX & oldY
		//coordinates as the mouses x & y coordinates
		addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				graphics2D.setStroke(new BasicStroke(main.font));
				currentX = e.getX();
				currentY = e.getY();
				if(graphics2D != null)
				graphics2D.drawLine(oldX, oldY, currentX, currentY);
				repaint();
				oldX = currentX;
				oldY = currentY;
			}

		});
		
		
		//while the mouse is dragged it sets currentX & currentY as the mouses x and y
		//then it draws a line at the coordinates
		//it repaints it and sets oldX and oldY as currentX and currentY
	}

	public void paintComponent(Graphics g){
		if(image == null){
			image = createImage(getSize().width, getSize().height);
			graphics2D = (Graphics2D)image.getGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			clear();

		}
		g.drawImage(image, 0, 0, null);
	}
	//this is the painting bit
	//if it has nothing on it then
	//it creates an image the size of the window
	//sets the value of Graphics as the image
	//sets the rendering
	//runs the clear() method
	//then it draws the image


	public void clear(){
		graphics2D.setPaint(Color.white);
		graphics2D.fillRect(0, 0, getSize().width, getSize().height);
		graphics2D.setPaint(Color.black);
		repaint();
	}
	//this is the clear
	//it sets the colors as white
	//then it fills the window with white
	//thin it sets the color back to black
	public void red(){
		graphics2D.setPaint(Color.red);
		repaint();
	}
	//this is the red paint
	public void black(){
		graphics2D.setPaint(Color.black);
		repaint();
	}
	//black paint
	public void magenta(){
		graphics2D.setPaint(Color.magenta);
		repaint();
	}
	//magenta paint
	public void blue(){
		graphics2D.setPaint(Color.blue);
		repaint();
	}
	//blue paint
	public void green(){
		graphics2D.setPaint(Color.green);
		repaint();
	}
	//green paint

}