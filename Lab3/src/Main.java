import java.io.IOException;
import java.util.Scanner;

public class Main {
    private final Menu _view;
    private final User _user;
    private String _enteredPassword = "";

    Main(Menu view, User user) throws InterruptedException, IOException {
        this._user = user;
        this._view = view;
        _view.welcome(_user.getName());
        _view.userData(_user);
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
            attempts--;
            _view.invalidPassword(attempts);
        }
        sc.close();
        if (attempts <= 0) {
            throw new InterruptedException("-1");
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

    private void chooseCommand(int command) throws InterruptedException { // TODO
        switch (command) {
            case (1):
                //  TODO - operation 1
                break;
            case (2):
                //  TODO - operation 2
                break;
            case (3):
                throw new InterruptedException("0");
            default:
                _view.errorCommand(command);
        }
    }

    private void chooseSudoCommand(int command) throws InterruptedException{
        switch (command)  {
            case (1):
                //  TODO - operation 1
                break;

            case (2):
                //  TODO - operation 2
                break;

            case (3):
                //  TODO - launch auto tests
                break;

            case (4):
                //  TODO
                break;

            case (5):
                throw new InterruptedException("0");

            default:
                _view.errorCommand(command);
        }
    }

    public void run() throws InterruptedException {
        int command = enterCommand();
        if (_user.isSudoMode()) {
            _view.show();
            chooseCommand(command);
        } else {
            _view.sudoShow();
            chooseSudoCommand(command);
        }

    }

    public String getEnteredPassword() {
        return _enteredPassword;
    }
}
