package gamebuilders.gamelevels;

import sprites.collidables.Block;

import java.util.Map;

public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    // returns true if 's' is a valid space symbol.
    public boolean isSpaceSymbol(String s) {
        if (spacerWidths.containsKey("s")) {
            return true;
        }
        return false;
    }
    // returns true if 's' is a valid block symbol.
    public boolean isBlockSymbol(String s) {
        if (blockCreators.containsKey("s")) {
            return true;
        }
        return false;
    }

    // Return a block according to the definitions associated
    // with symbol s. The block will be located at position (xpos, ypos).
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    // Returns the width in pixels associated with the given spacer-symbol.
    public int getSpaceWidth(String s){
        return this.spacerWidths.get(s);
    }


    public void setBlockCreators(Map<String, BlockCreator> blockCreators) {
        this.blockCreators = blockCreators;
    }

    public void setSpacerWidths(Map<String, Integer> spacerWidths) {
        this.spacerWidths = spacerWidths;
    }
}