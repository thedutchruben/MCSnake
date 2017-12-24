package com.duckelekuuk.mcsnake.models;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameOverScreen {

    public static final String[] LAYOUT = new String[]{
            "**********************************************",
            "**####*####*#***#*####**####*#***#*####*###***",
            "**#****#**#*##*##*#*****#**#*#***#*#****#**#**",
            "**#*##*####*#*#*#*####**#**#*##*##*####*###***",
            "**#**#*#**#*#***#*#*****#**#**#*#**#****#**#**",
            "**####*#**#*#***#*####**####**###**####*#**#**",
    };

    public static final int SCROLLLENGTH = LAYOUT[0].length() - 9;

    public static List<String> getSubLayout(int start) {
        return Arrays.stream(LAYOUT).map(string -> string.substring(start, start+9)).collect(Collectors.toList());
    }
}
