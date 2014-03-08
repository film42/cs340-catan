package model.messaging;

import model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: film42 on: 3/7/14.
 */
public class Chat extends Model {

    private List<Line> lines;

    public Chat() {

        lines = new ArrayList<>();
        lines.add(new Line());
        lines.add(new Line());
        lines.add(new Line());
        lines.add(new Line());

    }
}
