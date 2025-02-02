package net.mp3skater.schnabelvokabel;

import net.mp3skater.schnabelvokabel.model.AppState;
import net.mp3skater.schnabelvokabel.view.panels.*;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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

            // Create panel instances
            MainMenuPanel mainMenuPanel = new MainMenuPanel();
            SettingsPanel settingsPanel = new SettingsPanel();
            TrainMenuPanel trainMenuPanel = new TrainMenuPanel();
            ImportPanel importPanel = new ImportPanel();
            LoadPanel loadPanel = new LoadPanel();
            TrainingModePanel trainingModePanel = new TrainingModePanel();
            FaecherPanel karteienPanel = new FaecherPanel();
            ExportScreenPanel exportScreenPanel = new ExportScreenPanel();

            // Add panels to the main panel with their respective names
            mainPanel.add(mainMenuPanel, "mainMenu");
            mainPanel.add(settingsPanel, "settings");
            mainPanel.add(trainMenuPanel, "trainMenu");
            mainPanel.add(importPanel, "import");
            mainPanel.add(loadPanel, "load");
            mainPanel.add(trainingModePanel, "trainingMode");
            mainPanel.add(karteienPanel, "karteien");
            mainPanel.add(exportScreenPanel, "exportScreen");

            // Create a map from card names to Updatable panels
            Map<String, Updatable> updatableCards = new HashMap<>();
            updatableCards.put("mainMenu", mainMenuPanel);
            updatableCards.put("settings", settingsPanel);
            updatableCards.put("trainMenu", trainMenuPanel);
            updatableCards.put("import", importPanel);
            updatableCards.put("load", loadPanel);
            updatableCards.put("trainingMode", trainingModePanel);
            updatableCards.put("karteien", karteienPanel);

            // Initialize NavigationController with the map
            NavigationController.initialize(cardLayout, mainPanel, "mainMenu", updatableCards);
            cardLayout.show(mainPanel, "mainMenu");

            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }
}
