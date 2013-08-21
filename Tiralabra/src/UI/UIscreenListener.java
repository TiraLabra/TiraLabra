/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Encoding.Compressor;
import Encoding.Decompressor;
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
import javax.swing.JTextArea;
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
    private JTextArea compressionStatusWindow;
    private JButton decompressSelectionButton;
    private JLabel decompressSelectionLabel;
    private JTextArea decompressStatusWindow;
    private JButton decompressButton;
    private File selectedSingleFileForCompression;
    private JButton interruptCompression;
    private JButton interruptDecompression;
    private File selectedFileForDecompression;
    private String pathToFileForDecompression;
    private String pathToSingleFileForCompression;
    private Decompressor decompressor;
    private StatusUpdater decompressorStatusUpdater;
    private Compressor compressor;
    private StatusUpdater compressorStatusUpdater;

    public UIscreenListener(JFrame frame, JRadioButton fileMethodSingle, JRadioButton fileMethodRecursive,
            JButton fileChooser, JLabel pathView, JRadioButton byteWidth4, JRadioButton byteWidth8,
            JRadioButton byteWidth12, JRadioButton byteWidth16, JButton compressButton, JTextArea compressionStatusWindow,
            JButton decopressSelectionButton, JLabel decompressSelectionLabel, JTextArea decompressStatus, JButton decompressButton,
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
        this.decompressStatusWindow = decompressStatus;
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

    private void chooseFileForDecompression() throws IOException {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("vZipped files", "vZip"));
        int validity = chooser.showOpenDialog(frame);

        if (validity == JFileChooser.APPROVE_OPTION) {
            pathToFileForDecompression = chooser.getSelectedFile().getCanonicalPath();
            decompressSelectionLabel.setText(selectedFileForDecompression.getCanonicalPath());
        } else {
            decompressSelectionLabel.setText("Invalid file");
        }
    }

    private void chooseFolderForCompression() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int validity = chooser.showOpenDialog(frame);

        if (validity == JFileChooser.APPROVE_OPTION) {
            pathToCompressFile.setText("approved");         //make it work
        }
    }

    private void chooseSingleFileForCompression() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        int validity = fileChooser.showOpenDialog(frame);

        if (validity == JFileChooser.APPROVE_OPTION) {
//            selectedSingleFileForCompression = fileChooser.getSelectedFile();
            pathToSingleFileForCompression = fileChooser.getSelectedFile().getCanonicalPath();
            pathToCompressFile.setText(pathToSingleFileForCompression);
        } else {
            pathToCompressFile.setText("Invalid file");
        }
    }

    private void compressFile() {

        if (pathToSingleFileForCompression == null) {
            compressionStatusWindow.setText("Select file for compression");

        } else if (byteWidth4.isSelected()) {
            startSinlgeFileCompression(4);

        } else if (byteWidth8.isSelected()) {
            startSinlgeFileCompression(8);

        } else if (byteWidth12.isSelected()) {
            startSinlgeFileCompression(12);

        } else if (byteWidth16.isSelected()) {
            startSinlgeFileCompression(16);

        } else {
            compressionStatusWindow.setText("Select byte-width for compression");
        }
    }

    private void startSinlgeFileCompression(int bytewidth) {

        toggleCompressorButtons(false);

        compressor = new Compressor(pathToSingleFileForCompression, bytewidth);
        Thread compressorThread = new Thread(compressor);

        compressorStatusUpdater = new StatusUpdater(compressor, null, compressionStatusWindow, null, true, 1);
        Thread compressorUpdaterThread = new Thread(compressorStatusUpdater);

        compressionStatusWindow.setText("");
        
        compressorThread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            
        }
        compressorUpdaterThread.start();

    }

    private void interruptCompressionAction() {
        toggleCompressorButtons(true);
        compressor.interrupt();
    }

    private void interruptDecompressionAction() {
        toggleDecompressorButtons(true);

        decompressor.interrupt();
    }

    private void startDecompression() {
        if (selectedFileForDecompression != null) {
            toggleDecompressorButtons(false);

            decompressor = new Decompressor(pathToFileForDecompression);
            Thread decompressorThread = new Thread(decompressor);

            decompressorStatusUpdater = new StatusUpdater(null, decompressor, null, decompressStatusWindow, false, 1);
            Thread updaterThread = new Thread(decompressorStatusUpdater);

            decompressorThread.start();
            updaterThread.start();
        } else {
            decompressStatusWindow.setText("Select a file for decompression");
        }

    }

    private void toggleCompressorButtons(boolean isEnabled) {
        byteWidth4.setEnabled(isEnabled);
        byteWidth8.setEnabled(isEnabled);
        byteWidth12.setEnabled(isEnabled);
        byteWidth16.setEnabled(isEnabled);
        compressButton.setEnabled(isEnabled);
        interruptCompression.setEnabled(!isEnabled);
        fileChooser.setEnabled(isEnabled);
    }

    private void toggleDecompressorButtons(boolean isEnabled) {
        decompressButton.setEnabled(isEnabled);
        decompressSelectionButton.setEnabled(isEnabled);
        interruptDecompression.setEnabled(!isEnabled);
    }
}
