package ru.itmo.wp.web.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class TicTacToePage {
    public enum Phase {
        RUNNING,
        WON_X,
        WON_O,
        DRAW
    }

    public static class State {
        private class Cell {
            int row;
            int col;

            private Cell(int row, int col) {
                this.row = row;
                this.col = col;
            }

            private boolean equals(Cell right) {
                return isValid() && right.isValid() && cells[row][col] != null
                        && cells[row][col] == cells[right.row][right.col];
            }

            private boolean isValid() {
                return isValidCell(row, col);
            }
        }

        private final int size;
        private boolean crossesMove = true;
        private final Character[][] cells;
        private Phase phase = Phase.RUNNING;
        private int movesCount = 0;

        public State(int size) {
            this.size = size;
            cells = new Character[size][size];
        }

        private void makeMove(int row, int col) {
            if (phase != Phase.RUNNING || cells[row][col] != null) {
                return;
            }
            char value = crossesMove ? 'X' : 'O';
            cells[row][col] = value;
            ++movesCount;
            if (isWinner(row, col)) {
                phase = crossesMove ? Phase.WON_X : Phase.WON_O;
            } else if (movesCount == size * size) {
                phase = Phase.DRAW;
            }
            crossesMove ^= true;
        }

        private boolean isWinner(int row, int col) {
            Cell cell = new Cell(row, col);
            //row
            for (int i = 0; i < size; ++i) {
                if (!cell.equals(new Cell(row, i))) {
                    break;
                }
                if (i == size - 1) {
                    return true;
                }
            }

            //col
            for (int i = 0; i < size; ++i) {
                if (!cell.equals(new Cell(i, col))) {
                    break;
                }
                if (i == size - 1) {
                    return true;
                }
            }

            // diag
            if (row == col) {
                for (int i = 0; i < size; ++i) {
                    if (!cell.equals(new Cell(i, i))) {
                        break;
                    }
                    if (i == size - 1) {
                        return true;
                    }
                }
            }

            // anti diag
            if (row + col == size - 1) {
                for (int i = 0; i < size; ++i) {
                    if (!cell.equals(new Cell(i, size - 1 - i))) {
                        break;
                    }
                    if (i == size - 1) {
                        return true;
                    }
                }
            }
            return false;
        }

        public int getSize() {
            return size;
        }

        public boolean isCrossesMove() {
            return crossesMove;
        }

        public Character[][] getCells() {
            return cells;
        }

        public Phase getPhase() {
            return phase;
        }

        public boolean isValidCell(int row, int col) {
            return 0 <= row && row < size && 0 <= col && col < size;
        }
    }

    private void action(HttpServletRequest request, Map<String, Object> view) {
        HttpSession session = request.getSession();
        if (session.getAttribute("state") == null) {
            newGame(request, view);
        } else {
            view.put("state", session.getAttribute("state"));
        }
    }

    private void newGame(HttpServletRequest request, Map<String, Object> view) {
        HttpSession session = request.getSession();
        session.setAttribute("state", new State(3));
        view.put("state", session.getAttribute("state"));
    }

    private void onMove(HttpServletRequest request, Map<String, Object> view) {
        HttpSession session = request.getSession();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            String key = entry.getKey();
            if (key.matches("cell_[0-9]{2}")) {
                int row = Character.getNumericValue(key.charAt(key.length() - 2));
                int col = Character.getNumericValue(key.charAt(key.length() - 1));
                State state = (State) session.getAttribute("state");
                if (state != null && state.isValidCell(row, col)) {
                    state.makeMove(row, col);
                }
                break;
            }
        }
        view.put("state", session.getAttribute("state"));
    }
}
