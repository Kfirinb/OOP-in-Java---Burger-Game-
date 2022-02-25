package listeners;

import gamebuilders.Counter;
import gamebuilders.GameLevel;
import gamebuilders.Velocity;
import geometry.Ball;
import sprites.collidables.Block;

import java.awt.Color;
/**
 * @author Kfir Inbal kfir.inbal@live.biu.ac.il
 * This class represents a function for an object to be able to add a ball to the game.
 */
public class BallAdder implements HitListener {
    private GameLevel game;
    private Counter remainingBallsInGame;

    /**
     * This is a constructor method which creates the ball adder function to a game and adding the remaining balls.
     * @param game the game currently played.
     * @param remainingBallsInGame a record of the remaining balls in the game.
     */
    public BallAdder(GameLevel game, Counter remainingBallsInGame) {
        this.game = game;
        this.remainingBallsInGame = remainingBallsInGame;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        Ball ball;
        Velocity vBall;
        if (game.getLevel().levelName() == "Burger after dessert") {
            for (int i = 0; i < 12; i++) {
                if (i >= 6) {
                    vBall = Velocity.fromAngleAndSpeed(155 - (i - 6) * 8.5, 4);
                } else {
                    vBall = Velocity.fromAngleAndSpeed(205 + i * 8.5, 4);
                }
                ball = new Ball(397,
                        172, 6, Color.RED, game.getEnvironment());
                ball.setVelocity(vBall);
                ball.addToGame(game);
                remainingBallsInGame.increase(1);
             }
            } else if (game.getLevel().levelName() == "Thirsty?") {
            vBall = Velocity.fromAngleAndSpeed(75, 5);
            ball = new Ball((int) beingHit.getRec().getTopLine().middle().getX(),
                    (int) beingHit.getRec().getTopLine().middle().getY(), 6, Color.white, game.getEnvironment());
            ball.setVelocity(vBall);
            ball.addToGame(game);
            remainingBallsInGame.increase(1);
        } else if (game.getLevel().levelName() == "Paycheck" && beingHit.getOtherColor() == "Safebox") {
            for (int i = 0; i < 12; i++) {
                if (i >= 6) {
                    vBall = Velocity.fromAngleAndSpeed(155 - (i - 6) * 8.5, 5);
                } else {
                    vBall = Velocity.fromAngleAndSpeed(205 + i * 8.5, 5);
                }
                ball = new Ball(400,
                        100, 6, "Safebox", game.getEnvironment());
                ball.setVelocity(vBall);
                ball.addToGame(game);
                remainingBallsInGame.increase(1);
            }
        } else if (game.getLevel().levelName() == "Paycheck") {
            vBall = Velocity.fromAngleAndSpeed(45, 6);
            ball = new Ball((int) beingHit.getRec().getTopLine().middle().getX(),
                    (int) beingHit.getRec().getTopLine().middle().getY(), 6, "Credit", game.getEnvironment());
            ball.setVelocity(vBall);
            ball.addToGame(game);
            remainingBallsInGame.increase(1);

        } else {
            ball = new Ball((int) beingHit.getRec().getBottomLine().middle().getX(),
                    (int) beingHit.getRec().getBottomLine().middle().getY() + 10,
                    5, Color.WHITE, game.getEnvironment());
            ball.setVelocity(Velocity.fromAngleAndSpeed(180, 3));
            ball.addToGame(game);
            remainingBallsInGame.increase(1);
        }
    }
}
