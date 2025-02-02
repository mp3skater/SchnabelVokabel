package net.mp3skater.schnabelvokabel.view.panels;

import net.mp3skater.schnabelvokabel.model.AppState;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.Fach;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.Karte;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.Lernkartei;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.VokabeltrainerDB;
import net.mp3skater.schnabelvokabel.view.elements.BackButton;
import net.mp3skater.schnabelvokabel.view.elements.BaseButton;
import net.mp3skater.schnabelvokabel.view.elements.BaseFont;
import net.mp3skater.schnabelvokabel.view.elements.DisabledBaseButton;

import javax.swing.*;
import java.util.ArrayList;

public class EditPanel extends BasePanel {
	private BaseButton newKartei;
	private DisabledBaseButton delKartei, newKarte, delKarte;
	private Lernkartei kartei;
	private Fach fach;
	private Karte karte;
	private ArrayList<Fach> faches;

	@Override
	public void update() {
		removeAll();
		add(new BackButton());

		int panelWidth = 720;
		int panelHeight = 770;
		int spacing = 25;
		int descHeight = 70;
		int l1Height = 70;
		int w1Height = 70;
		int l2Height = 70;
		int btnHeight = 40;
		int w2Width = 500;

		int totalComponentsHeight = descHeight + l1Height + w1Height + l2Height + descHeight + btnHeight;
		int totalSpacing = spacing * 5;
		int totalUsedHeight = totalComponentsHeight + totalSpacing;
		int startY = (int) ((panelHeight - totalUsedHeight) / 2);

		int y = startY + descHeight + spacing;
		newKartei = new BaseButton("New Kartei", 0, 0);
		newKartei.setFont(new BaseFont());
		newKartei.setBounds((panelWidth - w2Width) / 2, y, w2Width, descHeight);
		newKartei.setFont(new BaseFont());
		newKartei.addActionListener(e -> NavigationController.getInstance().navigateTo("newKartei"));
		add(newKartei);

		y += l1Height + spacing;
		delKartei = new DisabledBaseButton("Delete Kartei", 0, 0);
		delKartei.setBounds((panelWidth - w2Width) / 2, y, w2Width, descHeight);
		delKartei.setFont(new BaseFont());
//		delKartei.addActionListener(e -> NavigationController.getInstance().navigateTo("deleteKartei"));
		add(delKartei);

		y += w1Height + spacing;
		newKarte = new DisabledBaseButton("New Karte", 0, 0);
		newKarte.setFont(new BaseFont());
		newKarte.setBounds((panelWidth - w2Width) / 2, y, w2Width, descHeight);
		newKarte.setFont(new BaseFont());
		add(newKarte);

		y += l2Height + spacing;
		delKarte = new DisabledBaseButton("Delete Karte", 0, 0);
		delKarte.setBounds((panelWidth - w2Width) / 2, y, w2Width, descHeight);
		delKarte.setFont(new BaseFont());
		add(delKarte);

		revalidate();
		repaint();
	}
}
