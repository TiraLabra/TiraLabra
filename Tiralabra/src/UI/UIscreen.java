/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

/**
 *
 * @author virta
 */
public class UIscreen implements Runnable {
    
    private JFrame frame;
    private JRadioButton fileMethodSingle;
    private JRadioButton fileMethodRecursive;
    private JButton fileChooser;
    private JLabel pathView;
    private JRadioButton byteWidth4;
    private JRadioButton byteWidth8;
    private JRadioButton byteWidth12;
    private JRadioButton byteWidth16;
    private JButton compressButton;
    private JLabel compressionStatusWindow;
    private JButton decompressSelectionButton;
    private JLabel decompressSelectionLabel;
    private JLabel decompressStatus;
    private JButton decompressButton;
    private JButton interruptCompression;
    private JButton interruptDecompression;
    
    public UIscreen(){
        
    }
    
    @Override
    public void run(){
        this.frame = new JFrame("vZipper");
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setPreferredSize(new Dimension(500, 500));
        
        createComponents(frame.getContentPane());
        addActionListenerToComponents();
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void addActionListenerToComponents(){
        UIscreenListener listener = new UIscreenListener(frame, fileMethodSingle, fileMethodRecursive, fileChooser, pathView, 
                byteWidth4, byteWidth8, byteWidth12, byteWidth16, compressButton, compressionStatusWindow, 
                decompressSelectionButton, decompressSelectionLabel, decompressStatus, decompressButton, interruptCompression, interruptDecompression);
        
        fileChooser.addActionListener(listener);
        compressButton.addActionListener(listener);
        decompressSelectionButton.addActionListener(listener);;
        decompressButton.addActionListener(listener);
        interruptCompression.addActionListener(listener);
        interruptDecompression.addActionListener(listener);
    }
    
    private void createComponents(Container container){
        JTabbedPane tabPane = new JTabbedPane();
        
        createCompressionTab(tabPane);
        
        createDecompressionTab(tabPane);
        
        container.add(tabPane);
        
    }

    private void createCompressionTab(JTabbedPane tabPane) {
        JComponent baseComponent = new JPanel();
        LayoutManager baseComponentLayout = new BoxLayout(baseComponent, BoxLayout.Y_AXIS);
        baseComponent.setLayout(baseComponentLayout);
        
        JComponent fileMethod = createFileMethodChooser();
        baseComponent.add(fileMethod);
        JComponent options = createOptionsChooser();
        baseComponent.add(options);
        
        compressButton = new JButton("Compress");
        baseComponent.add(compressButton);
        
        interruptCompression = new JButton("Stop");
        interruptCompression.setEnabled(false);
        baseComponent.add(interruptCompression);
        
        compressionStatusWindow = new JLabel("No operations");
        baseComponent.add(compressionStatusWindow);
        
        tabPane.addTab("Compress", baseComponent);
        
    }
    
    private JComponent createOptionsChooser(){
        JComponent options = new JPanel();
        LayoutManager man = new BoxLayout(options, BoxLayout.Y_AXIS);
        options.setLayout(man);
        
        JLabel optionsLabel = new JLabel("Select comression options");
        options.add(optionsLabel);
        
        createByteWidthChooser(options);
        
        return options;
    }

    private void createDecompressionTab(JTabbedPane tabPane) {
        JComponent baseComponent = new JPanel();
        LayoutManager layout = new BoxLayout(baseComponent, BoxLayout.Y_AXIS);
        baseComponent.setLayout(layout);
        
        decompressSelectionButton = new JButton("Select");
        baseComponent.add(decompressSelectionButton);
        decompressSelectionLabel = new JLabel("No file selected");
        baseComponent.add(decompressSelectionLabel);
        
        decompressButton = new JButton("Decompress");
        baseComponent.add(decompressButton);
        interruptDecompression = new JButton("Stop");
        interruptDecompression.setEnabled(false);
        baseComponent.add(interruptDecompression);
        
        decompressStatus = new JLabel("No operations");
        baseComponent.add(decompressStatus);
        
        tabPane.add("Decompress", baseComponent);
    }

    private JComponent createFileMethodChooser() {
        JComponent fileMethod = new JPanel();
        LayoutManager twoByTwo = new BoxLayout(fileMethod, BoxLayout.Y_AXIS);
        fileMethod.setLayout(twoByTwo);
        
        createfileMethodRadioButtons();
        
        fileMethod.add(fileMethodSingle);
        fileMethod.add(fileMethodRecursive);
        
        fileChooser = new JButton("Select");
        fileMethod.add(fileChooser);
        pathView = new JLabel("No file chosen");
        fileMethod.add(pathView);
        
        return fileMethod;
    }

    private void createfileMethodRadioButtons() {
        ButtonGroup fileMethodChooser = new ButtonGroup();
        fileMethodSingle = new JRadioButton("Single file");
        fileMethodRecursive = new JRadioButton("Folder");
        
        fileMethodChooser.add(fileMethodSingle);
        fileMethodChooser.add(fileMethodRecursive);
//        fileMethodRecursive.setEnabled(false);      //disabled until recursive method is coded
    }

    private void createByteWidthChooser(JComponent options) {
        JLabel byteWidthChooser = new JLabel("Select bytewidth:");
        ButtonGroup byteWidthGroup = new ButtonGroup();
        byteWidth4 = new JRadioButton("4 bytes");
        byteWidth8 = new JRadioButton("8 bytes");
        byteWidth12 = new JRadioButton("12 bytes");
        byteWidth16 = new JRadioButton("16 bytes");
        byteWidthGroup.add(byteWidth4);
        byteWidthGroup.add(byteWidth8);
        byteWidthGroup.add(byteWidth12);
        byteWidthGroup.add(byteWidth16);
        options.add(byteWidthChooser);
        options.add(byteWidth4);
        options.add(byteWidth8);
        options.add(byteWidth12);
        options.add(byteWidth16);
    }
    
}
