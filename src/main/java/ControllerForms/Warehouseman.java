package ControllerForms;

import GuiForms.Forms;
import com.angelx.builder.ConnectToDatabase;
import com.mysql.fabric.jdbc.FabricMySQLDriver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;

public class Warehouseman extends JFrame {
    public static Forms fr = new Forms();
    public static JButton menu, products1, back, updatec, deletec, updatep, deletep, updatet, deletet, insertp, insertc;
    public static JLabel stock, cocktails, namec, pricec, typec, recipec, products, namep, pricep, quantityp, otherp, packing, quantityt, namet, pricet, typet, insert;
    public static JTextField jt1, jt2, jt3, jt4, jt1p, jt2p, jt3p, jt4p, jt1t, jt2t, jt3t, jt4t;
    public static JComboBox combobox, comboboxp, comboboxt;

    public static final String UPDATE = "UPDATE cocktails SET name = ?, price = ?, recipes = ?, type = ? WHERE name = ?";
    public static final String DELETE = "DELETE FROM cocktails WHERE name = ?";
    public static final String UPDATEP = "UPDATE producttable SET name = ?, quantity = ?, price = ?, other = ? WHERE name = ?";
    public static final String DELETEP = "DELETE FROM producttable WHERE name = ?";
    public static final String UPDATET = "UPDATE packing SET name = ?, type = ?, quantity = ?, price = ? WHERE name = ?";
    public static final String DELETET = "DELETE FROM packing WHERE name = ?";

    eHandler handler = new eHandler();
    HandlerCombobox hcombobox = new HandlerCombobox();

    public static final String query = "SELECT id, name, price, recipes, type FROM cocktails";
    public static final String queryp = "SELECT id, name, price, quantity, other FROM producttable";
    public static final String queryt = "SELECT id, name, price, quantity, type FROM packing";

    public Warehouseman(String s) {
        super(s);
        setLayout(null);
        menu = new JButton("Menu");
        products1 = new JButton("Products");
        back = new JButton("Back");

        updatec = new JButton("Update");
        deletec = new JButton("Delete");

        insertc = new JButton("Add Cocktails");
        insertp = new JButton("Add Products");

        updatep = new JButton("Update");
        deletep = new JButton("Delete");

        updatet = new JButton("Update");
        deletet = new JButton("Delete");

        stock = new JLabel("Warehouse Management");
        cocktails = new JLabel("Cocktails");
        namec = new JLabel("Name");
        pricec = new JLabel("Price");
        typec = new JLabel("Type");
        recipec = new JLabel("Recipes");
        insert = new JLabel("Add new items");

        packing = new JLabel("Packing");
        namet = new JLabel("Name");
        pricet = new JLabel("Price");
        typet = new JLabel("Type");
        quantityt = new JLabel("Quantity");

        products = new JLabel("Products");
        namep = new JLabel("Name");
        quantityp = new JLabel("Quantity");
        pricep = new JLabel("Price");
        otherp = new JLabel("Replace");

        combobox = new JComboBox();
        comboboxp = new JComboBox();
        comboboxt = new JComboBox();

        jt1 = new JTextField(7);
        jt2 = new JTextField(7);
        jt3 = new JTextField(7);
        jt4 = new JTextField(7);

        jt1p = new JTextField(7);
        jt2p = new JTextField(7);
        jt3p = new JTextField(7);
        jt4p = new JTextField(7);

        jt1t = new JTextField(7);
        jt2t = new JTextField(7);
        jt3t = new JTextField(7);
        jt4t = new JTextField(7);

        stock.setFont(products.getFont().deriveFont(16.0f));
        insert.setFont(products.getFont().deriveFont(14.0f));
        add(stock);
        add(combobox);
        add(comboboxp);
        add(comboboxt);

        add(jt1);
        add(jt2);
        add(jt3);
        add(jt4);
        add(cocktails);
        add(namec);
        add(pricec);
        add(typec);
        add(recipec);
        add(updatec);
        add(deletec);

        add(jt1p);
        add(jt2p);
        add(jt3p);
        add(jt4p);
        add(products);
        add(namep);
        add(pricep);
        add(otherp);
        add(quantityp);
        add(updatep);
        add(deletep);

        add(jt1t);
        add(jt2t);
        add(jt3t);
        add(jt4t);
        add(packing);
        add(namet);
        add(pricet);
        add(typet);
        add(quantityt);
        add(updatet);
        add(deletet);

        add(insertp);
        add(insertc);
        add(insert);

        products1.addActionListener(handler);
        menu.addActionListener(handler);
        back.addActionListener(handler);

        combobox.addActionListener(hcombobox);
        comboboxp.addActionListener(hcombobox);
        comboboxt.addActionListener(hcombobox);

        insertp.addActionListener(handler);
        insertc.addActionListener(handler);

        updatec.addActionListener(handler);
        deletec.addActionListener(handler);

        updatep.addActionListener(handler);
        deletep.addActionListener(handler);

        updatet.addActionListener(handler);
        deletet.addActionListener(handler);
        //menu.addActionListener(menus);
    }

