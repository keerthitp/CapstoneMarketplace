package com.company.Marketplace.Temp;

public class Honda implements Vehicle {

    private int xPosition = 0;
    @Override
    public void moveLeft() {

        this.xPosition--;
    }

    @Override
    public void moveRight() {

        this.xPosition++;

    }
}
