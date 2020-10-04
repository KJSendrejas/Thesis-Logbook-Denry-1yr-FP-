import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AdminLogin extends JFrame implements ActionListener {
    JPanel LoginPanel, InputPanel, ImagePanel, BtnPanel, AdPanel, KeyListener;
    JLabel lblUsername, lblPassword;
    JTextField txtUsername;
    JPasswordField  txtPassword;
    JButton btnLogIn;

    DatabaseUser uadatabase = new DatabaseUser();

    public static void main(String[] args){
        new AdminLogin();
    }

    AdminLogin(){

        LoginPanel = new JPanel();
        InputPanel = new JPanel();
        BtnPanel = new JPanel();

        lblUsername = new JLabel("Username:");
        lblPassword = new JLabel("Password:");

        txtUsername = new JTextField();
        txtPassword = new JPasswordField();

        btnLogIn = new JButton("Log-In");

        setTitle("WELCOME");
        setSize(780, 400);

        BtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        BtnPanel.add(btnLogIn);


        InputPanel.setLayout(new GridLayout(2, 1, 2, 3));
        InputPanel.add(lblUsername);
        InputPanel.add(txtUsername);
        InputPanel.add(lblPassword);
        InputPanel.add(txtPassword);

        LoginPanel.setLayout(new GridLayout(2, 1, 2, 3));
        LoginPanel.add(InputPanel);
        LoginPanel.add(BtnPanel);

        LoginPanel.setBorder(BorderFactory.createTitledBorder("Log-in Form"));
        LoginPanel.setBackground(new Color(0,0,0,0));
        InputPanel.setBackground(new Color(0,0,0,0));
        BtnPanel.setBackground(new Color(0,0,0,0));

        add(LoginPanel).setBounds(270, 130, 250, 120);

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("/home/jude/IdeaProjects/Denry Revised FP/Nudes/Login.jpg"));
        }catch (IOException e) {
            e.printStackTrace();

        }
        Image dimg = img.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        setLayout(null);
        JLabel Labelimage = new JLabel(imageIcon);
        Labelimage.setBounds(0, 0, 780, 400);
        add(Labelimage);

        setResizable(false);
        setLayout(null);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnLogIn.addActionListener(this);

    }
    String logname, logpass;
    public void actionPerformed(ActionEvent act) {

        if(act.getSource().equals(btnLogIn)){
            logname= txtUsername.getText();
            logpass=txtPassword.getText();
            if(uadatabase.isFound(logname, logpass)){
                new ThesisTable();
                dispose();
            }else{
                JOptionPane.showMessageDialog(this, "Log-in Failed, Please check your account");
            }
        }
    }
}

