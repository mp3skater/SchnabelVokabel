package net.mp3skater.schnabelvokabel.view.panels;

import net.mp3skater.schnabelvokabel.view.elements.BaseButtonHover;
import net.mp3skater.schnabelvokabel.view.elements.Colors;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoadPanel extends BasePanel {


	private static final long serialVersionUID = 1L;
	private static final ArrayList<Dictionaries> dictionariesList = new ArrayList<>();
	private final JPanel scrollContent;
	public static int x = 10;
	public static int y = 11;
	public static int width = 411;
	public static int height = 60;

	/**
	 * Create the panel.
	 */
	public LoadPanel() {
		setLayout(null);
		setBounds(0, 0, 720, 480);

		JLabel titleLabel = new JLabel("Choose Dictionary");
		titleLabel.setFont(new Font("Arial", Font.PLAIN, 40));
		titleLabel.setBounds(180, 20, 340, 50);
		add(titleLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(135, 100, 450, 310);
		add(scrollPane);

		scrollContent = new JPanel();
		scrollContent.setLayout(null);

		// Load dictionaries into the list model
		scrollPane.setViewportView(scrollContent);
		loadDictionaries();
	}

	private void loadDictionaries() {
		// Clear the existing components in scrollContent
		scrollContent.removeAll();

		for (Dictionaries d : dictionariesList) {
			d.thisIs(Dictionaries.CHOOSE);
			Rectangle bounds = new Rectangle(x, y, width, height);
			scrollContent.add(d);

			y += 70;

			if (bounds.y + 70 > scrollContent.getHeight()) {
				// Increase the size of the scrollContent panel
				scrollContent.setPreferredSize(new java.awt.Dimension(450, bounds.y + 70));
			}
		}

		// Revalidate and repaint the scroll content panel
		scrollContent.revalidate();
		scrollContent.repaint();
	}

	public static void addDictionaryToList(Dictionaries dic) {
		dictionariesList.add(dic);
	}
}
