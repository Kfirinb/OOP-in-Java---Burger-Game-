package gamebuilders;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Kfir Inbal kfir.inbal@live.biu.ac.il
 * This class represents the stopping of an animation by a key press.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * This method builds the stopping key by the keyboard of the game, the key to stop, and the animation to stop.
     * @param sensor the keyboard of the game.
     * @param key the key to stop by the animation.
     * @param animation the animation to stop.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        animation.doOneFrame(d);

        if (this.sensor.isPressed(key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed  = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
       /* if (this.sensor.isPressed(key)) {
            return !animation.shouldStop();
        }
        return animation.shouldStop();*/
    }

    public void setStop(boolean stop) {
        this.stop = stop;
        //this.isAlreadyPressed = true;
    }
}