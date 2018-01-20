package com.duckelekuuk.mcsnake.models;

import com.duckelekuuk.mcsnake.utils.InventoryUtils;
import com.duckelekuuk.mcsnake.utils.Properties;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.math.IntRange;
import org.bukkit.Bukkit;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class Snake {

    @Setter
    private Direction direction;
    private Console console;
    private List<Integer> parts = new ArrayList<>();
    private int size = 1;

    public Snake(Console console) {
        this.console = console;
        parts.add(Properties.START_POSITION);
    }

    public void eatFood() {
        // Increase size
        size++;
    }

    public void spawnFood() {
        // Place new food block
        List<Integer> spots = IntStream.range(0, (Properties.WIDTH * Properties.HEIGHT) - 1).boxed().collect(Collectors.toList());
        // Remove snake parts
        spots.removeAll(parts);

        // Shuffle options
        Collections.shuffle(spots);

        // Pick random spot
        console.getScreen().setItem(spots.get(0), Properties.FOOD);
    }

    public boolean canGo(Direction direction) {
        if (direction.equals(Direction.UP)) return direction != Direction.DOWN;
        if (direction.equals(Direction.DOWN)) return direction != Direction.UP;
        if (direction.equals(Direction.LEFT)) return direction != Direction.RIGHT;
        if (direction.equals(Direction.RIGHT)) return direction != Direction.LEFT;

        return true;
    }

    public boolean update() {
        Bukkit.getServer().broadcastMessage(parts.get(0) + "");
        int head = parts.get(parts.size() - 1);
        int nextX = InventoryUtils.getCoordinates(head)[0] + direction.getOffsetX();
        int nextY = InventoryUtils.getCoordinates(head)[1] + direction.getOffsetY();

        // Checking for collision with wall
        if (nextX < 0 || nextX > (Properties.WIDTH - 1)) return false;
        if (nextY < 0 || nextY > (Properties.HEIGHT - 1)) return false;

        // Checking for collision with itself
        if (parts.contains(InventoryUtils.getLocation(nextX, nextY))) return false;

        // Check if next block is food
        if (console.getScreen().getItem(InventoryUtils.getLocation(nextX, nextY)).isSimilar(Properties.FOOD)) eatFood();

        // Move snake in direction
        console.getScreen().setItem(InventoryUtils.getLocation(nextX, nextY), Properties.SNAKE);
        parts.add(InventoryUtils.getLocation(nextX, nextY));

        // Let the last item stay to increase size
        if (parts.size() <= size) {
            return true;
        }

        // Remove last part
        int tail = parts.remove(0);
        console.getScreen().setItem(tail, Properties.SCREEN_BACKGROUND);
        return true;
    }
}
