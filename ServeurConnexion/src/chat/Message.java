package chat;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    int idReceiver,idSender;
    String message;
    Date date;

    public Message(int idReceiver, int idSender, String message, Date date) {
        this.idReceiver = idReceiver;
        this.idSender = idSender;
        this.message = message;
        this.date = date;
    }

    public int getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(int idReceiver) {
        this.idReceiver = idReceiver;
    }

    public int getIdSender() {
        return idSender;
    }

    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {

        return message+"\n"+date;
    }
}
