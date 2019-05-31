package ro.ubb.labproblems.Repository.Page;

import ro.ubb.labproblems.Repository.Interfaces.IPage;
import ro.ubb.labproblems.Repository.Interfaces.IPageable;

import java.util.stream.StreamSupport;

public class Paginator {
    public static <T> IPage<T> paginate(Iterable<T> data, IPageable pageable) {
        return new PageImpl<>(StreamSupport.stream(data.spliterator(), false)
                .skip(pageable.getPageSize() * pageable.getPageNumber())
                .limit(pageable.getPageSize()), pageable);
    }
}
