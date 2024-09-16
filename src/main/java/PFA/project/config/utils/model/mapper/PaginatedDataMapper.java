package PFA.project.config.utils.model.mapper;

import PFA.project.config.utils.model.PaginatedDataDto;
import org.springframework.data.domain.Page;

import java.util.List;

public class PaginatedDataMapper {
    public static <TSource, TResult> PaginatedDataDto<TResult> mapToPage(Page<TSource> sourcePage, List<TResult> resultList  // Mapper as a parameter (pass the MapStruct mapper instance)
    ) {
        return new PaginatedDataDto<>(resultList,sourcePage.getSize(),sourcePage.getTotalElements(),sourcePage.getTotalPages(),sourcePage.getPageable().getPageNumber()+1);
    }
}
