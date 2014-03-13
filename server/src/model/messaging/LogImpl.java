package model.messaging;

import model.Model;
import modelInterfaces.messaging.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: film42 on: 3/7/14.
 */
public class LogImpl extends Model implements modelInterfaces.messaging.Log {

    private List<Line> lines;

    public LogImpl() {

        lines = new ArrayList<LineImpl>();
        lines.add(new LineImpl());
        lines.add(new LineImpl());
        lines.add(new LineImpl());
        lines.add(new LineImpl());

    }
}
