package controller;

import app.App;
import model.User;

import java.util.logging.Logger;


public abstract class Controller {
    protected App app;
    protected User user;
    protected Logger logger;

    public void setApp(App app) throws Exception {
        System.out.println("setApp");
        this.app = app;
        this.user = app.getUser();
        this.logger = app.getLogger();
        setLang();
        initialize();
    }

    protected abstract void setLang();
    protected abstract void initialize() throws Exception;

}
