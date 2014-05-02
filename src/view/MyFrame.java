package view;
import controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


/**
 * view
 * Created by: Elia Grady
 * ID : 300907060
 * Username:  gradyel
 */
public class MyFrame extends JFrame {
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    private static final String TITLE = "Evolutionary Algorithms - Ex1";
    private final Controller controller;
    private JPanel rootPanel;

    private JButton setTrain;
    private JButton setTest;
    private JButton setClustering;
    private JButton setOutput;
    private JButton run;

    public MyFrame(Controller controller) {
        super(TITLE);
        setContentPane(rootPanel);
        this.controller = controller;
        initFrameSizeAndLocation();

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        initButtons();

    }

    private void initButtons() {
        setTrain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String folder = loadFileDialog();
                controller.setFolder(Controller.Folder.TRAIN, folder);
            }
        });

        setTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String folder = loadFileDialog();
                controller.setFolder(Controller.Folder.TEST, folder);
            }
        });

        setClustering.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String folder = loadFileDialog();
                controller.setFolder(Controller.Folder.CLUSTER, folder);
            }
        });

        setOutput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String folder = loadFileDialog();
                controller.setFolder(Controller.Config.OUTPUT, folder);
            }
        });





    }

    public void initFrameSizeAndLocation() {
        //Sets the location
        setSize(MyFrame.WIDTH, MyFrame.HEIGHT);
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) screenDim.getWidth() / 2 - (MyFrame.WIDTH / 2), (int) screenDim.getHeight() / 2 - (MyFrame.HEIGHT / 2));
        //pack();
    }

    /**
     * Shows a file selection popup to get the filepath
     * @return file path to load, or null if loading aborted
     */
    private String loadFileDialog() {
        JFileChooser fileChooser = new JFileChooser(new File("").getAbsolutePath());
        String fileName = "";
        String dirName = "";
        File myFile = null;
        int rVal;

        // filter allowed extensions
        //FileFilter scnFilter = new FileNameExtensionFilter("SCN file", "scn");
        //FileFilter viwFilter = new FileNameExtensionFilter("VIW file", "viw");
        //fileChooser.addChoosableFileFilter(scnFilter);
        //fileChooser.addChoosableFileFilter(viwFilter);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        // load a file
        rVal = fileChooser.showOpenDialog(fileChooser);

        if (rVal == JFileChooser.APPROVE_OPTION) {
            // gets directory path
            myFile = fileChooser.getCurrentDirectory();
            dirName = myFile.toString();
            fileName = fileChooser.getSelectedFile().getName();
        } else if (rVal == JFileChooser.CANCEL_OPTION) {
            return null;
        }
        String filePath = dirName + "\\" + fileName;
        return filePath;
    }
}
