package SystemUI;

import java.util.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.SwingConstants;
import javax.swing.event.*;



import javax.swing.ScrollPaneConstants;

import Property.*;
import PRMS.*;
import Email.*;
import Users.*;

/**
 * @version 1.0
 * @since 1.0
 * @author Kaitlin Culligan <a
 * href="mailto: kaitlin.culligan@ucalgary.ca">kaitlin.culligan@ucalgary.ca</a>
 */

public class GUI extends JFrame{
    private JFrame frame;
    private JMenuBar menu;
    private JPanel loginPanel;
    private JPanel searchPanel;
    private JPanel propertyPanel;
    private JPanel emailPanel;
    private JPanel landlordPanel;
    private JPanel managerPanel;
    private JPanel notificationPanel;
    boolean loggedIn = false;

    public GUI(){
        Login.getOnlyInstance();
        createLoginPanel();
        createSearchPanel();
        createPropertyPanel();
        createLandlordPanel();
        createManagerPanel();
        createNotificationPanel();
        createFrame();
    }

    public void display(){

    }

    public void login(String username, String password){
         loggedIn = Login.getOnlyInstance().login(username, password);
         if(loggedIn){
             createManagerPanel();
             createLandlordPanel();
         }
    }

    private void createFrame(){
        frame = new JFrame("Property Rental Management System");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        createMenuBar();
        frame.setJMenuBar(menu);
        createSearchPanel();
        frame.setContentPane(searchPanel);
        frame.setVisible(true);
    }

