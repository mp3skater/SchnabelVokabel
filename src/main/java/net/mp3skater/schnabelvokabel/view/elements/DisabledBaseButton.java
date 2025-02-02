package net.mp3skater.schnabelvokabel.view.elements;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DisabledBaseButton extends JButton {
	public DisabledBaseButton(String message, int x, int y) {
		this(message, x, y, 630, 70);
	}

	public DisabledBaseButton(String message, int x, int y, int width, int height) {
		this.setFont(new BaseFont());
		this.setBounds(x, y, width, height);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		this.setBorder(new LineBorder(Color.GRAY));
		this.setText(message);
		this.setVisible(true);
	}
}
