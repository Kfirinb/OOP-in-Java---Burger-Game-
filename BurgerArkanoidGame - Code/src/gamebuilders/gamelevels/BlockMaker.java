package gamebuilders.gamelevels;

import sprites.collidables.Block;

import java.awt.Color;

public class BlockMaker implements BlockCreator {
    private Color fillColor;
    private Color strokeColor;
    private int height, width, hitPoints;

    public BlockMaker (int height, int width, int hitPoints, Color fillColor, Color strokeColor) {
        this.height = height;
        this.width = width;
        this.hitPoints = hitPoints;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
    }
    @Override
    public Block create(int xpos, int ypos) {
        Block newBlock = new Block(xpos, ypos, this.width, this.height, this.fillColor, this.strokeColor);
        newBlock.setHitCounter(this.hitPoints);
        return newBlock;
    }


}
