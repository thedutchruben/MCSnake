package com.duckelekuuk.mcsnake.models;

import com.duckelekuuk.mcsnake.MCSnake;
import com.duckelekuuk.mcsnake.models.buttons.IButton;
import com.duckelekuuk.mcsnake.schedulers.DisplayGameOver;
import com.duckelekuuk.mcsnake.schedulers.GameTimer;
import com.duckelekuuk.mcsnake.utils.InventoryUtils;
import com.duckelekuuk.mcsnake.utils.Properties;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class Console {

    private Player player;
    private Inventory inventory;
    private BukkitTask timer;
    private BukkitTask gameOverTimer;
    private Button pressedButton;

    private Snake snake;
    private List<Integer> food = new ArrayList<>();

    private boolean playing = false;
    private boolean gameOver = false;

    public Console(Player player) {
        this.player = player;
        this.inventory = Bukkit.getServer().createInventory(null, Properties.WIDTH * Properties.HEIGHT,  "Snake - " + player.getName());
        this.snake = new Snake(this);
        setupConsole();
    }

    public void start() {
        playing = true;

        timer = Bukkit.getServer().getScheduler().runTaskTimer(MCSnake.getPlugin(), new GameTimer(this), 1, Properties.GAME_SPEED);
    }

    public void endGame() {
        playing = false;
        gameOver = true;
        timer.cancel();
        getScreen().clear();

        gameOverTimer = Bukkit.getServer().getScheduler().runTaskTimer(MCSnake.getPlugin(), new DisplayGameOver(this), 1, Properties.GAMEOVER_SPEED);
    }

    /**
     * Get score based of snake size
     */
    public int getScore() {
        return snake.getParts().size() - 1;
    }

    public Inventory getController() {
        return player.getOpenInventory().getBottomInventory();
    }

    public Inventory getScreen() {
        return player.getOpenInventory().getTopInventory();
    }

    public void onClick(Button button) {
        Direction nextDirection = null;

        switch (button) {
            case UP:
                nextDirection = Direction.UP;
                break;
            case DOWN:
                nextDirection = Direction.DOWN;
                break;
            case LEFT:
                nextDirection = Direction.LEFT;
                break;
            case RIGHT:
                nextDirection = Direction.RIGHT;
                break;
            case QUIT:
                close();
                return;
        }

        if (snake.getDirection() != null && !snake.getDirection().canGo(nextDirection)) return;

        if (snake.getDirection() != null) {
            Button oldButton = snake.getDirection().getButton();
            getController().setItem(InventoryUtils.getLocation(oldButton.getX(), oldButton.getY()), oldButton.getItem());
        }

        snake.setDirection(nextDirection);

        Button clickedButton = snake.getDirection().getButton();

        getController().setItem(InventoryUtils.getLocation(clickedButton.getX(), clickedButton.getY()), clickedButton.getItem_active());

        if (!playing) start();
    }

    public void open() {
        player.openInventory(inventory);
    }

    public void close() {
        player.closeInventory();
    }

    private void setupConsole() {
        Bukkit.getServer().getScheduler().runTaskLater(MCSnake.getPlugin(), () -> {

            getScreen().clear();
            getController().clear();

            /* Setup screen */
            List<Integer> spots = IntStream.range(0, (Properties.WIDTH * Properties.HEIGHT) - 1).boxed().collect(Collectors.toList());
            spots.remove(Properties.START_POSITION);
            Collections.shuffle(spots);

            getScreen().setItem(spots.get(0), Properties.FOOD);

            getScreen().setItem(Properties.START_POSITION, Properties.SNAKE);
            InventoryUtils.fillEmpty(getScreen(), Properties.SCREEN_BACKGROUND);

            /* Setup controller */
            Arrays.asList(Button.values()).forEach(button -> getController().setItem(InventoryUtils.getLocation(button.getInfo().getX(), button.getInfo().getY()), button.getInfo().getItem(false)));
            InventoryUtils.fillEmpty(getController(), Properties.CONTROLLER_BACKGROUND);

        }, 1);
    }
}
