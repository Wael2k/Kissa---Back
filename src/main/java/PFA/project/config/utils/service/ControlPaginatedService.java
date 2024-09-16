package PFA.project.config.utils.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ControlPaginatedService {
    private static final Sort sortDescending = Sort.by("created").descending();
    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 5;

    public static PageRequest getPageRequest(Integer page, Integer size) {
        if (size == null || page == null) {
            return PageRequest.of(DEFAULT_PAGE, DEFAULT_SIZE, sortDescending);
        } else {
            if (page < 1 || size < 1) {
                return PageRequest.of(DEFAULT_PAGE, DEFAULT_SIZE, sortDescending);
            } else {
                return PageRequest.of(page - 1, size, sortDescending);
            }
        }
    }
}
