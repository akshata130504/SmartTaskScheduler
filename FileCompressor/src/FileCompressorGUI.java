import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.zip.*;

public class FileCompressorGUI {

    private JFrame frame;

    public FileCompressorGUI() {

        frame = new JFrame("File Compressor Tool");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JButton compressButton = new JButton("Compress File");
        JButton decompressButton = new JButton("Decompress File");

        frame.add(compressButton);
        frame.add(decompressButton);

        compressButton.addActionListener(e -> compressFile());
        decompressButton.addActionListener(e -> decompressFile());

        frame.setVisible(true);
    }

    private void compressFile() {

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {

            File inputFile = fileChooser.getSelectedFile();
            File outputFile = new File(inputFile.getAbsolutePath() + ".zip");

            try {

                FileInputStream fis = new FileInputStream(inputFile);
                FileOutputStream fos = new FileOutputStream(outputFile);
                ZipOutputStream zos = new ZipOutputStream(fos);

                ZipEntry zipEntry = new ZipEntry(inputFile.getName());
                zos.putNextEntry(zipEntry);

                byte[] buffer = new byte[1024];
                int length;

                while ((length = fis.read(buffer)) >= 0) {
                    zos.write(buffer, 0, length);
                }

                zos.close();
                fis.close();
                fos.close();

                JOptionPane.showMessageDialog(frame, "File Compressed Successfully!");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void decompressFile() {

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {

            File zipFile = fileChooser.getSelectedFile();

            try {

                ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
                ZipEntry entry = zis.getNextEntry();

                File outputFile = new File(zipFile.getParent(), entry.getName());

                FileOutputStream fos = new FileOutputStream(outputFile);

                byte[] buffer = new byte[1024];
                int length;

                while ((length = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }

                zis.closeEntry();
                zis.close();
                fos.close();

                JOptionPane.showMessageDialog(frame, "File Decompressed Successfully!");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new FileCompressorGUI();
    }
}