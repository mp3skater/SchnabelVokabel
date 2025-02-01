package net.mp3skater.schnabelvokabel.view.elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BaseButtonHover implements MouseListener {

	final double sizeDifference = 0.01;
	final int fontSizeDifference = 6;

	private static Rectangle bounds = new Rectangle();

	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		enlargeButton((BaseButton)e.getSource());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		smallerButton((BaseButton)e.getSource());
	}

	public void enlargeButton(BaseButton button) {
		Rectangle rec = button.getBounds();
		bounds = rec;
		button.setBounds(rec.x - (int) (rec.width  * (sizeDifference/2)), rec.y - (int) (rec.height  * (sizeDifference/2)),
						rec.width + (int) (rec.width  * sizeDifference), rec.height + (int) (rec.height  * sizeDifference));
		button.setFont(new Font("Arial", Font.PLAIN, button.getFont().getSize()+this.fontSizeDifference));
	}

	public void smallerButton(BaseButton button) {
		button.setBounds(bounds);
		button.setFont(new Font("Arial", Font.PLAIN, button.getFont().getSize()-this.fontSizeDifference));
	}
}
