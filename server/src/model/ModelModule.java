package model;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Module;
import model.users.UserImpl;
import modelInterfaces.users.User;

public class ModelModule extends AbstractModule {

//    @Override
//	public void configure(Binder arg0) {
//
//
//	}

    @Override
    protected void configure() {
        bind(User.class).to(UserImpl.class);
    }

}
