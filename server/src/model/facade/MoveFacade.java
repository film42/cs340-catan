package model.facade;

import comm.moves.*;
import comm.moves.base.Commandable;
import comm.moves.base.InvalidCommandException;
import model.Model;
import static comm.moves.base.Command.moveFromJson;
import java.io.IOException;

/**
 * Created by Jon George on 3/7/14.
 */
public class MoveFacade {

    final String SEND_CHAT = "/moves/sendChat";
    final String ROLL_NUMBER = "/moves/rollNumber";

    private Model m_model;

    public MoveFacade(Model model) {
        m_model = model;
    }

    public boolean onMove(String json, String url) {
        //TODO decide whether we want one method per move in this function
        //TODO implement this method to use model
        Commandable command = null;
        try {
            // ////////////////////////////////// //
            // Switching on all possible commands //
            // ////////////////////////////////// //
            switch (url) {
                case SEND_CHAT:
                    command = moveFromJson(json, SendChat.class); break;
                case ROLL_NUMBER:
                    command = moveFromJson(json, RollNumber.class); break;
                default:
                    return false;
            }

            // Run this command then return OK
            command.execute();
            return true;


        // ERRORS
        } catch (IOException e) {
            // Server Error
            return false;
        } catch (InvalidCommandException e) {
            // Syntax Correct, but Error
            return false;
        }
    }

}
