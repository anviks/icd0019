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
    private int recentlyTurnedNextPage = -5;
    private int previousCachedNum = 0;
    private int cachedNum = 0;


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
                    if (recentlyTurnedNextPage != 0) {
                        data.add(page.get(breakPointIndex));
                    } else {
                        recentlyTurnedNextPage = 1;
                    }

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
        recentlyTurnedNextPage = 1;

        previousCachedNum = cachedNum;
        cachedNum = data.get(data.size() - 1);

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

        if (recentlyTurnedNextPage == 1) {
            data.add(previousCachedNum);
        }


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
        recentlyTurnedNextPage = 0;

        return data;
    }

}
