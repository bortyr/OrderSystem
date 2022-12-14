package adapter.presenter;


import adapter.gateway.OrderProvider;
import entities.Order;
import usecase.OrderService;
import usecase.OrderStructure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static entities.ItemType.PRODUCT;
import static entities.ItemType.SERVICE;

public class gui {

        OrderService orderService = new OrderService();
        OrderProvider ordersDb = new OrderProvider();

        OrderStructure orderStructure = new OrderStructure();
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
        private JButton buttonFinishOrder;
        private JButton buttonRemoveOrders;
        private JTextArea textShoppingList;
        private JScrollPane paneShoppingList;
        private JTextArea textCheckOut;
        private JScrollPane paneCheckOut;
        private JButton buttonProductClear;
        private JButton buttonServiceClear;
//        private JButton buttonClearCart;
        private int itemsInCart;

        public void startGui(){

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

                buttonProductClear = new JButton("Clear");
                buttonServiceClear = new JButton("Clear");

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

                textShoppingList = new JTextArea();
                paneShoppingList = new JScrollPane(textShoppingList);
                textShoppingList.setEditable(false);
                textShoppingList.setText("Current Cart:\n");

                textCheckOut = new JTextArea();
                paneCheckOut = new JScrollPane(textCheckOut);
                textCheckOut.setEditable(false);
                textCheckOut.setBounds(500, 0,300,500);

                //Finish Order Button
                buttonFinishOrder = new JButton("Finish Order");
                buttonRemoveOrders = new JButton("Remove Orders");
//                buttonClearCart = new JButton("Clear Cart");

                //Panel .add Product
                productPanel.add(labelProductName);
                productPanel.add(textProductName);
                productPanel.add(labelProductQuantity);
                productPanel.add(textProductQuantity);
                productPanel.add(labelProductPrice);
                productPanel.add(textProductPrice);
                productPanel.add(buttonProductClear);
                productPanel.add(buttonAddProduct);

                //Panel .add Service
                servicePanel.add(labelServiceName);
                servicePanel.add(textServiceName);
                servicePanel.add(labelServicePersonel);
                servicePanel.add(textServicePersonel);
                servicePanel.add(labelServiceHours);
                servicePanel.add(textServiceHours);
                servicePanel.add(buttonServiceClear);
                servicePanel.add(buttonAddService);

                //Shopping List and Check out
                shoppingListPanel.add(paneShoppingList);
                checkOutPanel.add(paneCheckOut);

                //FinishOrderButton
                orderButtonsPanel.add(buttonFinishOrder);
                orderButtonsPanel.add(buttonRemoveOrders);
//                orderButtonsPanel.add(buttonClearCart);

                //Product Button Action
                buttonAddProduct.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                String name = textProductName.getText();

                                if(!checkForInt(textProductQuantity.getText())){
                                        checkForIntFailed(2);
                                        return;
                                }
                                if(!checkForInt(textProductPrice.getText())){
                                        checkForIntFailed(3);
                                        return;
                                }
                                int quantity = Integer.parseInt(textProductQuantity.getText());
                                int price = Integer.parseInt(textProductPrice.getText());
                                //Change setText to append to extend the shopping cart
                                textShoppingList.append(name+", "+quantity+" pcs, "+"???"+price+"\n");
                                orderService.processOrder(PRODUCT, name, price, quantity);
                                itemsInCart++;
                        }
                });

                //Service Button Action
                buttonAddService.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                String serviceName = textServiceName.getText();
                                if(!checkForInt(textServicePersonel.getText())){
                                        checkForIntFailed(0);
                                        return;
                                }
                                if(!checkForInt(textServiceHours.getText())){
                                        checkForIntFailed(1);
                                        return;
                                }
                                int personel = Integer.parseInt(textServicePersonel.getText());
                                int hours = Integer.parseInt(textServiceHours.getText());
                                //Change setText to append to extend the shopping cart
                                textShoppingList.append(serviceName+", "+personel+" person/s, "+hours+"h\n");
                                orderService.processOrder(SERVICE, serviceName, personel, hours);
                                itemsInCart++;
                        }
                });

                //Finish Order Button Action
                buttonFinishOrder.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                if (itemsInCart ==0){
                                        JOptionPane.showMessageDialog(frame, "Empty Cart");
                                }
                                else {

                                        orderService.finishOrder();

                                        orderStructure.create(orderService.getOrder());
                                        ordersDb.create(orderStructure);
                                        int counter = 0;
                                        textCheckOut.setText("");
                                        // TODO: implement <<Data Structure>> class for use here between UI & usecase

                                        ArrayList<Order> orders = ordersDb.read();
                                        for (var o : orders) {
                                                textCheckOut.append("Order[" + counter + "]\n" + o.getItem() + "\n" + "Sum:  " + o.getSum() + "\n" + "Date: " + getDate() + "\n\n");
                                                counter++;
                                        }
                                        orderService.newOrder();
                                        textShoppingList.setText("Current Cart:\n");
                                        itemsInCart = 0;
                                }
                        }
                });

                buttonRemoveOrders.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                ordersDb.delete();
                                textCheckOut.setText("");
                                textShoppingList.setText("Current Cart:\n");
                        }
                });

                buttonProductClear.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                textProductName.setText("");
                                textProductQuantity.setText("");
                                textProductPrice.setText("");
                        }
                });

                buttonServiceClear.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                textServiceName.setText("");
                                textServicePersonel.setText("");
                                textServiceHours.setText("");
                        }
                });

//                buttonClearCart.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                                textShoppingList.setText("Current Cart:\n");
//                        }
//                });

                frame.setVisible(true);
        }

        public boolean checkForInt(String inputParameter){
                try{
                        if(Integer.parseInt(inputParameter)<=0) {return false;}
                        return true;
                } catch (NumberFormatException ex){
                        return false;
                }

        }

        public void checkForIntFailed(int i){
                JOptionPane.showMessageDialog(frame, "Wrong input");
                switch (i) {
                        case 0:
                                textServicePersonel.setText("Error: Integer required");
                                break;
                        case 1:
                                textServiceHours.setText("Error: Integer required");
                                break;
                        case 2:
                                textProductQuantity.setText("Error: Integer required");
                                break;
                        case 3:
                                textProductPrice.setText("Error: Integer required");
                                break;
                }
        }
}