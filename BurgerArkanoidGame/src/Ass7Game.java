import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gamebuilders.*;
import gamebuilders.gamelevels.*;
import gamebuilders.screens.Menu;
import gamebuilders.screens.ShowHiScoresTask;
import gamebuilders.screens.Task;
import gamebuilders.screens.MenuAnimation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kfir Inbal kfir.inbal@live.biu.ac.il
 * This class calls methods of game class to create and run the game.
 */
public class Ass7Game {
    /**
     * This is main method which calls the methods of game class.
     * @param args no use.
     */
    public static void main(String[] args) throws FileNotFoundException, IOException{
        GUI gameGui = new GUI("Arkanoid", 800, 600);
        biuoop.KeyboardSensor keyboard = gameGui.getKeyboardSensor();
        AnimationRunner animationRunner = new AnimationRunner(gameGui, 60);
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
       // File lvlDefinition = new File("D://OOP_Projects_IntelliJ//Assignment7//src//level_definition.txt");
        //InputStreamReader reader = new InputStreamReader(new FileInputStream(lvlDefinition));
        //LevelSpecificationReader read = new LevelSpecificationReader();
       // read.fromReader(reader);
        File file = new File("highscores");
        DialogManager dialog = gameGui.getDialogManager();

        HighScoresTable highScoresTable = null;

        if (file.exists()) {
            highScoresTable = HighScoresTable.loadFromFile(file);
        } else {
            try {
                highScoresTable = new HighScoresTable(5);
                highScoresTable.save(file);
            } catch (IOException e) {
                System.out.println("failed saving the new table");
            }
        }

        for (int i = 0; i < args.length; i++) {
            try {
                switch (Integer.parseInt(args[i])) {
                    case 1:
                        levels.add(new Level1());
                        break;
                    case 2:
                        levels.add(new Level2());
                        break;
                    case 3:
                        levels.add(new Level3());
                        break;
                    case 4:
                        levels.add(new Level4());
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }
        if (levels.isEmpty()) {
            LevelInformation level1 = new Level1();
            levels.add(level1);
            LevelInformation level2 = new Level2();
            levels.add(level2);
            LevelInformation level3 = new Level3();
            levels.add(level3);
            LevelInformation level4 = new Level4();
            levels.add(level4);
        }

        GameFlow gameFlow = new GameFlow(animationRunner, keyboard, 7, highScoresTable, dialog);

       // Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(keyboard, animationRunner);
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(keyboard);
        Task<Void> gameTask = new Task<Void>() {

            @Override
            public Void run() {
                gameFlow.runLevels(levels);
                return null;
            }
        };

        menu.addSelection("s", "startGame", gameTask);


        HighScoresAnimation scoresScreen = new HighScoresAnimation(highScoresTable, keyboard);
        Animation highScoreAnimation =
                new KeyPressStoppableAnimation(keyboard, keyboard.SPACE_KEY, scoresScreen);

        Task<Void> hiTask = new Task<Void>() {
            @Override
            public Void run() {
                ((KeyPressStoppableAnimation) highScoreAnimation).setStop(false);
                animationRunner.run(highScoreAnimation);
                return null;
            }
        };

        menu.addSelection("h", "hiscore", hiTask);


        Task<Void> quitTask = new Task<Void>() {

            @Override
            public Void run() {
                System.exit(0);
                return null;
            }
        };

        menu.addSelection("q", "Quit", quitTask);

        while (true) {
            animationRunner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
        }


        //gameFlow.runLevels(levels);
        //gameGui.close();
    }
}
