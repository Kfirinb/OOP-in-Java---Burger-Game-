package gamebuilders;

import biuoop.DrawSurface;
/**
 * @author Kfir Inbal kfir.inbal@live.biu.ac.il
 * This class represents the Animation interface.
 */
public interface Animation {
    /**
     * This method draws on the surface for each frame in the animation.
     * @param d the surface to draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * This method stops the animation.
     * @return a boolean value which stops the animation.
     */
    boolean shouldStop();


  //  void setStop(boolean stop);

}