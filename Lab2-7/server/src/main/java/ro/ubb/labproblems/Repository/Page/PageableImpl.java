package ro.ubb.labproblems.Repository.Page;

import ro.ubb.labproblems.Repository.Interfaces.IPageable;

public class PageableImpl implements IPageable {
    private int pageNumber;
    private int pageSize;

    public PageableImpl(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }
}
