package gamebuilders.gamelevels;


import gamebuilders.Velocity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelSpecificationReader {
    //File lvlDefinition = new File("level_definition.txt");
    //Reader reader2 = new FileReader("c:\\level_definition.txt");
    //throws IOException
    public List<LevelInformation> fromReader(java.io.Reader reader) {
              /* List<List<String>> listOfLevels = this.FileToLevels(reader);
               List<Velocity> listOfVelocities = new ArrayList<>();

                  / for( c : l.get(1)) {
                        listOfVelocities.add()
                    }
                    LevelInformation newLevel = new GenericLevel(listOfLevels.get(0).get(0));
*/


            return null;
    }

    public List<List<String>> FileToLevels(Reader reader) {
        BufferedReader bufferReader = new BufferedReader(reader);
        List<List<String>> ListOfLevels = new ArrayList<List<String>>();
        List<String> newLevel = new ArrayList<String>();
        try {
            String line;
            while ((line = bufferReader.readLine()) != null) { // ’null ’->no more data in the stream

                while (line != "END_LEVEL") {
                    line = bufferReader.readLine();

                    if (line == "START_LEVEL") {
                        line = bufferReader.readLine();

                    }
                    if(line == null) {
                        break;
                    }
               newLevel.add(line.substring(line.indexOf(":")+ 1));
                 //   System.out.println(line.substring(line.indexOf(":")+ 1));
                }
                ListOfLevels.add(newLevel);
                newLevel = new ArrayList<String>();
            }

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
        return ListOfLevels;

    }


}