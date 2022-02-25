package gamebuilders;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import collisiondetection.SpriteCollection;

import java.awt.Color;
/**
 * @author Kfir Inbal kfir.inbal@live.biu.ac.il
 * This class runs the countdown animation (3,2,1,GO!).
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private Sleeper sleeper;

    /**
     * This is a constructor which builds the countdownanimation by numOfSeconds of the animation,
     * from what number to count from(countFrom), and the sprite collection.
     * @param numOfSeconds the num of seconds the animation runs by.
     * @param countFrom the number we count from in the animation itself.
     * @param gameScreen the sprites state at the moment.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds / countFrom;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.sleeper = new Sleeper();
    }

    /**
     * This method draws the animation (3,2,1,GO!) on the screen.
     * @param d the surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
       //d.setColor(Color.CYAN);
      //  d.fillRectangle(0, 0, 800, 600);
        if (this.countFrom != 3) {
            this.sleeper.sleepFor((int) this.numOfSeconds);
        }
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.blue);

        if (this.countFrom == 0) {
            d.drawText(350, 350, "GO!", 100);
        } else if (countFrom > 0) {
            d.drawText(350, 350, Integer.toString(countFrom), 100);
        }
        this.countFrom--;

        if (countFrom == -2) {
            this.stop = true;
            return;
        }



    }

    /**
     * A boolean method which stops the animation.
     * @return the right boolean value to stop the animation.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}