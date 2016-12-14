package gui;
import DB.DBIO;
import Exception.DateException;

import Entity.Product;
import Entity.Product_Category;
import Entity.Warehouse;
import org.pojava.datetime.DateTime;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by romanchubak on 14.12.2016.
 */


import java.awt.Dimension;

import java.awt.Insets;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.FileInputStream;

import java.io.IOException;

import java.util.List;
import java.util.Properties;



import javax.swing.Box;

import javax.swing.BoxLayout;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JTextField;

import javax.swing.SwingUtilities;

import javax.swing.border.EmptyBorder;




public class ui extends JFrame {
//Name, Category, Quantity, Date_of_manufacture, Dateof_admission, Date_fitness, Warehouse_id
    private JTextField NameTextField;
    private JTextField CategoryTextField;
    private JTextField QuantityTextField;
    private JTextField Date_of_manufactureTextField;
    private JTextField Date_of_admissionTextField;
    private JTextField Date_fitnessTextField;
    private JTextField Warehouse_idTextField;

    private static List<Product> ProductList;
    private static List<Warehouse> WarehouseList;
    private static int currindext;
    private static DBIO dbio;

    public ui() {

        initUI();

    }



    private void initUI() {
        add(createMainLayout());
        pack();
        setTitle("Lab2_Java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }



    private JPanel createMainLayout() {
        JPanel panel = new JPanel();

        panel.setBorder(new EmptyBorder(new Insets(20, 30, 20, 30)));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(createButtonsPanel());
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        createFields(panel);
        return panel;
    }



    private void createFields(JPanel parent) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
       // Name, Category, Quantity, Date_of_manufacture, Dateof_admission, Date_fitness, Warehouse_id
        panel.add(new JLabel("Name"));
        NameTextField = new JTextField();
        panel.add(NameTextField);

        panel.add(new JLabel("Category"));
        CategoryTextField = new JTextField();
        panel.add(CategoryTextField);

        panel.add(new JLabel("Quantity"));
        QuantityTextField = new JTextField();
        panel.add(QuantityTextField);

        panel.add(new JLabel("Date of manufacture"));
        Date_of_manufactureTextField = new JTextField();
        panel.add(Date_of_manufactureTextField);


        panel.add(new JLabel("Date of admission"));
        Date_of_admissionTextField= new JTextField();
        panel.add(Date_of_admissionTextField);

        panel.add(new JLabel("Date fitness"));
        Date_fitnessTextField= new JTextField();
        panel.add(Date_fitnessTextField);

        parent.add(panel);

    }



    private JPanel createButtonsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JButton AddButton = new JButton("Add New");
        AddButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                if(NameTextField.getText() != "")
                {
                    try {
                        dbio.addProduct(new Product(Product_Category.valueOf(CategoryTextField.getText()),
                                                    NameTextField.getText(),
                                                    new DateTime(Date_of_manufactureTextField.getText()),
                                                    new DateTime(Date_of_admissionTextField.getText()),
                                                    new DateTime(Date_fitnessTextField.getText()),
                                                    Integer.parseInt(QuantityTextField.getText())),
                                                    WarehouseList.get(0));
                    } catch (DateException e1) {
                        e1.printStackTrace();
                    }
                    ProductList = dbio.getProducts();
                    WarehouseList = dbio.getAllWarehouse();
                    clear();
                }
            }
        });
        panel.add(AddButton);


        JButton update = new JButton("Update");
        update.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                if(NameTextField.getText() != "")
                {
                    try {
                        dbio.updateProduct(new Product(Product_Category.valueOf(CategoryTextField.getText()),
                                        NameTextField.getText(),
                                        new DateTime(Date_of_manufactureTextField.getText()),
                                        new DateTime(Date_of_admissionTextField.getText()),
                                        new DateTime(Date_fitnessTextField.getText()),
                                        Integer.parseInt(QuantityTextField.getText())),
                                WarehouseList.get(0));
                    } catch (DateException e1) {
                        e1.printStackTrace();
                    }
                    ProductList = dbio.getProducts();
                    WarehouseList = dbio.getAllWarehouse();
                    clear();
                }
            }
        });
        panel.add(update);


        JButton First = new JButton("First");
        First.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                currindext = 0;
                NameTextField.setText(ProductList.get(currindext).getName());
                CategoryTextField.setText(ProductList.get(currindext).getCategory().toString());
                QuantityTextField.setText(ProductList.get(currindext).getCount().toString());
                Date_of_manufactureTextField.setText(ProductList.get(currindext).getDate_of_manufacture().toString());
                Date_of_admissionTextField.setText(ProductList.get(currindext).getDate_of_admission().toString());
                Date_fitnessTextField.setText(ProductList.get(currindext).getDate_fitness().toString());

            }
        });
        panel.add(First);



       JButton next = new JButton("Next");
        next.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                if(currindext < ProductList.size()-1)
                        currindext++;
                NameTextField.setText(ProductList.get(currindext).getName());
                CategoryTextField.setText(ProductList.get(currindext).getCategory().toString());
                QuantityTextField.setText(ProductList.get(currindext).getCount().toString());
                Date_of_manufactureTextField.setText(ProductList.get(currindext).getDate_of_manufacture().toString());
                Date_of_admissionTextField.setText(ProductList.get(currindext).getDate_of_admission().toString());
                Date_fitnessTextField.setText(ProductList.get(currindext).getDate_fitness().toString());
            }
        });
       panel.add(next);



        JButton previous = new JButton("Previous");
        previous.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                if(currindext > 0)
                        currindext--;
                NameTextField.setText(ProductList.get(currindext).getName());
                CategoryTextField.setText(ProductList.get(currindext).getCategory().toString());
                QuantityTextField.setText(ProductList.get(currindext).getCount().toString());
                Date_of_manufactureTextField.setText(ProductList.get(currindext).getDate_of_manufacture().toString());
                Date_of_admissionTextField.setText(ProductList.get(currindext).getDate_of_admission().toString());
                Date_fitnessTextField.setText(ProductList.get(currindext).getDate_fitness().toString());
            }
        });
        panel.add(previous);



        JButton Last = new JButton("Last");
        Last.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                currindext = ProductList.size()-1;
                NameTextField.setText(ProductList.get(currindext).getName());
                CategoryTextField.setText(ProductList.get(currindext).getCategory().toString());
                QuantityTextField.setText(ProductList.get(currindext).getCount().toString());
                Date_of_manufactureTextField.setText(ProductList.get(currindext).getDate_of_manufacture().toString());
                Date_of_admissionTextField.setText(ProductList.get(currindext).getDate_of_admission().toString());
                Date_fitnessTextField.setText(ProductList.get(currindext).getDate_fitness().toString());
            }
        });
        panel.add(Last);


        return panel;

    }



    public static void main(String[] args) {

        dbio = new DBIO();
        ProductList = dbio.getProducts();
        WarehouseList = dbio.getAllWarehouse();
        ui ex = new ui();
        ex.setVisible(true);

    }






     public void clear() {
          NameTextField.setText("");
          CategoryTextField.setText("");
          QuantityTextField.setText("");
          Date_of_manufactureTextField.setText("");
          Date_of_admissionTextField.setText("");
          Date_fitnessTextField.setText("");
          Warehouse_idTextField.setText("");
    }




}