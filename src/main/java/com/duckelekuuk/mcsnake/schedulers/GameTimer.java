package com.duckelekuuk.mcsnake.schedulers;

import com.duckelekuuk.mcsnake.models.Console;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GameTimer implements Runnable {

    private Console console;

    @Override
    public void run() {
        if (!console.getSnake().update()) {
            console.endGame();
        }
    }
}
