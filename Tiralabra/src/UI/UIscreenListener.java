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
    /**
     * RadioButton for single file compression method.
     */
    private JRadioButton fileMethodSingle;
    
    /**
     * Radiobutton for multiple file, ie recursive, compression method.
     */
    private JRadioButton fileMethodRecursive;
    
    /**
     * Button for opening the filechooser.
     */
    private JButton fileChooser;
    
    /**
     * Label for showing the path to currently compressed file in decompression view.
     */
    private JLabel pathToCompressFile;
    
    /**
     * Radiobutton for selecting bytewidth 4.
     */
    private JRadioButton byteWidth4;
    
    /**
     * Radiobutton for selecting bytewidth 8.
     */
    private JRadioButton byteWidth8;
    
    /**
     * Radiobutton for selecting bytewidth 12.
     */
    private JRadioButton byteWidth12;
    
    /**
     * RadioButton for selecting bytewidth 16.
     */
    private JRadioButton byteWidth16;
    
    /**
     * Button for starting the compression.
     */
    private JButton compressButton;
    
    /**
     * Text area for displaying the status of compression.
     */
    private JTextArea compressionStatusWindow;
    
    /**
     * Button for opening the file chooser in decompression view.
     */
    private JButton decompressSelectionButton;
    
    /**
     * Label for showing the path to selected file for decompression.
     */
    private JLabel decompressSelectionLabel;
    
    /**
     * Text area for displaying the status of decompression.
     */
    private JTextArea decompressStatusWindow;
    
    /**
     * Button for starting the decompression.
     */
    private JButton decompressButton;
    
    /**
     * File that is selected for compression, not used at the moment.
     */
    private File selectedSingleFileForCompression;
    
    /**
     * Button for interrupting the compression.
     */
    private JButton interruptCompression;
    
    /**
     * Button for interrupting the decompression.
     */
    private JButton interruptDecompression;
    
    /**
     * File that is selected for decompression, not used at the moment.
     */
    private File selectedFileForDecompression;
    
    /**
     * Path as string to the file selected for decompression.
     */
    private String pathToFileForDecompression;
    
    /**
     * Path as string to the single file delected for compression.
     */
    private String pathToSingleFileForCompression;
    
    /**
     * The decompressor used in decompression.
     */
    private Decompressor decompressor;
    
    /**
     * The statusupdater for updating the status of decompression.
     */
    private StatusUpdater decompressorStatusUpdater;
    
    /**
     * The compressor used in compression.
     */
    private Compressor compressor;
    
    /**
     * The status updater for updating the status of compression.
     */
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

    /**
     * Queries the source from the buttons and passes the functionality on to the appropriate method.
     * @param e 
     */
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

    /**
     * Opens the file chooser for choosing a file for decompression.
     * @throws IOException if the file is inaccessible.
     */
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

    /**
     * Opens the file chooser for choosing a folder for compression, does not work properly yet.
     */
    private void chooseFolderForCompression() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int validity = chooser.showOpenDialog(frame);

        if (validity == JFileChooser.APPROVE_OPTION) {
            pathToCompressFile.setText("approved");         //make it work
        }
    }

    /**
     * Opens the file chooser for selecting a single file for compression.
     * @throws IOException 
     */
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

    /**
     * Queries the path and bytewidth buttons for a oath and bytewidth, calls the compression method to start compression.
     */
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

    /**
     * Creates a new compressor and a statusupdater, envelopes them in a thread and starts them.
     * @param bytewidth 
     */
    private void startSinlgeFileCompression(int bytewidth) {

        toggleCompressorButtons(false);

        compressor = new Compressor(pathToSingleFileForCompression, bytewidth);
        Thread compressorThread = new Thread(compressor);

        compressorStatusUpdater = new StatusUpdater(compressor, null, compressionStatusWindow, null, true, 1, this);
        Thread compressorUpdaterThread = new Thread(compressorStatusUpdater);

        compressionStatusWindow.setText("");

        compressorThread.start();
        while (compressor.getEncoder() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
//                Logger.getLogger(UIscreenListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        compressorUpdaterThread.start();

    }

    /**
     * Calls the compressor interrupt method to stop current operations, does not work properly.
     */
    private void interruptCompressionAction() {
        toggleCompressorButtons(true);
        compressor.interrupt();
    }

    /**
     * Calls the decompressor interrupt method to stop current operations, doe not work properly.
     */
    private void interruptDecompressionAction() {
        toggleDecompressorButtons(true);
        decompressor.interrupt();
    }

    /**
     * Creates a new decompressor and statusupdater, envelopes them into threads and starts them.
     */
    private void startDecompression() {
        if (selectedFileForDecompression != null) {
            toggleDecompressorButtons(false);

            decompressor = new Decompressor(pathToFileForDecompression);
            Thread decompressorThread = new Thread(decompressor);

            decompressorStatusUpdater = new StatusUpdater(null, decompressor, null, decompressStatusWindow, false, 1, this);
            Thread updaterThread = new Thread(decompressorStatusUpdater);

            decompressorThread.start();

            while (decompressor.getDecoder() == null) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
//                Logger.getLogger(UIscreenListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            updaterThread.start();
        } else {
            decompressStatusWindow.setText("Select a file for decompression");
        }

    }

    /**
     * Toggles the buttons of compressor view on or off according to the given boolean.
     * @param isEnabled 
     */
    public void toggleCompressorButtons(boolean isEnabled) {
        byteWidth4.setEnabled(isEnabled);
        byteWidth8.setEnabled(isEnabled);
        byteWidth12.setEnabled(isEnabled);
        byteWidth16.setEnabled(isEnabled);
        compressButton.setEnabled(isEnabled);
        interruptCompression.setEnabled(!isEnabled);
        fileChooser.setEnabled(isEnabled);
    }

    /**
     * Toggles the buttons of decompressor view on or off according to the given boolean.
     * @param isEnabled 
     */
    public void toggleDecompressorButtons(boolean isEnabled) {
        decompressButton.setEnabled(isEnabled);
        decompressSelectionButton.setEnabled(isEnabled);
        interruptDecompression.setEnabled(!isEnabled);
    }
}
