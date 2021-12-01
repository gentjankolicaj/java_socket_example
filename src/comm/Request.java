package comm;

import java.io.Serializable;

public class Request implements Serializable {
    private Integer origin;
    private Integer packets;

    public Request(Integer origin, Integer packets) {
        this.origin = origin;
        this.packets = packets;
    }

    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    public Integer getPackets() {
        return packets;
    }

    public void setPackets(Integer packets) {
        this.packets = packets;
    }

    @Override
    public String toString() {
        return "Request{" +
                "origin=" + origin +
                ", packets=" + packets +
                '}';
    }
}
