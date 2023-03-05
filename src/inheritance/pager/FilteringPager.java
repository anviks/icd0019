package inheritance.pager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilteringPager {
    private final SimplePager dataSource;
    private final int pageSize;
    private int startingPage;
    private int endingPage;
    private int startingIndex;
    private int breakPointIndex;


    public FilteringPager(SimplePager dataSource, int pageSize) {
        this.dataSource = dataSource;
        this.pageSize = pageSize;
    }

    public List<Integer> getNextPage() {
        List<Integer> data = new ArrayList<>();
        startingPage = endingPage;
        startingIndex = breakPointIndex - 1;

        while (data.size() < pageSize) {

            if (!dataSource.hasPage(endingPage)) {
                throw new IllegalStateException();
            }

            List<Integer> page = dataSource.getPage(endingPage);

            while (breakPointIndex < page.size()) {
                Integer item = page.get(breakPointIndex);
                breakPointIndex++;

                if (item == null) {
                    continue;
                }

                data.add(item);

                if (data.size() == pageSize) {
                    return data;
                }
            }

            breakPointIndex = 0;
            endingPage++;
        }

        return data;
    }

    public List<Integer> getCurrentPage() {
        List<Integer> data = new ArrayList<>();
        int tempStartingPage = startingPage;
        int tempStartingIndex = startingIndex + 1;

        while (data.size() < pageSize) {

            if (!dataSource.hasPage(tempStartingPage)) {
                throw new IllegalStateException();
            }

            List<Integer> page = dataSource.getPage(tempStartingPage);

            while (tempStartingIndex < page.size()) {
                Integer element = page.get(tempStartingIndex++);
                if (element != null) {
                    data.add(element);
                    if (data.size() == pageSize) {
                        return data;
                    }
                }
            }
            tempStartingIndex = 0;
            tempStartingPage++;
        }

        return data;
    }

    public List<Integer> getPreviousPage() {
        List<Integer> data = new ArrayList<>();
        endingPage = startingPage;
        breakPointIndex = startingIndex + 1;

        while (data.size() < pageSize) {

            if (!dataSource.hasPage(startingPage)) {
                throw new IllegalStateException();
            }

            List<Integer> page = dataSource.getPage(startingPage);

            while (startingIndex >= 0) {
                Integer element = page.get(startingIndex);
                startingIndex--;
                if (element == null) {
                    continue;
                }
                data.add(element);
                if (data.size() == pageSize) {
                    Collections.reverse(data);
                    return data;
                }
            }

            startingPage--;
            startingIndex = page.size() - 1;
        }

        Collections.reverse(data);
        return data;
    }

}
