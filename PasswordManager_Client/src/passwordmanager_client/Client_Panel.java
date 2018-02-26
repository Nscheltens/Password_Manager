/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager_client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 *
 * @author Nick Scheltens
 */
public class Client_Panel extends JPanel implements ActionListener{
    
    private javax.swing.JButton G1Button, G2Button, SelectButton, EditButton, LoginButton;
    private javax.swing.JPanel Panel1,Panel2,Panel3, logPanel;
    private javax.swing.JComboBox<String> AppPick;
    private javax.swing.JTextArea passTextArea;
    private javax.swing.JScrollPane jScrollPane1;
    private JSplitPane splitPaneH, splitPaneV;
    private JFrame fr = new JFrame("PASSWORD MANAGER");
    private javax.swing.JPasswordField LoginPassField;
    private javax.swing.JTextField LoginTextField;
    private java.awt.Label LoginLabel;
    
    public Client_Panel(){
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        Dimension sz = new Dimension((9*d.width)/10,(9*d.height)/10);
        int width = sz.width;
        int height = sz.height;
        
        fr.setSize(sz);
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        fr.getContentPane().add(topPanel);
        
        Panel1 = createSidePanel();
        
        Panel2 = createContentPanel(width, height);
       
        logPanel = createLogPanel();
        
        Panel3 = createBotPanel();
        
        splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        System.out.println(width+" "+width/10);
        splitPaneH.setDividerLocation((width)/10);
        
        splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        System.out.println(height+" "+(9*height)/10);
        splitPaneV.setDividerLocation((9*height)/10);
        
        splitPaneV.setLeftComponent(logPanel);
        splitPaneV.setRightComponent(Panel3);
        
        splitPaneH.setLeftComponent(Panel1);
        splitPaneH.setRightComponent(splitPaneV);
        topPanel.add(splitPaneH);
        
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLocation(10, 10);
        fr.setResizable(false);
        fr.setVisible(true);
    }
    private JPanel createContentPanel(int width, int height){
        
        int p2width = width - ((width)/10);
        int p2height = (9*height)/10;
        
        JPanel Pan = new javax.swing.JPanel();
        Pan.setBackground(Color.CYAN);
        
        AppPick = new javax.swing.JComboBox<>();
        AppPick.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        AppPick.setFont(new java.awt.Font("Tahoma", 1, 20));
        AppPick.setPreferredSize(new Dimension(60, 30));
        
        jScrollPane1 = new javax.swing.JScrollPane();
        
        passTextArea = new javax.swing.JTextArea();
        passTextArea.setEditable(false);
        passTextArea.setColumns(20);
        passTextArea.setRows(5);
        jScrollPane1.setViewportView(passTextArea);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(Pan);
        Pan.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1536, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, ((p2height*86)/100), javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        return Pan;
    }
    
    //make layout dynamic, fix location issues
    private JPanel createLogPanel(){
        JPanel log = new javax.swing.JPanel();
        log.setBackground(Color.GREEN);
        
        LoginPassField = new javax.swing.JPasswordField();
        LoginTextField = new javax.swing.JTextField();
        LoginLabel = new java.awt.Label();
        LoginButton = new javax.swing.JButton();

        LoginTextField.setText("Username");

        LoginLabel.setAlignment(java.awt.Label.CENTER);
        LoginLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        LoginLabel.setText("Login:");

        LoginButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        LoginButton.setText("GO!");
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layoutn = new javax.swing.GroupLayout(log);
        log.setLayout(layoutn);
        layoutn.setHorizontalGroup(
            layoutn.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layoutn.createSequentialGroup()
                .addGap(270, 270, 270)
                .addGroup(layoutn.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LoginButton, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addComponent(LoginPassField)
                    .addComponent(LoginTextField))
                .addContainerGap(1062, Short.MAX_VALUE))
            .addGroup(layoutn.createSequentialGroup()
                .addContainerGap()
                .addComponent(LoginLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layoutn.setVerticalGroup(
            layoutn.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layoutn.createSequentialGroup()
                .addContainerGap(478, Short.MAX_VALUE)
                .addComponent(LoginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LoginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LoginPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(211, 211, 211))
        );
        return log;
    }
    
    private JPanel createBotPanel(){
        JPanel Pan3 = new javax.swing.JPanel();
        Pan3.setBackground(Color.YELLOW);
        Pan3.setLayout(new FlowLayout());
        
        SelectButton = new javax.swing.JButton();
        SelectButton.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        SelectButton.setText("Select!");
        Pan3.add(SelectButton);
        
        EditButton = new javax.swing.JButton();
        EditButton.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        EditButton.setText("Edit!");
        Pan3.add(EditButton);
        return Pan3;
    }
   /*
    *
    *
    */
    private JPanel createSidePanel(){
        JPanel Pan1 = new javax.swing.JPanel();
        Pan1.setBackground(Color.red);
        Pan1.setLayout(new BoxLayout(Pan1, BoxLayout.PAGE_AXIS));
        
        
        G1Button = new javax.swing.JButton();
        G1Button.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        G1Button.setText("GROUP 1!");
        Pan1.add(G1Button);
        
        Pan1.add(Box.createRigidArea(new Dimension(0,5)));
        
        G2Button = new javax.swing.JButton();
        G2Button.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        G2Button.setText("GROUP 2!");
        Pan1.add(G2Button);
        
        Pan1.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        
        return Pan1;
    }
    
    private void LoginButtonActionPerformed(ActionEvent evt) {
        splitPaneV.setLeftComponent(Panel2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}