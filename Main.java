import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private final Menu _view;
    private final User _user;
    private String _enteredPassword = "";

    Main(Menu view, User user) throws InterruptedException {
        this._user = user;
        this._view = view;
        _view.welcome(user.getName());
        _view.welcome(user);
        // this._view.setLang(user.getLanguage())
        tryEnterPassword();
    }

    private void tryEnterPassword() throws InterruptedException {
        int attempts = 3;
        Scanner sc = new Scanner(System.in);
        while (attempts != 0) {
            String password = sc.nextLine();
            if (_user.checkPassword(password)) {
                this._enteredPassword =  password;
                break;
            }
            _view.invalidPassword(attempts);
            attempts--;
        }
        sc.close();
        if (attempts <= 0) {
            throw new InterruptedException();
        }

    }

    private int enterCommand() {
        Scanner sc = new Scanner(System.in);
        int result = 0;
        while(true) {
            if (sc.hasNext()) {
                result = sc.nextInt();
                break;
            } else {
                _view.errorCommand(result);
                sc.nextLine();
            }
        } sc.close();
        return result;
    }

    public Boolean run() {
        boolean result = false;
        boolean rootMode = _user.getMode().equals("root");
        _view.run(rootMode);
        int command = enterCommand();
        // TODO
        switch (command) {
            case (1):
                //  TODO - operation 1
                result = true;
                break;
            case (2):
                //  TODO - operation 2
               result = true;
               break;
        }

        if (!rootMode || result) {
            if (command == 3) {
                result = false;
            } else {
                _view.errorCommand(command);
            }
        } else {
            switch (command)  {
                case (3):
                    //  TODO - launch auto tests
                    break;

                case (4):
                    //  TODO
                    break;

                case (5):
                    //  TODO
                    result = false;
                    break;

                default:
                    _view.errorCommand(command);
                    break;
            }
        }
        return true;
    }
}
