package view;

import java.util.Properties;

public abstract class BaseMenu{
    protected static Properties langData;
    protected String title = this.getClass().getName().split("\\.")[this.getClass().getName().split("\\.").length - 1].split("Menu")[0] + " Menu";

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

    public abstract void show();

    public void showTitle() {
        display_ln("");
        display_ln("|---------------------------");
        display_ln("|\t\t" + title);
//        display_ln("|---------------------------");
//        display_ln("|\tPress ESC to back or exit");
        display_ln("|---------------------------");
    }

//    protected void clearStack() { // TODO
//        try{
//            String operatingSystem = System.getProperty("os.name"); //Check the current operating system
//
//            if(operatingSystem.contains("Windows")){
//                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
//                Process startProcess = pb.inheritIO().start();
//                startProcess.waitFor();
//            } else {
//                ProcessBuilder pb = new ProcessBuilder("clear");
//                Process startProcess = pb.inheritIO().start();
//
//                startProcess.waitFor();
//            }
//        }catch(Exception e){
//            System.out.println(e);
//        }
//    }

}
