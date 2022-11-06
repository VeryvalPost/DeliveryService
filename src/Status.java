import java.util.Random;

public enum Status {
    IN_WORK,
    ACCEPTED,
    SHIPPED,
    DELIVERED;

    public static Status randomStatus() {
        int pick = new Random().nextInt(Status.values().length);
        return Status.values()[pick];
    }

    public static Status strToEnum(String input) {
        switch (input) {
            case "IN_WORK":
                return IN_WORK;
            case "ACCEPTED":
                return ACCEPTED;
            case "SHIPPED":
                return SHIPPED;
            case "DELIVERED":
                return DELIVERED;
        }
        return IN_WORK;
    }

    public static String enumToStr(Status status) {
        switch (status) {
            case IN_WORK:
                return "IN_WORK";
            case ACCEPTED:
                return "ACCEPTED";
            case SHIPPED:
                return "SHIPPED";
            case DELIVERED:
                return "DELIVERED";
        }
        return "Unknown";
    }
}
