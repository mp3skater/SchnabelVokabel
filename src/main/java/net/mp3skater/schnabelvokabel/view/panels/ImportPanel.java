package net.mp3skater.schnabelvokabel.view.panels;

import net.mp3skater.schnabelvokabel.model.AppState;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.Lernkartei;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.VokabeltrainerDB;
import net.mp3skater.schnabelvokabel.view.elements.Colors;
import net.mp3skater.schnabelvokabel.view.elements.DictionaryButton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ImportPanel extends BasePanel {
	private File selectedFile;
	private JTextField nameField;
	private JTextField lang1Field;
	private JTextField lang2Field;
	private JButton chooseFileButton;
	private JButton fileStatusLabel;
	private JButton confirmButton;
	private JButton abortButton;

	public ImportPanel() {
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

		chooseFileButton = new JButton("Choose File");
		chooseFileButton.setHorizontalAlignment(SwingConstants.LEADING);
		chooseFileButton.setFont(new Font("Arial", Font.PLAIN, 28));
		chooseFileButton.setBounds(175, 445, 200, 50); // (175, 470-25)
		chooseFileButton.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Colors.COLOR1.color));
		chooseFileButton.setContentAreaFilled(false);
		chooseFileButton.setFocusPainted(false);
		add(chooseFileButton);

		fileStatusLabel = new JButton("no file chosen");
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
		add(confirmButton);

		abortButton = new JButton("Decline");
		abortButton.setBounds(410, 530, 228, 41); // (410, 555-25)
		abortButton.setBorder(new LineBorder(Color.black));
		abortButton.setFocusPainted(false);
		add(abortButton);

		setVisible(true);

		fileStatusLabel.addActionListener(e -> {
				chooseFile();
		});

		confirmButton.addActionListener(e -> {
			Lernkartei kartei = new Lernkartei(-1, name.getText(), lang1Field.getText(), lang2Field.getText(),
							AppState.getInstance().isReverseLanguage(),
							AppState.getInstance().isCheckCapitalization());
			if (validateInputs() && VokabeltrainerDB.hinzufuegenLernkartei(kartei) == 0
								&& VokabeltrainerDB.importierenKarten(kartei.getNummer(), selectedFile.getPath()) == 0) {
				DictionaryButton dick = new DictionaryButton(new Rectangle(LoadPanel.x, LoadPanel.y - 70, LoadPanel.width, LoadPanel.height),
								kartei.getNummer(), kartei.getBeschreibung(), kartei.getWortEinsBeschreibung(),
								kartei.getWortZweiBeschreibung(), this, kartei, selectedFile);
				LoadPanel.addDictionaryToList(dick);
				NavigationController.getInstance().goBack();
			} else if (dictionaryExists(nameField.getText().trim())) {
				JOptionPane.showMessageDialog(ImportPanel.this,
								"A dictionary with the same name already exists. Please choose a different name.",
								"Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(ImportPanel.this, "Invalid input. Please check your entries.",
								"Warning", JOptionPane.WARNING_MESSAGE);
			}
		});

		abortButton.addActionListener(e -> {
			nameField.setText(null);
			NavigationController.getInstance().goBack();
		});
	}

	private void chooseFile() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
		fileChooser.setFileFilter(filter);

		int result = fileChooser.showOpenDialog(this);

		if (result == JFileChooser.APPROVE_OPTION) {
			selectedFile = fileChooser.getSelectedFile(); // Update the class-level selectedFile
			if ((!selectedFile.getName().toLowerCase().endsWith(".txt"))) {
				JOptionPane.showMessageDialog(this, "Invalid file format. Please use the specified format.", "Warning",
								JOptionPane.WARNING_MESSAGE);
				selectedFile = null; // Reset selectedFile if the format is invalid
				return;
			}
			if (!validateFile(selectedFile)) {
				JOptionPane.showMessageDialog(this, "Invalid file format. Please use the specified format.", "Warning",
								JOptionPane.WARNING_MESSAGE);
				selectedFile = null; // Reset selectedFile if the format is invalid
				return;
			}
			// Update the fileStatusLabel text
			fileStatusLabel.setText("File chosen: " + selectedFile.getName());
		}
	}

	private boolean dictionaryExists(String name) {
		for (Lernkartei kartei : VokabeltrainerDB.getLernkarteien()) {
			if (kartei.getBeschreibung().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	private boolean validateInputs() {
		String name = nameField.getText().trim();
		String language1 = lang1Field.getText().trim();
		String language2 = lang2Field.getText().trim();

		// Validate that the text fields contain only letters and numbers
		if (!name.matches("[a-zA-Z0-9]+") || !language1.matches("[a-zA-Z]+") || !language2.matches("[a-zA-Z]+")) {
			return false;
		}

		// Validate that a file is chosen
		if (selectedFile == null) {
			return false;
		}

		// Check if a dictionary with the same name already exists
		if (dictionaryExists(name)) {
			return false;
		}

		return true;
	}

	private boolean validateFile(File file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

			String line;
			while ((line = reader.readLine()) != null) {
				if (!isValidLine(line)) {
					return false;
				}
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean isValidLine(String line) {
		return line.matches("^[a-zA-Z]+\\s*;\\s*[a-zA-Z]+(\\s*;\\s*\\d+)?\\s*$");
	}

	public String getDictionaryName() {
		return nameField.getText().trim();
	}

	public String getLanguage1() {
		return lang1Field.getText().trim();
	}

	public String getLanguage2() {
		return lang2Field.getText().trim();
	}

	public File getSelectedFile() {
		return selectedFile;
	}

	@Override
	public void update() {}
}
