import java.util.Properties;

public class Menu {
    private final Properties _langData;
    public Menu(Properties langData) {
        _langData = langData;
    }

    public void notify(String msg) {
        System.out.println(msg);
    }
    public void error(String msg) {
        notify(msg);
    }
    public void welcome(String username) {
        notify(_langData.get("WELCOME") + " " + username + "!");
    }

    public void userData(User user) {
        notify(user.toString());
    }

    public void errorCommand(int command) {
        error(_langData.get("INVALID_CMD") + " - " + command);
    }

    public void invalidPassword(int attempts) {
        String dopMsg = (attempts > 0) ? ". " + _langData.get("LOST_ATTEMPTS") + " - " + attempts : "";
        error(_langData.get("INVALID_PASS") + dopMsg);
    }

    public void show() {  // TODO

    }

    public void sudoShow() {  // TODO

    }
}
