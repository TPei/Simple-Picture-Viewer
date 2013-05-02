import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Navigation extends JPanel
{
	Navigation()
	{
		// picture buttons
        JButton nextButton = new JButton();
        ImageIcon nextButtonImage = new ImageIcon("/Users/Thomas/Dropbox/Programming/OOP/Labore/src/labor5/icons/right.png");
        nextButton.setIcon(nextButtonImage);
        
        JButton prevButton = new JButton();
        ImageIcon prevButtonImage = new ImageIcon("/Users/Thomas/Dropbox/Programming/OOP/Labore/src/labor5/icons/left.png");
        prevButton.setIcon(prevButtonImage);
        
        // zoom buttons
        JButton zoomOut = new JButton();
        ImageIcon zoomOutImage = new ImageIcon("/Users/Thomas/Dropbox/Programming/OOP/Labore/src/labor5/icons/zoomOut.png");
        zoomOut.setIcon(zoomOutImage);
        
        JButton zoomReal = new JButton();
        ImageIcon zoomRealImage = new ImageIcon("/Users/Thomas/Dropbox/Programming/OOP/Labore/src/labor5/icons/default.png");
        zoomReal.setIcon(zoomRealImage);
        
        JButton zoomIn = new JButton();
        ImageIcon zoomInImage = new ImageIcon("/Users/Thomas/Dropbox/Programming/OOP/Labore/src/labor5/icons/zoomIn.png");
        zoomIn.setIcon(zoomInImage);
        
        
        // action listeners
        nextButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("next Picture");
        	}
		});
        
        prevButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("previous Picture");
        	}
		});
        
        zoomOut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("zoom out");
        	}
		});
        
        zoomReal.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("default zoom");
        	}
		});
        
        zoomIn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("zoom in");
        	}
		});
        
        
        
        // add zoom and navigation buttons to JPanel
        this.add(zoomOut);
        this.add(zoomReal);
        this.add(zoomIn);
        this.add(prevButton);
        this.add(nextButton);
	}
	
}
