/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager_client;

/**
 *
 * @author Nick Scheltens
 */
public class PasswordManager_Client {
    
    public PasswordManager_Client(){
        Panel_Maker ClientPanel = new Panel_Maker(true, this);
    }
    
    public Panel_Items createGroupPanel(){
        
        Panel_Items select = new Panel_Items("select");
        select.addLabelItem("Groups","Groups");
        select.addListItem("selectList",getAllGroups());
        
        return select;
    }
    public Panel_Items createContentPanel(){
        
        Panel_Items content = new Panel_Items("content");
        content.addListItem("selectList",getCredentials("app"));
        content.addComboboxItem("Content", getApps("groups"));
        
        return content;
    }
    public Panel_Items createUsersPanel(){
        
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
        
        return login;
    }
    public Panel_Items createUpdatePanel(int panel){
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
    public Panel_Items createRemovePanel(int panel){
        Panel_Items remove = new Panel_Items("remove");
        remove.addLabelItem("Items to remove","Items to remove");
        remove.addListItem("appList", getAllApps());
        remove.addListItem("inList", new String[] {});
        return remove;
    }
    public Panel_Items createAppCredPanel(){
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
    
    
    private String[] getAllGroups(){
        return new String[] {"Group One", "Group Two", "Group Three"};
    }
    private String[] getUsers(String group){
        return new String[] { "User 1", "User 2", "User 3", "User 4" };
    }
    private String[] getGroups(String user){
        return new String[] {"one", "Two", "THREE"};
    }
    private String[] getCredentials(String App){
        
        return new String[] { "cred 1", "cred 2", "cred 3", "cred 4" };
    }
    private String[] getApps(String group){
        
        return new String[] { "App 1", "App 2", "App 3", "App 4" };
    }
    private String[] getAllApps(){
        return new String[] { "App 1", "App 2", "App 3", "App 4" };
    }
    
    public static void main(String[] args) {
        //Client_Panel ClientPanel = new Client_Panel();
        //Panel_Maker ClientPanel = new Panel_Maker(false);
        PasswordManager_Client n = new PasswordManager_Client();
    }
    
}
