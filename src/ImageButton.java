import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton
{
	ImageButton(String imagePath)
	{
		// default path for my machines
		ImageIcon nextButtonImage = new ImageIcon("/Users/Thomas/Dropbox/Programming/OOP/OOP Labor 5/icons/"+imagePath);
		this.setIcon(nextButtonImage);
	}
}
