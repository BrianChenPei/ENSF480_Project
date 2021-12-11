package SystemUI;

import java.util.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color.*;
import javax.swing.SwingConstants;
import javax.swing.event.*;



import javax.swing.ScrollPaneConstants;

import Property.*;
import PRMS.*;
import Users.*;

/**
 * @version 1.0
 * @since 1.0
 * @author Kaitlin Culligan <a
 * href="mailto: kaitlin.culligan@ucalgary.ca">kaitlin.culligan@ucalgary.ca</a>
 */

public class GUI extends JFrame{
    //frame components
    private JFrame frame;
    private JMenuBar menu;
    private JPanel loginPanel;
    private JPanel searchPanel;
    private JPanel propertyPanel;
    private JPanel emailPanel;
    private JPanel landlordPanel;
    private JPanel managerPanel;
    private JPanel notificationPanel;
    private JPanel previousPanel;
    private JPanel listPanel;
    private JPanel registerPanel;
    private JPanel modifyPanel;
    private JPanel userInfoPanel;

    //control variables
    PRMS prms;
    boolean loggedIn = false;
    boolean notifications = true;

    /**
     * Constructor
     */
    public GUI(){
        prms = new PRMS();

        createLoginPanel();
        createSearchPanel();
        createLandlordPanel();
        createManagerPanel();
        createNotificationPanel();
        createFrame();
    }

    /**
     * logins in user
     * @param username username used
     * @param password password used
     */
    public void login(String username, String password){
         loggedIn = Login.getOnlyInstance().login(username, password);
         if(loggedIn){
             createManagerPanel();
             createLandlordPanel();
         }
    }

    /**
     * logs out logged in user
     */
    public void logout(){
        loggedIn = Login.getOnlyInstance().logout();
        createManagerPanel();
        createLandlordPanel();
        updateMenuBar();
   }

   /**
    * used for frame initialization and intial panel creation
    */
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

