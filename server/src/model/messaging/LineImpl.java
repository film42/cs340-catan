package model.messaging;

import model.JsonImpl;
import model.Model;

/**
 * Created by: film42 on: 3/7/14.
 */
public class LineImpl extends JsonImpl implements modelInterfaces.messaging.Line {

    private String source;
    private String message;

    public LineImpl() {
        source = "Test";
        message = "Message";
    }
}
