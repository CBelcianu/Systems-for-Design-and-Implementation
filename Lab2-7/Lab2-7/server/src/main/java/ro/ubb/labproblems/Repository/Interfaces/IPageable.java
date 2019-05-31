package ro.ubb.labproblems.Repository.Interfaces;

public interface IPageable {
    /**
     * @return page number; page numbers start at 0.
     */
    int getPageNumber();

    /**
     * @return the number of elements in a page.
     */
    int getPageSize();
}
