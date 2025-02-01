package net.mp3skater.schnabelvokabel.view.elements;

import net.mp3skater.schnabelvokabel.view.panels.NavigationController;

public class BackButton extends BaseButton {
	public BackButton() {
		super("Back", 30, 30, 120, 70);
		this.addActionListener(e -> NavigationController.getInstance().goBack());
	}
}
