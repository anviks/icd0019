package inheritance.pager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilteringPager {

    private final SimplePager dataSource;
    private final int pageSize;
    private int startingPage;
    private int endingPage = 0;
    private int startingIndex = -1;
    private int breakPointIndex = 0;
    private int lastDirection = 0;


    public FilteringPager(SimplePager dataSource, int pageSize) {
        this.dataSource = dataSource;
        this.pageSize = pageSize;
        this.startingPage = -pageSize;
    }

    public List<Integer> getNextPage() {
        boolean loopBroken = false;
        List<Integer> data = new ArrayList<>();
        startingPage = endingPage;
        startingIndex = breakPointIndex + 1;

        int i;
        for (i = startingPage; data.size() < pageSize; i++) {

            if (!dataSource.hasPage(i)) {
                throw new IllegalStateException();
            }

            List<Integer> page = dataSource.getPage(i);

            while (breakPointIndex < page.size()) {

                if (page.get(breakPointIndex) != null) {
                    data.add(page.get(breakPointIndex));
                }
                if (data.size() == pageSize) {
                    breakPointIndex++;
                    loopBroken = true;
                    break;
                }

                breakPointIndex++;
            }

            if (!loopBroken) {
                breakPointIndex = 0;
            }

        }
        endingPage = loopBroken ? i - 1 : i;
        lastDirection = 1;

        return data;
    }

    public List<Integer> getCurrentPage() {
        List<Integer> data = new ArrayList<>();

        for (int i = startingPage; data.size() < pageSize; i++) {

            if (!dataSource.hasPage(i)) {
                throw new IllegalStateException();
            }

            for (Integer num : dataSource.getPage(i)) {
                if (num != null) {
                    data.add(num);
                }
                if (data.size() == pageSize) {
                    break;
                }
            }
        }

        return data;
    }

    public List<Integer> getPreviousPage() {
        boolean loopBroken = false;
        List<Integer> data = new ArrayList<>();
        endingPage = startingPage - 1;
        breakPointIndex = startingIndex - 1;



        int i;
        for (i = endingPage; data.size() < pageSize; i--) {

            if (!dataSource.hasPage(i)) {
                throw new IllegalStateException();
            }

            List<Integer> page = dataSource.getPage(i);

            while (startingIndex >= 0) {
                if (page.get(startingIndex) != null) {
                    data.add(page.get(startingIndex));
                }
                if (data.size() == pageSize) {
                    System.out.println("loop is broken");
                    startingIndex--;
                    loopBroken = true;
                    break;
                }

                startingIndex--;
            }

            if (!loopBroken) {
                startingIndex = page.size() - 1;
            } else {
                break;
            }


//            for (Integer num : dataSource.getPage(i)) {
//                if (num != null) {
//                    data.add(num);
//                }
//                if (data.size() == pageSize) {
//                    break;
//                }
//            }
        }
        startingPage = loopBroken ? i + 1 : i;
        Collections.reverse(data);
//        System.out.println(startingPage + " - " + endingPage);
        System.out.println(startingIndex + " - " + breakPointIndex);
//        lastDirection = -1;

        return data;
    }

}
