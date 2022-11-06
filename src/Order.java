public class Order {
    protected final int trackID;
    private String nameOfSender;
    private String surnameOfSender;
    private String telSender;
    private String nameOfRecipient;
    private String surnameOfRecipient;
    private String telRecipient;
    private Cities citySend;
    private Cities cityDeliver;
    private Status status;

    protected Order(int trackID,
                    String nameOfSender,
                    String surnameOfSender,
                    String telSender,
                    String nameOfRecipient,
                    String surnameOfRecipient,
                    String telRecipient,
                    Cities citySend,
                    Cities cityDeliver,
                    Status status){
        this.trackID = trackID;
        this.nameOfSender = nameOfSender;
        this.surnameOfSender = surnameOfSender;
        this.telSender = telSender;
        this.nameOfRecipient = nameOfRecipient;
        this.surnameOfRecipient = surnameOfRecipient;
        this.telRecipient = telRecipient;
        this.citySend = citySend;
        this.cityDeliver = cityDeliver;
        this.status = status;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(trackID+",")
                .append(nameOfSender+",")
                .append(surnameOfSender+",")
                .append(telSender+",")
                .append(nameOfRecipient+",")
                .append(surnameOfRecipient+",")
                .append(telRecipient+",")
                .append(citySend+",")
                .append(cityDeliver+",")
                .append(status);
        return sb.toString();
    }


    public int getTrackID() {
        return trackID;
    }

    public String getNameOfSender() {
        return nameOfSender;
    }

    public String getSurnameOfSender() {
        return surnameOfSender;
    }

    public void setSurnameOfSender(String surnameOfSender) {
        this.surnameOfSender = surnameOfSender;
    }

    public String getTelSender() {
        return telSender;
    }

    public String getNameOfRecipient() {
        return nameOfRecipient;
    }

    public String getSurnameOfRecipient() {
        return surnameOfRecipient;
    }

    public String getTelRecipient() {
        return telRecipient;
    }

    public Status getStatus() {
        return status;
    }

}
