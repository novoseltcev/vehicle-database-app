public class Menu {
    public void notify(String msg) {  // TODO
        System.out.print(msg + "\n");
    } 
    public void error(String msg) {  // TODO
        notify(msg);
    }  
    public void welcome(String username) {  //TODO
        notify("Welcome " + username + "!");
    }
    
    public void welcome(User user) {  //TODO
        notify(user.toString());
    }
    
    public void run(boolean sudoMode) {  // TODO
    }
    
    public void errorCommand(int command) {  // TODO
        error("Invalid function - " + command);
    }
    
    public void invalidPassword(int attempts) {  // TODO
        String dopMsg = (attempts > 0) ? "Lost attempts - " + attempts : "";
        error("Invalid Password." + dopMsg);
    }    
}
