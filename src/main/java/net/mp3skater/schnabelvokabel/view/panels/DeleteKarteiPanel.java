package net.mp3skater.schnabelvokabel.view.panels;

import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.Lernkartei;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.VokabeltrainerDB;
import net.mp3skater.schnabelvokabel.view.elements.DeleteDictionaryButton;
import net.mp3skater.schnabelvokabel.view.elements.DictionaryButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DeleteKarteiPanel extends BasePanel {
	private static final long serialVersionUID = 1L;
	private static final ArrayList<DictionaryButton> dictionariesList = new ArrayList<>();
	private static final JPanel scrollContent = new JPanel();
	public static int x = 10;
	public static int y = 70;
	public static int width = 620;
	public static int height = 60;

	@Override
	public void update() {
		setLayout(null);
		setBounds(0, 0, 720, 480);

		JLabel titleLabel = new JLabel("Delete Dictionary");
		titleLabel.setFont(new Font("Arial", Font.PLAIN, 40));
		titleLabel.setBounds(230, 20, 450, 50);
		add(titleLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(50, 150, 660, 600);
		add(scrollPane);

		scrollContent.setLayout(null);

		// Load dictionaries into the list model
		scrollPane.setViewportView(scrollContent);

		dictionariesList.clear();
		for(Lernkartei kart : VokabeltrainerDB.getLernkarteien()) {
			DeleteDictionaryButton del = new DeleteDictionaryButton(
						new Rectangle(LoadPanel.x, LoadPanel.y - 70, LoadPanel.width, LoadPanel.height),
						kart.getNummer(), kart.getBeschreibung(), kart.getWortEinsBeschreibung(),
						kart.getWortZweiBeschreibung(), this, kart, null);
			del.addActionListener(e -> {
				update();

				revalidate();
				repaint();
			});
			add(del);
		}

		loadDictionaries();
	}

	public static void loadDictionaries() {
		// Clear the existing components in scrollContent
		scrollContent.removeAll();

		for (DictionaryButton d : dictionariesList) {
			d.thisIs(DictionaryButton.CHOOSE);
			Rectangle bounds = new Rectangle(x, y, width, height);
			scrollContent.add(d);

			y += 70;

			if (bounds.y + 70 > scrollContent.getHeight()) {
				// Increase the size of the scrollContent panel
				scrollContent.setPreferredSize(new Dimension(450, bounds.y + 70));
			}
		}

		// Revalidate and repaint the scroll content panel
		scrollContent.revalidate();
		scrollContent.repaint();
	}
}
