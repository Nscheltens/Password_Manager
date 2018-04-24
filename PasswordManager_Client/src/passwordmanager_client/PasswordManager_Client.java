/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager_client;

import java.sql.*;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author Nick Scheltens, Mark Jacobs
 */
public class PasswordManager_Client {
    private static Connection con;
    final static String secretKey = "Password";
    
    public PasswordManager_Client(){
        Panel_Maker ClientPanel = new Panel_Maker(true, this);
    }
    
    public Panel_Items createGroupPanel() throws SQLException{
        
        Panel_Items select = new Panel_Items("select");
        select.addLabelItem("Groups","Groups");
        select.addListItem("selectList",getAllGroups());
        
        return select;
    }
    public Panel_Items createContentPanel(String group,boolean admin) throws SQLException{
        
        Panel_Items content = new Panel_Items("content");
        String[] apps = getApps(group);
        //System.out.println(apps[0]);
        content.addListItem("selectList",getCredentials(apps[0], admin));
        content.addComboboxItem("Content", apps);
        return content;
    }
    public Panel_Items createUsersPanel()throws SQLException{
        
        Panel_Items content = new Panel_Items("content");
        String[] groups = getAllGroups();
        content.addListItem("selectList",getUsers(groups[0]));
        content.addComboboxItem("Content", groups);
        
        return content;
    }
    public Panel_Items createLoginPanel(){
        
        Panel_Items login = new Panel_Items("Login");
        
        login.addLabelItem("Login:","Login:");
        login.addFieldItem("Username","Username", false);
        login.addFieldItem("Password","Password", true);
        /*String query =
            "call login('"+user+"', '"+ pass+"')";*/
        return login;
    }
    public Panel_Items createUpdatePanel(int panel)throws SQLException{
        Panel_Items update = new Panel_Items("Update");
        switch (panel) {
            case 0:
                update.addLabelItem("Group Name","Fieldlabel");
                update.addLabelItem("Avaliable Apps","ListLabel");
                update.addListItem("appList", getAllApps());
                update.addListItem("inList", new String[] {});
                update.addFieldItem("Group Name", "Group Name", false);
                
            case 1:
                update.addLabelItem("Username","Fieldlabel");
                update.addLabelItem("Avaliable Groups","ListLabel");
                update.addListItem("appList", getAllGroups());
                update.addListItem("inList", new String[] {});
                update.addFieldItem("Group Name","Username", false);
                update.addLabelItem("Password", "Password");
                update.addFieldItem("Password Field","Password", false);
        }
        /*
        update.addLabelItem("Group Name","llFieldlabel");
        update.addLabelItem("Avaliable Apps","llListLabel");
        update.addListItem("appList", getAllGroups());
        update.addListItem("inList", new String[] {});
        update.addFieldItem("Group Name", "Group Name", false);
        */
        return update;
    }
    public Panel_Items createRemovePanel(int panel, String app, boolean admin)throws SQLException{
      
        Panel_Items remove = new Panel_Items("remove");
        remove.addLabelItem("Items to remove","Items to remove");
        switch(panel){
            case 0:
                remove.addListItem("appList", getAllGroups());
            case 1:
                remove.addListItem("appList", getAllUsers());
            case 2:
                remove.addListItem("appList", getCredentials(app, admin));
        }
        remove.addListItem("inList", new String[] {});
        return remove;
    }
    public Panel_Items createAppCredPanel(boolean admin) throws SQLException{
        Panel_Items appcred = new Panel_Items("AppCred");
        String[] apps = getAllApps();
        appcred.addListItem("credPanel", getCredentials(apps[0], admin));
        appcred.addComboboxItem("App Select", apps);
        
        return appcred;
    }
    public Panel_Items createNewCredPanel(){
        
        Panel_Items Newcred = new Panel_Items("NewCred");
        
        Newcred.addFieldItem("username", "username", false);
        Newcred.addFieldItem("password", "password", false);
        Newcred.addFieldItem("CredName", "CredName", false);
        
        Newcred.addLabelItem("Username","Userlabel");
        Newcred.addLabelItem("Password","Passlabel");
        Newcred.addLabelItem("Credential Name","Namelabel");
        
        return Newcred;
    }
    public Panel_Items createNewAppPanel(){
        
        Panel_Items Newcred = new Panel_Items("NewCred");
        
        Newcred.addFieldItem("AppName","Application Name", false);
        
        Newcred.addLabelItem("Username","Userlabel");
        Newcred.addLabelItem("Password","Passlabel");
        Newcred.addLabelItem("Application Name","Namelabel");
        
        return Newcred;
    } 
    public Panel_Items createInfoPanel(){
        return null;
    }
    
    
    public boolean checkLogin(String user, String pass) throws SQLException{
        String query =
            "call login('"+user+"', '"+ pass+"')";
        
        String username = user;
        String password = getPassword();
        
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()){
            password = rs.getString("Password");
        }
        password = AES.decrypt(password, secretKey);
        if(user.equals(username)){
            if(pass.equals(password)){
                return true;
            }
        }
        System.out.println("Wrong UserName or Password");
        return false;
    }
    private String getPassword(){
        return "pass";
    }
    
    String[] getAllGroups()throws SQLException{
        String query =
        "SELECT GroupName " +
        "FROM mydb.groups ";

    ArrayList<String> a = new ArrayList<String>();
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery(query);
      while (rs.next()){
       a.add(rs.getString("GroupName"));
     }
    return (String[]) a.toArray(new String[a.size()]);
    }
    String[] getUsers(String group) throws SQLException{
        String query =
        "Select UserName "+
        "From users "+
        "Where idUsers in (Select users_has_groups.Users_idUsers From users_has_groups Where users_has_groups.Groups_GroupName = '"+group+"')";

    ArrayList<String> a = new ArrayList<String>();
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery(query);
      while (rs.next()){
       a.add(rs.getString("UserName"));
     }
     

      return (String[]) a.toArray(new String[a.size()]);
    }
    String[] getGroups(String user) throws SQLException{
        String query =
            "call display_group('"+user+"')";
        ArrayList<String> a = new ArrayList<String>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while (rs.next()){
            a.add(rs.getString("GroupName"));
            
        }
        return (String[]) a.toArray(new String[a.size()]);
    }
    String[] getCredentials(String App, boolean admin)throws SQLException{
        String query =
        "call display_creds('"+App+"')";

    ArrayList<String> a = new ArrayList<String>();
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery(query);
      while (rs.next()){
          int id = rs.getInt("credID");
          int inUse = rs.getInt("inUse");
          String available;
          if(inUse == 0 || admin)
              a.add(rs.getString("credID"));
          else{}
              
       
     }
    String[] creds = (String[]) a.toArray(new String[a.size()]);
    return creds;
   }       
    private String[] getApps(String group) throws SQLException{
        String query =
        "call display_apps('"+group+"')";

    ArrayList<String> a = new ArrayList<String>();
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery(query);
      while (rs.next()){
       a.add(rs.getString("Applications_AppName"));
     }
    return (String[]) a.toArray(new String[a.size()]);
   }   
    private String[] getAllApps()throws SQLException{
        String query =
        "SELECT AppName " +
        "FROM applications ";

    ArrayList<String> a = new ArrayList<String>();
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery(query);
      while (rs.next()){
       a.add(rs.getString("AppName"));
     }
    return (String[]) a.toArray(new String[a.size()]);
        
    }   
    void removeCred(String CredName) throws SQLException{
        String query = 
             "call delete_credentials('"+CredName+"')";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
    }
    void removeApp(String AppName) throws SQLException{
        String query = 
             "call delete_app('"+AppName+"')";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
    }
    void addApp(int appID, String appName)throws SQLException{
        String query = 
             "call new_app('"+appID+"', '"+appName+"')";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
    }
    void addCred(int credID, String username, String Password, String appName) throws SQLException{
        String encryptPassword = AES.encrypt(Password,secretKey);
        String query = 
             "call new_credential('"+credID+"', '"+username+"', '"+encryptPassword+"', '"+appName+"')";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
    }
    void addUser(int UserID, String username, String password) throws SQLException{
        String encryptPassword = AES.encrypt(password,secretKey);
        String query = 
             "call new_user('"+UserID+"', '"+username+"', '"+encryptPassword+"')";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
    }
    void addUserToGroup(int UserID, String groupName) throws SQLException{
        String query = 
             "call insert_user_in_group('"+UserID+"', '"+groupName+"')";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
    }
    void addGroup(int GroupID, String groupName) throws SQLException{
        String query = 
             "call new_group('"+GroupID+"', '"+groupName+"')";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
    }
    void addAppToGroup(String groupName, String appName) throws SQLException{
        String query = 
             "call insert_group_in_app('"+groupName+"', '"+appName+"')";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
    }
    
    public static void main(String[] args) throws NamingException {
        //Client_Panel ClientPanel = new Client_Panel();
        //Panel_Maker ClientPanel = new Panel_Maker(false);
        
        String originalString = "password";
        String encryptedString = AES.encrypt(originalString, secretKey) ;
        String decryptedString = AES.decrypt(encryptedString, secretKey) ;
     
        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
        
        PasswordManager_Client n = new PasswordManager_Client();
      
        try{  
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/mydb","root","toor");
            if(con != null){
            System.out.println("connected to database");
            }
        }catch (SQLException e){
            throw new IllegalStateException("not connected", e);
        }
    }

    private String[] getAllUsers() throws SQLException {
       String query =
        "SELECT UserName " +
        "FROM users ";

    ArrayList<String> a = new ArrayList<String>();
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery(query);
      while (rs.next()){
       a.add(rs.getString("UserName"));
     }
    return (String[]) a.toArray(new String[a.size()]);
    }   
    String[] getProtectedCred(int credID) throws SQLException{
        String query =
        "call show_protected_cred('"+credID+"')";

    ArrayList<String> a = new ArrayList<String>();
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery(query);
      while (rs.next()){
       String userName = rs.getString("UserName");
       String passWord = rs.getString("Password");
       passWord = AES.decrypt(passWord, secretKey);
       a.add("UserName: "+userName+"\n\nPassword: "+passWord);
     }
    
    return (String[]) a.toArray(new String[a.size()]);
    }
}
