package gamebuilders.gamelevels;

import gamebuilders.Velocity;
import sprites.Sprite;
import sprites.collidables.Block;

import java.awt.*;
import java.util.List;

public class GenericLevel implements LevelInformation {

    private int numberOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Image background;
    private int numberOfBlocksToRemove;

    public GenericLevel(String levelName, List<Velocity> initialBallVelocities, Image background, int paddleSpeed, int paddleWidth, int numberOfBlocksToRemove ){
        this.numberOfBalls = numberOfBalls;
        this.initialBallVelocities = initialBallVelocities;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.levelName = levelName;
        this.background = background;
        this.numberOfBlocksToRemove = numberOfBlocksToRemove;
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        return null;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
