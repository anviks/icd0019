package inheritance.pager;

import java.util.ArrayList;
import java.util.List;

public class FilteringPager {
    private final SimplePager dataSource;
    private final int pageSize;
    private int startingPage;
    private int endingPage;
    private int startingIndex;
    private int endingIndex;


    public FilteringPager(SimplePager dataSource, int pageSize) {
        this.dataSource = dataSource;
        this.pageSize = pageSize;
    }

    public List<Integer> getNextPage() {
        List<Integer> filteredPage = new ArrayList<>();
        startingPage = endingPage;
        startingIndex = endingIndex - 1;

        while (filteredPage.size() < pageSize) {

            if (!dataSource.hasPage(endingPage)) {
                throw new IllegalStateException();
            }

            List<Integer> page = dataSource.getPage(endingPage);

            while (endingIndex < page.size()) {
                Integer item = page.get(endingIndex++);

                if (item != null) {
                    filteredPage.add(item);
                }

                if (filteredPage.size() == pageSize) {
                    return filteredPage;
                }
            }
            endingIndex = 0;
            endingPage++;
        }

        return filteredPage;
    }

    public List<Integer> getCurrentPage() {
        List<Integer> filteredPage = new ArrayList<>();
        int tempStartingPage = startingPage;
        int tempStartingIndex = startingIndex + 1;

        while (filteredPage.size() < pageSize) {

            if (!dataSource.hasPage(tempStartingPage)) {
                throw new IllegalStateException();
            }

            List<Integer> page = dataSource.getPage(tempStartingPage);

            while (tempStartingIndex < page.size()) {
                Integer item = page.get(tempStartingIndex++);

                if (item != null) {
                    filteredPage.add(item);
                }

                if (filteredPage.size() == pageSize) {
                    return filteredPage;
                }
            }
            tempStartingIndex = 0;
            tempStartingPage++;
        }

        return filteredPage;
    }

    public List<Integer> getPreviousPage() {
        List<Integer> filteredPage = new ArrayList<>();
        endingPage = startingPage;
        endingIndex = startingIndex + 1;

        while (filteredPage.size() < pageSize) {

            if (!dataSource.hasPage(startingPage)) {
                throw new IllegalStateException();
            }

            List<Integer> page = dataSource.getPage(startingPage);

            while (startingIndex >= 0) {
                Integer item = page.get(startingIndex--);

                if (item != null) {
                    filteredPage.add(0, item);
                }

                if (filteredPage.size() == pageSize) {
                    return filteredPage;
                }
            }
            startingPage--;
            startingIndex = page.size() - 1;
        }

        return filteredPage;
    }

}
