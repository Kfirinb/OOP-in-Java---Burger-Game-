package gamebuilders.screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamebuilders.AnimationRunner;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenuAnimation<T> implements Menu<T> {
    private List<Selection<T>> selections;
    private T status;
    private boolean stop;
    private KeyboardSensor keyboard;
    private AnimationRunner ar;



   // public MenuAnimation(KeyboardSensor keyboardSensor, AnimationRunner ar) {
            public MenuAnimation(KeyboardSensor keyboardSensor) {
            this.selections = new ArrayList<Selection<T>>();
        this.status = null;
        this.keyboard = keyboardSensor;
       // this.ar = ar;
        this.stop = false;
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.selections.add(new Selection<T>(key, message, returnVal));
    }

    @Override
    public T getStatus() {
        T statusSaver = this.status;
        this.stop = false;
        this.status = null;
        return statusSaver;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        Selection temp;
        for (int i = 0; i < this.selections.size(); i++) {
            temp = selections.get(i);
            d.drawText(100, ((i + 1) * 120), " (" + temp.getKey() + ") " + temp.getMessage(), 20);
        }

        for (Selection<T> s : selections) {
            if (this.keyboard.isPressed(s.getKey())) {
                this.status = s.getReturnVal();
                this.stop = true;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }


}