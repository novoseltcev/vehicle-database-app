package view;

import java.util.Properties;

public class BaseMenu {
    protected static Properties langData;
    protected String title;
    BaseMenu() {
        title = BaseMenu.class.getName();
    }

    protected void display(String msg) {
        System.out.print(msg);
    }

    protected void display_ln(String msg) {
        display(msg + "\n");
    }

    protected void error(String msg) {
        display_ln(msg);
    }

    public void errorCommand(String command) {
        String msg = String.format(langData.getProperty("INVALID_CMD"), command);
        error(msg);
    }

    protected void clearStack() { // TODO
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

}
