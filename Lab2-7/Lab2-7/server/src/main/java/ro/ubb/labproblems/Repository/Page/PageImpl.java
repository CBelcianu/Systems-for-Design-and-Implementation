package ro.ubb.labproblems.Repository.Page;

import ro.ubb.labproblems.Repository.Interfaces.IPage;
import ro.ubb.labproblems.Repository.Interfaces.IPageable;

import java.util.stream.Stream;

public class PageImpl<T> implements IPage<T> {
    private IPageable pageableObj;
    private Stream<T> content;

    PageImpl(Stream<T> content, IPageable pageableObj) {
        this.content = content;
        this.pageableObj = pageableObj;
    }

    @Override
    public IPageable getPageable() {
        return pageableObj;
    }

    @Override
    public IPageable nextPageable() {
        return new PageableImpl(pageableObj.getPageNumber() + 1, pageableObj.getPageSize());
    }

    @Override
    public Stream<T> getContent() {
        return content;
    }
}
