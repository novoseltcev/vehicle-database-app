package app.controller;

import app.EntryPoint;
import model.User;

import java.util.logging.Logger;


public abstract class Base {
    protected EntryPoint app;
    protected User user;
    protected Logger logger;


    public void setApp(EntryPoint app) {
        System.out.println("setApp");
        this.app = app;
        this.user = app.getUser();
        this.logger = app.getLogger();
        setLang();
        initialize();
    }

    protected abstract void setLang();
    protected abstract void initialize();

}
