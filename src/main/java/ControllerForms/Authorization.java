package ControllerForms;

import GuiForms.Forms;
import com.angelx.builder.ConnectToDatabase;
import com.mysql.fabric.jdbc.FabricMySQLDriver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Authorization extends JFrame {
    public JButton auth, close;
    public JLabel l1, l2, l3, l4;
    public JTextField t1, t2;
    public eHandler handler = new eHandler();
    public Connection connection = null;
    String query = "SELECT login, password, roles FROM users";

    public Authorization(String s) {
        super(s);
        setLayout(null);
        auth = new JButton("Login");
        close = new JButton("Close");
        l1 = new JLabel("Enter your login");
        l2 = new JLabel("Enter your password");
        t1 = new JTextField(48);
        t2 = new JPasswordField(48);
        l3 = new JLabel("");
        l4 = new JLabel("California Bar");
        add(l3);
        add(l4);
        l4.setFont(l4.getFont().deriveFont(36.0f));
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(auth);
        add(close);
        auth.addActionListener(handler);
        close.addActionListener(handler);
    }

    public class eHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Forms fr = new Forms();
            Authorization ak = new Authorization("");
            if (e.getSource() == auth) {
                try {
                    connection = DriverManager.getConnection(ConnectToDatabase.URL, ConnectToDatabase.USERNAME, ConnectToDatabase.PASSWORD);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    Driver driver = new FabricMySQLDriver();
                    DriverManager.registerDriver(driver);
                    String log = "", pass = "";
                    Integer roles = 0;
                    while (resultSet.next()) {
                        log = resultSet.getString("login");
                        pass = resultSet.getString("password");
                        roles = resultSet.getInt("roles");

                        if (log.equals(t1.getText()) && pass.equals(t2.getText())) {
                            if (roles == 1) {
                                setVisible(false);
                                fr.User();
                                break;
                            } else if (roles == 2) {
                                break;
                            } else if (roles == 3) {
                                setVisible(false);
                                fr.Warehouseman();
                                break;
                            }
                        } else {
                            l3.setText("Incorrect login or password! Try again.");
                        }
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            if (e.getSource() == close) {
                System.exit(0);
            }
        }
    }
}

