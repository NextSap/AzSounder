package com.nextsap.sounder.setup;

import com.nextsap.sounder.utils.Settings;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Initializer {

    public boolean initialize() {
        try {
            createFolder();
            createFile();
            dlIcon();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void createFile() {
        try {
            String name = System.getProperty("user.name");
            File config = new File("C:\\Users\\" + name + "\\AppData\\Roaming\\AzSounder\\Config.txt");

            if (!config.exists()) {
                config.createNewFile();
                System.out.println("Le fichier 'config.txt' a bien été créé.");
            } else
                System.out.println("Le fichier 'config.txt' existe déjà.");

        } catch (Exception e) {
            System.out.println("Une erreur est survenue : ");
            e.printStackTrace();
        }
    }

    /**
     * Create program's folder
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
     * Download an icon from the web
     */
    private void dlIcon() {
        File file = new File(Settings.getIconPath());
        if (!file.exists()) {
            try {
                URL url = new URL("https://mamak.s-ul.eu/bdl76DvI");
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
}
