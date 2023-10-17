package controllers;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import minggu9.*;

public class FrameHelper {
    private JFrame currentFrame;

    public FrameHelper(JFrame frame) {
        this.currentFrame = frame;
    }

    public void setup() {
        currentFrame.setLayout(new BorderLayout());

        JLabel currentFrameLabel = new JLabel("Frame saat ini: " + currentFrame.getClass().getSimpleName());
        JPanel labelPanel = new JPanel();
        labelPanel.add(currentFrameLabel);
        currentFrame.add(labelPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5); // Padding

        // Mencari dan menampilkan tombol untuk semua JFrame yang ada dalam proyek
        List<Class<?>> frameClasses = allFrameClasses();
        int row = 0;
        for (Class<?> frameClass : frameClasses) {
            if (frameClass != currentFrame.getClass()) {
                JButton button = new JButton("Menuju ke " + frameClass.getSimpleName());
                button.addActionListener(e -> showFrame(frameClass));
                gridBagConstraints.gridy = row;
                buttonPanel.add(button, gridBagConstraints);
                row++;
            }
        }

        currentFrame.add(buttonPanel, BorderLayout.CENTER);

        // Label "Frame sebelumnya" tetap ada dan diletakkan di bagian bawah
        JLabel previousFrameLabel = new JLabel("Frame sebelumnya: ");
        JPanel previousFramePanel = new JPanel();
        previousFramePanel.add(previousFrameLabel);
        currentFrame.add(previousFramePanel, BorderLayout.SOUTH);

        currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        currentFrame.setSize(350, 450); // Memperbesar ukuran JFrame
        currentFrame.setVisible(true);
    }

    private List<Class<?>> allFrameClasses() {
        List<Class<?>> frameClasses = new ArrayList<>();
        frameClasses.add(Frame1.class);
        frameClasses.add(Frame2.class);
        frameClasses.add(Frame3.class);
        frameClasses.add(Frame4.class);
        frameClasses.add(Frame5.class);
        frameClasses.add(Frame6.class);
        frameClasses.add(Frame7.class);
        frameClasses.add(Frame8.class);
        frameClasses.add(Frame9.class);
        frameClasses.add(Frame10.class);
        return frameClasses;
    }

    private void showFrame(Class<?> frameClass) {
        if (currentFrame != null) {
            try {
                Class<?> previousFrameClass = currentFrame.getClass();
                currentFrame.dispose();
                currentFrame = (JFrame) frameClass.getDeclaredConstructor().newInstance();
                JLabel previousFrameLabel = new JLabel("Frame sebelumnya: " + previousFrameClass.getSimpleName());
                JPanel previousFramePanel = new JPanel();
                previousFramePanel.add(previousFrameLabel);
                currentFrame.add(previousFramePanel, BorderLayout.SOUTH);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
