package domain;

import java.io.Serializable;

public class Packet implements Serializable {
    private Integer origin;
    private Integer destination;
    private String message;

    public Packet(Integer origin, Integer destination, String message) {
        this.origin = origin;
        this.destination = destination;
        this.message = message;
    }

    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    public Integer getDestination() {
        return destination;
    }

    public void setDestination(Integer destination) {
        this.destination = destination;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Packet{" +
                "origin=" + origin +
                ", destination=" + destination +
                ", message='" + message + '\'' +
                '}';
    }
}
