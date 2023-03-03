package inheritance.pager;

import java.util.ArrayList;
import java.util.List;

public class FilteringPager {

    private final SimplePager dataSource;
    private final int pageSize;
    private int pageNumber = -1;

    public FilteringPager(SimplePager dataSource, int pageSize) {
        this.dataSource = dataSource;
        this.pageSize = pageSize;
    }

    public List<Integer> getNextPage() {
        pageNumber++;

        return getCurrentPage();
    }

    public List<Integer> getCurrentPage() {
        if (!dataSource.hasPage(pageNumber)) {
            throw new IllegalStateException("there is no current page");
        }

        return noNullsData(pageNumber * pageSize, (pageNumber + 1) * pageSize);
    }

    public List<Integer> getPreviousPage() {
        pageNumber--;

        return getCurrentPage();
    }

    private List<Integer> noNullsData(int start, int end) {
        List<Integer> data = new ArrayList<>();

        for (int i = 0; data.size() < end; i++) {

            if (!dataSource.hasPage(i)) {
                throw new IllegalArgumentException();
            }

            for (Integer num : dataSource.getPage(i)) {
                if (num != null) {
                    data.add(num);
                }
            }
        }

        return data.subList(start, end);
    }
}
