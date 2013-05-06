import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton
{
	ImageButton(String imagePath)
	{
		// default path for my machines
		ImageIcon nextButtonImage = new ImageIcon("icons/"+imagePath);
		this.setIcon(nextButtonImage);
	}
}
