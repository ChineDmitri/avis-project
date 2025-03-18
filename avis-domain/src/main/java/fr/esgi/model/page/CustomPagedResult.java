package fr.esgi.model.page;

import java.util.List;

public class CustomPagedResult<T> {
    private final List<T> content;
    private final int     offset;
    private final int     limit;
    private final long    totalElements;

    public CustomPagedResult(List<T> content, int offset, int limit, long totalElements) {
        this.content       = content;
        this.offset        = offset;
        this.limit         = limit;
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public boolean hasNext() {
        return offset + limit < totalElements;
    }
}
