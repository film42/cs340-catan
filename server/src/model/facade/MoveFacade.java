package model.facade;

import model.IModel;

/**
 * Created by qzcx on 3/7/14.
 */
public class MoveFacade {

    private IModel m_model;

    public MoveFacade(IModel model) {
        m_model = model;
    }

    public String onMove(){
        //TODO decide whether we want one method per move in this function
        //TODO implement this method to use model
        return "Insert fake JSON here";
    }

}
