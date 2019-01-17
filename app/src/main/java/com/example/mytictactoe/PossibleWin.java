package com.example.mytictactoe;

class PossibleWin {

    private int first;
    private int second;
    private int third;

    public PossibleWin(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public int getThird() {
        return third;
    }
}
