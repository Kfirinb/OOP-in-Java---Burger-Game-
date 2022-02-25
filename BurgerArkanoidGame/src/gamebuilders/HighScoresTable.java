package gamebuilders;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.List;

public class HighScoresTable implements Serializable {
    private int tableSize;
    private List<ScoreInfo> highScoresTable;
    // Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.
    public HighScoresTable(int size) {
        this.tableSize = size;
        highScoresTable = new ArrayList<ScoreInfo>(this.tableSize);

    }
    public HighScoresTable() {
        this.tableSize = 5;
        this.highScoresTable = new ArrayList<ScoreInfo>(tableSize);

    }
    // Add a high-score.
    public void add(ScoreInfo score) {
        int index = this.getRank(score.getScore());
        if (index > this.tableSize) {
            return;
        }
        this.highScoresTable.add(index - 1, score);
        if (this.highScoresTable.size() == (this.tableSize + 1)) {
            this.highScoresTable.remove(this.tableSize - 1);
        }
    }

    // Return table size.
    public int size() {
        return this.tableSize;
    }

    // Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    public List<ScoreInfo> getHighScores() {
        return this.highScoresTable;
    }

    // return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.
    public int getRank(int score) {
        if (this.highScoresTable.isEmpty()) {
            return 1;
        }
        int i = 1;
        for (ScoreInfo s : this.highScoresTable) {
            if (score > s.getScore()) {
                return i;
            } //else if (score == s.getScore() && s.getName() ==)
            i++;
        }
       // i++;
        return i;
    }

    // Clears the table
    public void clear() {
        this.highScoresTable.clear();
    }

    // Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException, ClassNotFoundException {
        ObjectInputStream oi = new ObjectInputStream(new FileInputStream(filename));
        HighScoresTable tempTable = (HighScoresTable) oi.readObject();
        this.highScoresTable = tempTable.highScoresTable;
        this.tableSize = tempTable.size();

        oi.close();

    }

    // Save table data to the specified file.
    public void save(File filename) throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename.getName()));
        os.writeObject(this);
        os.close();
    }

    // Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) {
        ObjectInputStream oi = null;
        HighScoresTable temp = new HighScoresTable();
        try {
            oi = new ObjectInputStream(new FileInputStream(filename));
            temp = (HighScoresTable) oi.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading or opening the file");
            e.printStackTrace();
            return temp;

        }

        return temp;
    }
}