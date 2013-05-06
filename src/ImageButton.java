import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/** extends JButton
 * automatically sets image path for button to given path
 * 
 * @author Thomas
 */
public class ImageButton extends JButton
{
	ImageButton(String imagePath)
	{
		// default path for my machines
		ImageIcon nextButtonImage = new ImageIcon("icons/"+imagePath);
		this.setPreferredSize(new Dimension(100, 100));
		this.setIcon(nextButtonImage);
	}
}
