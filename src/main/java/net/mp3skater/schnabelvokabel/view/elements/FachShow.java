package net.mp3skater.schnabelvokabel.view.elements;

import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.Fach;
import net.mp3skater.schnabelvokabel.view.panels.NavigationController;

public class FachShow extends BaseButton {
	public FachShow(Fach fach) {
		super(String.valueOf(fach.getNummer()-2), 45, (fach.getNummer()-1)*100);
		System.out.println(fach.getNummer());

		addActionListener(e -> NavigationController.getInstance().navigateTo("trainingMode"));
	}
}
