public class OrderChild extends Order{
    float deliveryCost;
    public OrderChild(int trackID, String nameOfSender, String surnameOfSender, String telSender, String nameOfRecipient, String surnameOfRecipient, String telRecipient, Cities citySend, Cities cityDeliver, Status status) {
        super(trackID, nameOfSender, surnameOfSender, telSender, nameOfRecipient, surnameOfRecipient, telRecipient, citySend, cityDeliver, status);
    }

    public float getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(float deliveryCost) {
        this.deliveryCost = deliveryCost;
    }
}
