package com.duckelekuuk.mcsnake.schedulers;

import com.duckelekuuk.mcsnake.models.Console;
import com.duckelekuuk.mcsnake.models.GameOverScreen;
import com.duckelekuuk.mcsnake.utils.InventoryUtils;
import com.duckelekuuk.mcsnake.utils.Properties;

import java.util.List;
import java.util.stream.IntStream;

public class DisplayGameOver implements Runnable {

    private Console console;
    private int step = -1;

    public DisplayGameOver(Console console) {
        this.console = console;
    }

    @Override
    public void run() {
        step++;

        if (step == 0) {
            display();
            return;
        }

        if (step > GameOverScreen.SCROLLLENGTH + 5) {
            console.close();
            return;
        }


        if ((step > 0 && step <= 5) || (step > GameOverScreen.SCROLLLENGTH && step <= GameOverScreen.SCROLLLENGTH + 5)) {
            return;
        }

        display();
    }

    private void display() {
        List<String> nextFrame = GameOverScreen.getSubLayout(step);

        IntStream.range(0, nextFrame.size()).forEach(y -> {
            String row = nextFrame.get(y);
            String[] segments = row.split("");

            IntStream.range(0, segments.length).forEach(x -> {
                changeItem(x, y, segments[x].equalsIgnoreCase("#"));
            });
        });
    }

    private void changeItem(int x, int y, boolean red) {
        int position = InventoryUtils.getLocation(x, y);

        if (console.getScreen().getItem(position) == null) {
            console.getScreen().setItem(position, red ? Properties.GAMEOVER_TEXT : Properties.SCREEN_BACKGROUND);
            return;
        }

        if (console.getScreen().getItem(position).isSimilar(Properties.GAMEOVER_TEXT) && red) {
            return;
        }

        if (!console.getScreen().getItem(position).isSimilar(Properties.GAMEOVER_TEXT) && !red) {
            return;
        }

        console.getScreen().setItem(position, red ? Properties.GAMEOVER_TEXT : Properties.SCREEN_BACKGROUND);
    }
}
