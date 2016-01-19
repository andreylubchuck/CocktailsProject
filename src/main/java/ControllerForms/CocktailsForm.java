package ControllerForms;

import GuiForms.Forms;
import com.angelx.builder.ConnectToDatabase;
import com.mysql.fabric.jdbc.FabricMySQLDriver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class CocktailsForm extends JFrame {
    Forms fr = new Forms();
    public static JButton insert, back, csv;
    public static JLabel l1, l2, l3, products, rpname;
    public static JTextField t1, t2, t3, j1, t4;
    public static final String INSERT = "INSERT INTO cocktails(name, price, type, recipes) VALUES (?, ?, ?, ?)";
    public static final String query = "SELECT name, price, type, recipes FROM cocktails";

    eHandler handler = new eHandler();

    public CocktailsForm(String s) {
        super(s);
        setLayout(null);
        insert = new JButton("Insert");
        csv = new JButton("Import from CSV");
        back = new JButton("Back");
        l1 = new JLabel("Enter the name of the cocktail");
        l2 = new JLabel("Enter the price");
        l3 = new JLabel("Enter the type(Alcohol-1, No-Alcohol-2)");
        rpname = new JLabel("Enter the recipe for cocktail");
        products = new JLabel("Cocktails");
        products.setFont(products.getFont().deriveFont(18.0f));
        j1 = new JTextField(20);
        t1 = new JTextField(35);
        t2 = new JTextField(35);
        t3 = new JTextField(35);
        t4 = new JTextField(35);
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(t3);
        add(insert);
        add(back);
        add(products);
        add(t4);
        add(rpname);
        add(csv);
        insert.addActionListener(handler);
        back.addActionListener(handler);
        csv.addActionListener(handler);
    }

    public class eHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == insert) {
                String namec, name, recipes = "";
                int type;
                float price;

                Connection connection = null;
                PreparedStatement preparedStatement = null;
                PreparedStatement preparedStatementupdate = null;
                try {
                    name = t1.getText();
                    price = Float.parseFloat(t2.getText());
                    type = Integer.parseInt(t3.getText());
                    recipes = t4.getText();

                    Driver driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);
                    connection = DriverManager.getConnection(ConnectToDatabase.URL, ConnectToDatabase.USERNAME, ConnectToDatabase.PASSWORD);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    preparedStatement = connection.prepareStatement(INSERT);
                    boolean check = false;

                    while (resultSet.next()) {
                        namec = resultSet.getString("name");

                        if (name.equals(namec)) {
                            JOptionPane.showMessageDialog(null, "This cocktail is already in the database! You can update the data in the warehouse management menu!");
                            check = true;
                        }
                    }
                    if (check = false) {
                        preparedStatement.setString(1, name);
                        preparedStatement.setFloat(2, price);
                        preparedStatement.setInt(3, type);
                        preparedStatement.setString(4, recipes);
                        preparedStatement.execute();
                        JOptionPane.showMessageDialog(null, "Data have successfully entered into the database!");
                    }
                } catch (SQLException e1) {
                    System.out.println("You have an error in SQL");
                    JOptionPane.showMessageDialog(null, "You have an error in SQL");
                    e1.printStackTrace();
                } catch (Exception e1) {
                    System.out.println("You have an error");
                    JOptionPane.showMessageDialog(null, "You have an error");
                    e1.printStackTrace();
                } finally {
                }
            }
            if (e.getSource() == csv) {
                String namec, name, recipes, data = "";
                int type;
                float price;
                String[] values = {};

                Connection connection = null;
                PreparedStatement preparedStatement = null;
                PreparedStatement preparedStatementupdate = null;
                try {
                    Driver driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);
                    connection = DriverManager.getConnection(ConnectToDatabase.URL, ConnectToDatabase.USERNAME, ConnectToDatabase.PASSWORD);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    preparedStatement = connection.prepareStatement(INSERT);

                    String fileName = "Cocktails.csv";
                    File file = new File(fileName);
                    try {
                        Scanner inputStream = new Scanner(file);
                        while (inputStream.hasNext()) {
                            data = inputStream.next();
                            values = data.split(";");
                            name = values[0];
                            price = Float.parseFloat(values[1]);
                            type = Integer.parseInt(values[2]);
                            recipes = values[3];
                            boolean check1 = false;

                            while (resultSet.next()) {
                                namec = resultSet.getString("name");
                                if (name.equals(namec)) {
                                    JOptionPane.showMessageDialog(null, "This cocktail is already in the database! You can update the data in the warehouse management menu!");
                                    check1 = true;
                                }
                            }
                            if (check1 = false) {
                                preparedStatement.setString(1, name);
                                preparedStatement.setFloat(2, price);
                                preparedStatement.setInt(3, type);
                                preparedStatement.setString(4, recipes);
                                preparedStatement.execute();
                                JOptionPane.showMessageDialog(null, "Imports from the CSV file success!");
                            }

                        }
                        inputStream.close();
                    } catch (FileNotFoundException e1) {
                        JOptionPane.showMessageDialog(null, "File not found!");
                        e1.printStackTrace();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Number Format Exception!");
                        ex.printStackTrace();
                    }
                } catch (SQLException e1) {
                    System.out.println("You have an error in SQL");
                    JOptionPane.showMessageDialog(null, "You have an error in SQL");
                    e1.printStackTrace();
                } catch (Exception e1) {
                    System.out.println("You have an error");
                    JOptionPane.showMessageDialog(null, "You have an error");
                    e1.printStackTrace();
                } finally {
                }
            }
            if (e.getSource() == back) {
                setVisible(false);
                fr.Warehouseman();
            }
        }
    }
}
