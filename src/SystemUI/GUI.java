package SystemUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public void display(){

    }

    /*public Login login(){

    }*/

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
                  //  frame.setContentPane(propertyPanel);
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
                 frame.setContentPane(propertyPanel);
                 frame.validate();
            }
        }
    );
        searchPanel.add(searchButton,c);
    }

    private void createPropertyPanel(){
        propertyPanel = new JPanel();
        propertyPanel.add(new JLabel("Property"));
    }

    private void createEmailPanel(){
        
    }

    private void createLandlordPanel(){

        //create a menu bar for the buttons for landlord
        JMenuItem list = new JMenuItem("List");
        JMenuItem modify = new JMenuItem("Modify");
        JMenuBar landlordBar = new JMenuBar();
        landlordBar.add(list);
        landlordBar.add(modify);

        landlordPanel.add("South", landlordBar);
    }

    private void createManagerPanel(){
        managerPanel = new JPanel(new BorderLayout());
        createMenuBar();
        managerPanel.add("North", menu);
        //create menu bar for buttons
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
    }

    private void createMenuBar(){
        JMenuItem log = new JMenuItem("Login");
        JMenuItem search = new JMenuItem("Search");
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
        menu = new JMenuBar();
        menu.add(log);
        menu.add(search);
    }

    public static void main(String[] args){
        GUI g = new GUI();
        g.createLoginPanel();
        g.createSearchPanel();
        g.createPropertyPanel();
        g.createFrame();
    }
}