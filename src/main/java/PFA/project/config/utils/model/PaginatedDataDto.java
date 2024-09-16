package PFA.project.config.utils.model;

import lombok.Data;

import java.util.List;
@Data
public class PaginatedDataDto<T> {
    private List<T> content ;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private int page ;
    public PaginatedDataDto(List<T> content, int pageSize, long totalElements, int totalPages,int page) {
        this.setContent(content);
        this.setPageSize(pageSize);
        this.setTotalElements(totalElements);
        this.setTotalPages(totalPages);
        this.setPage(page);
    }


}
