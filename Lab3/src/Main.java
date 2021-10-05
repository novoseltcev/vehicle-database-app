import java.util.Scanner;

public class Main {
    private final Menu _view;
    private final User _user;
    private String _enteredPassword = "";

    Main(Menu view, User user) throws InterruptedException {
        this._view = view;
        this._user = user;
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
            _view.error("Invalid Password. Lost attempts - " + attempts);
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
        // TODO
        sc.close();
        return result;
    }

    public Boolean run() {
        boolean rootMode = _user.getMode().equals("root");
        _view.run(rootMode);
        int command = enterCommand();
        // TODO
        switch (command) {
            case (1):
                //  TODO - operation 1
                return true;
            case (2):
                //  TODO - operation 2
                return true;
        }

        if (!rootMode) {
            if (command == 3) {
                return false;
            }
            _view.error("Invalid function - " + command);
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
                    return false;

                default:
                    _view.error("Invalid function - " + command);
                    break;
            }
        }
        return true;
    }
}
