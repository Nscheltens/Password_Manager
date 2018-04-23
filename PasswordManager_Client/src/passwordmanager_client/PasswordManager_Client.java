/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager_client;

import java.sql.*;
import java.util.ArrayList;
import javax.activation.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Nick Scheltens, Mark Jacobs
 */
public class PasswordManager_Client {
    private static Connection con;
    
    public PasswordManager_Client(){
        Panel_Maker ClientPanel = new Panel_Maker(true, this);
    }
    
    public Panel_Items createGroupPanel() throws SQLException{
        
        Panel_Items select = new Panel_Items("select");
        select.addLabelItem("Groups","Groups");
        select.addListItem("selectList",getAllGroups());
        
        return select;
    }
    public Panel_Items createContentPanel() throws SQLException{
        
        Panel_Items content = new Panel_Items("content");
        content.addListItem("selectList",getCredentials("app"));
        content.addComboboxItem("Content", getApps("IT Department"));
        
        return content;
    }
    public Panel_Items createUsersPanel()throws SQLException{
        
        Panel_Items content = new Panel_Items("content");
        content.addListItem("selectList",getUsers("group"));
        content.addComboboxItem("Content", getAllGroups());
        
        return content;
    }
    public Panel_Items createLoginPanel(){
        
        Panel_Items login = new Panel_Items("Login");
        
        login.addLabelItem("Login:","Login:");
        login.addFieldItem("Username", false);
        login.addFieldItem("Password", true);
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
                update.addFieldItem("Group Name", false);
                
            case 1:
                update.addLabelItem("Username","Fieldlabel");
                update.addLabelItem("Avaliable Groups","ListLabel");
                update.addListItem("appList", getAllApps());
                update.addListItem("inList", new String[] {});
                update.addFieldItem("Group Name", false);
        }
        update.addLabelItem("Group Name","llFieldlabel");
        update.addLabelItem("Avaliable Apps","llListLabel");
        update.addListItem("appList", getAllGroups());
        update.addListItem("inList", new String[] {});
        update.addFieldItem("Group Name", false);
        return update;
    }
    public Panel_Items createRemovePanel(int panel)throws SQLException{
        Panel_Items remove = new Panel_Items("remove");
        remove.addLabelItem("Items to remove","Items to remove");
        remove.addListItem("appList", getAllApps());
        remove.addListItem("inList", new String[] {});
        return remove;
    }
    public Panel_Items createAppCredPanel() throws SQLException{
        Panel_Items appcred = new Panel_Items("AppCred");
        
        appcred.addListItem("credPanel", getCredentials("app"));
        appcred.addComboboxItem("App Select", getAllApps());
        
        return appcred;
    }
    public Panel_Items createNewCredPanel(){
        
        Panel_Items Newcred = new Panel_Items("NewCred");
        
        Newcred.addFieldItem("username", false);
        Newcred.addFieldItem("password", false);
        Newcred.addFieldItem("CredName", false);
        
        Newcred.addLabelItem("Username","Userlabel");
        Newcred.addLabelItem("Password","Passlabel");
        Newcred.addLabelItem("Credential Name","Namelabel");
        
        return Newcred;
    }
    public Panel_Items createNewAppPanel(){
        
        Panel_Items Newcred = new Panel_Items("NewCred");
        
        Newcred.addFieldItem("FieldOne", false);
        Newcred.addFieldItem("FieldTwo", false);
        Newcred.addFieldItem("AppName", false);
        
        Newcred.addLabelItem("Username","Userlabel");
        Newcred.addLabelItem("Password","Passlabel");
        Newcred.addLabelItem("Application Name","Namelabel");
        
        return Newcred;
    }
    
    /*public Panel_Items createCredPanel(){
        
        Panel_Items cred = new Panel_Items("content");
        cred.addListItem("GroupList", getCredentials("app"));
        cred.addComboboxItem("Content", getAllGroups());
        return cred;
    }*/
    
    
    private String[] getAllGroups()throws SQLException{
        String query =
        "SELECT GroupName " +
        "FROM groups ";

    ArrayList<String> a = new ArrayList<String>();
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery(query);
      while (rs.next()){
       a.add(rs.getString("GroupName"));
     }
    return (String[]) a.toArray(new String[a.size()]);
    }
    private String[] getUsers(String group) throws SQLException{
        String query =
        "Select UserName "+
        "From users "+
        "Where idUsers = (Select Users_idUsers From users_has_groups Where Groups_GroupName = '"+group+"')";

    ArrayList<String> a = new ArrayList<String>();
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery(query);
      while (rs.next()){
       a.add(rs.getString("UserName"));
     }
      return (String[]) a.toArray(new String[a.size()]);
    }
    private String[] getGroups(String user) throws SQLException{
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
    private String[] getCredentials(String App)throws SQLException{
        String query =
        "call display_apps('"+App+"')";

    ArrayList<String> a = new ArrayList<String>();
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery(query);
      while (rs.next()){
       a.add(rs.getString("credID"));
     }
    return (String[]) a.toArray(new String[a.size()]);
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
    
    public static void main(String[] args) throws NamingException {
        //Client_Panel ClientPanel = new Client_Panel();
        //Panel_Maker ClientPanel = new Panel_Maker(false);
        PasswordManager_Client n = new PasswordManager_Client();

      
        try{  
            con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/mydb","root","toor");
            if(con != null){
            System.out.println("connected to database");
            }
        }catch (Exception e){
            throw new IllegalStateException("not connected", e);
        }
    }
}
    
