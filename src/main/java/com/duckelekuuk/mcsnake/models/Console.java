package com.duckelekuuk.mcsnake.models;

import com.duckelekuuk.mcsnake.MCSnake;
import com.duckelekuuk.mcsnake.models.buttons.IButton;
import com.duckelekuuk.mcsnake.schedulers.DisplayGameOver;
import com.duckelekuuk.mcsnake.schedulers.GameTimer;
import com.duckelekuuk.mcsnake.utils.InventoryUtils;
import com.duckelekuuk.mcsnake.utils.Properties;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
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
    private @Setter Button pressedButton;

    private Snake snake;
    private List<Integer> food = new ArrayList<>();

    private @Setter boolean playing = false;
    private @Setter boolean gameOver = false;

    public Console(Player player) {
        this.player = player;
        this.inventory = Bukkit.getServer().createInventory(null, Properties.WIDTH * Properties.HEIGHT,  "Snake - " + player.getName());
        this.snake = new Snake(this);

        Bukkit.getServer().getScheduler().runTaskLater(MCSnake.getPlugin(), this::setupConsole, 1);

    }

    public void start() {
        playing = true;

        timer = Bukkit.getServer().getScheduler().runTaskTimer(MCSnake.getPlugin(), new GameTimer(this), 1, Properties.GAME_SPEED);
    }

    public void endGame(boolean displayCredits) {
        setPlaying(false);
        setGameOver(true);
        if (getTimer() != null) getTimer().cancel();
        getScreen().clear();

        if (displayCredits) gameOverTimer = Bukkit.getServer().getScheduler().runTaskTimer(MCSnake.getPlugin(), new DisplayGameOver(this), 1, Properties.GAMEOVER_SPEED);
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
        if (button == getPressedButton()) return;
        if (!button.getInfo().canBePressed(this)) return;

        if (getPressedButton() != null) getPressedButton().getInfo().unPress(this);

        button.getInfo().press(this);
        pressedButton = button;

        attemptStarting();
    }

    public void setItemInController(int x, int y, ItemStack itemStack) {
        Bukkit.getServer().broadcastMessage(itemStack.getItemMeta().getDisplayName() + ": " + x + " - " + y);
        getController().setItem(InventoryUtils.getLocation(x, y), itemStack);
    }

    public void attemptStarting() {
        if (!isPlaying() && !isGameOver()) start();
    }

    public void open() {
        player.openInventory(inventory);
    }

    public void close() {
        player.closeInventory();
    }

    private void setupConsole() {
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
        Arrays.asList(Button.values()).forEach(button -> setItemInController(button.getInfo().getX(), button.getInfo().getY(), button.getInfo().getItem(false)));
        InventoryUtils.fillEmpty(getController(), Properties.CONTROLLER_BACKGROUND);
    }
}
