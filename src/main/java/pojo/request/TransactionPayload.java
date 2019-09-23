package pojo.request;

import static java.util.Objects.isNull;

public class TransactionPayload {
    private String sender;
    private String receiver;
    private String amount;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public boolean isValid() {
        return !isNull(sender) && !sender.isEmpty()
                && !isNull(receiver) && !receiver.isEmpty()
                && !isNull(amount) && !amount.isEmpty();
    }
}
