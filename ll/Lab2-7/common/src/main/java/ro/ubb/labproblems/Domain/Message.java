package ro.ubb.labproblems.Domain;

import java.io.Serializable;

public class Message implements Serializable {
    public static final String OK = "OK";
    public static final String ERROR = "ERROR";
    private String header;
    private Object body = null;

    public Message(String header) {
        this.header = header;
    }

    public Message(String header, Object body) {
        this.header = header;
        this.body = body;
    }

    public String getHeader() {
        return header;
    }

    public Object getBody() {
        return body;
    }
}
