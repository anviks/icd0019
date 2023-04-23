package gol;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Point> aliveCells = new ArrayList<>();

    public void markAlive(int x, int y) {
        aliveCells.add(new Point(x, y));
    }

    public boolean isAlive(int x, int y) {
        return aliveCells.contains(new Point(x, y));
    }

    public void toggle(int x, int y) {
        if (isAlive(x, y)) {
            aliveCells.remove(new Point(x, y));
        } else {
            markAlive(x, y);
        }
    }

    public Integer getNeighbourCount(int x, int y) {
        int count = 0;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (isAlive(x + i, y + j) && (i != 0 || j != 0)) {
                    count++;
                }
            }
        }

        return count;
    }

    private List<Point> getDeadNeighbours(int x, int y) {
        List<Point> neighbours = new ArrayList<>();

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Point point = new Point(x + i, y + j);
                if (!isAlive(x + i, y + j)
                        && x + i >= 0
                        && y + j >= 0
                        && (i != 0 || j != 0)
                        && !neighbours.contains(point)) {
                    neighbours.add(point);
                }
            }
        }

        return neighbours;
    }

    public void nextFrame() {
        List<Point> allDeadNeighbours = new ArrayList<>();
        List<Point> newAliveCells = new ArrayList<>();

        for (Point alive : aliveCells) {
            for (Point dead : getDeadNeighbours(alive.x, alive.y)) {
                if (!allDeadNeighbours.contains(dead)) {
                    allDeadNeighbours.add(dead);
                }
            }

            if (nextState(true, getNeighbourCount(alive.x, alive.y))) {
                newAliveCells.add(alive);
            }
        }

        for (Point dead : allDeadNeighbours) {
            if (getNeighbourCount(dead.x, dead.y) == 3) {
                newAliveCells.add(dead);
            }
        }

        aliveCells = newAliveCells;
    }

    public void clear() {
        aliveCells.clear();
    }

    public boolean nextState(boolean isLiving, int neighborCount) {
        return isLiving && neighborCount == 2 || neighborCount == 3;
    }
}
