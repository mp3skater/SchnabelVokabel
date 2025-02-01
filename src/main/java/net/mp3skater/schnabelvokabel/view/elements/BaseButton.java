package net.mp3skater.schnabelvokabel.view.elements;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class BaseButton extends JButton {
	public BaseButton(String message, int x, int y) {
		this(message, x, y, 630, 70);
	}

	public BaseButton(String message, int x, int y, int width, int height) {
		this.setFont(new BaseFont());
		this.setBounds(x, y, width, height);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		this.setBorder(new LineBorder(Colors.COLOR1.color,4));
		this.setText(message);
		this.addMouseListener(new BaseButtonHover());
	}
}
