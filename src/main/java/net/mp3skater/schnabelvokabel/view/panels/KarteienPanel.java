package net.mp3skater.schnabelvokabel.view.panels;

import net.mp3skater.schnabelvokabel.model.AppState;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.Fach;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.VokabeltrainerDB;
import net.mp3skater.schnabelvokabel.view.elements.BackButton;
import net.mp3skater.schnabelvokabel.view.elements.BaseButton;
import net.mp3skater.schnabelvokabel.view.elements.FaecherButton;
import net.mp3skater.schnabelvokabel.view.elements.UpdateButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;

public class KarteienPanel extends BasePanel {

	static ArrayList<Fach> faecher = new ArrayList<>();

	public KarteienPanel() {
		setLayout(null);
		add(new UpdateButton(this));
	}

	@Override
	public void update() {
		removeAll();
		add(new BackButton());
		add(new UpdateButton(this));
		faecher.clear();
		faecher.addAll(VokabeltrainerDB.getFaecher(AppState.getInstance().getCurrentKarteiNummer()));
		for (Fach f : faecher) {
			add(new FaecherButton(f.getNummer()));
		}
		revalidate();
		repaint();
	}
}
