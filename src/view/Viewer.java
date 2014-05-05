package view;

import controller.Controller;
/**
 * PACKAGE_NAME
 * Created by: Elia Grady
 * ID : 300907060
 * Username:  gradyel
 */
public class Viewer implements Runnable {
    private Controller controller;
    private MyFrame myFrame;

    public Viewer(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        this.myFrame = new MyFrame(controller);
    }
}
