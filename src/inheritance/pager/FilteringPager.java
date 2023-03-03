package inheritance.pager;

import java.util.ArrayList;
import java.util.List;

public class FilteringPager {

    private final SimplePager dataSource;
    private final int pageSize;
    private int pageNumber = -1;
    private int pages = 0;

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

        return noNullsData().subList(pageNumber * pageSize, Math.min((pageNumber + 1) * pageSize, pages));
    }

    public List<Integer> getPreviousPage() {
        if (pageNumber <= 0) {
            throw new IllegalStateException("there is no previous page");
        } else {
            pageNumber--;
        }

        return getCurrentPage();
    }

    private List<Integer> noNullsData() {
        List<Integer> data = new ArrayList<>();

        for (int i = 0; dataSource.hasPage(i); i++) {
            for (Integer num : dataSource.getPage(i)) {
                if (num != null) {
                    data.add(num);
                }
            }
        }

        pages = data.size();
        return data;
    }
}
