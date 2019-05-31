package ro.ubb.labproblems.Repository.Interfaces;

import java.util.stream.Stream;

public interface IPage<T> {
    IPageable getPageable();

    IPageable nextPageable();

    Stream<T> getContent();
}
