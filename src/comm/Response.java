package comm;

import java.io.Serializable;

public class Response implements Serializable {
    private Integer origin;
    private Status status;

    public Response(Integer origin, Status status) {
        this.origin = origin;
        this.status = status;
    }

    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Response{" +
                "origin=" + origin +
                ", status=" + status +
                '}';
    }
}
