package fr.esgi.adapter.page;

import fr.esgi.model.page.CustomPagedResult;
import fr.esgi.model.page.PaginationParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class PageAdapter {

    /**
     * Converts custom PaginationParams to Spring Pageable
     */
    public Pageable toSpringPageable(PaginationParams params) {
        // Convert offset/limit to page/size
        int pageNumber = params.getLimit() > 0 ? params.getOffset() / params.getLimit() : 0;
        return PageRequest.of(pageNumber, params.getLimit() > 0 ? params.getLimit() : 10);
    }

    /**
     * Converts custom PaginationParams to Spring Pageable with sorting
     */
    public Pageable toSpringPageable(PaginationParams params, Sort sort) {
        int pageNumber = params.getLimit() > 0 ? params.getOffset() / params.getLimit() : 0;
        return PageRequest.of(pageNumber, params.getLimit() > 0 ? params.getLimit() : 10, sort);
    }

    /**
     * Converts Spring Page to custom CustomPagedResult with mapping function
     */
    public <T, R> CustomPagedResult<R> fromSpringPage(Page<T> springPage, Function<T, R> mapper) {
        List<R> content = springPage.getContent()
                                    .stream()
                                    .map(mapper)
                                    .collect(Collectors.toList());

        return new CustomPagedResult<>(
                content,
                (int) springPage.getPageable()
                                .getOffset(),
                springPage.getSize(),
                springPage.getTotalElements()
        );
    }

    /**
     * Converts Pageable to PaginationParams
     */
    public PaginationParams toPaginationParams(Pageable pageable) {
        return new PaginationParams(
                (int) pageable.getOffset(),
                pageable.getPageSize()
        );
    }
}
