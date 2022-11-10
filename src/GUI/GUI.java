package GUI;


import model.Order;
import model.OrderService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static model.ItemType.PRODUCT;
import static model.ItemType.SERVICE;

public class GUI{

        OrderService orderService = new OrderService();
        private final java.util.Date date = new java.util.Date();
        public java.util.Date getDate() {return date;}
        private JFrame frame;
        private JPanel productPanel;
        private JPanel servicePanel;
        private JPanel checkOutPanel;
        private JPanel shoppingListPanel;
        private JPanel orderButtonsPanel;
        private JTextField textProductName;
        private JLabel labelProductName;

        private JTextField textServiceName;
        private JLabel labelServiceName;

        private JTextField textProductQuantity;
        private JLabel labelProductQuantity;
        private JTextField textProductPrice;
        private JLabel labelProductPrice;

        private JTextField textServicePersonel;
        private JLabel labelServicePersonel;
        private JTextField textServiceHours;
        private JLabel labelServiceHours;
        private JButton buttonAddProduct;
        private JButton buttonAddService;
        private JButton buttonFinnishOrder;
        private JButton buttonRemoveOrders;
        private JTextArea shoppingList;
        private JTextArea checkOut;
        private JButton productClear;
        private JButton serviceClear;
        private JButton buttonClearCart;
        float   cartPrice;


        public void startGui(){
                cartPrice =0;
                productPanel = new JPanel();
                productPanel.setBounds(0, 300, 250, 400);
                productPanel.setLayout(new GridLayout(4,2));

                servicePanel = new JPanel();
                servicePanel.setBounds(250,300,250,400);
                servicePanel.setLayout(new GridLayout(4,2));

                checkOutPanel = new JPanel();
                checkOutPanel.setBounds(510,0,260,500);
                checkOutPanel.setLayout(new BorderLayout(1,1));

                orderButtonsPanel = new JPanel();
                orderButtonsPanel.setBounds(510,550,260,150);
                orderButtonsPanel.setLayout(new GridLayout(2,1));

                shoppingListPanel = new JPanel();
                shoppingListPanel.setBounds(0,0,500,300);
                shoppingListPanel.setLayout(new BorderLayout(10,10));

                productClear = new JButton("Clear");
                serviceClear = new JButton("Clear");

                frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(null);
                frame.setSize(800,750);
                frame.setTitle("Order System GUI");
                frame.add(productPanel);
                frame.add(servicePanel);
                frame.add(checkOutPanel);
                frame.add(shoppingListPanel);
                frame.add(orderButtonsPanel);

                //Product GUI
                textProductName = new JTextField();
                textProductQuantity  = new JTextField();
                textProductPrice = new JTextField();
                labelProductName = new JLabel("Product name: ");
                labelProductQuantity = new JLabel("Quantity: ");
                labelProductPrice = new JLabel("Price: ");
                buttonAddProduct = new JButton("Add Product");

                //Service GUI
                textServiceName = new JTextField();
                textServicePersonel  = new JTextField();
                textServiceHours = new JTextField();
                labelServiceName = new JLabel("Service name: ");
                labelServicePersonel = new JLabel("Personel: ");
                labelServiceHours = new JLabel("Hours: ");
                buttonAddService = new JButton("Add Service");



                shoppingList = new JTextArea();
                shoppingList.setText("Current Cart:\n");

                checkOut = new JTextArea();
                checkOut.setBounds(500, 0,300,500);

                //Finnish Order Button
                buttonFinnishOrder = new JButton("Finnish Order");
                buttonRemoveOrders = new JButton("Remove Orders");
                buttonClearCart = new JButton("Clear Cart");

                //Panel .add Product
                productPanel.add(labelProductName);
                productPanel.add(textProductName);
                productPanel.add(labelProductQuantity);
                productPanel.add(textProductQuantity);
                productPanel.add(labelProductPrice);
                productPanel.add(textProductPrice);
                productPanel.add(productClear);
                productPanel.add(buttonAddProduct);

                //Panel .add Service
                servicePanel.add(labelServiceName);
                servicePanel.add(textServiceName);
                servicePanel.add(labelServicePersonel);
                servicePanel.add(textServicePersonel);
                servicePanel.add(labelServiceHours);
                servicePanel.add(textServiceHours);
                servicePanel.add(serviceClear);
                servicePanel.add(buttonAddService);

                //Shopping List and Check out
                shoppingListPanel.add(shoppingList);
                checkOutPanel.add(checkOut);

                //FinishOrderButton
                orderButtonsPanel.add(buttonFinnishOrder);
                orderButtonsPanel.add(buttonRemoveOrders);
                orderButtonsPanel.add(buttonClearCart);

                //Product Button Action
                buttonAddProduct.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                String name = textProductName.getText();
                                int quantity = Integer.parseInt(textProductQuantity.getText());
                                int price = Integer.parseInt(textProductPrice.getText());
                                //Change setText to append to extend the shopping cart
                                shoppingList.setText(name+", "+quantity+"pcs, "+price+"€");
                                orderService.processOrder(PRODUCT, name, price, quantity);
                        }
                });

                //Service Button Action
                buttonAddService.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                String serviceName = textServiceName.getText();
                                int personel = Integer.parseInt(textServicePersonel.getText());
                                int hours = Integer.parseInt(textServiceHours.getText());
                                //Change setText to append to extend the shopping cart
                                shoppingList.append(serviceName+", "+personel+"persons, "+hours+"h");
                                orderService.processOrder(SERVICE, serviceName, personel, hours);
                        }
                });

                //FinnishOrder Button Action
                buttonFinnishOrder.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                orderService.finishOrder();
                                int counter = 0;
                                ArrayList<Order> orders = orderService.ordersDbread();
                                for (var o : orders) {

                                        checkOut.append("Order[" + counter + "]\n" + o.getItem() + "\n"
                                                + "Sum:  " + o.getSum() + "\n"+ "Date: " + getDate() + "\n\n");
                                        counter++;
                                }
                                orderService.newOrder();
                        }
                });

                buttonRemoveOrders.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                orderService.DeleteDb();
                                checkOut.setText("");
                                shoppingList.setText("Current Cart:\n");
                        }
                });

                productClear.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                textProductName.setText("");
                                textProductQuantity.setText("");
                                textProductPrice.setText("");
                        }
                });

                serviceClear.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                textServiceName.setText("");
                                textServicePersonel.setText("");
                                textServiceHours.setText("");
                        }
                });

                buttonClearCart.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                shoppingList.setText("Current Cart:\n");
                                cartPrice = 0;
                        }
                });

                frame.setVisible(true);
        }
}
