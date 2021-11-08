package app.controller;

import app.EntryPoint;
import model.User;

import java.util.HashMap;

public abstract class Base {
    protected EntryPoint app;
    protected User user = EntryPoint.user;
    protected HashMap<String, String> langData = EntryPoint.langData;

    public void setApp(EntryPoint app) {
        this.app = app;
        setLang();
    }

    protected abstract void setLang();
}
