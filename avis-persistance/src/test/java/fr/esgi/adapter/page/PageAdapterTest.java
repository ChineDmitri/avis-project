package fr.esgi.adapter.page;

import fr.esgi.model.page.CustomPagedResult;
import fr.esgi.model.page.PaginationParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PageAdapterTest {

    private PageAdapter pageAdapter;

    @BeforeEach
    void setUp() {
        pageAdapter = new PageAdapter();
    }

    @Test
    void testToSpringPageableWithoutSort() {
        final PaginationParams params = new PaginationParams(20, 10); // page 2
        final Pageable pageable = pageAdapter.toSpringPageable(params);

        assertEquals(2, pageable.getPageNumber());
        assertEquals(10, pageable.getPageSize());
    }

    @Test
    void testToSpringPageableWithSort() {
        final PaginationParams params = new PaginationParams(30, 15); // page 2
        final Sort sort = Sort.by("name").ascending();
        final Pageable pageable = pageAdapter.toSpringPageable(params, sort);

        assertEquals(2, pageable.getPageNumber());
        assertEquals(15, pageable.getPageSize());
        assertEquals(sort, pageable.getSort());
    }

    @Test
    void testToSpringPageableWithZeroLimit() {
        final PaginationParams params = new PaginationParams(0, 0);
        final Pageable pageable = pageAdapter.toSpringPageable(params);

        assertEquals(0, pageable.getPageNumber());
        assertEquals(10, pageable.getPageSize()); // default size
    }

    @Test
    void testFromSpringPage() {
        final List<String> data = List.of("A", "B", "C");
        final Pageable pageable = PageRequest.of(1, 3); // offset 3
        final Page<String> springPage = new PageImpl<>(data, pageable, 10);

        final CustomPagedResult<String> result = pageAdapter.fromSpringPage(springPage, s -> s + "!");

        assertEquals(List.of("A!", "B!", "C!"), result.getContent());
        assertEquals(3, result.getOffset());
        assertEquals(3, result.getLimit());
        assertEquals(10, result.getTotalElements());
    }

    @Test
    void testToPaginationParams() {
        final Pageable pageable = PageRequest.of(2, 5); // offset = 10
        final PaginationParams params = pageAdapter.toPaginationParams(pageable);

        assertEquals(10, params.getOffset());
        assertEquals(5, params.getLimit());
    }
}
