package com.nextsap.sounder.load;

import com.nextsap.sounder.AzSounder;
import com.nextsap.sounder.config.CustomConfig;
import com.nextsap.sounder.utils.DateUtils;
import com.nextsap.sounder.utils.Settings;
import com.nextsap.sounder.utils.SoundUtils;
import com.nextsap.sounder.utils.SplitUtils;

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
        load();
    }

    private List<String> getLogs(long start) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(Settings.getLogPath()), StandardCharsets.UTF_8);
            List<String> chat = new ArrayList<>();

            lines.parallelStream().forEach(line -> {
                if (!line.contains(" [Client Thread/INFO]: [CHAT] ")) return;

                long date = DateUtils.getTime(line.split("\\[")[1].split("]")[0]);
                if (DateUtils.startAt(date, start)) {
                    chat.add(line.split(" \\[Client Thread/INFO]: \\[CHAT] ")[1]);
                }
            });
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
                List<String> logs = getLogs(System.currentTimeMillis() - 2000);
                logs.parallelStream().forEach(log -> {
                    customConfig.getCustomDetections().forEach((name, detection) -> {
                        switch (detection.getOption()) {
                            case GLOBAL:
                                if (SplitUtils.parseGlobal(log).contains(name.toLowerCase()))
                                    SoundUtils.play(detection.getSound_path());
                                break;
                            case CHAT:
                                if (!SplitUtils.isChat(log) && !SplitUtils.isFreecube(log)) return;
                                if (SplitUtils.parseChat(log).contains(name.toLowerCase()))
                                    SoundUtils.play(detection.getSound_path());
                                break;
                            case PRIVATE_MESSAGE:
                                if (!SplitUtils.isPrivateMessage(log)) return;
                                if (SplitUtils.parsePrivateMessage(log).contains(name.toLowerCase()))
                                    SoundUtils.play(detection.getSound_path());
                                break;
                            case PARTY:
                                if (!SplitUtils.isParty(log)) return;
                                if (SplitUtils.parseParty(log).contains(name.toLowerCase()))
                                    SoundUtils.play(detection.getSound_path());
                                break;
                            case STAFF_CHAT:
                                if (!SplitUtils.isStaffChat(log)) return;
                                if (SplitUtils.parseStaff(log).contains(name.toLowerCase()))
                                    SoundUtils.play(detection.getSound_path());
                                break;
                            default:
                        }
                    });
                });
            }
        }, 0, 1500);
    }

    public Timer getTimer() {
        return timer;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