    /**
     * sets up the login panel
     */
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
                        JButton logoutButton = new JButton("Log Out");
                        logoutButton.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent evt){
                                logout();
                                menu.remove(4);
                                createLoginPanel();
                                frame.setContentPane(loginPanel);
                                frame.revalidate();
                            }
                        });
                        menu.remove(4);
                        loginPanel.add(logoutButton, c);
                        updateMenuBar();
                    }
                    frame.validate();
                }
            }
        );


        loginPanel.add(enterButton,c);
    }

    /**
     * sets up the search panel
     */
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
                int bedrooms = Integer.parseInt(bedroomField.getText());
                int bathrooms = Integer.parseInt(bathroomField.getText());
                boolean furnished;
                if(furnField.getText().toLowerCase().equals("furnished")){
                    furnished = true;
                }else{
                    furnished = false;
                }
                Property searchCriteria = new Property(typeField.getText(), bedrooms, bathrooms, furnished, quadField.getText());
                ArrayList<Property> results = prms.getProperties(searchCriteria);
                //update propertyPanel
                createPropertyPanel(results);
                 frame.setContentPane(propertyPanel);
                 frame.validate();
            }
        }
    );
        searchPanel.add(searchButton,c);
    }

    /**
     * sets up the property search results panel
     */
    private void createPropertyPanel(ArrayList<Property> results){
        propertyPanel = new JPanel(new BorderLayout());
        ArrayList<String> lists = new ArrayList<>(15);
        //make Strings out of query results
        for(int i = 0; i < 15; i++){
            String p = results.get(i).getType();
            p +='/';
            p+=  results.get(i).getQuadrant();
            p +='/';
            p+=  results.get(i).getBedRoom() + "bedrooms";
            p +='/';
            p+=  results.get(i).getBathroom() + "bathrooms";
            if(results.get(i).getFurnish()){
                p +='/';
                p+=  "Furnished";
            }
            else{
                p +='/';
                p+=  "Not Furnished";
            }
            p +='/';
            p+=  results.get(i).getQuadrant();
            lists.add(p);
        }
        JScrollPane scroll = new JScrollPane();
        JList<String> list = new JList<String>(lists.toArray(new String[lists.size()]));
        list.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                int i = list.getSelectedIndex();
                Property p = results.get(i);
                createEmailPanel(p);
                previousPanel = propertyPanel;
                frame.setContentPane(emailPanel);
                frame.validate();
            }
        });
        scroll.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        scroll.createVerticalScrollBar();
        propertyPanel.add("Center", scroll);
    }

    /**
     *  sets up the email panel
     * @param p property being emailed about
     */
    private void createEmailPanel(Property p){
        emailPanel = new JPanel(new BorderLayout());
        emailPanel.add("North", new JLabel("Send email to landlord of: "+ p.getQuadrant()));
        JTextArea writingSpace = new JTextArea(30, 50);
        emailPanel.add("Center", writingSpace);
        JTextArea emailSpace = new JTextArea("Email address:", 30, 50);
        emailPanel.add("South", emailSpace);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    JOptionPane.showMessageDialog(null, "Email has been sent",
                    "Email Sent", JOptionPane.PLAIN_MESSAGE);
                    frame.setContentPane(previousPanel);
                     frame.validate();
                }
            }
        );
        emailPanel.add("East", sendButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    frame.setContentPane(previousPanel);
                    frame.validate();
                }
            }
        );
        emailPanel.add("West", backButton);

        
    }

    /**
     * sets up the landlord menu
     */
    private void createLandlordPanel(){
        if(loggedIn && Login.getOnlyInstance().getType().equals("Landlord")){
            landlordPanel = new JPanel(new BorderLayout());
            //create a menu bar for the buttons for landlord
            JMenuItem list = new JMenuItem("List");
            list.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        createListPanel();
                        frame.setContentPane(listPanel);
                        frame.validate();
                    }
                }
            );
            JMenuItem modify = new JMenuItem("Modify");
            modify.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        createModifyPanel();
                        frame.setContentPane(modifyPanel);
                        frame.validate();
                    }
                }
            );
            JMenuBar landlordBar = new JMenuBar();
            landlordBar.add(list);
            landlordBar.add(modify);

            landlordPanel.add("South", landlordBar);
        }else{
            landlordPanel = new JPanel(new BorderLayout());
            landlordPanel.add("South", new JLabel("Must sign in to access this page"));
        }
    }

    /**
     * sets up the manager menu
     */
    private void createManagerPanel(){
        //create menu bar for buttons
        
        if(loggedIn && Login.getOnlyInstance().getType().equals("Manager")){
            managerPanel = new JPanel(new BorderLayout());
            JMenuItem report = new JMenuItem("Report");
            report.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        String start = JOptionPane.showInputDialog(null, "Please enter the starting month for the report");
                        String end = JOptionPane.showInputDialog(null, "Please enter the ending month for the report");
                        JTextArea reportWriteup = new JTextArea();
                        String placeholder = prms.askReport(start, end);
                        reportWriteup.setColumns(30);
                        reportWriteup.setRows(20);
                        reportWriteup.setText(placeholder);
                        managerPanel.add("Center", reportWriteup);
                        frame.validate();
                    }
                }
            );
            JMenuItem fees = new JMenuItem("Edit Fees");
            fees.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        int amount = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the new amount in dollars:"));
                        int time = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the new time in days:"));
                        prms.changeFee(amount);
                        prms.changeFeePeriod(time);
                    }
                }
            );
            JMenuItem userInfo = new JMenuItem("User Info");
            userInfo.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        createUserInfoPanel();
                        frame.setContentPane(userInfoPanel);
                        frame.validate();
                    }
                }
            );
            JMenuItem properties = new JMenuItem("Properties");
            properties.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        createModifyPanel();
                        frame.setContentPane(modifyPanel);
                        frame.validate();
                    }
                }
            );
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

    /**
     * sets up the notification page for registered renters
     */
    private void createNotificationPanel(){
        notificationPanel = new JPanel(new BorderLayout());
        ArrayList<String> lists = new ArrayList<>(15);
        //make Strings out of query results
        for(int i = 0; i < 15; i++){
            lists.add("TESTTESTTESTESTESTESTESTESTESTESTESTESTESTESTEST"+String.valueOf(i));
        }
        JScrollPane scroll = new JScrollPane();
        JList<String> list = new JList<String>(lists.toArray(new String[lists.size()]));
        list.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                int i = list.getSelectedIndex();
               //Property p = results.get(i); What does this mean here? --Andres
                //createEmailPanel(p);
                previousPanel = notificationPanel;
                frame.setContentPane(emailPanel);
                frame.validate();
            }
        });
        scroll.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        scroll.createVerticalScrollBar();
        notificationPanel.add("Center", scroll);

        JButton optOutButton = new JButton("Notifications On");
        optOutButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    if(Login.getOnlyInstance().getRegisteredRenter().getObserver().checkSubscribed(Login.getOnlyInstance().getRegisteredRenter())){
                        Login.getOnlyInstance().getRegisteredRenter().getObserver().unsubscribe(Login.getOnlyInstance().getRegisteredRenter());
                        optOutButton.setText("Notifications Off");
                    }else{
                        Login.getOnlyInstance().getRegisteredRenter().getObserver().subscribe(Login.getOnlyInstance().getRegisteredRenter());
                        optOutButton.setText("Notifications On");
                    }
                    frame.validate();
                }
            }
        );
        notificationPanel.add("South", optOutButton);
    }

    /**
     * sets up the listing page for landlords
     */
    private void createListPanel(){
        listPanel = new JPanel();
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(0,0,00,0);
        c.gridx = 0;
        c.gridy = 1;
        listPanel.add(new JLabel("Type:"),c);
        c.gridx = 2;
        c.gridy = 1;
        JTextField typeField = new JTextField(20);
        listPanel.add(typeField,c);

        c.gridx = 0;
        c.gridy = 2;
        listPanel.add(new JLabel("Number of Bedrooms:"),c);
        c.gridx = 2;
        c.gridy = 2;
        JTextField bedroomField = new JTextField(20);
        listPanel.add(bedroomField,c);

        c.gridx = 0;
        c.gridy = 3;
        listPanel.add(new JLabel("Number of Bathrooms:"),c);
        c.gridx = 2;
        c.gridy = 3;
        JTextField bathroomField = new JTextField(20);
        listPanel.add(bathroomField,c);

        c.gridx = 0;
        c.gridy = 4;
        listPanel.add(new JLabel("Furnished/Unfurnished:"),c);
        c.gridx = 2;
        c.gridy = 4;
        JTextField furnField = new JTextField(20);
        listPanel.add(furnField,c);

        c.gridx = 0;
        c.gridy = 5;
        listPanel.add(new JLabel("Quadrant:"),c);
        c.gridx = 2;
        c.gridy = 5;
        JTextField quadField = new JTextField(20);
        listPanel.add(quadField,c);

        c.gridx = 2;
        c.gridy = 6;
        c.insets = new Insets(0,0,50,0);
        JButton listButton = new JButton("List");
        listButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    int bedrooms = Integer.parseInt(bedroomField.getText());
                    int bathrooms = Integer.parseInt(bathroomField.getText());
                    boolean furnished;
                    if(furnField.getText().toLowerCase().equals("furnished")){
                        furnished = true;
                    }else{
                        furnished = false;
                    }
                    Property newListing = new Property(typeField.getText(), bedrooms, bathrooms, furnished, quadField.getText());
                    newListing.setLandlordName(Login.getOnlyInstance().getLandlord().getFname()+" "+Login.getOnlyInstance().getLandlord().getLname());
                    newListing.setLandlordEmail(Login.getOnlyInstance().getLandlord().getEmail());
                    prms.registerProperty(newListing);
                JOptionPane.showMessageDialog(null, "Press the OK button below to pay",
                    "Please Pay", JOptionPane.PLAIN_MESSAGE);
                //update prms again
                 frame.setContentPane(landlordPanel);
                 frame.validate();
                  }
              }
        );
        listPanel.add(listButton,c);

        c.gridx = 0;
        c.gridy = 6;
        c.insets = new Insets(0,0,50,0);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                 frame.setContentPane(landlordPanel);
                 frame.validate();
                 }
             }
       );
        listPanel.add(backButton,c);
    }

    /**
     * sets up the new user register page
     */
    private void createRegisterPanel(){
        registerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.7;
        c.weighty = 0.7;
        registerPanel.add(new JLabel("Username:"),c);
        c.gridx = 2;
        JTextField usernameField = new JTextField(20);
        registerPanel.add(usernameField,c);
        c.gridx = 0;
        c.gridy = 3;
        registerPanel.add(new JLabel("Password:"),c);
        c.gridx =2;
        JTextField passwordField = new JTextField(20);
        registerPanel.add(passwordField, c);
        c.gridx = 0;
        c.gridy = 4;
        registerPanel.add(new JLabel("Type:"),c);
        c.gridx =2;
        JTextField typeField = new JTextField(20);
        registerPanel.add(typeField, c);
        c.gridx = 0;
        c.gridy = 5;
        registerPanel.add(new JLabel("First Name:"),c);
        c.gridx =2;
        JTextField fNameField = new JTextField(20);
        registerPanel.add(fNameField, c);
        c.gridx = 0;
        c.gridy = 6;
        registerPanel.add(new JLabel("Last Name:"),c);
        c.gridx =2;
        JTextField lNameField = new JTextField(20);
        registerPanel.add(lNameField, c);
        c.gridx = 0;
        c.gridy = 7;
        registerPanel.add(new JLabel("Email:"),c);
        c.gridx =2;
        JTextField emailField = new JTextField(20);
        registerPanel.add(emailField, c);

        c.gridx = 1;
        c.gridy = 8;
        JButton enterButton = new JButton("Register");
        enterButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    //register in system
                    String userName = usernameField.getText();
                    String type = typeField.getText();
                    String fname = fNameField.getText();
                    String lname = lNameField.getText();
                    String email = emailField.getText();
                    String password = passwordField.getText();
                    if(typeField.getText().equals("Landlord")){
                        prms.addUser(new Landlord(userName, type, fname, lname, email, password));
                    }else if(typeField.getText().equals("Manager")){
                        prms.addUser(new Manager(userName, type, fname, lname, email, password));
                    }else if(typeField.getText().equals("Registered Renter")){
                        prms.addUser(new RegisteredRenter(userName, type, fname, lname, email, password));
                    }
                    frame.validate();
                }
            }
        );


        registerPanel.add(enterButton,c);
}
    
    /**
     * sets up the modify existing listing panel for landlords and managers
     */
    private void createModifyPanel(){
        modifyPanel = new JPanel(new BorderLayout());
        ArrayList<Property> results;
        if(Login.getOnlyInstance().getType().equals("Landlord")){
            results = prms.landlordPropertyEdit(Login.getOnlyInstance().getLandlord().getFname()+" "+ Login.getOnlyInstance().getLandlord().getFname());
        }
        else{
            results = prms.managerPropertyEdit();
        }
        ArrayList<String> lists = new ArrayList<>(15);
        //make Strings out of query results
        for(int i = 0; i < 15; i++){
            String p = results.get(i).getType();
            p +='/';
            p+=  results.get(i).getQuadrant();
            p +='/';
            p+=  results.get(i).getBedRoom() + "bedrooms";
            p +='/';
            p+=  results.get(i).getBathroom() + "bathrooms";
            if(results.get(i).getFurnish()){
                p +='/';
                p+=  "Furnished";
            }
            else{
                p +='/';
                p+=  "Not Furnished";
            }
            p +='/';
            p+=  results.get(i).getQuadrant();
            lists.add(p);
        }
        JScrollPane scroll = new JScrollPane();
        JList<String> list = new JList<String>(lists.toArray(new String[lists.size()]));
        list.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                int i = list.getSelectedIndex();
                Property p = results.get(i);
                String state = JOptionPane.showInputDialog(null, "Please enter the new listing state");
                prms.changeState(Integer.parseInt(p.getID()), state);
            }
        });


        scroll.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        scroll.createVerticalScrollBar();
        modifyPanel.add("Center", scroll);
    }

    /**
     * sets up the user info page for managers
     */
    private void createUserInfoPanel(){
       userInfoPanel = new JPanel(new BorderLayout());

        ArrayList<String> lists = new ArrayList<>(15);
        //make Strings out of query results
        for(int i = 0; i < 15; i++){
            lists.add("TESTTESTTESTESTESTESTESTESTESTESTESTESTESTESTEST"+String.valueOf(i));
        }
        JScrollPane scroll = new JScrollPane();
        JList<String> list = new JList<String>(lists.toArray(new String[lists.size()]));
        list.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                //stuff here
            }
        });
        scroll.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        scroll.createVerticalScrollBar();
        userInfoPanel.add("Center", scroll);
    }

    /**
     * updates the menu bar according to logged in status
     */
    private void updateMenuBar(){
        if(!loggedIn){
            JMenuItem register = new JMenuItem("Register");
            register.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        createRegisterPanel();
                        frame.setContentPane(registerPanel);
                        frame.validate();
                }
            }
            );
            menu.add(register);
        }
        else if(loggedIn && Login.getOnlyInstance().getType().equals("Registered Renter")){
            JMenuItem notif = new JMenuItem("Notifications");
            notif.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        notif.setBackground(new JButton().getBackground());
                        frame.setContentPane(notificationPanel);
                        frame.validate();
                    }
                }
            );
            
            //if theres new listings and notifications is on
            if(Login.getOnlyInstance().getRegisteredRenter().getObserver().checkSubscribed(Login.getOnlyInstance().getRegisteredRenter())){
               notif.setBackground(Color.RED);
            }
            menu.add(notif);   
        }
    }

    /**
     * creates the menu bar
     */
    private void createMenuBar(){
        JMenuItem register = new JMenuItem("Register");
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

        register.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    createRegisterPanel();
                    frame.setContentPane(registerPanel);
                    frame.validate();
            }
        }
        );

        menu = new JMenuBar();
        menu.add(log);
        menu.add(search);
        menu.add(landlord);
        menu.add(manager);
        menu.add(register);
    }

    public static void main(String[] args){
        GUI g = new GUI();

    }
}


