package net.mp3skater.schnabelvokabel.view.panels;

import net.mp3skater.schnabelvokabel.model.AppState;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.Fach;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.Karte;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.Lernkartei;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.VokabeltrainerDB;
import net.mp3skater.schnabelvokabel.view.elements.Colors;
import net.mp3skater.schnabelvokabel.view.elements.DictionaryButton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class NewKarteiPanel extends BasePanel {
	private JTextField nameField, lang1Field, lang2Field;
	private JButton chooseFileButton, fileStatusLabel, confirmButton, abortButton;
	private File selectedFile;

	public NewKarteiPanel() {
		JLabel name = new JLabel("Name:");
		name.setBounds(175, 250, 200, 50); // (175, 275-25)
		name.setFont(new Font("Arial", Font.PLAIN, 28));
		name.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Colors.COLOR1.color));
		add(name);

		nameField = new JTextField();
		nameField.setFont(new Font("Arial", Font.PLAIN, 28));
		nameField.setBounds(408, 250, 230, 50); // (408, 275-25)
		nameField.setBorder(new LineBorder(Colors.COLOR1.color, 4));
		add(nameField);

		JLabel lang1 = new JLabel("Language 1:");
		lang1.setBounds(175, 315, 200, 50); // (175, 340-25)
		lang1.setFont(new Font("Arial", Font.PLAIN, 28));
		lang1.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Colors.COLOR1.color));
		add(lang1);

		lang1Field = new JTextField();
		lang1Field.setFont(new Font("Arial", Font.PLAIN, 28));
		lang1Field.setBounds(408, 315, 230, 50); // (408, 340-25)
		lang1Field.setBorder(new LineBorder(Colors.COLOR1.color, 4));
		add(lang1Field);

		JLabel lang2 = new JLabel("Language 2:");
		lang2.setBounds(175, 380, 200, 50); // (175, 405-25)
		lang2.setFont(new Font("Arial", Font.PLAIN, 28));
		lang2.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Colors.COLOR1.color));
		add(lang2);

		lang2Field = new JTextField();
		lang2Field.setFont(new Font("Arial", Font.PLAIN, 28));
		lang2Field.setBounds(408, 380, 230, 50); // (408, 405-25)
		lang2Field.setBorder(new LineBorder(Colors.COLOR1.color, 4));
		add(lang2Field);

		chooseFileButton = new JButton("Choose Connected File");
		chooseFileButton.setHorizontalAlignment(SwingConstants.LEADING);
		chooseFileButton.setFont(new Font("Arial", Font.PLAIN, 28));
		chooseFileButton.setBounds(175, 445, 200, 50); // (175, 470-25)
		chooseFileButton.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Colors.COLOR1.color));
		chooseFileButton.setContentAreaFilled(false);
		chooseFileButton.setFocusPainted(false);
		add(chooseFileButton);

		fileStatusLabel = new JButton("file chosen");
		fileStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fileStatusLabel.setFont(new Font("Arial", Font.PLAIN, 28));
		fileStatusLabel.setBackground(new Color(255, 255, 255));
		fileStatusLabel.setOpaque(true);
		fileStatusLabel.setBorder(new LineBorder(Colors.COLOR1.color, 4));
		fileStatusLabel.setBounds(408, 445, 230, 50); // (408, 470-25)
		add(fileStatusLabel);

		confirmButton = new JButton("Confirm");
		confirmButton.setBounds(175, 530, 225, 41); // (175, 555-25)
		confirmButton.setBorder(new LineBorder(Color.black));
		confirmButton.setFocusPainted(false);
		confirmButton.addActionListener(e -> {
			Lernkartei kart = new Lernkartei(-1, nameField.getText(), lang1.getText(), lang2.getText(),
											AppState.getInstance().isReverseLanguage(), AppState.getInstance().isCheckCapitalization());
			VokabeltrainerDB.hinzufuegenLernkartei(kart);
			VokabeltrainerDB.hinzufuegenFach(kart.getNummer(), new Fach(1, "beschreibung", 3, new Date()));
			LoadPanel.addDictionaryToList(new DictionaryButton(new Rectangle(LoadPanel.x, LoadPanel.y - 70, LoadPanel.width, LoadPanel.height),
							kart.getNummer(), kart.getBeschreibung(), kart.getWortEinsBeschreibung(),
							kart.getWortZweiBeschreibung(), this, kart, null));
			NavigationController.getInstance().goBack();
		});
		add(confirmButton);

		abortButton = new JButton("Decline");
		abortButton.setBounds(410, 530, 228, 41); // (410, 555-25)
		abortButton.setBorder(new LineBorder(Color.black));
		abortButton.setFocusPainted(false);
		abortButton.addActionListener(e -> NavigationController.getInstance().goBack());
		add(abortButton);

		fileStatusLabel.addActionListener(e -> {
			createFile();
			NavigationController.getInstance().goBack();
		});

		setVisible(true);
	}

	private void createFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select Directory to Save File");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int result = fileChooser.showSaveDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedDirectory = fileChooser.getSelectedFile();
			String fileName = JOptionPane.showInputDialog("Enter file name (without extension):");

			if (fileName == null || fileName.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Invalid file name.", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}

			File newFile = new File(selectedDirectory, fileName + ".txt");

			try {
				if (newFile.createNewFile()) {
					JOptionPane.showMessageDialog(null, "File created: " + newFile.getAbsolutePath(), "Success", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "File already exists.", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error creating file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	@Override
	public void update() {}
}