    private void createLoginPanel(){
        loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.7;
        c.weighty = 0.7;
        loginPanel.add(new JLabel("Username:"),c);
        c.gridx = 2;
        JTextField usernameField = new JTextField(20);
        loginPanel.add(usernameField,c);
        c.gridx = 0;
        c.gridy = 3;
        loginPanel.add(new JLabel("Password:"),c);
        c.gridx =2;
        JTextField passwordField = new JTextField(20);
        loginPanel.add(passwordField, c);

        c.gridx = 1;
        c.gridy = 4;
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    login(usernameField.getText(), passwordField.getText());
                    usernameField.setText(" ");
                    passwordField.setText(" ");
                    c.gridx = 2;
                    c.gridy = 4;
                    if(loggedIn){
                        loginPanel.add(new JLabel("Logged in"), c);
                        updateMenuBar();
                    }
                    frame.validate();
                }
            }
        );


        loginPanel.add(enterButton,c);
    }

    private void createSearchPanel(){
        searchPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(0,0,00,0);
        c.gridx = 0;
        c.gridy = 1;
        searchPanel.add(new JLabel("Type:"),c);
        c.gridx = 2;
        c.gridy = 1;
        JTextField typeField = new JTextField(20);
        searchPanel.add(typeField,c);

        c.gridx = 0;
        c.gridy = 2;
        searchPanel.add(new JLabel("Number of Bedrooms:"),c);
        c.gridx = 2;
        c.gridy = 2;
        JTextField bedroomField = new JTextField(20);
        searchPanel.add(bedroomField,c);

        c.gridx = 0;
        c.gridy = 3;
        searchPanel.add(new JLabel("Number of Bathrooms:"),c);
        c.gridx = 2;
        c.gridy = 3;
        JTextField bathroomField = new JTextField(20);
        searchPanel.add(bathroomField,c);

        c.gridx = 0;
        c.gridy = 4;
        searchPanel.add(new JLabel("Furnished/Unfurnished:"),c);
        c.gridx = 2;
        c.gridy = 4;
        JTextField furnField = new JTextField(20);
        searchPanel.add(furnField,c);

        c.gridx = 0;
        c.gridy = 5;
        searchPanel.add(new JLabel("Quadrant:"),c);
        c.gridx = 2;
        c.gridy = 5;
        JTextField quadField = new JTextField(20);
        searchPanel.add(quadField,c);

        c.gridx = 1;
        c.gridy = 6;
        c.insets = new Insets(0,0,50,0);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                //search with PRMS
                //update propertyPanel
                 frame.setContentPane(propertyPanel);
                 frame.validate();
            }
        }
    );
        searchPanel.add(searchButton,c);
    }

    private void createPropertyPanel(){
        propertyPanel = new JPanel();
        ArrayList<String> lists = new ArrayList<>(15);
        //make Strings out of query results
        for(int i = 0; i < 15; i++){
            lists.add("TESTTESTTESTESTESTESTESTESTESTESTESTESTESTESTEST"+String.valueOf(i));
        }
        JScrollPane scroll = new JScrollPane();
        JList<String> list = new JList<String>(lists.toArray(new String[lists.size()]));
        list.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                createEmailPanel(list.getSelectedValue());
                frame.setContentPane(emailPanel);
                frame.validate();
            }
        });
        scroll.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        scroll.createVerticalScrollBar();
        propertyPanel.add(scroll);
    }

    private void createEmailPanel(String s){
        emailPanel = new JPanel(new BorderLayout());
        emailPanel.add("North", new JLabel("Send email to landlord of: "+s));
        JTextArea writingSpace = new JTextArea(30, 50);
        emailPanel.add("Center", writingSpace);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    //send email to landlord
                    frame.setContentPane(propertyPanel);
                     frame.validate();
                }
            }
        );
        emailPanel.add("East", sendButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    frame.setContentPane(propertyPanel);
                    frame.validate();
                }
            }
        );
        emailPanel.add("West", backButton);

        
    }

    private void createLandlordPanel(){
        if(loggedIn){
            landlordPanel = new JPanel(new BorderLayout());
            //create a menu bar for the buttons for landlord
            JMenuItem list = new JMenuItem("List");
            JMenuItem modify = new JMenuItem("Modify");
            JMenuBar landlordBar = new JMenuBar();
            landlordBar.add(list);
            landlordBar.add(modify);

            landlordPanel.add("South", landlordBar);
        }else{
            landlordPanel = new JPanel(new BorderLayout());
            landlordPanel.add("South", new JLabel("Must sign in to access this page"));
        }
    }

    private void createManagerPanel(){
        //create menu bar for buttons
        
        if(loggedIn){
            managerPanel = new JPanel(new BorderLayout());
            JMenuItem report = new JMenuItem("Report");
            JMenuItem fees = new JMenuItem("Edit Fees");
            JMenuItem userInfo = new JMenuItem("User Info");
            JMenuItem properties = new JMenuItem("Properties");
            JMenuBar managerBar = new JMenuBar();
            managerBar.add(report);
            managerBar.add(fees);
            managerBar.add(userInfo);
            managerBar.add(properties);
            managerPanel.add("South", managerBar);
        }else{
            managerPanel = new JPanel(new BorderLayout());
            managerPanel.add("South", new JLabel("Must sign in to access this page"));
        }
    }

    private void createNotificationPanel(){
        notificationPanel = new JPanel(new BorderLayout());

    }

    private void updateMenuBar(){
        JMenuItem notif = new JMenuItem("Notifications");
        notif.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    frame.setContentPane(notificationPanel);
                    frame.validate();
                }
            }
        );
        menu.add(notif);   
    }

    private void createMenuBar(){
        JMenuItem log = new JMenuItem("Login");
        JMenuItem search = new JMenuItem("Search");
        JMenuItem landlord = new JMenuItem("Landlord");
        JMenuItem manager = new JMenuItem("Manager");
        log.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    frame.setContentPane(loginPanel);
                    frame.validate();
                }
            }
        );

       search.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    frame.setContentPane(searchPanel);
                    frame.validate();
            }
        }
        );
        
        landlord.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    frame.setContentPane(landlordPanel);
                    frame.validate();
            }
        }
        );

        manager.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    createManagerPanel();
                    frame.setContentPane(managerPanel);
                    frame.validate();
            }
        }
        );
        menu = new JMenuBar();
        menu.add(log);
        menu.add(search);
        menu.add(landlord);
        menu.add(manager);
    }

    public static void main(String[] args){
        GUI g = new GUI();

    }
}