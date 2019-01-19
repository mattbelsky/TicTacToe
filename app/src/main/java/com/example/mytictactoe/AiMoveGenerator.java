package com.example.mytictactoe;

import java.util.Random;

class AiMoveGenerator {

    private final Random random = new Random();
    private Board board;

    public AiMoveGenerator(Board board) {
        this.board = board;
    }

    public Integer getMove() {
        if (board.isFull()) return null;
        while (true) {
            int iaMove = random.nextInt(9);
            if (board.isEmpty(iaMove)) return iaMove;
        }
    }
}
