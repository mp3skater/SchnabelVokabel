package net.mp3skater.schnabelvokabel.view.panels;

import net.mp3skater.schnabelvokabel.view.elements.BaseButton;

import javax.swing.*;
import java.awt.*;

public class NewDictionaryPanel extends BasePanel {
    public NewDictionaryPanel() {
        setLayout(new BorderLayout());
        add(createHeader(), BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(5, 2));
        form.add(new JLabel("Name:"));
        form.add(new JTextField());
        form.add(new JLabel("Language 1:"));
        form.add(new JTextField());
        form.add(new JLabel("Language 2:"));
        form.add(new JTextField());
        form.add(new JLabel("Choose File:"));
        BaseButton chooseFileBtn = new BaseButton("Browse");
        chooseFileBtn.addActionListener(e -> {}); // Empty listener
        form.add(chooseFileBtn);

        JPanel buttons = new JPanel();
        BaseButton confirm = new BaseButton("CONFIRM");
        confirm.addActionListener(e -> {}); // Empty listener
        BaseButton decline = new BaseButton("DECLINE");
        decline.addActionListener(e -> NavigationController.getInstance().goBack());

        buttons.add(confirm);
        buttons.add(decline);

        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }
}