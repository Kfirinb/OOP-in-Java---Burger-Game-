package gamebuilders.screens;

import gamebuilders.Animation;

public interface Menu<T> extends Animation {
    void addSelection(String key, String message, T returnVal);

    T getStatus();

}