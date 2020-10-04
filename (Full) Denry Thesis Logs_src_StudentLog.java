import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class StudentLog extends JFrame implements ActionListener, WindowListener, KeyListener {
    JTable tblStudThesisInfo;
    DefaultTableModel ModelStudThesisInfo;

    DataUser data = new DataUser();
    DatabaseUser database = new DatabaseUser();

    JPanel pnlSearch, pnlBtn;
    JLabel Search;
    JTextField txtSearch;
    JButton btnClear, btnMain;

    public static void main(String []args) {
        new StudentLog();
    }

    public StudentLog(){

        Search = new JLabel("Search");
        txtSearch = new JTextField(20);
        btnClear = new JButton("Clear");
        btnMain = new JButton("Main Menu");

        pnlSearch = new JPanel();
        pnlBtn = new JPanel();

        setTitle("Thesis Search");
        setSize(780,400);
        setLayout(null);


        Vector<String> Column = new Vector<>(data.setThesisSearch());

        tblStudThesisInfo = new JTable();
        ModelStudThesisInfo = new DefaultTableModel();

        tblStudThesisInfo.setModel(ModelStudThesisInfo);
        ModelStudThesisInfo.setColumnIdentifiers(Column);

        database.setFilename("ThesisInfo");
        database.displayRecordsForStud(ModelStudThesisInfo);

        pnlSearch.add(Search);
        pnlSearch.add(txtSearch);

        pnlBtn.add(btnClear);
        pnlBtn.add(btnMain);

        tblStudThesisInfo.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        tblStudThesisInfo.setLayout(new FlowLayout());
        pnlSearch.setLayout(new FlowLayout());
        pnlBtn.setLayout(new GridLayout(1,2,2,2));

        add(new JScrollPane((tblStudThesisInfo))).setBounds(10, 40, 756, 290);
        add(pnlSearch).setBounds(-60, 10, 600, 30);
        add(pnlBtn).setBounds(10, 334, 300, 25);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        addWindowListener(this);
        addKeyListener(this);

        btnMain.addActionListener(this);
        btnClear.addActionListener(this);
        txtSearch.addKeyListener(this);
    }


    public void actionPerformed(ActionEvent actevent) {
        if (actevent.getSource().equals(btnClear)){
            txtSearch.setText("");
        }
        else if (actevent.getSource().equals(btnMain)){
            new MainMenu();
            dispose();
        }
    }

    public void windowOpened(WindowEvent windowEvent) {

    }

    public void windowClosing(WindowEvent windowEvent) {
        new MainMenu();
        dispose();
    }

    public void windowClosed(WindowEvent windowEvent) {
    }

    public void windowIconified(WindowEvent windowEvent) {

    }

    public void windowDeiconified(WindowEvent windowEvent) {

    }

    public void windowActivated(WindowEvent windowEvent) {

    }

    public void windowDeactivated(WindowEvent windowEvent) {

    }
    public void keyTyped(KeyEvent keyEvent) {

    }

    public void keyPressed(KeyEvent keyEvent) {

    }

    public void keyReleased(KeyEvent e) {
        if(e.getSource().equals(txtSearch)){
            String search=txtSearch.getText();
            TableRowSorter tblSort = new TableRowSorter(ModelStudThesisInfo);
            tblStudThesisInfo.setRowSorter(tblSort);
            tblSort.setRowFilter(RowFilter.regexFilter(search, 0,1));
        }
    }
}
