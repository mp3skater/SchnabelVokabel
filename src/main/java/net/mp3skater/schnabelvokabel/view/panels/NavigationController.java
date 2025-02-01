package net.mp3skater.schnabelvokabel.view.panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class NavigationController {
    private static NavigationController instance;
    private CardLayout layout;
    private JPanel parent;
    private final Deque<String> history = new ArrayDeque<>();
    private String currentCard;

    private NavigationController() {}

    public static void initialize(CardLayout layout, JPanel parent, String initialCard) {
        instance = new NavigationController();
        instance.layout = layout;
        instance.parent = parent;
        instance.currentCard = initialCard;
    }

    public static NavigationController getInstance() {
        return instance;
    }

    public void navigateTo(String newCard) {
        history.push(currentCard);
        currentCard = newCard;
        layout.show(parent, newCard);
    }

    public void goBack() {
        if (!history.isEmpty()) {
            String previous = history.pop();
            currentCard = previous;
            layout.show(parent, previous);
        }
    }
}