/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author virta
 */
public class UIscreenListener implements ActionListener {

    private JFrame frame;
    private JRadioButton fileMethodSingle;
    private JRadioButton fileMethodRecursive;
    private JButton fileChooser;
    private JLabel pathToCompressFile;
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
    private File selectedSingleFileForCompression;
    private JButton interruptCompression;
    private JButton interruptDecompression;
    private File selectedFileForDecompression;
    private Path pathToFileForDecompression;

    public UIscreenListener(JFrame frame, JRadioButton fileMethodSingle, JRadioButton fileMethodRecursive,
            JButton fileChooser, JLabel pathView, JRadioButton byteWidth4, JRadioButton byteWidth8,
            JRadioButton byteWidth12, JRadioButton byteWidth16, JButton compressButton, JLabel compressionStatusWindow,
            JButton decopressSelectionButton, JLabel decompressSelectionLabel, JLabel decompressStatus, JButton decompressButton,
            JButton interruptCompression, JButton interruptDecompression) {

        this.frame = frame;
        this.fileMethodSingle = fileMethodSingle;
        this.fileMethodRecursive = fileMethodRecursive;
        this.fileChooser = fileChooser;
        this.pathToCompressFile = pathView;
        this.byteWidth4 = byteWidth4;
        this.byteWidth8 = byteWidth8;
        this.byteWidth12 = byteWidth12;
        this.byteWidth16 = byteWidth16;
        this.compressButton = compressButton;
        this.compressionStatusWindow = compressionStatusWindow;
        this.decompressSelectionButton = decopressSelectionButton;
        this.decompressSelectionLabel = decompressSelectionLabel;
        this.decompressStatus = decompressStatus;
        this.decompressButton = decompressButton;
        this.interruptCompression = interruptCompression;
        this.interruptDecompression = interruptDecompression;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(compressButton)) {
            compressFile();

        } else if (source.equals(fileChooser)) {
            try {
                if (fileMethodSingle.isSelected()) {
                    chooseSingleFileForCompression();
                } else if (fileMethodRecursive.isSelected()) {
                    chooseFolderForCompression();
                } else {
                    pathToCompressFile.setText("Choose type of file method");
                }
            } catch (IOException ex) {
                pathToCompressFile.setText("Inaccessible file");
            }

        } else if (source.equals(decompressButton)) {
            startDecompression();
            
        } else if (source.equals(decompressSelectionButton)) {
            try {
                chooseFileForDecompression();
            } catch (IOException ex) {
                decompressSelectionLabel.setText("Inaccessible file");
            }
            
        } else if (source.equals(interruptCompression)) {
            interruptCompressionAction();

        } else if (source.equals(interruptDecompression)) {
            interruptDecompressionAction();
        }
    }
    
    private void chooseFileForDecompression() throws IOException{
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("vZipped files", "vZip"));
        int validity = chooser.showOpenDialog(frame);
        
        if (validity == JFileChooser.APPROVE_OPTION){
            selectedFileForDecompression = chooser.getSelectedFile();
            pathToFileForDecompression = selectedFileForDecompression.toPath();
            decompressSelectionLabel.setText(selectedFileForDecompression.getCanonicalPath());
        } else {
            decompressSelectionLabel.setText("Invalid file");
        }
    }
    
    private void chooseFolderForCompression(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int validity = chooser.showOpenDialog(frame);
        
        if (validity == JFileChooser.APPROVE_OPTION){
            pathToCompressFile.setText("approved");         //make it work
        }
    }

    private void chooseSingleFileForCompression() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        int validity = fileChooser.showOpenDialog(frame);

        if (validity == JFileChooser.APPROVE_OPTION) {
            selectedSingleFileForCompression = fileChooser.getSelectedFile();
            pathToCompressFile.setText(selectedSingleFileForCompression.getCanonicalPath());
        } else {
            pathToCompressFile.setText("Invalid file");
        }
    }

    private void compressFile() {
        if (byteWidth4.isSelected()) {
            startSinlgeFileCompression(4);

        } else if (byteWidth8.isSelected()) {
            startSinlgeFileCompression(8);

        } else if (byteWidth12.isSelected()) {
            startSinlgeFileCompression(12);

        } else if (byteWidth16.isSelected()) {
            startSinlgeFileCompression(16);

        } else if (selectedSingleFileForCompression == null) {
            compressionStatusWindow.setText("Select file for compression");

        } else {
            compressionStatusWindow.setText("Select byte-width for compression");
        }
    }

    private void startSinlgeFileCompression(int bytewidth) {
        byteWidth4.setEnabled(false);
        byteWidth8.setEnabled(false);
        byteWidth12.setEnabled(false);
        byteWidth16.setEnabled(false);
        compressButton.setEnabled(false);
        interruptCompression.setEnabled(true);
        fileChooser.setEnabled(false);
        
        //start compression with parameters
    }

    private void interruptCompressionAction() {
        byteWidth4.setEnabled(true);
        byteWidth8.setEnabled(true);
        byteWidth12.setEnabled(true);
        byteWidth16.setEnabled(true);
        compressButton.setEnabled(true);
        interruptCompression.setEnabled(false);
        fileChooser.setEnabled(true);
        
        //interrupt the algorithm
    }

    private void interruptDecompressionAction() {
        decompressButton.setEnabled(true);
        decompressSelectionButton.setEnabled(true);
        interruptDecompression.setEnabled(false);
        
        //interrupt the algorithm
    }
    
    private void startDecompression(){
        if (selectedFileForDecompression != null) {
            decompressButton.setEnabled(false);
            decompressSelectionButton.setEnabled(false);
            interruptDecompression.setEnabled(true);
            
            //start decompression
        } else {
            decompressStatus.setText("Select a file for decompression");
        }
        
    }
}
