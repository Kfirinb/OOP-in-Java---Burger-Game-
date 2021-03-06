package gamebuilders.gamelevels;

import sprites.collidables.Block;

public interface BlockCreator {
    // Create a block at the specified location.
    Block create(int xpos, int ypos);
}