    public class eHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == insertp) {
                setVisible(false);
                fr.ProductForm();
            }
            if (e.getSource() == insertc) {
                setVisible(false);
                fr.CocktailsForm();
            }
            if (e.getSource() == menu) {
                String a, d, name = "";
                int b, quantity, newquantity = 0;
                float c = 0;

                Connection connection = null;
                PreparedStatement preparedStatementupdate = null;
                try {
                    a = jt1.getText();

                    Driver driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);
                    connection = DriverManager.getConnection(ConnectToDatabase.URL, ConnectToDatabase.USERNAME, ConnectToDatabase.PASSWORD);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    preparedStatementupdate = connection.prepareStatement(UPDATE);

                    while (resultSet.next()) {
                        name = resultSet.getString("name");

                        if (combobox.getSelectedItem().equals(name)) {
                            preparedStatementupdate.setString(1, a);
                            preparedStatementupdate.setString(2, name);
                            preparedStatementupdate.executeUpdate();
                        }
                    }

                    setVisible(false);
                    fr.Warehouseman();

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
            if (e.getSource() == back) {
                setVisible(false);
                fr.Authorization();
            }
            if (e.getSource() == products) {
                setVisible(false);
                fr.ProductForm();
            }

            if (e.getSource() == updatec) {
                String name, price, recipe, namec, pricec, recipec = "";
                int type, typec = 0;

                Connection connection = null;
                PreparedStatement preparedStatement = null;
                PreparedStatement preparedStatementupdate = null;
                try {
                    namec = jt1.getText();
                    pricec = jt2.getText();
                    recipec = jt3.getText();
                    typec = Integer.parseInt(jt4.getText());

                    Driver driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);
                    connection = DriverManager.getConnection(ConnectToDatabase.URL, ConnectToDatabase.USERNAME, ConnectToDatabase.PASSWORD);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    preparedStatementupdate = connection.prepareStatement(UPDATE);

                    while (resultSet.next()) {
                        name = resultSet.getString("name");
                        price = resultSet.getString("price");
                        type = resultSet.getInt("type");
                        recipe = resultSet.getString("recipes");
                        if (namec.equals(name) && pricec.equals(price) && typec == type && recipec.equals(recipe)) {
                            JOptionPane.showMessageDialog(null, "You have not changed a single field!");
                        }
                        if (namec.equals(name)) {
                            preparedStatementupdate.setString(1, namec);
                            preparedStatementupdate.setString(2, pricec);
                            preparedStatementupdate.setString(3, recipec);
                            preparedStatementupdate.setInt(4, typec);
                            preparedStatementupdate.setString(5, namec);
                            preparedStatementupdate.executeUpdate();

                            setVisible(false);
                            fr.Warehouseman();
                            JOptionPane.showMessageDialog(null, "Data have successfully UPDATE into the database!");
                        }
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
            if (e.getSource() == deletec) {
                String name, price, recipe, namec, pricec, recipec = "";
                int type, typec = 0;

                Connection connection = null;
                PreparedStatement preparedStatementdelete = null;
                try {
                    namec = jt1.getText();

                    Driver driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);
                    connection = DriverManager.getConnection(ConnectToDatabase.URL, ConnectToDatabase.USERNAME, ConnectToDatabase.PASSWORD);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    preparedStatementdelete = connection.prepareStatement(DELETE);

                    while (resultSet.next()) {
                        name = resultSet.getString("name");

                        if (namec.equals(name)) {
                            preparedStatementdelete.setString(1, namec);
                            preparedStatementdelete.execute();
                        }
                    }
                    setVisible(false);
                    fr.Warehouseman();

                    JOptionPane.showMessageDialog(null, "Data have successfully DELETE in the database!");
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

            if (e.getSource() == updatep) {
                String name, namep, other, otherp = "";
                int quantity, quantityp = 0;
                Float price, pricep;

                Connection connection = null;
                PreparedStatement preparedStatement = null;
                PreparedStatement preparedStatementupdate = null;
                try {
                    namep = jt1p.getText();
                    pricep = Float.parseFloat(jt2p.getText());
                    otherp = jt3p.getText();
                    quantityp = Integer.parseInt(jt4p.getText());

                    Driver driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);
                    connection = DriverManager.getConnection(ConnectToDatabase.URL, ConnectToDatabase.USERNAME, ConnectToDatabase.PASSWORD);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(queryp);
                    preparedStatementupdate = connection.prepareStatement(UPDATEP);

                    while (resultSet.next()) {
                        name = resultSet.getString("name");
                        price = resultSet.getFloat("price");
                        other = resultSet.getString("other");
                        quantity = resultSet.getInt("quantity");
                        if (namep.equals(name) && pricep.equals(price) && otherp.equals(other) && quantityp == quantity) {
                            JOptionPane.showMessageDialog(null, "You have not changed a single field!");
                            break;
                        }
                        if (namep.equals(name)) {
                            preparedStatementupdate.setString(1, namep);
                            preparedStatementupdate.setInt(2, quantityp);
                            preparedStatementupdate.setFloat(3, pricep);
                            preparedStatementupdate.setString(4, otherp);
                            preparedStatementupdate.setString(5, namep);
                            preparedStatementupdate.executeUpdate();

                            setVisible(false);
                            fr.Warehouseman();
                            JOptionPane.showMessageDialog(null, "Data have successfully UPDATE into the database!");
                        }
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
            if (e.getSource() == deletep) {
                String name, namep = "";

                Connection connection = null;
                PreparedStatement preparedStatementdelete = null;
                try {
                    namep = jt1p.getText();

                    Driver driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);
                    connection = DriverManager.getConnection(ConnectToDatabase.URL, ConnectToDatabase.USERNAME, ConnectToDatabase.PASSWORD);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(queryp);
                    preparedStatementdelete = connection.prepareStatement(DELETEP);

                    while (resultSet.next()) {
                        name = resultSet.getString("name");

                        if (namep.equals(name)) {
                            preparedStatementdelete.setString(1, namep);
                            preparedStatementdelete.execute();
                        }
                    }
                    setVisible(false);
                    fr.Warehouseman();

                    JOptionPane.showMessageDialog(null, "Data have successfully DELETE in the database!");
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

            if (e.getSource() == updatet) {
                String name, namet = "";
                int type, typet, quantity, quantityt  = 0;
                Float price, pricet;

                Connection connection = null;
                PreparedStatement preparedStatement = null;
                PreparedStatement preparedStatementupdate = null;
                try {
                    namet = jt1t.getText();
                    pricet = Float.parseFloat(jt2t.getText());
                    quantityt = Integer.parseInt(jt3t.getText());
                    typet = Integer.parseInt(jt4t.getText());

                    Driver driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);
                    connection = DriverManager.getConnection(ConnectToDatabase.URL, ConnectToDatabase.USERNAME, ConnectToDatabase.PASSWORD);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(queryt);
                    preparedStatementupdate = connection.prepareStatement(UPDATET);

                    while (resultSet.next()) {
                        name = resultSet.getString("name");
                        price = resultSet.getFloat("price");
                        type = resultSet.getInt("type");
                        quantity = resultSet.getInt("quantity");
                        if (namet.equals(name) && pricet.equals(price) && typet == type && quantityt == quantity) {
                            JOptionPane.showMessageDialog(null, "You have not changed a single field!");
                        }
                        if (namet.equals(name)) {
                            preparedStatementupdate.setString(1, namet);
                            preparedStatementupdate.setInt(2, typet);
                            preparedStatementupdate.setInt(3, quantityt);
                            preparedStatementupdate.setFloat(4, pricet);
                            preparedStatementupdate.setString(5, namet);
                            preparedStatementupdate.executeUpdate();

                            setVisible(false);
                            fr.Warehouseman();
                            JOptionPane.showMessageDialog(null, "Data have successfully UPDATE into the database!");
                        }
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
            if (e.getSource() == deletet) {
                String name, namet = "";

                Connection connection = null;
                PreparedStatement preparedStatementdelete = null;
                try {
                    namet = jt1t.getText();

                    Driver driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);
                    connection = DriverManager.getConnection(ConnectToDatabase.URL, ConnectToDatabase.USERNAME, ConnectToDatabase.PASSWORD);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(queryt);
                    preparedStatementdelete = connection.prepareStatement(DELETET);

                    while (resultSet.next()) {
                        name = resultSet.getString("name");

                        if (namet.equals(name)) {
                            preparedStatementdelete.setString(1, namet);
                            preparedStatementdelete.execute();
                        }
                    }
                    setVisible(false);
                    fr.Warehouseman();

                    JOptionPane.showMessageDialog(null, "Data have successfully DELETE in the database!");
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
        }
    }

    public class HandlerCombobox implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == combobox) {
                combobox.getSelectedItem();
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(ConnectToDatabase.URL, ConnectToDatabase.USERNAME, ConnectToDatabase.PASSWORD);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    Driver driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);

                    while (resultSet.next()) {
                        String id = resultSet.getString("id");
                        String nameb = resultSet.getString("name");
                        String price = resultSet.getString("price");
                        String recipes = resultSet.getString("recipes");
                        Integer type = resultSet.getInt("type");

                        if (combobox.getSelectedItem().equals(nameb)) {
                            jt1.setText(nameb);
                            jt2.setText(price);
                            jt3.setText(recipes);
                            jt4.setText(type.toString());
                        /*if (type == 1) {
                            jt4.setText("Non-alcoholic");
                        }
                        if (type == 2) {
                            jt4.setText("Alcoholic");
                        }*/
                        }
                    }
                } catch (SQLException e1) {
                    System.out.println("You have an error in SQL!!!");
                    e1.printStackTrace();
                } catch (Exception e1) {
                    System.out.println("You have an error!!!");
                    e1.printStackTrace();
                }

                System.out.println(combobox.getSelectedItem());
            }
            if (e.getSource() == comboboxp) {
                comboboxp.getSelectedItem();
                Connection connection = null;
                String name, replace;
                Float price;
                Integer quantity;
                try {
                    connection = DriverManager.getConnection(ConnectToDatabase.URL, ConnectToDatabase.USERNAME, ConnectToDatabase.PASSWORD);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(queryp);
                    Driver driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);

                    while (resultSet.next()) {
                        String id = resultSet.getString("id");
                        name = resultSet.getString("name");
                        price = resultSet.getFloat("price");
                        quantity = resultSet.getInt("quantity");
                        replace = resultSet.getString("other");

                        if (comboboxp.getSelectedItem().equals(name)) {
                            jt1p.setText(name);
                            jt2p.setText(price.toString());
                            jt3p.setText(replace);
                            jt4p.setText(quantity.toString());
                        }
                    }
                } catch (SQLException e1) {
                    System.out.println("You have an error in SQL!!!");
                    e1.printStackTrace();
                } catch (Exception e1) {
                    System.out.println("You have an error!!!");
                    e1.printStackTrace();
                }

                System.out.println(combobox.getSelectedItem());
            }
            if (e.getSource() == comboboxt) {
                comboboxt.getSelectedItem();
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(ConnectToDatabase.URL, ConnectToDatabase.USERNAME, ConnectToDatabase.PASSWORD);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(queryt);
                    Driver driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);

                    while (resultSet.next()) {
                        String id = resultSet.getString("id");
                        String name = resultSet.getString("name");
                        Float price = resultSet.getFloat("price");
                        Integer quantity = resultSet.getInt("quantity");
                        Integer type = resultSet.getInt("type");

                        if (comboboxt.getSelectedItem().equals(name)) {
                            jt1t.setText(name);
                            jt2t.setText(price.toString());
                            jt3t.setText(quantity.toString());
                            jt4t.setText(type.toString());
                        }
                    }
                } catch (SQLException e1) {
                    System.out.println("You have an error in SQL!!!");
                    e1.printStackTrace();
                } catch (Exception e1) {
                    System.out.println("You have an error!!!");
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args0) {
        fr.ProductForm();
    }

    public static class TestWindowListener implements WindowListener {
        public void windowActivated(WindowEvent e) {
            System.out.println("windowActivated   ");
        }

        public void windowClosed(WindowEvent e) {
            System.out.println("windowClosed  ");
        }

        public void windowClosing(WindowEvent e) {
            System.out.println("windowClosing  ");
        }

        public void windowDeactivated(WindowEvent e) {
            System.out.println("windowDeactivated  ");
        }

        public void windowDeiconified(WindowEvent e) {
            System.out.println("windowDeiconified    ");
        }

        public void windowIconified(WindowEvent e) {
            System.out.println("windowIconified      ");
        }

        public void windowOpened(WindowEvent e) {
            System.out.println("windowOpened");
            String[] words = {};
            int n = 1;
            String id, nameb, recipes = "";
            Double price;
            Connection connection = null;

            try {
                connection = DriverManager.getConnection(ConnectToDatabase.URL, ConnectToDatabase.USERNAME, ConnectToDatabase.PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                Driver driver = new FabricMySQLDriver();
                DriverManager.registerDriver(driver);

                while (resultSet.next()) {
                    combobox.addItem(resultSet.getString("name"));
                }
                resultSet.close();

                resultSet = statement.executeQuery(queryp);
                while (resultSet.next()) {
                    comboboxp.addItem(resultSet.getString("name"));
                }
                resultSet.close();

                resultSet = statement.executeQuery(queryt);
                while (resultSet.next()) {
                    comboboxt.addItem(resultSet.getString("name"));
                }
                resultSet.close();

            } catch (SQLException e1) {
                System.out.println("You have an error in SQL!!!");
                e1.printStackTrace();
            } catch (Exception e1) {
                System.out.println("You have an error!!!");
                e1.printStackTrace();
            }
        }
    }
}

