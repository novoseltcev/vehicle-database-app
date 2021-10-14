import utils.AutoTest;
import vehicle.Vehicle;

import java.util.List;
import java.util.Properties;

public class Menu {
    private final Properties _langData;
    public Menu(Properties langData) {
        _langData = langData;
    }

    private void clearStack() { // TODO
        try{
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system

            if(operatingSystem.contains("Windows")){
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    protected void notify(String msg) {
        System.out.print(msg);
    }
    protected void notify_ln(String msg) {
        notify(msg + "\n");
    }
    public void error(String msg) {
        notify_ln(msg);
    }

    public void welcome(String username) {
        String msg = String.format(_langData.getProperty("WELCOME"), username);
        notify_ln(msg);
        notify( _langData.getProperty("INPUT_PASS") );
    }

    public void userData(User user) {
        notify_ln( user.toString() );
    }

    public void errorCommand(String command) {
        String msg = String.format(_langData.getProperty("INVALID_CMD"), command);
        error(msg);
    }

    public void invalidPassword(int attempts) {
        String dopMsg = (attempts > 0) ? String.format(_langData.getProperty("LOST_ATTEMPTS"), attempts) : "";
        String msg = _langData.getProperty("INVALID_PASS") + dopMsg;
        error(msg);
    }

    public void validPassword() {
        notify_ln(_langData.getProperty("VALID_PASS"));
    }

    public void show(boolean isSudo) {
        String[] msgList =new String[] {
            _langData.getProperty("EXIT_CMD"),
            _langData.getProperty("LOAD_CMD"),
            _langData.getProperty("NEW_CMD"),
            _langData.getProperty("TESTS_CMD"),
        };
        notify_ln("");
        for (int i = 0; i <msgList.length; ++i) {
            if (i > 2 && !isSudo)
                break;
            notify_ln(String.format(msgList[i], i));
        }
        notify(_langData.getProperty("ENTER_CMD"));
    }

    public void workWitList(List<Vehicle> vehicles) {
        clearStack();
        String[] msgList =new String[] {
                _langData.getProperty("SHOW_CMD"),
                _langData.getProperty("ADD_CMD"),
                _langData.getProperty("EDIT_CMD"),
                _langData.getProperty("REMOVE_CMD"),
                _langData.getProperty("SAVE_CMD"),
                _langData.getProperty("BACK_CMD"),
        };
        notify_ln("");
        for (int i = 0; i <msgList.length; ++i)
            notify_ln(String.format(msgList[i], i));

        notify(_langData.getProperty("ENTER_CMD"));
    }

    public void showTests(List<AutoTest> autoTests) {
        clearStack();
        notify_ln(_langData.getProperty("AUTOTEST_TITLE"));
        for (AutoTest autoTest: autoTests) {
            notify_ln("");
            notify_ln(autoTest.toString());
        }
    }
}
