package com.nextsap.sounder.setup;

import com.nextsap.sounder.graphics.FrameManager;
import com.nextsap.sounder.utils.Settings;
import com.nextsap.sounder.utils.SystemTrayUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
/*
    The initializer class
 */
public class Initializer {

    /**
     * {@link Initializer} constructor
     *
     * @param frame the default frame
     * @return {@link Boolean} initialized or not
     */
    public boolean initialize(FrameManager frame) {
        try {
            createFolder();
            createFile();
            dlIcon();
            dlTrayIcon();
            SystemTrayUtils.createTrayIcon(frame);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Create data files of the program
     */
    private void createFile() {
        try {
            String name = System.getProperty("user.name");
            File config = new File("C:\\Users\\" + name + "\\AppData\\Roaming\\AzSounder\\Config.txt");
            File currentLogs = new File("C:\\Users\\" + name + "\\AppData\\Roaming\\AzSounder\\CurrentLogs.txt");

            if (!config.exists()) {
                config.createNewFile();
                System.out.println("Le fichier 'Config.txt' a bien été créé.");
            } else
                System.out.println("Le fichier 'Config.txt' existe déjà.");

            if (!currentLogs.exists()) {
                currentLogs.createNewFile();
                System.out.println("Le fichier 'CurrentLogs.txt' a bien été créé.");
            } else
                System.out.println("Le fichier 'CurrentLogs.txt' existe déjà.");

        } catch (Exception e) {
            System.out.println("Une erreur est survenue : ");
            e.printStackTrace();
        }
    }

    /**
     * Create the folder of the program
     */
    private void createFolder() {
        String name = System.getProperty("user.name");
        File file = new File("C:\\Users\\" + name + "\\AppData\\Roaming\\AzSounder\\");

        if (!file.exists()) {
            file.mkdir();
            System.out.println("Le répertoire a bien été créé.");
            return;
        }

        System.out.println("Le répertoire existe déjà.");
    }

    /**
     * Download the icon from the web
     */
    private void dlIcon() {
        File file = new File(Settings.getIconPath());
        if (!file.exists()) {
            try {
                URL url = new URL("https://mamak.s-ul.eu/bdl76DvI"); // TODO: Create on mine
                URLConnection urlConnection = url.openConnection();
                urlConnection.setRequestProperty("User-Agent", "NING/1.0");
                InputStream bufferedReader = new BufferedInputStream(urlConnection.getInputStream());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int n;
                while (-1 != (n = bufferedReader.read(buf))) {
                    out.write(buf, 0, n);
                }
                out.close();
                bufferedReader.close();
                byte[] response = out.toByteArray();
                FileOutputStream fos = new FileOutputStream(Settings.getIconPath());
                fos.write(response);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Download the tray icon from the web
     */
    private void dlTrayIcon() {
        File file = new File(Settings.getTrayIconPath());
        if (!file.exists()) {
            try {
                URL url = new URL("https://nextsap.s-ul.eu/MqHwhpt7");
                URLConnection urlConnection = url.openConnection();
                urlConnection.setRequestProperty("User-Agent", "NING/1.0");
                InputStream bufferedReader = new BufferedInputStream(urlConnection.getInputStream());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int n;
                while (-1 != (n = bufferedReader.read(buf))) {
                    out.write(buf, 0, n);
                }
                out.close();
                bufferedReader.close();
                byte[] response = out.toByteArray();
                FileOutputStream fos = new FileOutputStream(Settings.getTrayIconPath());
                fos.write(response);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
