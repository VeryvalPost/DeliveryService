public enum Cities {
    MSK,
    SPB,
    SOCH,
    KRD,
    VRN;

    public static Cities detectCity(String input) {
        switch (input){
            case "SPB":
                return SPB;
            case "MSK":
                return MSK;
            case "SOCH":
                return SOCH;
            case "KRD":
                return KRD;
            case "VRN":
                return VRN;
        }
        return null;
    }

    public int Distance(Cities cities){
        int dist;
        switch (cities){
            case SPB:
                dist = 0;
                return dist;
            case MSK:
                dist = 700;
                return dist;
            case VRN:
                dist = 1300;
                return dist;
            case KRD:
                dist = 2200;
                return dist;
            case SOCH:
                dist = 2600;
                return dist;
        } return 0;
    }
}
