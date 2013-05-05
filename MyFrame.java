import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**creates frames that shows a picture and a navigational bar below
 * @author Thomas
 * @version 0.3
 * 
 * @see ImageHolder
 */
public class MyFrame extends JFrame
{
	/**
	 * creates a frame that contains a navigational bar and a larger picture
	 */
	MyFrame()
	{
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);
		
		// Image to be added
		final ImageHolder image = new ImageHolder();
		
		
		// create Navigation (buttons with images and action listeners)
		JPanel buttonContainer = new JPanel();
		//Navigation buttonContainer = new Navigation();
		
		JButton nextButton = new JButton();
        ImageIcon nextButtonImage = new ImageIcon("/Users/Thomas/Dropbox/Github/OOP-Labor-5/icons/right.png");
        nextButton.setIcon(nextButtonImage);
        
        JButton prevButton = new JButton();
        ImageIcon prevButtonImage = new ImageIcon("/Users/Thomas/Dropbox/Github/OOP-Labor-5/icons/left.png");
        prevButton.setIcon(prevButtonImage);
        
        // zoom buttons
        JButton zoomOut = new JButton();
        ImageIcon zoomOutImage = new ImageIcon("/Users/Thomas/Dropbox/Github/OOP-Labor-5/icons/zoomOut.png");
        zoomOut.setIcon(zoomOutImage);
        
        JButton zoomReal = new JButton();
        ImageIcon zoomRealImage = new ImageIcon("/Users/Thomas/Dropbox/Github/OOP-Labor-5/icons/defaultSize.png");
        zoomReal.setIcon(zoomRealImage);
        
        JButton zoomIn = new JButton();
        ImageIcon zoomInImage = new ImageIcon("/Users/Thomas/Dropbox/Github/OOP-Labor-5/icons/zoomIn.png");
        zoomIn.setIcon(zoomInImage);
        
        // action listeners
        nextButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("next Picture");
        		int thisPicture = image.getWhichPicture();
        		image.setWhichPicture(++thisPicture);
        		image.resetImage();
        		image.repaint();
        	}
		});
        
        prevButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("previous Picture");
        		int thisPicture = image.getWhichPicture();
        		image.setWhichPicture(--thisPicture);
        		image.resetImage();
        		image.repaint();
        	}
		});
        
        zoomOut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		image.setZoom(image.getZoom() - 0.1);
        		image.repaint();
        	}
		});
        
        zoomReal.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		image.resetImage();
        		image.repaint();
        	}
		});
        
        zoomIn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("zoom in");
        		image.setZoom(image.getZoom() + 0.1);
        		image.repaint();
        	}
		});
        
        // add zoom and navigation buttons to JPanel
        buttonContainer.add(zoomOut);
        buttonContainer.add(zoomReal);
        buttonContainer.add(zoomIn);
        buttonContainer.add(prevButton);
        buttonContainer.add(nextButton);
        
        // add image to center of frame
        add(image, BorderLayout.CENTER);
        
        // add panel containing buttons to south of frame
        add(buttonContainer, BorderLayout.SOUTH);
		
        // Menu to choose border color
        MenuBar myMenuBar = new MenuBar();
        Menu myMenu = new Menu("Preferences");
        MenuItem myMenuItem = new MenuItem("Choose Border Color");
        MenuItem myMenuItem2 = new MenuItem("Choose file path");
        myMenuBar.add(myMenu);
        myMenu.add(myMenuItem2);
        myMenu.add(myMenuItem);  
        
        setMenuBar(myMenuBar);
        
        // set color to chosen color when using color chooser menu
        myMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("myMenuItem clicked");
        		
        		Color newColor = JColorChooser.showDialog(null, "Farbe waehlen", image.getBorderColor()); 
        		
        		if (newColor != null)
        		{
        			image.setBorderColor(newColor);
        			repaint(); 
        		}
        	}
		});
        myMenuItem2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("myMenuItem clicked");
        		
        		//File newFile = JFileChooser.showDialog(null, "Pick a image directory"); 
        		
        		/*if (newColor != null)
        		{
        			image.setBorderColor(newColor);
        			repaint(); 
        		}*/
        	}
		});
        
		setVisible(true);
	}
}
