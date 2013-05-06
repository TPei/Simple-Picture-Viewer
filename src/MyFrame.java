import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

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
		
		// multiple buttons for navigation and zoom
		ImageButton nextButton = new ImageButton("right.png");
		ImageButton prevButton = new ImageButton("left.png");
		ImageButton zoomOut = new ImageButton("zoomOut.png");
		ImageButton zoomReal = new ImageButton("defaultSize.png");
		ImageButton zoomIn = new ImageButton("zoomIn.png");
        
		
		
        // action listeners
        nextButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int thisPicture = image.getWhichPicture();
        		image.setWhichPicture(++thisPicture);
        		image.resetImage();
        		image.repaint();
        	}
		});
        
        prevButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
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
		
        // Menu to choose border color and file path
        MenuBar myMenuBar = new MenuBar();
        Menu myMenu = new Menu("Preferences");
        MenuItem myMenuItem = new MenuItem("Choose Border Color");
        MenuItem myMenuItem2 = new MenuItem("Choose File Path");
        myMenuBar.add(myMenu);
        myMenu.add(myMenuItem2);
        myMenu.add(myMenuItem);  
        
        setMenuBar(myMenuBar);
         
        // set color to chosen color when using JColorChooser menu
        myMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		Color newColor = JColorChooser.showDialog(null, "Farbe waehlen", image.getBorderColor()); 
        		
        		if (newColor != null)
        		{
        			image.setBorderColor(newColor);
        			repaint(); 
        		}
        	}
		});
        
        // set file directory for images via JFileChooser menu
        myMenuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser myFileDialog = new JFileChooser(); 
				
				myFileDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
				
				int result = myFileDialog.showDialog(null, "Choose Directory"); 
				
				if (result == JFileChooser.APPROVE_OPTION)
				{
					image.setMyPictureDirectory(myFileDialog.getSelectedFile());
					repaint();
				}
        	}
		});
        
		setVisible(true);
	}
}
