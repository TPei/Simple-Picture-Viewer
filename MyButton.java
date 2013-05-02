package labor5;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class MyButton extends JButton implements MouseListener
{
	int id;
	public MyButton(int id)
	{
		addMouseListener(this);
		this.id = id;
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		switch(id)
		{
		case 0:
			System.out.println("nextButton");
			break;
		case 1:
			System.out.println("prevButton");
			break;
		case 2:
			System.out.println("zoomOut");
			break;
		case 3:
			System.out.println("zoomReal");
			break;
		case 4:
			System.out.println("zoomIn");
			break;
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
}
