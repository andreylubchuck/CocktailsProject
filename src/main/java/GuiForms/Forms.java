package GuiForms;

import ControllerForms.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;

public class Forms extends JFrame {
    public static void Authorization() {
        Authorization auth1 = new Authorization("Cocktails");
        auth1.setVisible(true);
        auth1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        auth1.setSize(650, 550);
        auth1.setResizable(false);
        auth1.setLocationRelativeTo(null);

        Insets insets = auth1.getInsets();
        Dimension size = auth1.auth.getPreferredSize();
        auth1.auth.setBounds(55 + insets.left, 325 + insets.top,
                size.width + 10, size.height + 10);

        size = auth1.close.getPreferredSize();
        auth1.close.setBounds(520 + insets.left, 325 + insets.top,
                size.width + 10, size.height + 10);

        size = auth1.t1.getPreferredSize();
        auth1.t1.setBounds(55 + insets.left, 170 + insets.top,
                size.width + 15, size.height + 15);

        size = auth1.t2.getPreferredSize();
        auth1.t2.setBounds(55 + insets.left, 250 + insets.top,
                size.width + 15, size.height + 15);

        size = auth1.l1.getPreferredSize();
        auth1.l1.setBounds(55 + insets.left, 140 + insets.top,
                size.width + 50, size.height + 3);

        size = auth1.l2.getPreferredSize();
        auth1.l2.setBounds(55 + insets.left, 210 + insets.top,
                size.width + 50, size.height + 20);

        size = auth1.l3.getPreferredSize();
        auth1.l3.setBounds(215 + insets.left, 360 + insets.top,
                size.width + 250, size.height + 20);

        size = auth1.l4.getPreferredSize();
        auth1.l4.setBounds(200 + insets.left, 30 + insets.top,
                size.width + 250, size.height + 50);
    }

    public static void User() {
        User user = new User("Order Table");
        user.setVisible(true);
        user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        user.setSize(650, 550);
        user.setResizable(false);
        user.setLocationRelativeTo(null);
        WindowListener winListener = new User.TestWindowListener();
        user.addWindowListener(winListener);
        Insets insets = user.getInsets();
        Dimension size = user.menu.getPreferredSize();

        user.menu.setBounds(50 + insets.left, 185 + insets.top,
                100, 30);

        user.order.setBounds(50 + insets.left, 230 + insets.top,
                140, 35);

        user.back.setBounds(450 + insets.left, 230 + insets.top,
                140, 35);

        user.l1.setFont(user.getFont().deriveFont(14.0f));
        size = user.l1.getPreferredSize();
        user.l1.setBounds(50 + insets.left, 180 + insets.top,
                size.width, size.height);

        user.l2.setFont(user.getFont().deriveFont(16.0f));
        size = user.l2.getPreferredSize();
        user.l2.setBounds(240 + insets.left, -15 + insets.top,
                size.width, size.height);

        size = user.t1.getPreferredSize();
        user.t1.setBounds(215 + insets.left, 180 + insets.top,
                size.width + 320, size.height + 5);

        user.combobox.setEditable(true);
        size = user.combobox.getPreferredSize();
        user.combobox.setBounds(115 + insets.left, 170 + insets.top,
                size.width, size.height);

        user.table.setBounds(115 + insets.left, 220 + insets.top,
                size.width + 325, size.height+150);

        user.pane.setBounds(0 + insets.left, 30 + insets.top,
                size.width + 510, size.height + 100);
    }

