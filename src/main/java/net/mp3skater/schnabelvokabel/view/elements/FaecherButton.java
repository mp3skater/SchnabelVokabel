package net.mp3skater.schnabelvokabel.view.elements;

import net.mp3skater.schnabelvokabel.model.AppState;
import net.mp3skater.schnabelvokabel.view.panels.NavigationController;

public class FaecherButton extends BaseButton {
	public FaecherButton(int fachNummer) {
		super(String.valueOf(fachNummer-2), 45, (fachNummer-1)*100);
		addActionListener(e -> {
			AppState.getInstance().setCurrentFach(fachNummer);
			NavigationController.getInstance().navigateTo("trainingMode");
		});
	}
}
