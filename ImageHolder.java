package labor5;

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
		
		//width = Math.min(width, height);
		//height = width;
		
		// gets the space that is available
		int minSpace = Math.min(width, height);
		int side = (int)Math.round(minSpace);
		
		g.drawRect(0, 0, width-1, height-1); // -1 so that the border is inside
		
		// calculates the space that is left
		int restHeight = (height - side)/2;
		int restWidth = (width - side)/2;
		
		
		// calls specialPaint
		specialPaint(g, side, side, restWidth, restHeight);
	}
	
	protected void specialPaint(Graphics g, int xSize, int ySize, int xStart, int yStart)
	{
		
		// get nth image in Image folder
		Image image = getImage(0);
	}	
	
	public Image getImage(int nth)
	{
		File myPictureDirectory = new File ("/Users/Thomas/Dropbox/Programming/OOP/Labore/src/labor5/pictures");
		File[] myFiles = myPictureDirectory.listFiles();
		
		int file = 0;
		
		// takes the nth file with .pgn as ending in folder
		while(true)
		{
			String imageName = myFiles[file].toString();
			String imageEnding = imageName.substring(imageName.length()-4, imageName.length());
			
			if (imageEnding.equals(".png"))
				break;
			
			file++;
			System.out.println(imageEnding);
		}
		return Toolkit.getDefaultToolkit().getImage(myFiles[file].toString());
	}
}
