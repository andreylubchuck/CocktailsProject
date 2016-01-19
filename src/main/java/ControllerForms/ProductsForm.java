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

public class ProductsForm extends JFrame {
    Forms fr = new Forms();
    public static JButton insert, back, csv;
    public static JLabel l1, l2, l3, products, rpname;
    public static JTextField t1, t2, t3, j1, replacefield;
    public static final String INSERT = "INSERT INTO producttable(name, quantity, price, other) VALUES (?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE producttable SET quantity = ?, name = ?, price = ?, other = ? WHERE name = ?";
    public static final String query = "SELECT name, quantity, price, other FROM producttable";

    eHandler handler = new eHandler();

    public ProductsForm(String s) {
        super(s);
        setLayout(null);
        insert = new JButton("Insert");
        csv = new JButton("Import from CSV");
        back = new JButton("Back");
        l1 = new JLabel("Enter the name of the product");
        l2 = new JLabel("Enter the number");
        l3 = new JLabel("Enter the price");
        rpname = new JLabel("What can be replaced?");
        products = new JLabel("Products");
        products.setFont(products.getFont().deriveFont(18.0f));
        j1 = new JTextField(20);
        t1 = new JTextField(35);
        t2 = new JTextField(35);
        t3 = new JTextField(35);
        replacefield = new JTextField(35);

        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(t3);
        add(insert);
        add(back);
        add(products);
        add(replacefield);
        add(rpname);
        add(csv);

        insert.addActionListener(handler);
        back.addActionListener(handler);
        csv.addActionListener(handler);
    }


    public class eHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == insert) {
                String a, d, name;
                int b, quantity, newquantity;
                float c;

                Connection connection = null;
                PreparedStatement preparedStatement = null;
                PreparedStatement preparedStatementupdate = null;
                try {
                    a = t1.getText();
                    b = Integer.parseInt(t2.getText());
                    c = Float.parseFloat(t3.getText());
                    d = replacefield.getText();

                    Driver driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);
                    connection = DriverManager.getConnection(ConnectToDatabase.URL, ConnectToDatabase.USERNAME, ConnectToDatabase.PASSWORD);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    preparedStatement = connection.prepareStatement(INSERT);
                    preparedStatementupdate = connection.prepareStatement(UPDATE);

                    boolean check = false;
                    while (resultSet.next()) {
                        name = resultSet.getString("name");
                        quantity = resultSet.getInt("quantity");

                        if (a.equals(name)) {
                            newquantity = b + quantity;
                            preparedStatementupdate.setInt(1, newquantity);
                            preparedStatementupdate.setString(2, a);
                            preparedStatementupdate.setFloat(3, c);
                            preparedStatementupdate.setString(4, d);
                            preparedStatementupdate.setString(5, a);
                            preparedStatementupdate.executeUpdate();
                            check = true;
                        }
                    }
                    if (check == false) {
                        preparedStatement.setString(1, a);
                        preparedStatement.setInt(2, b);
                        preparedStatement.setFloat(3, c);
                        preparedStatement.setString(4, d);
                        preparedStatement.execute();
                    }

                    JOptionPane.showMessageDialog(null, "Data have successfully entered into the database!");
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
                String n, m, name = "";
                int k, quantity, newquantity = 0;
                float l = 0;

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
                    preparedStatementupdate = connection.prepareStatement(UPDATE);

                    String fileName = "Products.csv";
                    File file = new File(fileName);
                    try {
                        Scanner inputStream = new Scanner(file);
                        while (inputStream.hasNext()) {
                            String data = inputStream.next();
                            String[] values = data.split(";");
                            n = values[0];
                            k = Integer.parseInt(values[1]);
                            l = Float.parseFloat(values[2]);
                            m = values[3];

                            boolean check1 = false;
                            while (resultSet.next()) {
                                name = resultSet.getString("name");
                                quantity = resultSet.getInt("quantity");

                                if (n.equals(name)) {
                                    newquantity = k + quantity;
                                    preparedStatementupdate.setInt(1, newquantity);
                                    preparedStatementupdate.setString(2, n);
                                    preparedStatementupdate.setFloat(3, l);
                                    preparedStatementupdate.setString(4, m);
                                    preparedStatementupdate.setString(5, n);
                                    preparedStatementupdate.executeUpdate();
                                    check1 = true;
                                }
                            }

                            preparedStatement.setString(1, n);
                            preparedStatement.setInt(2, k);
                            preparedStatement.setFloat(3, l);
                            preparedStatement.setString(4, m);
                            preparedStatement.execute();
                        }
                        inputStream.close();
                        JOptionPane.showMessageDialog(null, "Imports from the CSV file success!");
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
