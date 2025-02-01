package net.mp3skater.schnabelvokabel;

import net.mp3skater.schnabelvokabel.model.AppState;
import net.mp3skater.schnabelvokabel.view.panels.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static AppState appState;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("SchnabelVokabel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(720, 770);

            CardLayout cardLayout = new CardLayout();
            JPanel mainPanel = new JPanel(cardLayout);

            appState = AppState.getInstance();

            mainPanel.add(new MainMenuPanel(), "mainMenu");
            mainPanel.add(new SettingsPanel(), "settings");
            mainPanel.add(new TrainMenuPanel(), "trainMenu");
            mainPanel.add(new ImportPanel(), "import");
            mainPanel.add(new LoadPanel(), "load");
//            mainPanel.add(new NewDictionaryPanel(), "newDictionary");
//            mainPanel.add(new ChooseDictionaryPanel(), "chooseDictionary");
//            mainPanel.add(new TrainingModePanel(), "trainingMode");
//            mainPanel.add(new WordManagementPanel(), "wordManagement");
            mainPanel.add(new ExportScreenPanel(), "exportScreen");

            NavigationController.initialize(cardLayout, mainPanel, "mainMenu");
            cardLayout.show(mainPanel, "mainMenu");

            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }
}