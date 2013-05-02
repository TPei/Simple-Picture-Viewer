import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JPanel;

public class ImageHolder extends JPanel
{
	private int whichPicture = 0;
	
	public int getWhichPicture()
	{
		return whichPicture;
	}

	public void setWhichPicture(int whichPicture)
	{
		this.whichPicture = whichPicture;
	}

	ImageHolder()
	{
		
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		// int width and height of window
		int width = getWidth(); 
		int height = getHeight();
		
		// gets the space that is available
		int minSpace = Math.min(width, height);
		int side = (int)Math.round(minSpace);
		
		// calculates the space that is left
		int restHeight = (height - side)/2;
		int restWidth = (width - side)/2;
		
		
		// calls specialPaint
		specialPaint(g, side, side, restWidth, restHeight);
	}
	
	protected void specialPaint(Graphics g, int xSize, int ySize, int xStart, int yStart)
	{
		// get nth image in Image folder
		Image image = getImage(whichPicture);
		System.out.println(whichPicture);
		g.drawImage(image, xStart, yStart, xSize, ySize, this);
	}	
	
	// returns nth (starting at 0) image from image directory
	public Image getImage(int nth)
	{
		File myPictureDirectory = new File ("/Users/Thomas/Dropbox/Programming/OOP/Labore/src/labor5/pictures");
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
}
