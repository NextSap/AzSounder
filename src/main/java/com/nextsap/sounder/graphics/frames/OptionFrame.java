package com.nextsap.sounder.graphics.frames;

import com.nextsap.sounder.AzSounder;
import com.nextsap.sounder.config.ConfigManager;
import com.nextsap.sounder.config.CustomFilter;
import com.nextsap.sounder.graphics.FrameManager;
import com.nextsap.sounder.graphics.listeners.DocListener;
import com.nextsap.sounder.utils.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Objects;

public class OptionFrame extends FrameManager {

    private final ConfigManager configManager = AzSounder.getConfigManager();
    private final JTextField stringTextField = new JTextField();
    private final JTextField soundPathTextField = new JTextField();
    private final JCheckBox activeBox = new JCheckBox("Activé ?");
    private final JComboBox<String> valueBox = new JComboBox<>(new String[]{"Global", "Chat", "Message privé", "Chat de Groupe", "Chat du Staff"});

    public OptionFrame() {
        this.setTitle("AzSounder - Options");
        this.setWidth(560);
        this.setHeight(340);
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

        ImageIcon image = new ImageIcon(Settings.getIconPath());
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

        JLabel spacerLabel1 = new JLabel(" ");
        spacerPanel.add(spacerLabel1);

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.getPanel().add(spacerPanel, constraints);


        // Labels
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanel.setBackground(new Color(123, 123, 123));
        labelPanel.setPreferredSize(new Dimension(510, 80));

        JLabel stringLabel = new JLabel("         Filtre :");
        stringLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        labelPanel.add(stringLabel);

        this.stringTextField.setPreferredSize(new Dimension(380, 30));
        this.stringTextField.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        this.stringTextField.getDocument().addDocumentListener(new DocListener(this.configManager, this.stringTextField, this.soundPathTextField, this.activeBox));
        labelPanel.add(this.stringTextField);

        JLabel pathLabel = new JLabel("Sound Path :");
        pathLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        labelPanel.add(pathLabel);

        this.soundPathTextField.setPreferredSize(new Dimension(343, 30));
        this.soundPathTextField.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        labelPanel.add(this.soundPathTextField);

        JButton searchButton = new JButton("^");
        searchButton.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        searchButton.setPreferredSize(new Dimension(30, 30));
        searchButton.addActionListener(this::searchClickEvent);
        labelPanel.add(searchButton);

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

        JLabel spacerLabel2 = new JLabel("           ");
        optionPanel.add(spacerLabel2);

        this.activeBox.setBackground(new Color(123, 123, 123));
        this.activeBox.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        optionPanel.add(this.activeBox);

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
        addButton.setPreferredSize(new Dimension(140, 35));
        addButton.addActionListener(this::addClickEvent);
        buttonPanel.add(addButton);

        JLabel spacerLabel3 = new JLabel("        ");
        buttonPanel.add(spacerLabel3);

        JButton deleteButton = new JButton("Supprimer");
        deleteButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        deleteButton.setBackground(new Color(239, 76, 76));
        deleteButton.setPreferredSize(new Dimension(140, 35));
        deleteButton.addActionListener(this::deleteClickEvent);
        buttonPanel.add(deleteButton);

        JButton backButton = new JButton("Retour");
        backButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        backButton.setBackground(Color.ORANGE);
        backButton.addActionListener(this::backClickEvent);
        backButton.setPreferredSize(new Dimension(100, 35));
        buttonPanel.add(backButton);

        JButton listButton = new JButton("Liste");
        listButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        listButton.setBackground(new Color(6, 150, 159));
        listButton.addActionListener(this::listClickEvent);
        listButton.setPreferredSize(new Dimension(100, 35));
        buttonPanel.add(listButton);

        constraints.gridx = 0;
        constraints.gridy = 4;
        this.getPanel().add(buttonPanel, constraints);
    }

    private void addClickEvent(ActionEvent event) {
        if (this.stringTextField.getText().equals("")) {
            this.showErrorDialog("Erreur", "Vous devez spécifier un nom pour ajouter un filtre.");
            return;
        }

        if (!fileExists(new File(this.soundPathTextField.getText()))) return;

        CustomFilter customFilter = new CustomFilter();
        customFilter.setSound_path(this.soundPathTextField.getText());
        customFilter.setOptionByName(Objects.requireNonNull(this.valueBox.getSelectedItem()).toString());
        customFilter.setActive(this.activeBox.isSelected());

        if (this.configManager.getConfig().getCustomFilters().containsKey(this.stringTextField.getText())) {
            this.configManager.getConfig().removeFilter(this.stringTextField.getText());
            this.configManager.getConfig().addFilter(this.stringTextField.getText(), customFilter);
            this.configManager.update();

            this.showInformationDialog("Succès !", "Vous avez modifié le filtre '" + this.stringTextField.getText() + "'");
            this.hide();
            return;
        }

        if (this.soundPathTextField.getText().equals("")) {
            this.showErrorDialog("Erreur", "Vous devez spécifier un path pour ajouter un filtre.");
            return;
        }

        this.configManager.getConfig().addFilter(this.stringTextField.getText(), customFilter);
        this.configManager.update();

        this.showInformationDialog("Succès !", "Vous avez ajouté le filtre '" + this.stringTextField.getText() + "' à votre liste.");
        this.hide();
    }

    private void deleteClickEvent(ActionEvent event) {
        if (this.stringTextField.getText().equals("")) {
            this.showErrorDialog("Erreur", "Vous devez spécifier un nom pour supprimer un filtre.");
            return;
        }

        if (!this.configManager.getConfig().getCustomFilters().containsKey(this.stringTextField.getText())) {
            int input = this.showQuestionDialog("Erreur", "Il n'existe pas de filtre nommé '" + this.stringTextField.getText() +
                    "'\nVoulez-vous afficher la liste de vos filtres?");
            if (input == 0)
                getList();

            return;
        }

        this.configManager.getConfig().removeFilter(this.stringTextField.getText());
        this.configManager.update();

        this.showInformationDialog("Succès !", "Vous avez supprimé le filtre '" + this.stringTextField.getText() + "' de votre liste.");
        this.hide();
    }

    private void backClickEvent(ActionEvent event) {
        this.stringTextField.setText("");
        this.soundPathTextField.setText("");
        this.hide();
    }

    private void searchClickEvent(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setPreferredSize(new Dimension(600, 400));
        int result = fileChooser.showOpenDialog(this.getFrame());
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            this.soundPathTextField.setText(selectedFile.getAbsolutePath());
        }
    }

    private void listClickEvent(ActionEvent event) {
        getList();
    }

    private boolean fileExists(File file) {
        if (!file.exists()) {
            this.showErrorDialog("Erreur", "Ce fichier son n'existe pas.");
            return false;
        }
        if (!file.getName().endsWith(".wav")) {
            this.showErrorDialog("Erreur", "Le fichier son doit être une extention waveform (.wav)");
            return false;
        }
        return true;
    }

    private void getList() {
        this.configManager.update();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-----------------------------\n");
        this.configManager.getConfig().getCustomFilters().forEach((name, filter) -> {
            stringBuilder.append("String: '").append(name).append("'").append("      ").append(filter.getActive()).append("\nSound Path: ")
                    .append(filter.getSound_path()).append("\nOption: ").append(filter.getOptions().getName()).append("\n-----------------------------\n");
        });
        this.showInformationDialog("AzSounder - Liste des filtres", stringBuilder.toString());
    }
}
