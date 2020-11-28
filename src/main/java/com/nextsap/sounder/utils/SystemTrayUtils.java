package com.nextsap.sounder.utils;

import com.nextsap.sounder.graphics.FrameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SystemTrayUtils {

    public static final SystemTray tray = SystemTray.getSystemTray();
    public static TrayIcon trayIcon;

    /**
     * Display the tray icon
     *
     * @param frame is the default {@link FrameManager}
     */
    public static void createTrayIcon(FrameManager frame) {
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        try {
            final PopupMenu popup = new PopupMenu();
            trayIcon = new TrayIcon(new ImageIcon(Settings.getTrayIconPath()).getImage(), "AzSounder", popup);

            MenuItem exitItem = new MenuItem("Quitter");
            exitItem.addActionListener(action -> {
                tray.remove(trayIcon);
                System.exit(0);
            });

            popup.add(exitItem);

            trayIcon.setPopupMenu(popup);

            trayIcon.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent event) {
                    if (event.getClickCount() == 1 && SwingUtilities.isLeftMouseButton(event))
                        frame.show();
                }
            });

            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
    }
}
