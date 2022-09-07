import java.util.*;

class Cell {
    enum CellStatus {
        EMPTY,
        X,
        O;
    }

    CellStatus cell = CellStatus.EMPTY;
    CellStatus none = CellStatus.EMPTY;

    public void setCell(char turn) {
        if (turn == 'X')
            this.cell = CellStatus.X;
        else
            this.cell = CellStatus.O;
    }

    void print() {
        if (cell == CellStatus.X)
            System.out.print("[X]");
        else if (cell == CellStatus.O)
            System.out.print("[O]");
        else
            System.out.print("[ ]");
    }
}

class Board {
    private Cell[][] board = new Cell[3][3];

    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                board[i][j] = new Cell();
        }
    }

    boolean win() {
        if (board[0][0].cell == board[1][1].cell && board[1][1].cell == board[2][2].cell
                && board[0][0].cell != board[0][0].none)
            return true;
        if (board[0][2].cell == board[1][1].cell && board[1][1].cell == board[2][0].cell
                && board[0][2].cell != board[0][2].none)
            return true;
        for (int i = 0; i < 3; i++) {
            if (board[0][i].cell == board[1][i].cell && board[1][i].cell == board[2][i].cell
                    && board[0][i].cell != board[0][i].none)
                return true;
            if (board[i][0].cell == board[i][1].cell && board[i][1].cell == board[i][2].cell
                    && board[i][0].cell != board[i][0].none)
                return true;
        }
        return false;
    }

    int check(int x, int y) {
        if (x >= 0 && x < 3 && y >= 0 && y < 3) {
            if (board[x][y].cell == board[x][y].none) return 2;
            else return 1;
        }
        return 0;
    }

    void update(int x, int y, char turn) {
        board[x][y].setCell(turn);
    }

    void print() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                board[i][j].print();
            System.out.print('\n');
        }
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Board cur_board = new Board();

        int cnt = 9;
        while (true) {
            boolean done = false;
            for (int i = 0; i < 2; i++) {
                if (i == 0)
                    System.out.println("X turn");
                else
                    System.out.println("O turn");
                int x = sc.nextInt();
                int y = sc.nextInt();

                while (cur_board.check(x, y) != 2) {
                    if (cur_board.check(x, y) == 0) System.out.println("Out of Board! Try again");
                    else System.out.println("Already Filled! Try Again");
                    x = sc.nextInt();
                    y = sc.nextInt();
                }

                if (i == 0)
                    cur_board.update(x, y, 'X');
                else
                    cur_board.update(x, y, 'O');

                cur_board.print();

                done = cur_board.win();
                if (done) {
                    if (i == 0)
                        System.out.println("X win");
                    else
                        System.out.println("O win");
                    break;
                }
                if(--cnt==0) break;
            }
            if (done || cnt==0)
                break;
            
        }

        if (cnt == 0) System.out.println("Draw");
    }
}