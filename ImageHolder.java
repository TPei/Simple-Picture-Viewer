import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JPanel;

public class ImageHolder extends JPanel
{
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
		Image image = getImage(2);
		g.drawImage(image, xStart, yStart, xSize, ySize, this);
	}	
	
	// returns nth (starting at 0) image from image directory
	public Image getImage(int nth)
	{
		File myPictureDirectory = new File ("/Users/Thomas/Dropbox/Programming/OOP/Labore/src/labor5/pictures");
		File[] myFiles = myPictureDirectory.listFiles();
		
		// to iterate through files
		int file = 0;
		
		// counts nth files, starting at 1
		int counter = 0;
		
		// finds the nth file with .pgn as ending in folder
		// I don't like those while(true) ... break; loops...
		while(true)
		{
			String imageName = myFiles[file].toString();
			String imageEnding = imageName.substring(imageName.length()-4, imageName.length());
			
			// right image, we're done
			if (imageEnding.equals(".png") && (counter >= nth))
			{
				// we're done, break, return
				break;
			}
			// we're at the nth image but it wasn't a png, let's take the next png we find
			else if (counter == nth)
			{
				file++;
			}
			// it was a png, but not the right one yet, let's take the next file and keep counting
			else if(imageEnding.equals(".png"))
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
}