    public static void Warehouseman() {
        Warehouseman warehouseman = new Warehouseman("Warehouseman");
        warehouseman.setVisible(true);
        warehouseman.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        warehouseman.setSize(650, 550);
        warehouseman.setResizable(false);
        warehouseman.setLocationRelativeTo(null);
        WindowListener winListener = new Warehouseman.TestWindowListener();
        warehouseman.addWindowListener(winListener);
        Insets insets = warehouseman.getInsets();
        Dimension size = warehouseman.menu.getPreferredSize();

        warehouseman.menu.setBounds(50 + insets.left, 300 + insets.top,
                90, 40);

        warehouseman.products.setBounds(150 + insets.left, 300 + insets.top,
                90, 40);

        warehouseman.back.setBounds(250 + insets.left, 300 + insets.top,
                90, 40);

        warehouseman.updatec.setBounds(50 + insets.left, 85 + insets.top,
                75, 25);
        warehouseman.deletec.setBounds(150 + insets.left, 85 + insets.top,
                75, 25);

        warehouseman.stock.setBounds(220 + insets.left, -40 + insets.top,
                size.width + 250, size.height + 50);

        warehouseman.insert.setBounds(280 + insets.left, 290 + insets.top,
                size.width + 250, size.height + 50);

        warehouseman.cocktails.setBounds(50 + insets.left, 0 + insets.top,
                size.width + 250, size.height + 50);
        warehouseman.namec.setBounds(210 + insets.left, 0 + insets.top,
                size.width + 250, size.height + 50);
        warehouseman.pricec.setBounds(300 + insets.left, 0 + insets.top,
                size.width + 250, size.height + 50);
        warehouseman.recipec.setBounds(390 + insets.left, 0 + insets.top,
                size.width + 250, size.height + 50);
        warehouseman.typec.setBounds(480 + insets.left, 0 + insets.top,
                size.width + 250, size.height + 50);

        warehouseman.updatep.setBounds(50 + insets.left, 180 + insets.top,
                75, 25);
        warehouseman.deletep.setBounds(150 + insets.left, 180 + insets.top,
                75, 25);

        warehouseman.products.setBounds(50 + insets.left, 95 + insets.top,
                size.width + 250, size.height + 50);
        warehouseman.namep.setBounds(210 + insets.left, 95 + insets.top,
                size.width + 250, size.height + 50);
        warehouseman.pricep.setBounds(300 + insets.left, 95 + insets.top,
                size.width + 250, size.height + 50);
        warehouseman.otherp.setBounds(390 + insets.left, 95 + insets.top,
                size.width + 250, size.height + 50);
        warehouseman.quantityp.setBounds(480 + insets.left, 95 + insets.top,
                size.width + 250, size.height + 50);

        warehouseman.updatet.setBounds(50 + insets.left, 275 + insets.top,
                75, 25);
        warehouseman.deletet.setBounds(150 + insets.left, 275 + insets.top,
                75, 25);

        warehouseman.insertc.setBounds(125 + insets.left, 350 + insets.top,
                115, 35);
        warehouseman.insertp.setBounds(425 + insets.left, 350 + insets.top,
                115, 35);

        warehouseman.packing.setBounds(50 + insets.left, 190 + insets.top,
                size.width + 250, size.height + 50);
        warehouseman.namet.setBounds(210 + insets.left, 190 + insets.top,
                size.width + 250, size.height + 50);
        warehouseman.pricet.setBounds(300 + insets.left, 190 + insets.top,
                size.width + 250, size.height + 50);
        warehouseman.quantityt.setBounds(390 + insets.left, 190 + insets.top,
                size.width + 250, size.height + 50);
        warehouseman.typet.setBounds(480 + insets.left, 190 + insets.top,
                size.width + 250, size.height + 50);

        warehouseman.combobox.setEditable(true);
        size = warehouseman.combobox.getPreferredSize();
        warehouseman.combobox.setBounds(50 + insets.left, 50 + insets.top,
                size.width, size.height);

        warehouseman.comboboxp.setEditable(true);
        size = warehouseman.comboboxp.getPreferredSize();
        warehouseman.comboboxp.setBounds(50 + insets.left, 145 + insets.top,
                size.width, size.height);

        warehouseman.comboboxt.setEditable(true);
        size = warehouseman.comboboxt.getPreferredSize();
        warehouseman.comboboxt.setBounds(50 + insets.left, 240 + insets.top,
                size.width, size.height);

        size = warehouseman.jt1.getPreferredSize();
        warehouseman.jt1.setBounds(210 + insets.left, 50 + insets.top,
                size.width +10, size.height +6);

        size = warehouseman.jt2.getPreferredSize();
        warehouseman.jt2.setBounds(300 + insets.left, 50 + insets.top,
                size.width +10, size.height +6);

        size = warehouseman.jt3.getPreferredSize();
        warehouseman.jt3.setBounds(390 + insets.left, 50 + insets.top,
                size.width +10, size.height +6);

        size = warehouseman.jt4.getPreferredSize();
        warehouseman.jt4.setBounds(480 + insets.left, 50 + insets.top,
                size.width +10, size.height +6);

        size = warehouseman.jt1p.getPreferredSize();
        warehouseman.jt1p.setBounds(210 + insets.left, 145 + insets.top,
                size.width +10, size.height +6);

        size = warehouseman.jt2p.getPreferredSize();
        warehouseman.jt2p.setBounds(300 + insets.left, 145 + insets.top,
                size.width +10, size.height +6);

        size = warehouseman.jt3p.getPreferredSize();
        warehouseman.jt3p.setBounds(390 + insets.left, 145 + insets.top,
                size.width +10, size.height +6);

        size = warehouseman.jt4p.getPreferredSize();
        warehouseman.jt4p.setBounds(480 + insets.left, 145 + insets.top,
                size.width +10, size.height +6);

        size = warehouseman.jt1t.getPreferredSize();
        warehouseman.jt1t.setBounds(210 + insets.left, 240 + insets.top,
                size.width +10, size.height +6);

        size = warehouseman.jt2t.getPreferredSize();
        warehouseman.jt2t.setBounds(300 + insets.left, 240 + insets.top,
                size.width +10, size.height +6);

        size = warehouseman.jt3t.getPreferredSize();
        warehouseman.jt3t.setBounds(390 + insets.left, 240 + insets.top,
                size.width +10, size.height +6);

        size = warehouseman.jt4t.getPreferredSize();
        warehouseman.jt4t.setBounds(480 + insets.left, 240 + insets.top,
                size.width +10, size.height +6);
    }

