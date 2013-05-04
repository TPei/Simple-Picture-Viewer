import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ImageHolder extends JPanel implements MouseWheelListener, MouseMotionListener
{	
	ImageHolder()
	{
		addMouseWheelListener(this);
		addMouseMotionListener(this);
	}
	
	private File myPictureDirectory = new File ("/Users/Thomas/Dropbox/Github/OOP-Labor-5/pictures");
	
	// decides which (nth) picture from folder is displayed
	private int whichPicture = 0;
	
	public int getWhichPicture()
	{
		return whichPicture;
	}

	public void setWhichPicture(int whichPicture)
	{
		this.whichPicture = whichPicture;
	}
	
	// zoom factor
	private double zoom = 1;
	
	public double getZoom()
	{
		return zoom;
	}

	public void setZoom(double zoom)
	{
		// shouldn't be too small or too large (between 20% and 400%)
		this.zoom = Math.min(Math.max(0.2, zoom), 4);
	}
	
	public void setDefaultZoom()
	{
		this.zoom = 1;
	}

	// border color
	Color borderColor = new Color(255, 0, 0);
	
	public Color getBorderColor()
	{
		return borderColor;
	}

	public void setBorderColor(Color borderColor)
	{
		this.borderColor = borderColor;
	}
	
	private int xOffset = 0;
	private int yOffset = 0;

	public int getxOffset()
	{
		return xOffset;
	}

	public void setxOffset(int xOffset)
	{
		this.xOffset = xOffset;
	}

	public int getyOffset()
	{
		return yOffset;
	}

	public void setyOffset(int yOffset)
	{
		this.yOffset = yOffset;
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		
		// int width and height of window
		int width = getWidth(); 
		int height = getHeight();
		
		// gets the space that is available
		int minSpace = Math.min(width, height);
		int side = (int)Math.round(minSpace*zoom);
		
		int widthSide = (int)Math.round(width*zoom);
		int heightSide = (int)Math.round(height*zoom);
		
		// calculates the space that is left
		int restHeight = (height - heightSide)/2;
		int restWidth = (width - widthSide)/2;
		
		System.out.println("width: "+width+"widthSide: "+widthSide);
		System.out.println("height: "+height+"heightSide: "+heightSide);
		
		// calls specialPaint
		//specialPaint(g, side, side, restWidth, restHeight, minSpace);
		
		
		// get nth image in Image folder
		Image image = getImage(whichPicture);
		//System.out.println(whichPicture);
				
		/*int xMargin = (int)Math.round(xStart * zoom);
		int yMargin = (int)Math.round(yStart * zoom);*/
				
		g.drawImage(image, restWidth+1, restHeight+1, widthSide-2, heightSide-2, this);
		//setBorder(BorderFactory.createLineBorder(borderColor));
		g.setColor(borderColor);
		g.drawRect(restWidth+1, restHeight+1, widthSide-2, heightSide-2);
	}	
	
	// returns nth (starting at 0) image from image directory
	public Image getImage(int nth)
	{
		File[] myFiles = myPictureDirectory.listFiles();
		
		// count how many pngs are in directory
		int directoryLength = 0;
		for(int i = 0; i < myFiles.length; i++)
		{
			if (isPng(myFiles[i].toString()))
				directoryLength++;
		}
		System.out.println("all: "+myFiles.length+ ", only png: "+directoryLength);
		
		if(nth < 0)
			nth = directoryLength - 1 - nth;
		
		System.out.println("nth: "+nth);
		
		// to iterate through files
		int file = 0;
		
		// counts nth files, starting at 1
		int counter = 0;
		
		// finds the nth file with .pgn as ending in folder
		// I don't like those while(true) ... break; loops...
		while(true)
		{
			if(file >= directoryLength)
				file = 0;
			
			boolean isPNG = isPng(myFiles[file].toString()); 
			if(isPNG && (counter >= nth))
			{
				break;
			}
			
			
			// we're at the nth image but it wasn't a png, let's take the next png we find
			else if (counter == nth)
			{
				file++;
			}
			// it was a png, but not the right one yet, let's take the next file and keep counting
			else if(isPNG)
			{
				file++;
				counter++;
			}
			// we're not yet at the nth image and it wasn't a png anyway, let's just take the next one
			else
			{
				file++;
			}
		}
		return Toolkit.getDefaultToolkit().getImage(myFiles[file].toString());
	}

	private boolean isPng(String fileName)
	{
		String imageName = fileName;
		String imageEnding = imageName.substring(imageName.length()-4, imageName.length());
		
		// right image, we're done
		if (imageEnding.equals(".png"))
			// we're done, break, return
			return true;
		
		return false;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		String message;
		int notches = e.getWheelRotation();
	       if (notches < 0) {
	           message = "Mouse wheel moved UP "
	                        + -notches + " notch(es)";
	           setZoom(getZoom() + 0.1);
	           repaint();
	       } else {
	           message = "Mouse wheel moved DOWN "
	                        + notches + " notch(es)";
	           setZoom(getZoom() - 0.1);
	           repaint();
	       }
	    
	   System.out.println(message);
	}

	@Override
	public void mouseDragged(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		System.out.println("dragged image");
	}

	@Override
	public void mouseMoved(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
