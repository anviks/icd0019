package oo.hide;

public class PointSet {

    public Point[] set;
    private boolean[] addedNull;

    public PointSet(int capacity) {
        set = new Point[capacity];
        addedNull = new boolean[capacity];
    }

    public PointSet() {
        this(10);
    }

    public void add(Point point) {
        if (point == null || !contains(point)) {
            boolean added = false;

            for (int i = 0; i < set.length; i++) {
                if (set[i] == null && !addedNull[i]) {
                    set[i] = point;
                    added = true;
                    if (point == null) {
                        addedNull[i] = true;
                    }
                    break;
                }
            }

            if (!added) {
                doubleSize();
                add(point);
            }
        }
    }

    public void doubleSize() {
        Point[] largerSet = new Point[set.length * 2];
        System.arraycopy(set, 0, largerSet, 0, set.length);
        set = largerSet;
    }

    public int size() {
        int count = 0;
        for (Point point : set) {
            if (point != null) count++;
        }

        return count;
    }

    public boolean contains(Point point) {
        for (Point p : set) {
            if (point.equals(p)) {
                return true;
            }
        }

        return false;
    }

    public PointSet subtract(PointSet other) {
        PointSet result = new PointSet(set.length);
        System.arraycopy(set, 0, result.set, 0, set.length);

        for (Point point : other.set) {
            if (point == null) continue;
            result.remove(point);
        }

        return result;
    }

    public PointSet intersect(PointSet other) {
        PointSet result = new PointSet(size());
        int i = 0;

        for (Point point : set) {
            if (point != null) {
                result.set[i] = point;
                i++;
            }
        }

        for (Point point : result.set) {
            if (point == null) continue;
            if (!other.contains(point)) {
                result.remove(point);
            }
        }

        return result;
    }

    public void remove(Point point) {
        if (!contains(point)) return;

        for (int i = 0; i < set.length; i++) {
            if (point.equals(set[i])) {
                set[i] = null;
                break;
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PointSet other) {

            for (Point point : other.set) {
                if (point != null && !contains(point)) {
                    return false;
                }
            }

            for (Point point : set) {
                if (point != null && !other.contains(point)) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < set.length; i++) {
            if (set[i] != null || addedNull[i]) {
                builder.append(set[i]).append(", ");
            }
        }

        return builder.substring(0, builder.length() - 2);
    }
}
