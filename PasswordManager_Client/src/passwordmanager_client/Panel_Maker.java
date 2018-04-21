/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager_client;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 *
 * @author Cole
 */
public class Panel_Maker extends JPanel{
    
    private JSplitPane splitPaneH, splitPaneV;
    private JFrame fr = new JFrame("PASSWORD MANAGER");
    private final PasswordManager_Client manager;
    private Boolean admin = false;
    private int width,height;
    
    private javax.swing.JPasswordField LoginPassField;
    javax.swing.JTextField LoginTextField;
    javax.swing.JComboBox<String> AppPick;
    javax.swing.JList aList;
    javax.swing.JScrollPane viewPane;
    
    /**
     * compartmentalize the panel tool
     * @param admin
     * @param p
     */
    public Panel_Maker(boolean admin, PasswordManager_Client p){
        manager = p;
        if(admin){
            fr = createAdminPanel();
        }
        else{
            fr = createClientPanel();
        }
    }
    /**
     * edit
     * @return 
     */
    private JFrame createClientPanel(){
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        Dimension sz = new Dimension((9*d.width)/10,(9*d.height)/10);
        width = sz.width;
        height = sz.height;
        
        fr.setSize(sz);
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        fr.getContentPane().add(topPanel);
        
        splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        System.out.println(width+" "+width/10);
        splitPaneH.setDividerLocation(172); //make it dynamic based on the size of the buttons
        
        //splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        System.out.println(height+" "+(9*height)/10);
        //splitPaneV.setDividerLocation(874); //make it dynamic based on the size of the buttons
        
        //splitPaneV.setLeftComponent(logPanel);
        //splitPaneV.setRightComponent(new String[] {});
        
        splitPaneH.setLeftComponent(createSidePanel(new String[] {}));
        splitPaneH.setRightComponent(createLogPanel(manager.createLoginPanel()));
        topPanel.add(splitPaneH);
        
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLocation(10, 10);
        fr.setResizable(false);
        fr.setVisible(true);
        admin = false;
        return null;
    }
    /**
     * edit
     * @return 
     */
    private JFrame createAdminPanel(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        Dimension sz = new Dimension((9*d.width)/10,(9*d.height)/10);
        width = sz.width;
        height = sz.height;
        
        fr.setSize(sz);
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        fr.getContentPane().add(topPanel);
        
        splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        System.out.println(width+" "+width/10);
        splitPaneH.setDividerLocation(172); //make it dynamic based on the size of the buttons
        
        splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        System.out.println(height+" "+(9*height)/10);
        splitPaneV.setDividerLocation(874); //make it dynamic based on the size of the buttons
        
        splitPaneV.setLeftComponent(createLogPanel(manager.createLoginPanel()));
        splitPaneV.setRightComponent(createBotPanel((new String[] {}), null, 5));
        
        splitPaneH.setLeftComponent(createSidePanel(new String[] {}));
        splitPaneH.setRightComponent(splitPaneV);
        topPanel.add(splitPaneH);
        
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLocation(10, 10);
        fr.setResizable(false);
        fr.setVisible(true);
        admin = true;
        return null;
    }
    
