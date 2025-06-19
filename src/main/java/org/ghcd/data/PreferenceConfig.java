package org.ghcd.data;

import java.util.prefs.Preferences;

public class PreferenceConfig {
    String userIp = "";

    public void savePreferences() {
        String newIp = "";
        Preferences connnPreferences = Preferences.userRoot().node(newIp);
        connnPreferences.put("ip", newIp);
    }

    public void readPreferences() {
        Preferences connPreferences = Preferences.userRoot().node("/home/vanhoemhein/Documents/PrefTest");
        String url = connPreferences.get("ip", userIp);
    }
}
