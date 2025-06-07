package vn.edu.ptithcm.WebUIS.domain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultPaginationDTO {
    private Meta meta;
    private Object data;

    @Getter
    @Setter
    public static class Meta {
        private int currentPage;
        private int pageSize;
        private int totalPages;
        private long totalItems;
    }
}
