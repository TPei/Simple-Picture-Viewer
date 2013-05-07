import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.FileFilter;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/** manages picture contents
 * adds a picture, provides methods to pick another picture
 * set colored border around picture
 * implements zoom functionality
 * let's you drag the picture around
 * 
 * @author Thomas
 * @version 1.0
 * 
 */
public class ImageHolder extends JPanel implements MouseWheelListener, MouseMotionListener, MouseListener
{	
	ImageHolder()
	{
		addMouseWheelListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
	}
	
	// picture directory
	private File myPictureDirectory = new File ("pictures");
	
	// decides which (nth) picture from folder is displayed
	private int whichPicture = 0;
	
	// border color
	private Color borderColor = new Color(255, 0, 0);
	
	// zoom factor
	private double zoom = 1;
	
	// drag offset
	private int mouseClickedPositionX = 0;
	private int mouseClickedPositionY = 0;
	private int xOffset = 0;
	private int yOffset = 0;
	
	/**
	 * reset dragging offset and zoom etc. (image position back to start)
	 */
	public void resetImage()
	{
		mouseClickedPositionX = 0;
		mouseClickedPositionY = 0;
		xOffset = 0;
		yOffset = 0;
		zoom = 1;
	}
	
	// multiple setter and getter methods
	public int getWhichPicture()
	{
		return whichPicture;
	}

	public void setWhichPicture(int whichPicture)
	{
		this.whichPicture = whichPicture;
	}	
	
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
	
	public Color getBorderColor()
	{
		return borderColor;
	}

	public void setBorderColor(Color borderColor)
	{
		this.borderColor = borderColor;
	}
	
	public File getMyPictureDirectory()
	{
		return myPictureDirectory;
	}

	public void setMyPictureDirectory(File myPictureDirectory)
	{
		this.myPictureDirectory = myPictureDirectory;
	}
	
	/** paint method
	 * checks layout, space etc
	 * draws image
	 */
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
		
		// get nth image in Image folder
		Image image = getImage(whichPicture);
		
		// calculate aspect ratio etc so that the image won't be compressed
		int imageWidth = (int)(image.getWidth(null) * zoom);
		int imageHeight = (int)(image.getHeight(null) * zoom);		
		
		if(imageWidth > imageHeight){
            double ratio = (double)widthSide / (double)imageWidth;   // get ratio for scaling image
            
            //imageWidth = width; // Set new width
            imageHeight = (int)(imageHeight * ratio);
            imageWidth = Math.min(imageWidth, widthSide);
        }
		else if(imageHeight > imageWidth){
            double ratio = (double)heightSide / (double)imageHeight; // get ratio for scaling image
            
            //imageHeight = height;
            imageWidth = (int)(imageWidth * ratio);
            imageHeight = Math.min(imageHeight, heightSide);
        }
		
		// calculates the space that is left
		int restHeight = (height - imageHeight)/2;
		int restWidth = (width - imageWidth)/2;
		
		g.drawImage(image, restWidth+1+xOffset, restHeight+1+yOffset, imageWidth-2, imageHeight-2, this);
		//setBorder(BorderFactory.createLineBorder(borderColor));
		g.setColor(borderColor);
		
		// border around image
		g.drawRect(restWidth+1 + xOffset, restHeight+1 + yOffset, imageWidth-2, imageHeight-2);
		
		// border around container
		//g.drawRect(0, 0, width-1, height-1);
		
		//g.drawRect(Math.max(0, restWidth+1 + xOffset), Math.max(0, restHeight+1 + yOffset), Math.min(width-1, imageWidth-2), Math.min(height-1, imageHeight-2));
		//setComponentZOrder(setBorder(BorderFactory.createLineBorder(borderColor)), 0);
	}	
	
	/**
	 * returns nth (starting at 0) image from image directory
	 * @param nth
	 * @return Image 
	 * @see Image
	 */
	public Image getImage(int nth)
	{
		File[] myFiles = myPictureDirectory.listFiles();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Pictures", "jpg", "jpeg", "png", "bmp");
		
		// count how many pngs are in directory
		int directoryLength = 0;
		for(int i = 0; i < myFiles.length; i++)
		{
			if (filter.accept(myFiles[i]))
				directoryLength++;
		}
		//System.out.println("all: "+myFiles.length+ ", only png: "+directoryLength);
		
		if(nth < 0)
			nth = directoryLength - 1 + (nth % (directoryLength-1));
		
		// to iterate through files
		int file = 0;
		
		// counts nth files, starting at 1
		int counter = 0;
		
		// finds the nth file with .png as ending in folder
		// I don't like those while(true) ... break; loops...
		while(true)
		{
			if(file >= directoryLength)
				file = 0;
			
			boolean fileIsPicture = filter.accept(myFiles[file]);
			if(fileIsPicture && (counter >= nth))
			{
				break;
			}
			
			
			// we're at the nth image but it wasn't a png, let's take the next png we find
			else if (counter == nth)
			{
				file++;
			}
			// it was a png, but not the right one yet, let's take the next file and keep counting
			else if(fileIsPicture)
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

	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		int notches = e.getWheelRotation();
	    if (notches < 0) {
	    	setZoom(getZoom() + 0.1);
	    	repaint();
	    } else {
	    	setZoom(getZoom() - 0.1);
	    	repaint();
	    }
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		int mouseXDiff = e.getX() - mouseClickedPositionX;
		int mouseYDiff = e.getY() - mouseClickedPositionY;

		xOffset += mouseXDiff;
		yOffset += mouseYDiff;

		repaint();

		mouseClickedPositionX = e.getX();
		mouseClickedPositionY = e.getY();
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		mouseClickedPositionX = e.getX();
		mouseClickedPositionY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
