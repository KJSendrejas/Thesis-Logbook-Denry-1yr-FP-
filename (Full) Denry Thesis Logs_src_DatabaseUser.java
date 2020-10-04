import javax.swing.table.DefaultTableModel;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Vector;


public class DatabaseUser {
        private FileReader FRead;
        private FileWriter FWrite;
        private Scanner Scan;
        private String Filename;
        private Vector Row;

        public static void main(String[] args) {
                new DatabaseUser();
        }

        public DatabaseUser() {
                FWrite = null;
                FRead = null;
                Scan = null;
        }

        public DatabaseUser(String Filename) {
                try {
                        FWrite =  new FileWriter(this.Filename);
                } catch (Exception e) {
                        errorMessage("Error 101: Database " + e.getMessage());
                }

        }
        public void setFilename(String Filename){
                try{
                        this.Filename = Filename;
                }catch(Exception e){
                        errorMessage("Error 102: setFilename "+e.getMessage());
                }
        }
        public void displayRecords(DefaultTableModel Model){
                try{
                        String Value[];
                        Scan = new Scanner(new FileReader("ThesisInfo"));
                        while (Scan.hasNext()){
                                Value = Scan.nextLine().split("#");
                                Row = new Vector<String>();
                                for(int i = 0; i<5; i++){
                                        Row.add(Value[i]);
                                }
                                Model.addRow(Row);
                        }
                        Scan.close();
                }catch (Exception e){
                        errorMessage("Error 103 : displayRecords "+e.getMessage());
                }
        }

        public void displayRecordsForStud(DefaultTableModel Model){
                try{
                        String Value[];
                        Scan = new Scanner(new FileReader("ThesisInfo"));
                        while (Scan.hasNext()){
                                Value = Scan.nextLine().split("#");
                                Row = new Vector<String>();
                                for(int i = 0; i<2; i++){
                                        Row.add(Value[i]);
                                }
                                Model.addRow(Row);
                        }
                        Scan.close();
                }catch (Exception e){
                        errorMessage("Error 103 : displayRecords "+e.getMessage());
                }
        }

        public void addRecords(String Records){
                try{
                        FWrite = new FileWriter(Filename);
                        FWrite.write(Records+"\n");
                        FWrite.close();
                        FWrite.flush();

                }catch (Exception e){
                        errorMessage("Record Saved Successfully : "+e.getMessage());

                }
        }
        public boolean isFound(String username, String password){
                boolean flag = false;
                try {
                        FRead=new FileReader("AdminAccount");
                        Scan=new Scanner(FRead);
                        Scan.useDelimiter("#");
                        while(Scan.hasNext()){
                                if(username.equals(Scan.next()) && password.equals(Scan.next())){
                                        flag=true;
                                }
                        }
                        FRead.close();
                        Scan.close();
                } catch (Exception e) {
                        errorMessage("Error 105: isFound\n"+e.getMessage());
                }
                return flag;
        }
        private void errorMessage(String s) {
                System.err.println(s);
        }
}