    /**
     * 
     * @param p a collection of items on the panel
     * @param content boolean to know if using as a content panel or user panel
     * @return panel to be displayed
     */
    private JPanel createContentPanel(Panel_Items p, boolean content){
        //placements for layout
        int p2width = width - (172);
        int p2height = (9*height)/10;
        
        //Create Panel
        JPanel Pan = new javax.swing.JPanel();
        
        //Create all items to be added to the panel
        viewPane = new javax.swing.JScrollPane();
        AppPick = p.getComboboxItem("Content");
        AppPick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(content) AppPickContent(evt);
                else AppPickUser(evt);
            }
        });
        aList = p.getListItem("selectList");
        aList.setFont(new java.awt.Font("Tahoma", 1, 18));
        viewPane.setViewportView(aList);
        //All elements created
        
        //Layout Panel
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(Pan);
        Pan.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(viewPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1536, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(((p2width/2)- 150), ((p2width/2)- 150), ((p2width/2)- 150))
                .addComponent(AppPick, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AppPick, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(viewPane, javax.swing.GroupLayout.PREFERRED_SIZE, ((p2height*86)/100), javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        return Pan; //Return panel
    }
    
    /**
     * 
     * @param p a collection of items on the panel
     * @return panel to be displayed
     */
    private JPanel createLogPanel(Panel_Items p){
        //Create Panel
        JPanel log = new javax.swing.JPanel();
        
        //Create all items to be added to the panel
        LoginPassField = p.getPassFieldItem("Password");
        LoginTextField = p.getFieldItem("Username");
        javax.swing.JLabel LoginLabel = p.getLabelItem("Login:");
        javax.swing.JButton LoginButton = new javax.swing.JButton();
        
        LoginButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        LoginButton.setText("GO!");
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });
        //All elements created
        
        //Layout Panel
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(log);
        log.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(600, 600, 600)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LoginPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LoginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(754, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(520, 520, 520)
                .addComponent(LoginLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(600, 600, 600))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(356, Short.MAX_VALUE)
                .addComponent(LoginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LoginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LoginPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(321, 321, 321))
        );
        return log; //Return panel
    }
    
    /**
     * make dynamic sizable
     * @return 
     */
    private JPanel createInfoPanel(){
        
        JPanel Pan = new javax.swing.JPanel();
        
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        javax.swing.JTextArea passTextArea = new javax.swing.JTextArea();
        javax.swing.JLabel groupLabel = new javax.swing.JLabel();

        passTextArea.setEditable(false);
        passTextArea.setColumns(20);
        passTextArea.setRows(5);
        jScrollPane1.setViewportView(passTextArea);
        
        groupLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        groupLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        groupLabel.setText("Cedentials for App");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(Pan);
        Pan.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1456, Short.MAX_VALUE)
                    .addComponent(groupLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(groupLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        return Pan;
    }
    
    /**
     * 
     * @param p a collection of items on the panel
     * @return panel to be displayed
     */
    private JPanel createGroupsPanel(Panel_Items p){
        //Create Panel
        JPanel Pan = new javax.swing.JPanel();
        
        //Create all items to be added to the panel
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        javax.swing.JLabel groupLabel = p.getLabelItem("Groups");
        javax.swing.JList aList = p.getListItem("selectList");
        aList.setFont(new java.awt.Font("Tahoma", 1, 18));
        jScrollPane1.setViewportView(aList);
        //All elements created    
        
        //Layout Panel
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(Pan);
        Pan.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1456, Short.MAX_VALUE)
                    .addComponent(groupLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(groupLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        return Pan; //Return panel
    }
    
    /**
     * make dynamic sizable
     * @return 
     */
    private JPanel createAppCredPanel(Panel_Items p){
        
        JPanel Pan = new javax.swing.JPanel();
        
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        AppPick = p.getComboboxItem("App Select");
        javax.swing.JButton jButton1 = new javax.swing.JButton();
        javax.swing.JButton jButton2 = new javax.swing.JButton();
        
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setText("New");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAppButtonActionPerformed(evt, Pan, 2);
            }
        });
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2.setText("Remove");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAppButtonActionPerformed(evt,AppPick);
            }
        });

        jScrollPane1.setViewportView(p.getListItem("credPanel"));
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(Pan);
        Pan.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AppPick, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 994, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AppPick, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        return Pan;
    }
    
    /**
     * make dynamic sizable
     * @return 
     */
    private JPanel createUpdatePanel(Panel_Items p){
        
        JPanel Pan = new javax.swing.JPanel();
        
        javax.swing.JTextField jTextField3 = p.getFieldItem("Group Name");
        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 24));
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        javax.swing.JButton jButton1 = new javax.swing.JButton();
        javax.swing.JButton jButton2 = new javax.swing.JButton();
        javax.swing.JButton jButton3 = new javax.swing.JButton();
        javax.swing.JButton jButton4 = new javax.swing.JButton();
        javax.swing.JButton jButton5 = new javax.swing.JButton();
        javax.swing.JLabel jLabel1 = p.getLabelItem("Fieldlabel");
        javax.swing.JLabel jLabel6 = p.getLabelItem("ListLabel");
        
        javax.swing.JList removeList = p.getListItem("inList");
        
        javax.swing.JList currentList = p.getListItem("appList");
        
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setText(">>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAllButtonActionPerformed(evt, currentList, removeList);
            }
        });
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2.setText("<");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt, currentList, removeList);
            }
        });
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton3.setText(">");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt, currentList, removeList);
            }
        });
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton4.setText("<<");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAllButtonActionPerformed(evt, currentList, removeList);
            }
        });
        
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton5.setText("Create");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt, removeList, jTextField3);
            }
        });

        jScrollPane1.setViewportView(p.getListItem("inList"));

        jScrollPane2.setViewportView(p.getListItem("appList"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(Pan);
        Pan.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 425, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(21, 21, 21)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(78, 78, 78)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(243, Short.MAX_VALUE))
        );
        return Pan;
    }
    
    private JPanel createNewCredPanel(Panel_Items p){
        
        JPanel Pan = new javax.swing.JPanel();
        
        javax.swing.JTextField jTextField1 = p.getFieldItem("username");
        javax.swing.JTextField jTextField2 = p.getFieldItem("CredName");
        javax.swing.JTextField jTextField3 = p.getFieldItem("password");
                
        javax.swing.JButton jButton3 = new javax.swing.JButton();
                
        javax.swing.JLabel jLabel1 = p.getLabelItem("Passlabel");
        javax.swing.JLabel jLabel2 = p.getLabelItem("Namelabel");
        javax.swing.JLabel jLabel3 = p.getLabelItem("Userlabel");
        
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton3.setText("Create");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt, jTextField1, jTextField2, jTextField3);
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(Pan);
        Pan.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField3)
                            .addComponent(jTextField2)
                            .addComponent(jTextField1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(807, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
                .addContainerGap(447, Short.MAX_VALUE))
        );
        
        return Pan;
    }
    
    private JPanel createNewAppPanel(Panel_Items p){
        
        JPanel Pan = new javax.swing.JPanel();
        
        javax.swing.JTextField jTextField1 = p.getFieldItem("FieldOne");
        javax.swing.JTextField jTextField2 = p.getFieldItem("AppName");
        javax.swing.JTextField jTextField3 = p.getFieldItem("FieldTwo");
                
        javax.swing.JButton jButton3 = new javax.swing.JButton();
                
        javax.swing.JLabel jLabel1 = p.getLabelItem("Passlabel");
        javax.swing.JLabel jLabel2 = p.getLabelItem("Namelabel");
        javax.swing.JLabel jLabel3 = p.getLabelItem("Userlabel");
        
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton3.setText("Create");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt, jTextField1, jTextField2, jTextField3);
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(Pan);
        Pan.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField3)
                            .addComponent(jTextField2)
                            .addComponent(jTextField1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(807, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
                .addContainerGap(447, Short.MAX_VALUE))
        );
        
        return Pan;
    }
    
    /**
     * make dynamic sizable
     * @return 
     */
    private JPanel createRemovePanel(Panel_Items p){
        
        JPanel Pan = new javax.swing.JPanel();
        
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        javax.swing.JButton jButton1 = new javax.swing.JButton();
        javax.swing.JButton jButton2 = new javax.swing.JButton();
        javax.swing.JButton jButton3 = new javax.swing.JButton();
        javax.swing.JButton jButton4 = new javax.swing.JButton();
        javax.swing.JButton jButton5 = new javax.swing.JButton();
        javax.swing.JLabel jLabel6 = p.getLabelItem("Items to remove");
        
        javax.swing.JList removeList = p.getListItem("inList");
        
        javax.swing.JList currentList = p.getListItem("appList");
        
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setText(">>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAllButtonActionPerformed(evt, currentList, removeList);
            }
        });
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2.setText("<");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt, currentList, removeList);
            }
        });
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton3.setText(">");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt, currentList, removeList);
            }
        });
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton4.setText("<<");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAllButtonActionPerformed(evt, currentList, removeList);
            }
        });
        
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton5.setText("Remove");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeListButtonActionPerformed(evt, removeList);
            }
        });

        jScrollPane1.setViewportView(removeList);

        jScrollPane2.setViewportView(currentList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(Pan);
        Pan.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(495, 495, 495)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(498, 498, 498)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(100, 495, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(310, Short.MAX_VALUE))
        );
        return Pan;
    }
    
    /**
     * 
     * @param buttonList list of strings(names of all buttons to add)
     * @param returnpanel panel to return to after certain buttons are selected
     * @param updatepanel int for certain functions in manager
     * @return Panel on the bottom of the screen
     */
    private JPanel createBotPanel(String[] buttonList, JPanel returnpanel, int updatepanel){
        JPanel Pan3 = new javax.swing.JPanel();
        Pan3.setBackground(Color.YELLOW);
        Pan3.setLayout(new FlowLayout());
        
        for(String buttonName : buttonList){
            Pan3.add(makeBotButton(buttonName, returnpanel, new java.awt.Font("Tahoma", 1, 20), updatepanel));
        }
        
        return Pan3;
    }

    /**
     * 
     * @param buttonList list of strings(names of all buttons to add)
     * @return Panel on the left hand side
     */
    private JPanel createSidePanel(String[] buttonList){
        
        JPanel Pan1 = new javax.swing.JPanel();
        Pan1.setBackground(Color.red);
        Pan1.setLayout(new BoxLayout(Pan1, BoxLayout.PAGE_AXIS));
        
        for(String buttonName : buttonList){
            Pan1.add(makeButton(buttonName, new java.awt.Font("Tahoma", 1, 16)));
            Pan1.add(Box.createRigidArea(new Dimension(0,5)));
        }
        
        Pan1.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        
        return Pan1;
    }
    
    /**
     * 
     * @param name name of button to create
     * @param f font of button
     * @return button to add to the side panel
     */
    private javax.swing.JButton makeButton(String name, Font f){
        javax.swing.JButton inButton = new javax.swing.JButton();
        inButton.setFont(f); // NOI18N
        inButton.setText(name);
        switch (name) {
            case "Groups":
                inButton.addActionListener((java.awt.event.ActionEvent evt) -> {
                    JPanel panel = createGroupsPanel(manager.createGroupPanel());
                    sideButtonActionPerformed(evt, panel, createBotPanel((new String[] {"New", "Remove"}),panel, 0));
                }); break;
            case "Credentials":
                inButton.addActionListener((java.awt.event.ActionEvent evt) -> {
                    JPanel panel = createAppCredPanel(manager.createAppCredPanel());
                    sideButtonActionPerformed(evt, panel, createBotPanel((new String[] {"New", "Remove"}), panel, 2));
                }); break;
            case "Users":
                inButton.addActionListener((java.awt.event.ActionEvent evt) -> {
                    JPanel panel = createContentPanel(manager.createUsersPanel(), false);
                    sideButtonActionPerformed(evt, panel, createBotPanel((new String[] {"New" , "Remove"}), panel, 1));
                }); break;     
            default:
                inButton.addActionListener((java.awt.event.ActionEvent evt) -> {
                    JPanel panel = createContentPanel(manager.createContentPanel(name), true);
                    sideButtonActionPerformed(evt, panel, createBotPanel((new String[] {"Checkout"}), panel, 4));
                }); break;
        }
        return inButton;
    }
    /**
     * 
     * @param name name of button to create
     * @param returnpanel panel to return to for actionlisteners
     * @param f font of button
     * @param panel int for certain functions in manager
     * @return button to add to the bottom panel
     */
    private javax.swing.JButton makeBotButton(String name,  JPanel returnpanel, Font f, int panel){
        javax.swing.JButton inButton = new javax.swing.JButton();
        inButton.setFont(f); // NOI18N
        inButton.setText(name);
        switch (name) {   
            case "New":
                switch(panel){
                    case 2:
                        inButton.addActionListener((java.awt.event.ActionEvent evt) -> {
                            sideButtonActionPerformed(evt, createNewCredPanel(manager.createNewCredPanel()), createBotPanel((new String[] {"Done", "Cancel"}), returnpanel, panel));
                        }); break;
                    default:
                        inButton.addActionListener((java.awt.event.ActionEvent evt) -> {
                            sideButtonActionPerformed(evt, createUpdatePanel(manager.createUpdatePanel(panel)), createBotPanel((new String[] {"Done", "Cancel"}), returnpanel, panel));
                        }); break;
                }
            case "Remove":
                inButton.addActionListener((java.awt.event.ActionEvent evt) -> {
                    sideButtonActionPerformed(evt, createRemovePanel(manager.createRemovePanel(panel)), createBotPanel((new String[] {"Done"}), returnpanel, 3));
                }); break;
            case "Checkout":
                inButton.addActionListener((java.awt.event.ActionEvent evt) -> {
                    sideButtonActionPerformed(evt,createInfoPanel(), createBotPanel((new String[] {"Cancel"}), returnpanel, panel));
                }); break;
            case "Cancel":
                inButton.addActionListener((java.awt.event.ActionEvent evt) -> {
                    sideButtonActionPerformed(evt, returnpanel, createBotPanel((new String[] {"New", "Remove"}), returnpanel, panel));
                }); break;
            case "Done":
                inButton.addActionListener((java.awt.event.ActionEvent evt) -> {
                    sideButtonActionPerformed(evt, returnpanel, createBotPanel((new String[] {"New", "Remove"}), returnpanel, panel));
                }); break;
        }
        return inButton;
    }
    
    
    private void LoginButtonActionPerformed(ActionEvent evt) {
        if(admin){
            if(manager.checkLogin("user", "pass")){ //for debugging
            //if(manager.checkLogin(LoginTextField.getText(), String.valueOf(LoginPassField.getPassword()))){
                splitPaneH.setLeftComponent(createSidePanel(new String[] {"Credentials", "Users", "Groups", "Client View"}));
                splitPaneV.setLeftComponent(createInfoPanel());
            }
        }
        else{
            if(manager.checkLogin(LoginTextField.getText(), String.valueOf(LoginPassField.getPassword()))){
                splitPaneH.setLeftComponent(createSidePanel(manager.getAllGroups()));
                splitPaneV.setLeftComponent(createInfoPanel());
            }
        }
    }
    private void sideButtonActionPerformed(ActionEvent evt, JPanel main , JPanel bot) {
        splitPaneV.setRightComponent(bot);
        splitPaneV.setLeftComponent(main);
    }
    
    private void removeButtonActionPerformed(ActionEvent evt, javax.swing.JList appList, javax.swing.JList addList){
        String removeItem = addList.getSelectedValue().toString();
        ((DefaultListModel)appList.getModel()).addElement(removeItem);
        ((DefaultListModel)addList.getModel()).removeElement(addList.getSelectedValue());
    }   
    private void removeAllButtonActionPerformed(ActionEvent evt, javax.swing.JList appList, javax.swing.JList addList){
        javax.swing.DefaultListModel appMod = (DefaultListModel)addList.getModel();
        for(Object part : appMod.toArray()){
            ((DefaultListModel)appList.getModel()).addElement(part.toString());
        }
        addList.setModel(new javax.swing.DefaultListModel());
    }
    private void addButtonActionPerformed(ActionEvent evt, javax.swing.JList appList, javax.swing.JList addList){
        String addItem = appList.getSelectedValue().toString();
        ((DefaultListModel)addList.getModel()).addElement(addItem);
        ((DefaultListModel)appList.getModel()).removeElement(appList.getSelectedValue());
    }
    private void addAllButtonActionPerformed(ActionEvent evt, javax.swing.JList appList, javax.swing.JList addList){
        javax.swing.DefaultListModel appMod = (DefaultListModel)appList.getModel();
        for(Object part : appMod.toArray()){
            ((DefaultListModel)addList.getModel()).addElement(part.toString());
        }
        appList.setModel(new javax.swing.DefaultListModel());
    }
    
    private void removeAppButtonActionPerformed(ActionEvent evt, javax.swing.JComboBox<String> appList){
            
    }
    
    private void addAppButtonActionPerformed(ActionEvent evt, JPanel returnpanel, int panel){
        sideButtonActionPerformed(evt, createNewAppPanel(manager.createNewAppPanel()), createBotPanel((new String[] {"Done", "Cancel"}), returnpanel, panel));
    }
    
    private void removeListButtonActionPerformed(ActionEvent evt, javax.swing.JList jList1){
            
    }
    
    private void createButtonActionPerformed(ActionEvent evt, javax.swing.JList jList1, javax.swing.JTextField name){
        
    }
    private void createButtonActionPerformed(ActionEvent evt, javax.swing.JTextField field1, javax.swing.JTextField field2, javax.swing.JTextField field3){
        
    }
    
    private void AppPickContent(ActionEvent evt){
        javax.swing.JComboBox cb = (javax.swing.JComboBox)evt.getSource();
        String app = (String)cb.getSelectedItem();
        String[] creds = manager.getCredentials(app);
        javax.swing.DefaultListModel listModel = new DefaultListModel();
        for(String item : creds){
            listModel.addElement(item);
        }
        aList.setModel(listModel);
    }
    private void AppPickUser(ActionEvent evt){
        javax.swing.JComboBox cb = (javax.swing.JComboBox)evt.getSource();
        String group = (String)cb.getSelectedItem();
        String[] users = manager.getUsers(group);
        javax.swing.DefaultListModel listModel = new DefaultListModel();
        for(String item : users){
            listModel.addElement(item);
        }
        aList.setModel(listModel);
    }
}