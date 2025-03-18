package fr.esgi.model.page;

public class PaginationParams {
    private final int offset;
    private final int limit;

    public PaginationParams(int offset, int limit) {
        this.offset = offset;
        this.limit  = limit;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public static PaginationParams of(int offset, int limit) {
        return new PaginationParams(offset, limit);
    }
}
