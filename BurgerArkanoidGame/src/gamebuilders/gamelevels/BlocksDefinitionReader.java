package gamebuilders.gamelevels;

import sprites.collidables.Block;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BlocksDefinitionReader {

    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BufferedReader bufferReader = new BufferedReader(reader);
        BlocksFromSymbolsFactory blocks = new BlocksFromSymbolsFactory();
        Map<String, Integer> spacerWidths = new TreeMap<String, Integer>();
        Map<String, BlockCreator> blockCreators = new TreeMap<String, BlockCreator>();
        int defaultH = 0, defaultW = 0, hitPoints, blockH, blockW;
        Color stroke = null, fill;
        try {
            String line;
            while ((line = bufferReader.readLine()) != null) { // ’null ’->no more data in the stream
                if (line.contains("#")) {
                    line = bufferReader.readLine();
                }
                if (line.contains("default")) {
                    if (line.contains("height")) {
                        defaultH = Integer.parseInt(line.substring(line.indexOf("height:") + 7,line.indexOf(" ")));
                    }
                    if (line.contains("width")) {
                        defaultW = Integer.parseInt(line.substring(line.indexOf("width:") + 6,line.indexOf(" ")));
                    }
                    if (line.contains("stroke")) {
                        stroke = Color.getColor(line.substring(line.indexOf("color(") + 6, line.indexOf(")")));
                        try {
                            Field field = Class.forName("java.awt.Color")
                                    .getField(line.substring(line.indexOf("color(",
                                            line.indexOf("stroke:")) + 6, line.indexOf(")")));
                            fill = (Color)field.get(null);
                        } catch (Exception e) {
                            fill = null; // Not defined
                        }
                    }
                }
                if (line.contains("bdef")) {
                    String symbol = line.substring(line.indexOf("symbol:") + 7, line.indexOf("symbol:") + 8);
                    hitPoints = Integer.parseInt(line.substring(line.indexOf("hit_points:") + 11, line.indexOf(" ")));
                    try {
                        Field field = Class.forName("java.awt.Color")
                                .getField(line.substring(line.indexOf("color(", line.indexOf("fill")) + 6, line.indexOf(")")));
                        fill = (Color)field.get(null);
                    } catch (Exception e) {
                        fill = null; // Not defined
                    }

                    if (line.contains("height")) {
                        blockH = Integer.parseInt(line.substring(line.indexOf("height:") + 7,line.indexOf(" ")));

                    } else {
                        blockH = defaultH;
                    }
                    if (line.contains("width")) {
                        blockW = Integer.parseInt(line.substring(line.indexOf("width:") + 6,line.indexOf(" ")));
                    } else {
                        blockW = defaultW;
                    }

                    BlockCreator blockToCreate = new BlockMaker(blockH, blockW, hitPoints, fill, stroke);
                    blockCreators.put(symbol, blockToCreate);

                }
                if (line.contains("sdef")) {
                    String symbol = line.substring(line.indexOf("symbol:") + 7, line.indexOf("symbol:") + 8);
                    int width = Integer.parseInt(line.substring(line.indexOf("width:") + 6));
                    spacerWidths.put(symbol, width);
                }

            }
            blocks.setBlockCreators(blockCreators);
            blocks.setSpacerWidths(spacerWidths);
        } catch (IOException e) {
            System.out.println(" Something went wrong while reading !");
        } finally {
            if (bufferReader != null) { // Exception might have happened at constructor
                try {
                    bufferReader.close(); // closes FileInputStream too
                } catch (IOException e) {
                    System.out.println(" Failed closing the file !");
                }
            }
        }

        return blocks;
    }
}
