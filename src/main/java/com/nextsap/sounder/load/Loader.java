package com.nextsap.sounder.load;

import com.nextsap.sounder.AzSounder;
import com.nextsap.sounder.config.CustomConfig;
import com.nextsap.sounder.utils.Settings;
import com.nextsap.sounder.utils.SoundUtils;
import com.nextsap.sounder.utils.LogsSplitUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
/*
    A loader class
 */
public class Loader {

    // Define attributes
    private Timer timer;
    private boolean running = false;

    /**
     * {@link Loader} constructor
     */
    public Loader() {
        try {
            Files.copy(Paths.get(Settings.getLogPath()), new FileOutputStream(Settings.getCurrentLogPath()));
            load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Compare the log file with the current log file
     *
     * @return the difference between them
     */
    private List<String> compare() {
        try {
            List<String> content1 = Files.readAllLines(Paths.get(Settings.getLogPath()));
            List<String> content2 = Files.readAllLines(Paths.get(Settings.getCurrentLogPath()));
            List<String> chat = new ArrayList<>();

            if (content1.equals(content2)) return chat;

            for (String line : content1) {
                if (content2.contains(line) || !line.contains("[Client Thread/INFO]: [CHAT] ")) continue;
                chat.add(line.split(" \\[Client Thread/INFO]: \\[CHAT] ")[1]); //AIOOBE
            }
            Files.copy(Paths.get(Settings.getLogPath()), new FileOutputStream(Settings.getCurrentLogPath()));
            return chat;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * The magic method *_*
     */
    public void load() {
        CustomConfig customConfig = AzSounder.getConfigManager().getConfig();

        this.timer = new Timer();
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                running = true;
                List<String> logs = compare();
                if (logs != null) {
                    for (String log : logs) {
                        customConfig.getCustomFilters().forEach((name, filter) -> {
                            if (!filter.isActive()) return;
                            switch (filter.getOptions()) {
                                case GLOBAL:
                                    if (LogsSplitUtils.parseGlobal(log).contains(name.toLowerCase())) {
                                        SoundUtils.play(filter.getSound_path());
                                        System.out.println("[DEBUG] (" + name + ") >>>" + log);
                                    }
                                    break;
                                case CHAT:
                                    if (!LogsSplitUtils.isChat(log) && !LogsSplitUtils.isFreecube(log) && !LogsSplitUtils.isGameChat(log))
                                        return;
                                    if (LogsSplitUtils.parseChat(log).contains(name.toLowerCase())) {
                                        SoundUtils.play(filter.getSound_path());
                                        System.out.println("[DEBUG] (" + name + ") >>>" + log);
                                    }
                                    break;
                                case PRIVATE_MESSAGE:
                                    if (!LogsSplitUtils.isPrivateMessage(log)) return;
                                    if (LogsSplitUtils.parsePrivateMessage(log).contains(name.toLowerCase())) {
                                        SoundUtils.play(filter.getSound_path());
                                        System.out.println("[DEBUG] (" + name + ") >>>" + log);
                                    }
                                    break;
                                case PARTY:
                                    if (!LogsSplitUtils.isParty(log)) return;
                                    if (LogsSplitUtils.parseParty(log).contains(name.toLowerCase())) {
                                        SoundUtils.play(filter.getSound_path());
                                        System.out.println("[DEBUG] (" + name + ") >>>" + log);
                                    }
                                    break;
                                case STAFF_CHAT:
                                    if (!LogsSplitUtils.isStaffChat(log)) return;
                                    if (LogsSplitUtils.parseStaff(log).contains(name.toLowerCase())) {
                                        SoundUtils.play(filter.getSound_path());
                                        System.out.println("[DEBUG] (" + name + ") >>>" + log);
                                    }
                                    break;
                            }
                        });
                    }
                }
            }
        }, 0, 500);

    }

    public Timer getTimer() {
        return timer;
    }

    public boolean isRunning() {
        return running;
    }
}
