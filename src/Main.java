import vehicle.Vehicle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner _scanner;
    private final Menu _view;
    private final User _user;
    private String _enteredPassword;
    private List<Vehicle> vehicles;

    Main(Menu view, User user) throws InterruptedException {
        this._scanner = new Scanner(System.in);
        this._user = user;
        this._view = view;
        _view.welcome(_user.getName());
        tryEnterPassword();
        _view.userData(_user);
    }

    private void tryEnterPassword() throws InterruptedException {
        int attempts = 3;
        while (attempts != 0) {
            String password = _scanner.nextLine();
            if (_user.checkPassword(password)) {
                this._enteredPassword =  password;
                _view.validPassword();
                break;
            } attempts--;
            _view.invalidPassword(attempts);
        }
        if (attempts <= 0)
            throw new InterruptedException("-1");
    }

    private int enterCommand() {
        int result = -1;
        while (true) {
            String buffer = _scanner.nextLine();
            try {
                result = Integer.parseInt(buffer);
                break;
            } catch (NumberFormatException e) {
                _view.errorCommand(buffer);
            }
        } return result;
    }

    private void chooseCommand(int command) throws InterruptedException {
        switch (command) {
            case (0):
                throw new InterruptedException("0");

            case (1):
                loadVehicle();
                _view.workWitList(vehicles);
                runBD();
                break;

            case (2):
                vehicles = new ArrayList<>();
                _view.workWitList(vehicles);
                runBD();
                break;

            default:
                if (_user.isSudoMode())
                    chooseSudoCommand(command);
                else
                    _view.errorCommand(String.valueOf(command));
        }
    }

    private void loadVehicle() {}
    private void runBD() {}
    private void runAutoTests() {}


    private void chooseSudoCommand(int command){
        switch (command)  {
            case (3):
                runAutoTests();
                break;

            default:
                _view.errorCommand(String.valueOf(command));
        }
    }

    public void run() throws InterruptedException {
        _view.show(_user.isSudoMode());
        int command = enterCommand();
        chooseCommand(command);
    }
}
