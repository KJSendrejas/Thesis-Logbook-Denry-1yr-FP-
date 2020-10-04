import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class ThesisTable extends JFrame implements ActionListener, KeyListener, WindowListener, MouseListener {
    JPanel UserInfoForm, ButtonForm, SearchForm;
    JLabel lblThesisTitle, lblAuthor, lblSubject, lblCourse, lblDept;
    JLabel lblSearch;
    JTextField txtThesisTitle, txtAuthor, txtSubject, txtCourse, txtSearch;
    JButton btnAdd, btnClear, btnUpdate, btnDelete;
    JComboBox cboDept;
    String records;

    JTable tblThesisInfo;
    DefaultTableModel ModelThesisInfo;

    DataUser data = new DataUser();
    DatabaseUser database = new DatabaseUser();
    Vector content;




    public static void main(String [] args){
        new ThesisTable();
    }

    public ThesisTable(){
        UserInfoForm = new JPanel();
        ButtonForm = new JPanel();
        SearchForm = new JPanel();

        lblThesisTitle = new JLabel("Thesis Title");
        lblAuthor = new JLabel("Author's Name");
        lblSubject = new JLabel("Subject");
        lblCourse = new JLabel("Course");
        lblDept = new JLabel("Department");

        txtThesisTitle = new JTextField();
        txtAuthor = new JTextField();
        txtSubject = new JTextField();
        txtCourse = new JTextField();

        lblSearch = new JLabel("Search");
        txtSearch = new JTextField(10);

        cboDept = new JComboBox();
        cboDept.addItem("PS");
        cboDept.addItem("CLE");
        cboDept.addItem("CCE");
        cboDept.addItem("CASE");
        cboDept.addItem("CAE");
        cboDept.addItem("CAFAE");
        cboDept.addItem("CCJE");
        cboDept.addItem("CHE");
        cboDept.addItem("CHSE");
        cboDept.addItem("CTE");
        cboDept.addItem("CEE");
        cboDept.addItem("TS");
        cboDept.addItem("BED");
        cboDept.addItem("BARBIE");

        btnAdd = new JButton("Add");
        btnClear = new JButton("Clear");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");

        Vector <String> Column = new Vector<>(data.setUserInfo());

        tblThesisInfo = new JTable();
        ModelThesisInfo = new DefaultTableModel();
        tblThesisInfo.setModel(ModelThesisInfo);
        ModelThesisInfo.setColumnIdentifiers(Column);

        database.setFilename("ThesisInfo");
        database.displayRecords(ModelThesisInfo);


        tblThesisInfo.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        tblThesisInfo.setLayout(new FlowLayout());

        UserInfoForm.setBorder(BorderFactory.createTitledBorder("Thesis Information"));
        UserInfoForm.setLayout(new GridLayout(9,2,2,3));

        SearchForm.setLayout(new FlowLayout());
        SearchForm.add(lblSearch);
        SearchForm.add(txtSearch);

        UserInfoForm.add(lblThesisTitle);
        UserInfoForm.add(txtThesisTitle);
        UserInfoForm.add(lblAuthor);
        UserInfoForm.add(txtAuthor);
        UserInfoForm.add(lblSubject);
        UserInfoForm.add(txtSubject);
        UserInfoForm.add(lblCourse);
        UserInfoForm.add(txtCourse);
        UserInfoForm.add(lblDept);
        UserInfoForm.add(cboDept);

        UserInfoForm.add(new JLabel());
        UserInfoForm.add(new JLabel());

        ButtonForm.setLayout(new GridLayout(2,2,2,2));
        ButtonForm.add(btnAdd);
        ButtonForm.add(btnClear);
        ButtonForm.add(btnUpdate);
        ButtonForm.add(btnDelete);

        add(new JScrollPane((tblThesisInfo))).setBounds(265, 40, 512, 310);
        add(UserInfoForm).setBounds(10, 10, 250, 270);
        add(ButtonForm).setBounds(10,290,247,50);
        add(SearchForm).setBounds(265,10,200,30);

        setTitle("Administrator");
        setSize(780, 400);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        addKeyListener(this);
        addWindowListener(this);
        txtSearch.addKeyListener(this);
        tblThesisInfo.addMouseListener(this);
        btnAdd.addActionListener(this);
        btnClear.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);

        Default();
    }
    public Vector addNewRecord(){
        content = new Vector<>();
        content.add(txtThesisTitle.getText());
        content.add(txtAuthor.getText());
        content.add(txtSubject.getText());
        content.add(txtCourse.getText());
        content.add(cboDept.getSelectedItem());
        return content;
    }

    public void processRecords() {
        records="";
        for (int row = 0; row < ModelThesisInfo.getRowCount(); row++) {
            for (int column = 0; column < ModelThesisInfo.getColumnCount(); column++) {
                records+= ModelThesisInfo.getValueAt(row, column)+"#";
            }
            records+="\n";
        }
        database.addRecords(records);
    }

    public void Default(){
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
    }

    public void Clear(){
        txtThesisTitle.setText("");
        txtAuthor.setText("");
        txtSubject.setText("");
        txtCourse.setText("");
        cboDept.setSelectedIndex(0);

        txtSearch.setText("");

        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        btnAdd.setEnabled(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnAdd)){
            ModelThesisInfo.addRow(addNewRecord());
            JOptionPane.showMessageDialog(null, "Records successfully added");
            Clear();
        }
        else if(e.getSource().equals(btnClear)){
            Clear();
        }
        else if(e.getSource().equals(btnDelete)){
            int i= tblThesisInfo.getSelectedRow();
            ModelThesisInfo.removeRow(i);
            Clear();
        }
        else if(e.getSource().equals(btnUpdate)){
            int i = tblThesisInfo.getSelectedRow();
            tblThesisInfo.setValueAt(txtThesisTitle.getText(), i, 0);
            tblThesisInfo.setValueAt(txtAuthor.getText(), i, 1);
            tblThesisInfo.setValueAt(txtSubject.getText(), i, 2);
            tblThesisInfo.setValueAt(txtCourse.getText(), i, 3);
            tblThesisInfo.setValueAt(cboDept.getSelectedItem(), i, 4);
            Clear();
        }
    }

    public void keyTyped(KeyEvent keyEvent) {
    }
    public void keyPressed(KeyEvent keyEvent) {
    }
    public void keyReleased(KeyEvent e) {
        if(e.getSource().equals(txtSearch)){
            String search=txtSearch.getText();
            TableRowSorter tblSort = new TableRowSorter(ModelThesisInfo);
            tblThesisInfo.setRowSorter(tblSort);
            tblSort.setRowFilter(RowFilter.regexFilter(search, 0,1,2,3,4));
        }
    }
    public void windowOpened(WindowEvent windowEvent) {
    }
    public void windowClosing(WindowEvent windowEvent) {
        processRecords();
        new MainMenu();
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
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(tblThesisInfo)){
            int i= tblThesisInfo.getSelectedRow();
            txtThesisTitle.setText(ModelThesisInfo.getValueAt(i,0)+"");
            txtAuthor.setText(ModelThesisInfo.getValueAt(i,1)+"");
            txtSubject.setText(ModelThesisInfo.getValueAt(i,2)+"");
            txtCourse.setText(ModelThesisInfo.getValueAt(i,3)+"");
            cboDept.setSelectedItem(ModelThesisInfo.getValueAt(i,4)+"");

            btnAdd.setEnabled(false);
            btnUpdate.setEnabled(true);
            btnDelete.setEnabled(true);
            btnClear.setEnabled(true);
        }
    }
    public void mousePressed(MouseEvent mouseEvent) {
    }
    public void mouseReleased(MouseEvent mouseEvent) {
    }
    public void mouseEntered(MouseEvent mouseEvent) {
    }
    public void mouseExited(MouseEvent mouseEvent) {
    }
}
