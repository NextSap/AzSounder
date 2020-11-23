package com.nextsap.sounder.graphics.frames;

import com.nextsap.sounder.AzSounder;
import com.nextsap.sounder.config.ConfigManager;
import com.nextsap.sounder.config.CustomDetection;
import com.nextsap.sounder.graphics.FrameManager;
import com.nextsap.sounder.utils.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Objects;

public class OptionFrame extends FrameManager {

    private final ImageIcon image = new ImageIcon(Settings.getIconPath());
    private final ConfigManager configManager = AzSounder.getConfigManager();
    private final JTextField stringTextField = new JTextField();
    private final JTextField soundPathTextField = new JTextField();
    private final JComboBox valueBox = new JComboBox(new String[]{"Global", "Chat", "Private message", "Party Chat", "Staff Chat"});

    public OptionFrame() {
        this.setTitle("AzSounder - Options");
        this.setWidth(550);
        this.setHeight(330);
        this.initialize();

        this.getPanel().setLayout(new GridBagLayout());
        this.getPanel().setBackground(new Color(123, 123, 123));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridheight = 1;
        constraints.gridwidth = 1;


        // Title
        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(200, 40));
        titlePanel.setBackground(new Color(123, 123, 123));

        JLabel titleLabel = new JLabel(image);
        titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        titleLabel.setForeground(new Color(44, 114, 235));
        titlePanel.add(titleLabel);

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.getPanel().add(titlePanel, constraints);

        // Spacer
        JPanel spacerPanel = new JPanel();
        spacerPanel.setBackground(new Color(123, 123, 123));
        spacerPanel.setPreferredSize(new Dimension(2, 15));

        JLabel spacerLabel = new JLabel(" ");
        spacerPanel.add(spacerLabel);

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.getPanel().add(spacerPanel, constraints);


        // Labels
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanel.setBackground(new Color(123, 123, 123));
        labelPanel.setPreferredSize(new Dimension(510, 80));

        JLabel stringLabel = new JLabel("        String :");
        stringLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        labelPanel.add(stringLabel);

        this.stringTextField.setPreferredSize(new Dimension(380, 30));
        this.stringTextField.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        labelPanel.add(this.stringTextField);

        JLabel pathLabel = new JLabel("Sound Path :");
        pathLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        labelPanel.add(pathLabel);

        this.soundPathTextField.setPreferredSize(new Dimension(380, 30));
        this.soundPathTextField.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        labelPanel.add(this.soundPathTextField);

        constraints.gridx = 0;
        constraints.gridy = 2;
        this.getPanel().add(labelPanel, constraints);

        // Option
        JPanel optionPanel = new JPanel();
        optionPanel.setBackground(new Color(123, 123, 123));
        optionPanel.setPreferredSize(new Dimension(490, 50));

        this.valueBox.setBackground(Color.WHITE);
        this.valueBox.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        optionPanel.add(this.valueBox);

        constraints.gridx = 0;
        constraints.gridy = 3;
        this.getPanel().add(optionPanel, constraints);

        // Button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(123, 123, 123));
        buttonPanel.setPreferredSize(new Dimension(350, 100));

        JButton addButton = new JButton("Ajouter");
        addButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        addButton.setBackground(new Color(108, 240, 152));
        addButton.setPreferredSize(new Dimension(140,35));
        addButton.addActionListener(this::addClickEvent);
        buttonPanel.add(addButton);

        JLabel spacerLab = new JLabel("        ");
        buttonPanel.add(spacerLab);

        JButton deleteButton = new JButton("Supprimer");
        deleteButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        deleteButton.setBackground(new Color(239, 76, 76));
        deleteButton.setPreferredSize(new Dimension(140,35));
        deleteButton.addActionListener(this::deleteClickEvent);
        buttonPanel.add(deleteButton);

        JButton backButton = new JButton("Retour");
        backButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        backButton.setBackground(Color.ORANGE);
        backButton.addActionListener(this::backClickEvent);
        buttonPanel.add(backButton);

        constraints.gridx = 0;
        constraints.gridy = 4;
        this.getPanel().add(buttonPanel, constraints);
    }

    private void addClickEvent(ActionEvent event) {
        if (this.stringTextField.getText().equals("") || this.soundPathTextField.getText().equals("")) {
            this.showErrorDialog("Erreur", "Vous devez spécifier un nom et un path pour ajouter un élément.");
            return;
        }
        File filetest = new File(this.soundPathTextField.getText());
        if (!filetest.exists()) {
            this.showErrorDialog("Erreur", "Ce fichier son n'existe pas.");
            return;
        }
        if (!filetest.getName().endsWith(".wav")) {
            this.showErrorDialog("Erreur", "Le fichier son doit être une extention waveform (.wav)");
            return;
        }

        CustomDetection customDetection = new CustomDetection();
        customDetection.setSound_path(this.soundPathTextField.getText());
        customDetection.setOptionByName(Objects.requireNonNull(this.valueBox.getSelectedItem()).toString());

        if (this.configManager.getConfig().getCustomDetections().containsKey(this.stringTextField.getText())) {
            this.configManager.getConfig().removeCustomDetection(this.stringTextField.getText());
            this.configManager.getConfig().addCustomDetection(this.stringTextField.getText(), customDetection);
            this.showInformationDialog("Succès !", "Vous avez modifié l'élément '" + this.stringTextField.getText() + "'");
            this.hide();
            return;
        }

        this.configManager.getConfig().addCustomDetection(this.stringTextField.getText(), customDetection);
        this.configManager.update();

        this.showInformationDialog("Succès !", "Vous avez ajouté l'élément '" + this.stringTextField.getText() + "' à votre list.");
        this.hide();
    }

    private void deleteClickEvent(ActionEvent event) {
        if (this.stringTextField.getText() == null) {
            this.showErrorDialog("Erreur", "Vous devez spécifier un nom pour supprimer un élément.");
            return;
        }

        this.configManager.getConfig().removeCustomDetection(this.stringTextField.getText());
        this.configManager.update();

        this.showInformationDialog("Succès !", "Vous avez supprimé l'élément '" + this.stringTextField.getText() + "' de votre list.");
        this.hide();
    }

    private void backClickEvent(ActionEvent event) {
        this.stringTextField.setText("");
        this.soundPathTextField.setText("");
        this.hide();
    }
}
