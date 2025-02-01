package net.mp3skater.schnabelvokabel.view.elements;

import javax.swing.*;

public class BaseCheckBox extends JCheckBox {
	public BaseCheckBox(String message, int x, int y) {
		setFont(new BaseFont(20));
		setText(message);
		setBounds(x,y, 400, 70);
		setVisible(true);
		setSelected(false);
	}
}
