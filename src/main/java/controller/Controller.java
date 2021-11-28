package controller;

import app.App;
import app.CustomApp;
import model.User;

import java.util.logging.Logger;


public abstract class Controller {
    protected CustomApp app;
    protected User user;
    protected Logger logger;

    public void setApp(CustomApp app) throws Exception {
        System.out.println("setApp");
        this.app = app;
        this.user = App.getUser();
        this.logger = App.getLogger();
        setLang();
        initialize();
    }

    protected abstract void setLang();
    protected abstract void initialize() throws Exception;

}
