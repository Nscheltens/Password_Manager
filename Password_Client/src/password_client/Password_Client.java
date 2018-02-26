/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password_client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 *
 * @author Cole
 */
public class Password_Client extends JPanel implements ActionListener 
{
    /**
     * @param args the command line arguments
     */
    private ClientPanel CP;
    private LoginPanel LP;
    private TestPanel TP;
    private String user,loginPass;
    private Queue<String> programs,usernames,passwords;
    private static File pass = new File("C:\\Users\\Cole\\Documents\\GitHub\\Password_Manager\\Password_Client\\dist\\Password.txt");
    private JSplitPane splitPaneH;
    private static JPanel topPanel = new JPanel();
    
    public Password_Client(int w, int h, JFrame f)
    {
        super.setOpaque(true);
        super.setPreferredSize(new Dimension(w, h));
        super.setBackground(new Color(225, 225, 225));
        
        //JPanel topPanel = new JPanel();
        //topPanel.setLayout(new BorderLayout());
        //f.getContentPane().add(topPanel);
        
        splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int width = d.width;
        splitPaneH.setDividerLocation((width)/10);
        
        CP = new ClientPanel(this);
        //CP.setBounds(0, 0, w, h);
        //super.add(CP);
        CP.setVisible(false);
        
        TP = new TestPanel(this);
        TP.setBounds(0, 0, w, h);
        //super.add(TP);
        TP.setVisible(true);
        
        LP = new LoginPanel(this);
        //LP.setBounds(0, 0, w, h);
        splitPaneH.add(LP);
        LP.setVisible(true);
          
        splitPaneH.setLeftComponent(TP);
        splitPaneH.setRightComponent(LP);
        topPanel.add(splitPaneH);
       
        
        programs = new LinkedList<>();
        usernames = new LinkedList<>();
        passwords = new LinkedList<>();
        
        try{
            Scanner sc = new Scanner(pass);
            while(sc.hasNext()){
                String test = sc.next();
                if(test.equals("Username")){
                    user = sc.next();
                }
                else if(test.equals("Password")){
                    loginPass = sc.next();
                }
                else if(test.equals("LG")){
                    programs.add(sc.next());
                }
                else if(test.equals("UN")){
                    usernames.add(sc.next());
                }
                else if(test.equals("PW")){
                    passwords.add(sc.next());
                }
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        } 
    }
    public String getUser(){
        return user;
    }
    public String getLoginPass(){
        return loginPass;
    }
    public Queue<String> getPrograms(){
        return programs;
    }
    public Queue<String> getUsernames(){
        return usernames;
    }
    public Queue<String> getPasswords(){
        return passwords;
    }
    public void callClientPanel(){
        LP.setVisible(false);
        TP.setVisible(true);
        //CP.setVisible(true);
    }
    public static void main(String[] args)
    {
        JFrame.setDefaultLookAndFeelDecorated(false);
        JFrame fr = new JFrame("Password_Client");
        fr.setContentPane(new Password_Client(1000, 500, fr));
        topPanel.setLayout(new BorderLayout());
        fr.getContentPane().add(topPanel);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLocation(10, 10);
        fr.setResizable(false);
        fr.pack();
        fr.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
