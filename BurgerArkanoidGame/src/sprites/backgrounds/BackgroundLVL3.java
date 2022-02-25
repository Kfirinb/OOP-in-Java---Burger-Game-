package sprites.backgrounds;

import biuoop.DrawSurface;
import gamebuilders.GameLevel;
import sprites.Sprite;
import sprites.collidables.Block;

import java.awt.Color;
/**
 * @author Kfir Inbal kfir.inbal@live.biu.ac.il
 * This class is a sprite that holds the background for level3.
 */
public class BackgroundLVL3 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        Block backgroundColorBlock = new Block(0, 0, 800, 600, Color.orange);
        backgroundColorBlock.drawOn(d);
     /*  //The cola back
      //  d.setColor(Color.RED);
        //d.fillRectangle(270, 25, 260, 340);
       //The sign of the cola
        d.setColor(Color.RED);
        d.fillRectangle(260, 150, 285, 70);
        d.setColor(Color.BLACK);
        d.drawRectangle(260, 149, 285, 70);
        d.setColor(Color.white);
        d.drawText(270, 205, "Coca-Cola", 54);
        d.fillRectangle(260, 212, 270, 5);
        d.fillRectangle(260, 153, 270, 5);

        //"CLASSIC" SIGN
        //d.setColor(Color.RED);
        //d.fillRectangle(325, 241, 145, 30);
        d.setColor(Color.white);
        d.drawText(335, 287, "CLASSIC", 30);

*/
        //Straws box
        d.setColor(Color.black);
        d.drawRectangle(26, 560, 130, 40);
        d.setColor(Color.lightGray);
        d.fillRectangle(25, 561, 130, 30);
        d.setColor(Color.red);
        d.drawText(55, 580, "Straws", 20);
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void timePassed() {

    }
}
