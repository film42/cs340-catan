package model.facade;

import model.IModel;

/**
 * Created by qzcx on 3/7/14.
 */
public abstract class ModelFacade {
    private IModel m_model;
    public ModelFacade(IModel model) {
        m_model = model;
    }
}
