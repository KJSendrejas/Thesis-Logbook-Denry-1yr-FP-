import java.util.Vector;

public class DataUser {
    private Vector <String> UserInfo;
    public static void main(String[]args){
        new DataUser();
    }
    public Vector <String> setUserInfo(){
        UserInfo = new Vector<>();
        UserInfo.add("Thesis Title");
        UserInfo.add("Author");
        UserInfo.add("Subject");
        UserInfo.add("Course");
        UserInfo.add("Department");
        return UserInfo;
    }

    public Vector <String> setThesisSearch(){
        UserInfo = new Vector<>();
        UserInfo.add("Thesis Title");
        UserInfo.add("Author");
        return UserInfo;
    }
 }
