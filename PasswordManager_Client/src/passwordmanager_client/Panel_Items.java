/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager_client;

import java.awt.Dimension;

/**
 *
 * @author Cole
 */
public class Panel_Items {
    
    private String Panel;
    private fieldItem[] fieldList;
    private comboboxItem[] comboList;
    private labelItem[] labelList;
    private listItem[] listList;
    
    public Panel_Items(String Pan){
        Panel = Pan;
        
    }
    
    public javax.swing.JTextField getFieldItem(String t){
        return getFieldItems(t).field;
    }
    public javax.swing.JPasswordField getPassFieldItem(String t){
        return getFieldItems(t).password;
    }
    private fieldItem getFieldItems(String t){
        for(fieldItem fieldName : fieldList){
            if(fieldName.name.equals(t)) return fieldName;
        }
        return null;
    }
    public void addFieldItem(String name, boolean pass){
        if(fieldList == null){
            fieldList = new fieldItem[1];
            fieldList[0] = new fieldItem(name, pass);
        }
        else {
            fieldItem[] tempList = new fieldItem[(fieldList.length + 1)];
            int x = 0;
            while(x < fieldList.length){
                tempList[x] = fieldList[x];
                x++;
            }
            tempList[x] = new fieldItem(name, pass);
            fieldList = tempList;
        }
    }
    public javax.swing.JComboBox<String> getComboboxItem(String t){
        for(comboboxItem comboName : comboList){
            if(comboName.name.equals(t)) return comboName.AppPick;
        }
        return null;
    }
    public void addComboboxItem(String name, String[] elem){
        if(comboList == null){
            comboList = new comboboxItem[1];
            comboList[0] = new comboboxItem(name, elem);
        }
        else {
            comboboxItem[] tempList = new comboboxItem[(comboList.length + 1)];
            int x = 0;
            while(x < comboList.length){
                tempList[x] = comboList[x];
                x++;
            }
            tempList[x] = new comboboxItem(name, elem);
            comboList = tempList;
        }
    }
    public javax.swing.JList getListItem(String t){
        for(listItem listName : listList){
            if(listName.name.equals(t)) return listName.jList1;
        }
        return null;
    }
    public void addListItem(String name, String[] elem){
        listItem tempListItem;
        if(listList == null){
            listList = new listItem[1];
            tempListItem = new listItem(name, elem);
            listList[0] = tempListItem;
        }
        else {
            listItem[] tempList = new listItem[(listList.length + 1)];
            int x = 0;
            while(x < listList.length){
                tempList[x] = listList[x];
                x++;
            }
            tempListItem = new listItem(name, elem);
            tempList[x] = tempListItem;
            listList = tempList;
        }
    }
    public javax.swing.JLabel getLabelItem(String t){
        for(labelItem labelText : labelList){
            if(labelText.name.equals(t)) return labelText.label;
        }
        return null;
    }
    public void addLabelItem(String text, String name){
        if(labelList == null){
            labelList = new labelItem[1];
            labelList[0] = new labelItem(text, name);
        }
        else {
            labelItem[] tempList = new labelItem[(labelList.length + 1)];
            int x = 0;
            while(x < labelList.length){
                tempList[x] = labelList[x];
                x++;
            }
            tempList[x] = new labelItem(text, name);
            labelList = tempList;
        }
    }
    
    private class fieldItem {
        public javax.swing.JTextField field;
        public javax.swing.JPasswordField password;
        public String name;
        public fieldItem(String n, boolean pass){
            name = n;
            if (pass) password = new javax.swing.JPasswordField();
            else{
                field = new javax.swing.JTextField();
                field.setText(name);
            }
        }
        
    }
    private class comboboxItem {
        public javax.swing.JComboBox<String> AppPick;
        public String name;
        public comboboxItem(String n, String[] items){
            name = n;
            AppPick = new javax.swing.JComboBox<>();
            
            AppPick.setModel(new javax.swing.DefaultComboBoxModel<>(items));
            AppPick.setFont(new java.awt.Font("Tahoma", 1, 20));
            AppPick.setPreferredSize(new Dimension(60, 30));
        }
    }
    private class listItem {
        public javax.swing.JList jList1;
        public String name;
        public listItem(String n, String[] items){
            name = n;
            jList1 = new javax.swing.JList<>();
            
            jList1.setModel(new javax.swing.AbstractListModel<String>() {
                String[] strings = items;
                public int getSize() { return strings.length; }
                public String getElementAt(int i) { return strings[i]; }
            });
        }
    }
    private class labelItem {
        public javax.swing.JLabel label;
        public String text,name;
        public labelItem(String t,String n){
            name = n;
            text = t;
            label = new javax.swing.JLabel();
            label.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
            label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            label.setText(t);
            
        }
    }
}
