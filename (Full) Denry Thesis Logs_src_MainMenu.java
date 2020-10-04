import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JFrame implements ActionListener {
    JButton btnAdmin, btnStudent;
    JPanel btnPanel;

    public static void main(String[]args){
        new MainMenu();
    }

    MainMenu(){

    btnAdmin = new JButton("ADMIN");
    btnStudent = new JButton("STUDENT");
    btnPanel = new JPanel();

    setTitle("Main Menu");
    setSize(780,400);
    setLayout(null);

    btnPanel.setLayout(new GridLayout(2,1,2,2));
    btnPanel.add(btnAdmin);
    btnPanel.add(btnStudent);
    btnPanel.setBackground(new Color(0,0,0,0));

    add(btnPanel).setBounds(290,150,200,80);

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("/home/jude/IdeaProjects/Denry Revised FP/Nudes/MainMenu.jpg"));
        }catch (IOException e) {
            e.printStackTrace();

        }
        Image dimg = img.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        setLayout(null);
        JLabel Labelimage = new JLabel(imageIcon);
        Labelimage.setBounds(0, 0, 780, 400);
        add(Labelimage);

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
    setResizable(false);

    btnStudent.addActionListener(this);
    btnAdmin.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnStudent)){
            new StudentLog();
            dispose();
        }
        else if (e.getSource().equals(btnAdmin)){
            new AdminLogin();
            dispose();
        }
    }
}