package net.mp3skater.schnabelvokabel.view.elements;

import java.awt.*;

public class BaseFont extends Font {
	public BaseFont() {
		this(40);
	}

	public BaseFont(int size) {
		super("Arial", Font.PLAIN, size);
	}
}
