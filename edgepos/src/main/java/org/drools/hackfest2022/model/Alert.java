package org.drools.hackfest2022.model;

public class Alert {
    private Item subject;
    private String message;
    public Alert() {
        // empty constructor.
    }
    public Alert(Item subject, String message) {
        this.subject = subject;
        this.message = message;
    }
    public Item getSubject() {
        return subject;
    }
    public void setSubject(Item subject) {
        this.subject = subject;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "Alert [message=" + message + ", subject=" + subject + "]";
    }
}
