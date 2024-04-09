package java.transferobject;

public enum StatusOrder {
    PENDING_APPROVE(1L),
    APPROVED(2L);

    private final long statusId;

    StatusOrder(long statusId) {
        this.statusId = statusId;
    }

    public long getStatusId() {
        return statusId;
    }
}