    public static void ProductForm() {
        ProductsForm form = new ProductsForm("Products");
        form.setVisible(true);
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setSize(650, 550);
        form.setLocationRelativeTo(null);
        Insets insets = form.getInsets();
        Dimension size = form.insert.getPreferredSize();

        form.insert.setBounds(115 + insets.left, 320 + insets.top,
                size.width = 95, size.height = 50);

        form.back.setBounds(425 + insets.left, 320 + insets.top,
                size.width = 95, size.height = 50);

        form.csv.setBounds(255 + insets.left, 320 + insets.top,
                size.width = 130, size.height = 50);

        size = form.l1.getPreferredSize();
        form.l1.setBounds(115 + insets.left, 30 + insets.top,
                size.width, size.height);

        size = form.l2.getPreferredSize();
        form.l2.setBounds(115 + insets.left, 100 + insets.top,
                size.width, size.height);

        size = form.l3.getPreferredSize();
        form.l3.setBounds(115 + insets.left, 170 + insets.top,
                size.width, size.height);

        size = form.rpname.getPreferredSize();
        form.rpname.setBounds(115 + insets.left, 240 + insets.top,
                size.width, size.height);

        size = form.products.getPreferredSize();
        form.products.setBounds(275 + insets.left, 1 + insets.top,
                size.width, size.height);

        size = form.t1.getPreferredSize();
        form.t1.setBounds(115 + insets.left, 50 + insets.top,
                size.width + 15, size.height + 15);

        size = form.t2.getPreferredSize();
        form.t2.setBounds(115 + insets.left, 120 + insets.top,
                size.width + 15, size.height + 15);

        size = form.t3.getPreferredSize();
        form.t3.setBounds(115 + insets.left, 190 + insets.top,
                size.width + 15, size.height + 15);

        size = form.replacefield.getPreferredSize();
        form.replacefield.setBounds(115 + insets.left, 260 + insets.top,
                size.width + 15, size.height + 15);
    }

    public static void CocktailsForm() {
        CocktailsForm form = new CocktailsForm("Cocktails");
        form.setVisible(true);
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setSize(650, 550);
        form.setLocationRelativeTo(null);
        Insets insets = form.getInsets();
        Dimension size = form.insert.getPreferredSize();

        form.insert.setBounds(115 + insets.left, 320 + insets.top,
                size.width = 95, size.height = 50);

        form.back.setBounds(425 + insets.left, 320 + insets.top,
                size.width = 95, size.height = 50);

        form.csv.setBounds(255 + insets.left, 320 + insets.top,
                size.width = 130, size.height = 50);

        size = form.l1.getPreferredSize();
        form.l1.setBounds(115 + insets.left, 30 + insets.top,
                size.width, size.height);

        size = form.l2.getPreferredSize();
        form.l2.setBounds(115 + insets.left, 100 + insets.top,
                size.width, size.height);

        size = form.l3.getPreferredSize();
        form.l3.setBounds(115 + insets.left, 170 + insets.top,
                size.width, size.height);

        size = form.rpname.getPreferredSize();
        form.rpname.setBounds(115 + insets.left, 240 + insets.top,
                size.width, size.height);

        size = form.products.getPreferredSize();
        form.products.setBounds(275 + insets.left, 1 + insets.top,
                size.width, size.height);

        size = form.t1.getPreferredSize();
        form.t1.setBounds(115 + insets.left, 50 + insets.top,
                size.width + 15, size.height + 15);

        size = form.t2.getPreferredSize();
        form.t2.setBounds(115 + insets.left, 120 + insets.top,
                size.width + 15, size.height + 15);

        size = form.t3.getPreferredSize();
        form.t3.setBounds(115 + insets.left, 190 + insets.top,
                size.width + 15, size.height + 15);

        size = form.t4.getPreferredSize();
        form.t4.setBounds(115 + insets.left, 260 + insets.top,
                size.width + 15, size.height + 15);
    }
}
