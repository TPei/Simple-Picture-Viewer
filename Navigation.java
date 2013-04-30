package labor5;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Navigation extends JPanel
{
	Navigation()
	{
		// picture buttons
        MyButton nextButton = new MyButton();
        ImageIcon nextButtonImage = new ImageIcon("/Users/Thomas/Dropbox/Programming/OOP/Labore/src/labor5/icons/right.png");
        nextButton.setIcon(nextButtonImage);
        
        MyButton prevButton = new MyButton();
        ImageIcon prevButtonImage = new ImageIcon("/Users/Thomas/Dropbox/Programming/OOP/Labore/src/labor5/icons/left.png");
        prevButton.setIcon(prevButtonImage);
        
        // zoom buttons
        MyButton zoomOut = new MyButton();
        ImageIcon zoomOutImage = new ImageIcon("/Users/Thomas/Dropbox/Programming/OOP/Labore/src/labor5/icons/zoomOut.png");
        zoomOut.setIcon(zoomOutImage);
        
        MyButton zoomReal = new MyButton();
        ImageIcon zoomRealImage = new ImageIcon("/Users/Thomas/Dropbox/Programming/OOP/Labore/src/labor5/icons/default.png");
        zoomReal.setIcon(zoomRealImage);
        
        MyButton zoomIn = new MyButton();
        ImageIcon zoomInImage = new ImageIcon("/Users/Thomas/Dropbox/Programming/OOP/Labore/src/labor5/icons/zoomIn.png");
        zoomIn.setIcon(zoomInImage);
        
        // add zoom and navigation buttons to JPanel
        this.add(zoomOut);
        this.add(zoomReal);
        this.add(zoomIn);
        this.add(prevButton);
        this.add(nextButton);
	}
}
