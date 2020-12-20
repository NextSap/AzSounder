package com.nextsap.sounder.graphics.listeners;

import com.nextsap.sounder.config.ConfigManager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
    A {@link DocumentListener} implemented class to make the design better
 */
public class DocListener implements DocumentListener {

    // Define attributes
    private final ConfigManager configManager;
    private final JTextField stringTextField;
    private final JTextField pathTextField;
    private final JCheckBox activeBox;

    /**
     * {@link DocListener} constructor
     *
     * @param configManager the {@link ConfigManager}
     * @param stringTextField the {@link JTextField} string
     * @param pathTextField the {@link JTextField} path
     * @param activeBox the {@link JCheckBox}
     */
    public DocListener(ConfigManager configManager, JTextField stringTextField, JTextField pathTextField, JCheckBox activeBox) {
        this.configManager = configManager;
        this.stringTextField = stringTextField;
        this.pathTextField = pathTextField;
        this.activeBox = activeBox;
    }


    @Override
    public void insertUpdate(DocumentEvent e) {
        update();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        update();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        update();
    }

    private void update() {
        String content = this.stringTextField.getText();
        if (this.configManager.getConfig().getCustomFilters().containsKey(content)) {
            if (this.pathTextField.getText().equals(""))
                this.pathTextField.setText(this.configManager.getConfig().getCustomFilters().get(content).getSound_path());
            this.activeBox.setSelected(this.configManager.getConfig().getCustomFilters().get(content).isActive());
            return;
        }
        this.pathTextField.setText("");
        this.activeBox.setSelected(false);
    }
}
