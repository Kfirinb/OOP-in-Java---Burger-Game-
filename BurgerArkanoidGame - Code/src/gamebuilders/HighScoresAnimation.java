package gamebuilders;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

public class HighScoresAnimation implements Animation {
    private List<ScoreInfo> highScoresTable;
    private KeyboardSensor keyboard;
    private boolean stop;
    public HighScoresAnimation(HighScoresTable highScoresTable, KeyboardSensor keyboard) {
        this.highScoresTable = highScoresTable.getHighScores();
        this.keyboard = keyboard;
        this.stop = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.red);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        int i = 0;
        for (ScoreInfo s : this.highScoresTable) {
            d.drawText(200, 200 + i * 50, s.getName(), 50);
            d.drawText(400, 200 + i * 50, " " + s.getScore(), 50);
            i++;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}