package model.messaging;

import model.JsonImpl;
import model.Model;

/**
 * Created by: film42 on: 3/7/14.
 */
public class LineImpl extends JsonImpl implements modelInterfaces.messaging.Line {

    private String source;
    private String message;

    public LineImpl(){
        source = "Test";
        source = "Message";

    }

    public LineImpl(String source, String message) {
        source = source;
        message = message;
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
