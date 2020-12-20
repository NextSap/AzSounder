package com.nextsap.sounder.config;

import com.nextsap.sounder.utils.Serializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * A class to manage config
 */
public class ConfigManager {

    // Define attribute
    private CustomConfig config;

    /**
     * {@link ConfigManager} constructor
     */
    public ConfigManager() {
        getConfigFromFile();
    }

    /**
     * Update the config
     */
    public void update() {
        try {
            String name = System.getProperty("user.name");

            FileWriter myWriter = new FileWriter("C:\\Users\\" + name + "\\AppData\\Roaming\\AzSounder\\Config.txt");
            myWriter.write(Serializer.serialize(this.config));
            myWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the config from the file
     */
    public void getConfigFromFile() {
        try {
            String name = System.getProperty("user.name");
            File file = new File("C:\\Users\\" + name + "\\AppData\\Roaming\\AzSounder\\Config.txt");
            Scanner scanner = new Scanner(file);
            String content = null;
            while (scanner.hasNextLine())
                content = scanner.nextLine();

            if (content == null) {
                this.config = new CustomConfig();
                return;
            }

            this.config = (CustomConfig) Serializer.deserialize(content, CustomConfig.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            this.config = new CustomConfig();
        }
    }

    /**
     * @return the {@link CustomConfig}
     */
    public CustomConfig getConfig() {
        return this.config;
    }
}
