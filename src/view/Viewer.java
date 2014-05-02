package view;

import controller.Controller;
import javax.swing.*;

/**
 * Created by Elia on 02/05/2014.
 */
public class Viewer {
    private final MyFrame myFrame;
    private Controller controller;
    private JButton runButton;
    private JPanel panel1;
    private JProgressBar progressBar1;

    public Viewer(Controller controller) {
        this.myFrame = new MyFrame(controller);
        this.controller = controller;
    }
}
