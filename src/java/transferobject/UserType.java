package java.transferobject;

public enum UserType {
    RETAILER(1L),
    CHARITABLE_ORGANIZATION(2L),
    CONSUMER(3L);

    private final long userType;

    UserType(long userType) {
        this.userType = userType;
    }

    public long getUserType() {
        return userType;
    }
}
