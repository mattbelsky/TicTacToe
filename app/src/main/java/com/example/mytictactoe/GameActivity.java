package com.example.mytictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private TextView gameResultTv;
    private List<Button> buttons;
    private final String PLAYER_SYMBOL = "X";
    private final String AI_SYMBOL = "O";
    private Button startButton;
    private Board board;
    private Player player;
    private Player aiPlayer;
    private AiMoveGenerator aiMoveGenerator;
    private boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initializeUi();
        board = new Board();
        player = new Player(PLAYER_SYMBOL, getString(R.string.youwin));
        aiPlayer = new Player(AI_SYMBOL, getString(R.string.lose));
        aiMoveGenerator = new AiMoveGenerator(board);
    }

    private void initializeUi() {
        gameResultTv = findViewById(R.id.tv_gameResult);
        initializeStartButton();
        initializeButtons();
    }

    private void initializeStartButton() {
        startButton = findViewById(R.id.b_start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanButtons();
                board.erase();
                gameResultTv.setText("");
                enableButtons();
                gameOver = false;
            }
        });
    }

    private void enableButtons() {
        for (Button button : buttons) {
            button.setClickable(true);
        }
    }

    private void cleanButtons() {
        for (Button button : buttons) {
            button.setText("");
        }
    }

    private void initializeButtons() {
        buttons = new ArrayList<>();
        buttons.add((Button) findViewById(R.id.b_top_left));
        buttons.add((Button) findViewById(R.id.b_top_center));
        buttons.add((Button) findViewById(R.id.b_top_right));
        buttons.add((Button) findViewById(R.id.b_center_left));
        buttons.add((Button) findViewById(R.id.b_center_center));
        buttons.add((Button) findViewById(R.id.b_center_right));
        buttons.add((Button) findViewById(R.id.b_bottom_left));
        buttons.add((Button) findViewById(R.id.b_bottom_center));
        buttons.add((Button) findViewById(R.id.b_bottom_right));
        addButtonActions();
    }

    private void addButtonActions() {
        for (final Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button clickedButton = (Button) v;
                    movePlayer(buttons.indexOf(clickedButton));
                    if (!gameOver) moveAi();
                }
            });
        }
    }

    // TODO Improve AI move generation.
    /* Three start scenarios: User starts...
        1)  On corner
                Mark middle
                Loop:
                    Is a win possible?
                        Mark square for win
                    Next player move is a win for them?
                        Obstruct win (three scenarios)
                            Positions are next to each other
                            Positions are corners in the same row/col
                            Positions are diagonal
                    Mark empty square in (any) outside row
                    Check if next move is stalemate

        2)  Outside center
                Same as corner

        3)  Inside center
                Mark (any) outside corner
                Same loop
     */
    private void moveAi() {
        int center = 4;
        Integer aiMove = aiMoveGenerator.getMove();

        // Marks the center square if empty. Checks if next player move is their win and blocks the
        // win. Marks a random spot otherwise.
        if (board.isEmpty(center)) markButtonAndBoard(center, aiPlayer);
        else {
            if (board.isNextMoveAWin(player)) {
                aiMove = board.getPositionToBlockWin(player);
                if (aiMove == null) aiMove = aiMoveGenerator.getMove();
            }
            markButtonAndBoard(aiMove, aiPlayer);
            checkWin(aiPlayer);
            checkTie();
        }
    }

    private void movePlayer(int playerMove) {
        markButtonAndBoard(playerMove, player);
        checkWin(player);
        checkTie();
    }

    private void checkTie() {
        if (!gameOver && board.isFull()) {
            gameOver = true;
            gameResultTv.setText(getString(R.string.tie));
        }
    }

    private void checkWin(Player player) {
        if (board.hasWon(player)) {
            gameResultTv.setText(player.getWinningText());
            disableButtons();
            gameOver = true;
        }
    }

    private void markBoard(int position, Player player) {
        board.mark(position, player.getSymbol());
    }

    private void markButton(Button clickedButton, Player player) {
        clickedButton.setText(player.getSymbol());
        clickedButton.setClickable(false);
    }

    private void markButtonAndBoard(int move, Player player) {
        markButton(buttons.get(move), player);
        markBoard(move, player);
    }

    private void disableButtons() {
        for (Button button : buttons) {
            button.setClickable(false);
        }
    }
}
