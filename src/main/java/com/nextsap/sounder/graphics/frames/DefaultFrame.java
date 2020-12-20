package com.nextsap.sounder.graphics.frames;

import com.nextsap.sounder.graphics.FrameManager;
import com.nextsap.sounder.load.Loader;
import com.nextsap.sounder.utils.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * An {@link FrameManager} extended class default frame
 */
public class DefaultFrame extends FrameManager {

    // Define attributes
    private final JButton onButton = new JButton("On");
    private final JButton offButton = new JButton("Off");

    private Loader loader;

    /**
     * {@link DefaultFrame} constructor
     */
    public DefaultFrame() {
        this.setTitle("AzSounder");
        this.setWidth(300);
        this.setHeight(225);
        this.initialize();
        this.setMain(true);

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
        spacerPanel.setPreferredSize(new Dimension(2, 40));

        JLabel spacerLabel = new JLabel(" ");
        spacerPanel.add(spacerLabel);

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.getPanel().add(spacerPanel, constraints);

        // Button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(250, 80));
        buttonPanel.setBackground(new Color(123, 123, 123));

        this.onButton.setBackground(new Color(108, 240, 152));
        this.onButton.setPreferredSize(new Dimension(80, 30));
        this.onButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.onButton.setBorderPainted(false);
        this.onButton.addActionListener(this::onClickEvent);
        buttonPanel.add(this.onButton);

        JLabel spacerL = new JLabel("");
        spacerL.setPreferredSize(new Dimension(30, 0));
        buttonPanel.add(spacerL);

        this.offButton.setBackground(new Color(239, 76, 76));
        this.offButton.setPreferredSize(new Dimension(80, 30));
        this.offButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.offButton.setBorderPainted(false);
        this.offButton.setEnabled(false);
        this.offButton.addActionListener(this::offClickEvent);
        buttonPanel.add(this.offButton);

        JButton optionButton = new JButton("Options");
        optionButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        optionButton.setBackground(Color.ORANGE);
        optionButton.setBorderPainted(false);
        optionButton.addActionListener(this::optionClickEvent);
        buttonPanel.add(optionButton);

        constraints.gridx = 0;
        constraints.gridy = 2;
        this.getPanel().add(buttonPanel, constraints);
    }

    /**
     * 'On' click event
     *
     * @param event {@link ActionEvent}
     */
    private void onClickEvent(ActionEvent event) {
        this.onButton.setEnabled(false);
        this.offButton.setEnabled(true);
        this.loader = new Loader();
    }

    /**
     * 'Off' click event
     *
     * @param event {@link ActionEvent}
     */
    private void offClickEvent(ActionEvent event) {
        this.onButton.setEnabled(true);
        this.offButton.setEnabled(false);
        this.loader.getTimer().cancel();
    }

    /**
     * 'Option' click event
     *
     * @param event {@link ActionEvent}
     */
    private void optionClickEvent(ActionEvent event) {
        new OptionFrame().show();
        try {
            if (this.loader.isRunning()) {
                this.onButton.setEnabled(true);
                this.offButton.setEnabled(false);
                this.loader.getTimer().cancel();
            }
        } catch (Exception ignored) {
        }
    }
}
