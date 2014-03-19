package model.messaging;

import model.JsonImpl;
import model.Model;import model.JsonImpl;
import model.Model;
import modelInterfaces.messaging.Line;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by klu on 3/18/14.
 */


public class MessageImpl extends JsonImpl {

    private List<Line> lines;

    public MessageImpl() {

        lines = new ArrayList<Line>();
        lines.add(new LineImpl());
        lines.add(new LineImpl());
        lines.add(new LineImpl());
        lines.add(new LineImpl());

    }
    public void addLine(LineImpl line){

        lines.add(line);
    }

    public List<Line> getLines(){

        return lines;
    }

}
