package net.mp3skater.schnabelvokabel.view.panels;


import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.Fach;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.Lernkartei;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.VokabeltrainerDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class Dictionaries extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<String> vocabList;
	private int nummer;
	private Lernkartei kartei;
	private JLabel vocabs;
	public final static int ADD = 0;
	public final static int CHOOSE = 1;
	private final int thisIs;
	private File file;
	private final JLabel plus = new JLabel();
	private MouseListenerAdd add = new MouseListenerAdd();
	private MouseListenerChoose choose = new MouseListenerChoose();

	/**
	 * Create the panel.
	 */
	private BasePanel parentPanel; // Reference to the parent panel

	public Dictionaries(Rectangle r, final int nummer, String nameB, String language1B, String language2B,
											BasePanel parentPanel, Lernkartei kartei, File file) {
		thisIs = ADD;
		setBounds(r);
		setLayout(null);
		setBorder(BorderFactory.createLineBorder(new Color(100,205,170),4));
		this.nummer = nummer;
		this.parentPanel = parentPanel;
		this.kartei = kartei;
		this.file = file;

		JLabel name = new JLabel(nameB);
		name.setFont(new Font("Arial", Font.PLAIN, 22));
		name.setBounds(10, 0, 100, 60);
		add(name);

		vocabs = new JLabel("Vocabs: " + getVocabs());
		vocabs.setFont(new Font("Arial", Font.PLAIN, 22));
		vocabs.setBounds(120, 0, 120, 60);
		add(vocabs);

		JLabel language1 = new JLabel(language1B);
		language1.setFont(new Font("Arial", Font.PLAIN, 18));
		language1.setBounds(260, 5, 100, 25);
		add(language1);

		JLabel language2 = new JLabel(language2B);
		language2.setFont(new Font("Arial", Font.PLAIN, 18));
		language2.setBounds(260, 30, 100, 25);
		add(language2);

		/*
		plus.setFont(new Font("Amiri", Font.PLAIN, 50));
		plus.setHorizontalAlignment(SwingConstants.CENTER);
		plus.setBorder(BorderFactory.createLineBorder(Color.black));
		plus.setBounds(360, 8, 45, 45);
		add(plus);

		// Add a MouseListener to the "plus" label
		plus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showOptionsDialog(plus.getLocationOnScreen(), nummer);
			}
		});
		*/
	}
	
	private int getVocabs() {
		int words = 0;

		for (Fach i : VokabeltrainerDB.getFaecher(nummer)) {
			words += VokabeltrainerDB.getKarten(i.getNummer()).size();
		}

		return words;
	}

//	public void showOptionsDialog(Point point, int nummer) {
//		OptionsDialog optionsDialog = new OptionsDialog(point, nummer, this, kartei);
//		optionsDialog.setVisible(true);
//	}

	public int getNummer() {
		return nummer;
	}

	public void refreshVocabs() {
		vocabs.setText("Vocabs: " + getVocabs());
	}
	
	public void setY(int y) {
		setLocation(this.getX(), y);
	}
	
	public void thisIs(int thisIs) {
		// Remove existing MouseListeners
	    plus.removeMouseListener(add);
	    plus.removeMouseListener(choose);

	    if (thisIs == CHOOSE) {
	        plus.setText("");
	        plus.setHorizontalAlignment(SwingConstants.CENTER);
	        plus.setBounds(360, 8, 45, 45);
	        plus.setIcon(new ImageIcon(getClass().getResource("/icons/triangleICO.png")));
	        plus.setBorder(null);
	        add(plus);

	        plus.addMouseListener(choose);

	    } else if (thisIs == ADD) {
	        plus.setText("+");
	        plus.setIcon(null);
	        plus.setFont(new Font("Amiri", Font.PLAIN, 50));
	        plus.setHorizontalAlignment(SwingConstants.CENTER);
	        plus.setBorder(BorderFactory.createLineBorder(Color.black));
	        plus.setBounds(360, 8, 45, 45);
	        add(plus);

	        plus.addMouseListener(add);
	    }
	}

	
	private class MouseListenerChoose extends MouseAdapter {
		@Override
	    public void mouseClicked(MouseEvent e) {

//			MainFrame.switchPanel(new NewPanel(VokabeltrainerDB.getLernkartei(nummer), file));
	    }
	}
	
	private class MouseListenerAdd extends MouseAdapter {
		//	    public void mouseClicked(MouseEvent e) {
//			showOptionsDialog(plus.getLocationOnScreen(), nummer);
//	    }
	}
}
