package labor5;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame
{
	MyFrame()
	{
		// creates a Frame that contains 8 rows and 8 columns of randomly picked, colored and sized shapes
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);
		
		ImageHolder image = new ImageHolder();
		
		//JPanel buttonContainer = createNavigation();
		Navigation buttonContainer = new Navigation();
        
        // add image to center of frame
        add(image, BorderLayout.CENTER);
        
        // add panel containing buttons to south of frame
        add(buttonContainer, BorderLayout.SOUTH);
		
		setVisible(true);
	}
}
