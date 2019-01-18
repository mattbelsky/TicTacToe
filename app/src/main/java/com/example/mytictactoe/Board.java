package com.example.mytictactoe;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

class Board {

    private List<String> marks = new ArrayList<>(asList("", "", "", "", "", "", "", "", ""));
    private List<PossibleWin> possibleWins = new ArrayList<>();

    public Board() {
        // Horizontal
        possibleWins.add(new PossibleWin(0,1,2));
        possibleWins.add(new PossibleWin(3,4,5));
        possibleWins.add(new PossibleWin(6,7,8));

        // Vertical
        possibleWins.add(new PossibleWin(0,3,6));
        possibleWins.add(new PossibleWin(1,4,7));
        possibleWins.add(new PossibleWin(2,5,8));

        // Diagonal
        possibleWins.add(new PossibleWin(0,4,8));
        possibleWins.add(new PossibleWin(2,4,6));
    }

    public void mark(Integer position, String symbol) {
        marks.set(position, symbol);
    }

    public boolean hasWon(Player player) {
        for (PossibleWin possibleWin : possibleWins) {
            if (checkIfAreTheSame(possibleWin, player.getSymbol())) return true;
        }
        return false;
    }

    public void erase() {
        marks = new ArrayList<>(asList("", "", "", "", "", "", "", "", ""));
    }

    private boolean checkIfAreTheSame(PossibleWin possibleWin, String playerSymbol) {
        return marks.get(possibleWin.getFirst()).equals(playerSymbol) &&
                marks.get(possibleWin.getSecond()).equals(playerSymbol) &&
                marks.get(possibleWin.getThird()).equals(playerSymbol);
    }

    public boolean isFull() {
        for (String mark : marks) {
            if (mark.isEmpty()) return false;
        }
        return true;
    }

    public boolean isEmpty(int iaMove) {
        return marks.get(iaMove).isEmpty();
    }
}
