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

    public boolean isNextMoveAWin(Player player) {
        String playerSymbol = player.getSymbol();
        for (PossibleWin possibleWin : possibleWins) {
            int matches = 0;
            if (marks.get(possibleWin.getFirst()).equals(playerSymbol)) matches++;
            if (marks.get(possibleWin.getSecond()).equals(playerSymbol)) matches++;
            if (marks.get(possibleWin.getThird()).equals(playerSymbol)) matches++;
            if (matches == 2) return true;
        }
        return false;
    }

    public Integer getPositionToBlockWin(Player player) {

        List<Integer> positions = new ArrayList<>();
        String playerSymbol = player.getSymbol();

        for (PossibleWin possibleWin : possibleWins) {

            int matches = 0;
            int first = possibleWin.getFirst();
            int second = possibleWin.getSecond();
            int third = possibleWin.getThird();

            if (marks.get(first).equals(playerSymbol)) {
                matches++;
                positions.add(first);
            }
            if (marks.get(second).equals(playerSymbol)) {
                matches++;
                positions.add(second);
            }
            if (marks.get(third).equals(playerSymbol)) {
                matches++;
                positions.add(third);
            }
            // WHY DOES IT SKIP THIS IF STATEMENT???
            if (matches == 2) {
                if (marks.get(first).equals(positions.get(0)) &&
                    marks.get(second).equals(positions.get(1)))
                    return third;
                else if (marks.get(first).equals(positions.get(0)) &&
                    marks.get(third).equals(positions.get(2)))
                    return second;
                else if (marks.get(second).equals(positions.get(1)) &&
                    marks.get(third).equals(positions.get(2)))
                    return first;
            }
            positions.clear();
        }

        return null;
    }
}
