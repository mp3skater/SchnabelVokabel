package net.mp3skater.schnabelvokabel.view.elements;

import net.mp3skater.schnabelvokabel.view.panels.FaecherPanel;

public class UpdateButton extends BaseButton {
	public UpdateButton(FaecherPanel root) {
		super("Update", 420, 30, 250, 70);
		this.addActionListener(e -> root.update());
	}
}
