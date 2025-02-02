package net.mp3skater.schnabelvokabel.view.elements;

import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.Lernkartei;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.VokabeltrainerDB;
import net.mp3skater.schnabelvokabel.view.panels.BasePanel;
import net.mp3skater.schnabelvokabel.view.panels.NavigationController;

import java.awt.*;
import java.io.File;

public class DeleteDictionaryButton extends DictionaryButton {
	private int nummer;

	public DeleteDictionaryButton(Rectangle r, int nummer, String nameB, String language1B, String language2B, BasePanel parentPanel, Lernkartei kartei, File file) {
		super(r, nummer, nameB, language1B, language2B, parentPanel, kartei, file);
		this.nummer = nummer;
	}

	@Override
	protected void action() {
		VokabeltrainerDB.loeschenLernkartei(nummer);
	}
}
