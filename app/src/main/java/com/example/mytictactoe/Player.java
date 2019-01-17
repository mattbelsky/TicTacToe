package com.example.mytictactoe;

class Player {
    private String symbol;
    private String winningText;

    public Player(String symbol, String winningText) {
        this.symbol = symbol;
        this.winningText = winningText;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getWinningText() {
        return winningText;
    }
}
