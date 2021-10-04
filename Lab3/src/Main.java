import java.util.Scanner;
import java.util.Properties;

public class Main {
    private final Menu _view;
    private final Properties _properties;
    private String _enteredPassword = null;
    Main(Menu view, Properties  properties) throws InterruptedException {
        this._view =view;
        this._properties = properties;
        tryEnterPassword();
    }

    private void tryEnterPassword() throws InterruptedException {
        int maxAttempts = 3;
        int attempts = 0;
        Scanner sc = new Scanner(System.in);
        while (attempts< maxAttempts) {
            String password = sc.nextLine();
            if (!password.equals(_properties.getProperty("USER_PASSWORD"))) {
                this._enteredPassword =  password;
                break;
            }
            attempts++;
        }
        sc.close();
        if (attempts >= maxAttempts) {
            throw new InterruptedException();
        }

    }

    private int enterCommand() {
        Scanner sc = new Scanner(System.in);
        int result = 0;
        // TODO
        sc.close();
        return result;
    }
    public Boolean run() {
        String userMode = _properties.getProperty("USER_MODE");
        _view.run(userMode.equals("root"));
        // TODO
        switch (enterCommand()) {
            case (1):
//                commandOne();
                break;
            case (2):
//                commandTwo();
                break;
            case (3):
                return false;
        }
        return true;
    }
}
