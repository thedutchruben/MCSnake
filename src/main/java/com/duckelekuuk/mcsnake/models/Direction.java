package com.duckelekuuk.mcsnake.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Direction {

    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private int offsetX;
    private int offsetY;

    public boolean canGo(Direction direction) {
        if (this.equals(UP)) return direction != DOWN;
        if (this.equals(DOWN)) return direction != UP;
        if (this.equals(LEFT)) return direction != RIGHT;
        if (this.equals(RIGHT)) return direction != LEFT;

        return true;
    }

    public Button getButton() {
        switch (this) {
            case UP:
                return Button.UP;
            case DOWN:
                return Button.DOWN;
            case LEFT:
                return Button.LEFT;
            case RIGHT:
                return Button.RIGHT;
        }
        return null;
    }
}
