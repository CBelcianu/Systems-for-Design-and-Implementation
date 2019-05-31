package ro.ubb.labproblems.Repository;

import java.util.stream.StreamSupport;

public class Paginator {
    public static <T> IPage<T> paginate(Iterable<T> data, IPageable pageable) {
        return new PageImpl<>(StreamSupport.stream(data.spliterator(), false)
                .skip(pageable.getPageSize() * pageable.getPageNumber())
                .limit(pageable.getPageSize()), pageable);
    }
}
