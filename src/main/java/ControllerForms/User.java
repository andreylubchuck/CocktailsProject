package ControllerForms;

import GuiForms.Forms;
import com.angelx.builder.ConnectToDatabase;
import com.mysql.fabric.jdbc.FabricMySQLDriver;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;
import java.util.ArrayList;

public class User extends JFrame {
    static Forms fr = new Forms();
    public static JButton menu, order, back;
    public static JTextField t1;
    public static JLabel l1, l2;
    public static JComboBox combobox;
    public eHandler handler = new eHandler();
    public tbl tblhandler = new tbl();
    public static MyTableModel model;
    public static JTable table;
    public static JScrollPane pane;
    static Connection connection = null;
    static String query = "SELECT id, name, price, recipes, type FROM cocktails";
    static String queryp = "SELECT id, name, price, quantity, other FROM producttable";
    static String recipes, id, nameb, sm, st = "";
    static Double price, sum = 0.0;
    public String[] wd = {};
    static boolean checkbox;
    static java.util.List<Object[]> list = new ArrayList<Object[]>();

    public User(String s) {
        super(s);
        setLayout(null);
        menu = new JButton("Menu");
        order = new JButton("Make a Cocktail");
        back = new JButton("Back");
        t1 = new JTextField(5);
        l1 = new JLabel("Cocktail/s are ready: ");
        l2 = new JLabel("Bartending Front");
        combobox = new JComboBox();
        model = new MyTableModel(new String[]{"№", "Name", "Price", "Type"}, list);
        table = new JTable(model);
        pane = new JScrollPane(table);
        add(order);
        add(back);
        add(l1);
        add(l2);
        add(t1);
        add(pane, BorderLayout.CENTER);
        menu.addActionListener(handler);
        order.addActionListener(handler);
        back.addActionListener(handler);
        model.addTableModelListener(tblhandler);
    }

    public class tbl implements TableModelListener {
        public void tableChanged(TableModelEvent e) {
            if (e.getColumn() == 3 && e.getFirstRow() > -1) {
                try {
                    String name, col = "";
                    Double price = 0.0;
                    Double[] fj = new Double[10];
                    Boolean checked = null;
                    connection = DriverManager.getConnection(ConnectToDatabase.URL, ConnectToDatabase.USERNAME, ConnectToDatabase.PASSWORD);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    Driver driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);

                    while (resultSet.next()) {
                        recipes = resultSet.getString("recipes");
                        id = resultSet.getString("id");
                        nameb = resultSet.getString("name");
                        for (int i = 0; i < 7; i++) {
                            checked = Boolean.valueOf(table.getValueAt(i, 3).toString());
                            col = table.getValueAt(i, 1).toString();
                            if (checked) {
                                if (col.equals(nameb)) {
                                    wd = recipes.split(",");
                                    for (i = 0; i < wd.length; i++) {
                                        resultSet = statement.executeQuery(queryp);
                                        while (resultSet.next()) {
                                            name = resultSet.getString("name");
                                            price = resultSet.getDouble("price");
                                            if (wd[i].equals(name)) {
                                                fj[i] = price;
                                                sum = sum + fj[i];
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public class eHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == order) {
                try {
                    String col = "";
                    Boolean checked;
                    connection = DriverManager.getConnection(ConnectToDatabase.URL, ConnectToDatabase.USERNAME, ConnectToDatabase.PASSWORD);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    Driver driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);
                    String builder = "";

                    while (resultSet.next()) {
                        recipes = resultSet.getString("recipes");
                        nameb = resultSet.getString("name");
                        for (int i = 0; i < table.getRowCount(); i++) {
                            checked = Boolean.valueOf(table.getValueAt(i, 3).toString());
                            col = table.getValueAt(i, 1).toString();
                            if (checked) {
                                if (col.equals(nameb)) {
                                    String[] name1 = new String[25];
                                    Coctails coctails = new CoctailsBuilder()
                                            .buildName(sm + "," + name1[i])
                                            .buildPrice(sum)
                                            .build();
                                    sm = nameb;
                                    builder = coctails.toString();
                                    t1.setText(builder);
                                }
                            }
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
            if (e.getSource() == menu) {
                System.out.print(sum);
            }
            if (e.getSource() == back) {
                setVisible(false);
                fr.Authorization();
            }
        }
    }

    public boolean jcx(int row, int column) {
        return checkbox;
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
            try {
                connection = DriverManager.getConnection(ConnectToDatabase.URL, ConnectToDatabase.USERNAME, ConnectToDatabase.PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                Driver driver = new FabricMySQLDriver();
                DriverManager.registerDriver(driver);

                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    id = resultSet.getString("id");
                    nameb = resultSet.getString("name");
                    price = resultSet.getDouble("price");

                    list.add(new Object[]{id, nameb, price, false});
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

    class MyTableModel extends AbstractTableModel {

        private String[] columnNames;
        private java.util.List<Object[]> data;

        public MyTableModel(String[] columnNames, java.util.List<Object[]> data) {
            this.columnNames = columnNames;
            this.data = data;

        }

        @Override
        public boolean isCellEditable(int row, int col) {
            if (col == 3) {
                return true;
            }
            return false;
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.size();
        }

        public void setValueAt(Object value, int row, int col) {
            data.get(row)[col] = value;
            fireTableCellUpdated(row, col);
        }

        public Class getColumnClass(int column) {
            if (column == 3) {
                return Boolean.class;
            }
            return (getValueAt(0, column).getClass());
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }


        public Object getValueAt(int row, int col) {
            return data.get(row)[col];
        }

    }

    public static void main(String[] args0) {

    }

}

