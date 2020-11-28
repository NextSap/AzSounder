package com.nextsap.sounder.load;

import com.nextsap.sounder.AzSounder;
import com.nextsap.sounder.config.CustomConfig;
import com.nextsap.sounder.utils.Settings;
import com.nextsap.sounder.utils.SoundUtils;
import com.nextsap.sounder.utils.SplitUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Loader {

    private Timer timer;
    private boolean running = false;

    public Loader() {
        try {
            Files.copy(Paths.get(Settings.getLogPath()), new FileOutputStream(new File(Settings.getCurrentLogPath())));
            load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> compare() {
        try {
            List<String> content1 = Files.readAllLines(Paths.get(Settings.getLogPath()), StandardCharsets.UTF_8);
            List<String> content2 = Files.readAllLines(Paths.get(Settings.getCurrentLogPath()), StandardCharsets.UTF_8);
            List<String> chat = new ArrayList<>();

            if (content1.equals(content2)) return chat;

            for (String line : content1) {
                if (content2.contains(line) || !line.contains("] [Client Thread/INFO]: [CHAT] ")) continue;
                chat.add(line.split(" \\[Client Thread/INFO]: \\[CHAT] ")[1]);
            }
            Files.copy(Paths.get(Settings.getLogPath()), new FileOutputStream(new File(Settings.getCurrentLogPath())));
            return chat;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void load() {
        CustomConfig customConfig = AzSounder.getConfigManager().getConfig();

        this.timer = new Timer();
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                running = true;
                for (String log : compare()) {
                    customConfig.getCustomFilters().forEach((name, filter) -> {
                        if (!filter.isActive()) return;
                        switch (filter.getOptions()) {
                            case GLOBAL:
                                if (SplitUtils.parseGlobal(log).contains(name.toLowerCase())) {
                                    SoundUtils.play(filter.getSound_path());
                                    System.out.println("[DEBUG] (" + name + ") >>>" + log);
                                }
                                break;
                            case CHAT:
                                if (!SplitUtils.isChat(log) && !SplitUtils.isFreecube(log)) return;
                                if (SplitUtils.parseChat(log).contains(name.toLowerCase())) {
                                    SoundUtils.play(filter.getSound_path());
                                    System.out.println("[DEBUG] (" + name + ") >>>" + log);
                                }
                                break;
                            case PRIVATE_MESSAGE:
                                if (!SplitUtils.isPrivateMessage(log)) return;
                                if (SplitUtils.parsePrivateMessage(log).contains(name.toLowerCase())) {
                                    SoundUtils.play(filter.getSound_path());
                                    System.out.println("[DEBUG] (" + name + ") >>>" + log);
                                }
                                break;
                            case PARTY:
                                if (!SplitUtils.isParty(log)) return;
                                if (SplitUtils.parseParty(log).contains(name.toLowerCase())) {
                                    SoundUtils.play(filter.getSound_path());
                                    System.out.println("[DEBUG] (" + name + ") >>>" + log);
                                }
                                break;
                            case STAFF_CHAT:
                                if (!SplitUtils.isStaffChat(log)) return;
                                if (SplitUtils.parseStaff(log).contains(name.toLowerCase())) {
                                    SoundUtils.play(filter.getSound_path());
                                    System.out.println("[DEBUG] (" + name + ") >>>" + log);
                                }
                                break;
                        }
                    });
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
