package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;


/**
 * view
 * Created by: Elia Grady
 * ID : 300907060
 * Username:  gradyel
 */
public class MyFrame extends JFrame {
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    private final Controller controller;
    private JPanel rootPanel;
    private JButton runButton;
    private JSpinner initialPopulation;
    private JProgressBar progressBar1;
    private JSlider mutationProbabilitySlider;
    private JPanel mutationProbability;
    private JTextField mutationProbabilityValue;
    private JScrollPane consoleScrollPane;
    private JTextArea consoleTextarea;
    private JSpinner elitism;
    private JSpinner numOfGenerations;

    private JButton setPart1Population;
    private JButton setPart1;
    private JButton setClustering;
    private JButton setOutput;

    public MyFrame(Controller controller) {
        super("GA-Ex1");
        setContentPane(rootPanel);
        consoleScrollPane.setMinimumSize(new Dimension(500,500));
        consoleScrollPane.setViewportView(consoleTextarea);

        //REDIRECTION
        redirectSystemStreams();

        Font font = new Font( "Monospaced", Font.PLAIN, 12 );
        consoleTextarea.setFont(font);

        this.controller = controller;
        initFrameSizeAndLocation();

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initButtons();
        setVisible(true);
    }


    private void updateTextArea(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                consoleTextarea.append(text);
                consoleTextarea.setCaretPosition(consoleTextarea.getDocument().getLength());

            }
        });
    }

    private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                updateTextArea(String.valueOf((char) b));
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                updateTextArea(new String(b, off, len));
            }

            @Override
            public void write(byte[] b) throws IOException {
                write(b, 0, b.length);
            }
        };

        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));
    }

    private void initButtons() {
        //Set the bounds of the initial population spinner (part one)
        initialPopulation.setModel(new SpinnerNumberModel(1500,2,10000,100));
        initialPopulation.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                controller.configPart1.setSizeOf_Population(Integer.parseInt(String.valueOf(initialPopulation.getValue())));
            }
        });
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.runPartOne();
            }
        });

        mutationProbabilitySlider.setPaintTicks(true);
        mutationProbabilitySlider.setPaintLabels(true);
        mutationProbabilitySlider.setLabelTable(mutationProbabilitySlider.createStandardLabels(25));
        mutationProbabilityValue.setText(String.valueOf(mutationProbabilitySlider.getValue())+"%");
        mutationProbabilitySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                mutationProbabilityValue.setText(String.valueOf(mutationProbabilitySlider.getValue())+"%");
                controller.configPart1.setMutationProbability(Integer.valueOf(String.valueOf(mutationProbabilitySlider.getValue())));
            }
        });


        elitism.setModel(new SpinnerNumberModel(0,0,100,1));
        elitism.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                controller.configPart1.setSizeOf_Population(Integer.parseInt(String.valueOf(elitism.getValue())));
            }
        });

        numOfGenerations.setModel(new SpinnerNumberModel(0,0,10000,10));
        numOfGenerations.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                controller.configPart1.setSizeOf_Population(Integer.parseInt(String.valueOf(numOfGenerations.getValue())));
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
        //FileFilter scnFilter = new FileNameExtensionFilter("Config file", "config");
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






