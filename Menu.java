
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.table.*;

// Menu options screen
public class Menu extends JFrame{
    // Creating instances of the inventory and sales which will be modified depending on menu options
    private Inventory itemsInventory = new Inventory();
    private Sales sales = new Sales();

    public Menu(){
        // Setting configuration for Menu window
        super("Dawn's Grocery Inventory Management System - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(getToolkit().getScreenSize());
        setResizable(true);

        // Adding test data to inventory
        Item testDataItem = new Item("0 77975 02231 3", "Snyder’s of Hanover Mini Pretzels, 3.5 OZ", 120.00, 35, "Snyder's of Hanover", "23/12/22", "Snacks");
        itemsInventory.addItem(testDataItem);

        // Creating panel to store menu components
        JPanel displayPanel = new JPanel();

        JButton viewItemsBtn = new JButton("View Items");
        viewItemsBtn.setBounds(300, 430, 200, 68);
        viewItemsBtn.addActionListener(new ViewItemsListener());

        JButton createOrderBtn = new JButton("Create Order");
        createOrderBtn.setBounds(300, 430, 200, 68);
        createOrderBtn.addActionListener(new CreateOrderListener());

        JButton createItemBtn = new JButton("Create Item");
        createItemBtn.setBounds(300, 430, 200, 68);
        createItemBtn.addActionListener(new CreateItemListener());

        JButton viewSalesBtn = new JButton("View Sales");
        viewSalesBtn.setBounds(300, 430, 200, 68);
        viewSalesBtn.addActionListener(new ViewSalesListener());

        JButton viewReportsBtn = new JButton("View Reports");
        viewReportsBtn.setBounds(300, 430, 200, 168);
        viewReportsBtn.addActionListener(new ViewReportsListener());

        displayPanel.add(viewItemsBtn);
        displayPanel.add(createOrderBtn);
        displayPanel.add(createItemBtn);
        displayPanel.add(viewSalesBtn);
        displayPanel.add(viewReportsBtn);

        add(displayPanel);
        
    }

    // Displays items that were previously added to the system
    private class ViewItemsListener extends JFrame implements ActionListener{
        private JButton saveBtn;
        private JButton delBtn;
        private JPanel viewPnl;
        private JTable itemsTable;
        private ViewItemsListener itemFrame;

        public ViewItemsListener(){
            // Setting configuration for View Items window
            super("Dawn's Grocery Inventory Management System - View Products");
            setSize(getToolkit().getScreenSize()); 
            setResizable(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            itemFrame = new ViewItemsListener();
            viewPnl = new JPanel();

            // Adding save and delete buttons to edit and delete items
            saveBtn = new JButton("Save");
            saveBtn.setBounds(300, 430, 200, 68);
            saveBtn.addActionListener(new SaveButtonListener());

            delBtn = new JButton("Delete Selected Item");
            delBtn.setBounds(300, 430, 200, 68);
            delBtn.addActionListener(new DeleteButtonListener());

            viewPnl.add(saveBtn);
            viewPnl.add(delBtn);

            // Setting column names for the table
            String[] columnNames = {"UPC", "Name", "Unit Price", "Quantity", "Manufacturer", "Expiration Date", "Category"};
        
            // Getting items from inventory list and displaying on table
            ArrayList<Item> itemsList = itemsInventory.getItemsList();
            String data[][] = new String[itemsList.size()][columnNames.length];
            for (int i = 0; i < itemsList.size(); i++){
                data[i][0] = itemsList.get(i).getUPC();
                data[i][1] = itemsList.get(i).getName();
                data[i][2] = Double.toString(itemsList.get(i).getUnitPrice());
                data[i][3] = Integer.toString(itemsList.get(i).getQuantity());
                data[i][4] = itemsList.get(i).getManufacturer();
                data[i][5] = itemsList.get(i).getExpiration();
                data[i][6] = itemsList.get(i).getCategory();
            }

            itemsTable=new JTable(data,columnNames);
            itemsTable.setBounds(30,40,200,300);
            
            viewPnl.add(new JScrollPane(itemsTable));
            itemFrame.add(viewPnl);
            itemFrame.setVisible(true);
        }

        // When the save button is clicked, the edited item will be updated
        private class SaveButtonListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                // The new value will replace the value in the previous value
                if (itemsTable.getCellEditor() != null) {
                    DefaultCellEditor cellEditor = (DefaultCellEditor) itemsTable.getCellEditor();
                    String value = ((JTextField) cellEditor.getComponent()).getText();
                    int editedColumn = itemsTable.getSelectedColumn();
                    int editedRow = itemsTable.getSelectedRow();
                    itemsTable.setValueAt(value, editedRow, editedColumn);
                    for(int i=0; i< itemsTable.getRowCount(); i++){
                        if(editedRow == i){
                            String UPC = String.valueOf(itemsTable.getValueAt(i, 0));
                            Item item = itemsInventory.getItem(UPC);
                            item.modify(editedColumn, value);
                        }                        
                    }
                    
                }
                JOptionPane.showMessageDialog(null, "Product information saved.");
                itemFrame.dispose();

            }
        }

        // When the delete button is pressed, the selected item will be removed from the inventory
        private class DeleteButtonListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                    int selectedRow = itemsTable.getSelectedRow();
                    String UPC = "";
                    for(int i=0; i< itemsTable.getRowCount(); i++){
                        if(selectedRow == i){
                            UPC = String.valueOf(itemsTable.getValueAt(i, 0));
                        }                        
                    }
                    JOptionPane.showMessageDialog(null, "Product information successfully deleted.");
                    itemsInventory.removeItem(UPC);
                    itemFrame.dispose();
            }
        } 
    }

    private class CreateOrderListener extends JFrame implements ActionListener {
        private CreateOrderListener itemFrame;
        private JPanel orderPnl; 
        private JButton createOrderBtn;
        private JTable itemsTable;

        private ArrayList<Item> itemsList = itemsInventory.getItemsList();

        public CreateOrderListener(){
            // Setting configuration for Create Order window
            super("Dawn's Grocery Inventory Management System - Create Order");
            setSize(getToolkit().getScreenSize()); 
            setResizable(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            itemFrame = new CreateOrderListener();

            // Creating panel for Create Order Window components
            orderPnl = new JPanel();

            // Adding components to panel
            createOrderBtn = new JButton("Create Order");
            createOrderBtn.addActionListener(new OrderBtnListener());
            createOrderBtn.setBounds(300, 430, 200, 168);
            orderPnl.add(createOrderBtn);

            itemsTable = new JTable(new BooleanTableModel());

         
            itemsTable.setBounds(30,40,200,300);
            
            orderPnl.add(new JScrollPane(itemsTable));

            // Adding panel to frame
            itemFrame.add(orderPnl);
            itemFrame.setVisible(true);
        }

        // When create order button is clicked, a new order is created with the selected items, the item is removed from the inventory and a new invoice is generated
        private class OrderBtnListener implements ActionListener{
            public void actionPerformed(ActionEvent h){
                ArrayList<Item> orderedItems = new ArrayList<Item>();
                double orderTotal = 0.0;
                Date orderDate = new Date();
                if(itemsTable.getSelectedRow() != -1){
                    for(int i=0; i< itemsTable.getRowCount(); i++){
                        Object checkboxVal = itemsTable.getValueAt(i, 7);
                        if(checkboxVal.equals(Boolean.TRUE)){
                                String orderUPC = String.valueOf(itemsTable.getValueAt(i, 0));
                                Item orderItem = itemsInventory.getItem(orderUPC);
                                if(orderItem.getQuantity() != 0){
                                    try{
                                        double itemPrice = Double.parseDouble(String.valueOf(itemsTable.getValueAt(i, 2)));
                                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                        Date dateWithoutTime = formatter.parse(formatter.format(new Date()));
                                        orderDate = dateWithoutTime;
                                        orderedItems.add(orderItem);
                                        orderTotal += itemPrice;
                                    } catch(ParseException dateE){
                                        System.out.println("Error formatting current date");
                                    }
                                } else{
                                    JOptionPane.showMessageDialog(null, "One or more of the selected items is out of stock. Only available items will be added to invoice.");
                                }
                        }
                    }
                    if(orderedItems.size() != 0){
                        Order newOrder = new Order(orderedItems,orderTotal, orderDate);
                        sales.addOrder(newOrder);
                        
                        Invoice customerInvoice = new Invoice(newOrder);
        
                        // Creating new window to show invoice details
                        JFrame invoiceFrame = new JFrame("Order #" + customerInvoice.getOrder().getOrderID() + " Invoice");
                        invoiceFrame.setSize(getToolkit().getScreenSize()); 
                        invoiceFrame.setResizable(true);
        
                        JPanel invPnl = new JPanel();
        
                        String invoiceInfo = customerInvoice.displayInvoice();
                        JTextArea invoiceInfoLabel = new JTextArea(invoiceInfo, 36, 5);
                        invPnl.add(invoiceInfoLabel);
        
                        invoiceFrame.add(invPnl);
                        invoiceFrame.pack();
                        invoiceFrame.setVisible(true);
                        itemsInventory.orderItems(orderedItems);
                    } else{
                        JOptionPane.showMessageDialog(null, "Selected items are out of stock. Invoice could not be generated.");
                    }
                    
                    
                } else{
                    JOptionPane.showMessageDialog(null, "No items selected.");
                }
           
            }
        }

        // Defining the abstract table model used by the create order table
        private class BooleanTableModel extends AbstractTableModel {
            // Defining columns
            String[] columns = {"UPC", "Name", "Unit Price", "Quantity", "Manufacturer", "Expiration Date", "Category", ""};
            
            // Calling function to create table data
            Object[][] tableData = generateData();

            // Creating table data based on inventory
            public Object[][] generateData(){
                Object[][] data = new Object[itemsList.size()][columns.length];
                for (int i = 0; i < itemsList.size(); i++){
                    data[i][0] = itemsList.get(i).getUPC();
                    data[i][1] = itemsList.get(i).getName();
                    data[i][2] = Double.toString(itemsList.get(i).getUnitPrice());
                    data[i][3] = Integer.toString(itemsList.get(i).getQuantity());
                    data[i][4] = itemsList.get(i).getManufacturer();
                    data[i][5] = itemsList.get(i).getExpiration();
                    data[i][6] = itemsList.get(i).getCategory();
                    data[i][7] = Boolean.FALSE;
                }
                return data;
            }

            @Override
            public int getRowCount() {
                return tableData.length;
            }
        
            public int getColumnCount() {
                return columns.length;
            }
        
            public Object getValueAt(int rowIndex, int columnIndex) {
                return tableData[rowIndex][columnIndex];
            }

            @Override
            public void setValueAt(Object value, int rowIndex, int columnIndex) {
                tableData[rowIndex][columnIndex] = value;
                fireTableCellUpdated(rowIndex, columnIndex);
            }
        
            @Override
            public String getColumnName(int column) {
                return columns[column];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return true;
            }
        
            // To render boolean data as a checkbox
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return tableData[0][columnIndex].getClass();
            }
        }
        
    }
    
    // When the user clicks the create item button, they will be able to enter product details which will then be stored in the system.
    private class CreateItemListener extends JFrame implements ActionListener {
        private JTextField upcField;
        private JTextField nameField;
        private JTextField priceField;
        private JTextField qtyField;
        private JTextField manField;
        private JTextField expField;
        private JTextField catField;
        private CreateItemListener itemFrame;

        public CreateItemListener(){
            // Setting configuration for Create Item window
            super("Dawn's Grocery Inventory Management System - Add Product");
            setSize(getToolkit().getScreenSize()); 
            setResizable(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Adding components to panel
            itemFrame = new CreateItemListener();
            JPanel forms = new JPanel();

            JLabel upcLabel = new JLabel("UPC");
            upcField = new JTextField(20);
            forms.add(upcLabel);
            forms.add(upcField);

            JLabel nameLabel = new JLabel("Product Name");
            nameField = new JTextField(20);
            forms.add(nameLabel);
            forms.add(nameField);

            JLabel priceLabel = new JLabel("Unit Price");
            priceField = new JTextField(20);
            forms.add(priceLabel);
            forms.add(priceField);

            JLabel qtyLabel = new JLabel("Quantity");
            qtyField = new JTextField(20);
            forms.add(qtyLabel);
            forms.add(qtyField);

            JLabel manLabel = new JLabel("Manufacturer");
            manField = new JTextField(20);
            forms.add(manLabel);
            forms.add(manField);

            JLabel expLabel = new JLabel("Expiration Date");
            expField = new JTextField(20);
            forms.add(expLabel);
            forms.add(expField);

            JLabel catLabel = new JLabel("Category");
            catField = new JTextField(20);
            forms.add(catLabel);
            forms.add(catField);

            JButton confirmBtn = new JButton("Confirm"); 
            confirmBtn.setBounds(300, 430, 200, 68);
            confirmBtn.addActionListener(new addItemConfirmListener());
            forms.add(confirmBtn);

            // Adding panel to frame
            itemFrame.add(forms);
            itemFrame.setVisible(true);
        }

        // When the user confirms the input values, a new item is greated with the associated input
        private class addItemConfirmListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                String upcValue = upcField.getText();
                String nameValue = nameField.getText();
                String priceValue = priceField.getText();
                String qtyValue = qtyField.getText();
                String manValue = manField.getText();
                String expValue = expField.getText();
                String catValue = catField.getText();

                // System.out.println(itemsInventory.getItem(upcValue).getUPC());
                if(!(itemsInventory.getItem(upcValue).getUPC() == null)){
                    JOptionPane.showMessageDialog(null, "UPC already exists. Please enter a different value.");
                }
              
                // If input value is incorrect, an error will be thrown
                try{
                    Item newItem = new Item(upcValue, nameValue, Double.parseDouble(priceValue), Integer.parseInt(qtyValue), manValue, expValue, catValue);
                    itemsInventory.addItem(newItem);
                } catch(Exception err) {
                    JOptionPane.showMessageDialog(null, "An error occurred. Please double check your input values.");
                }
                
                // System.out.println(itemsInventory);
                itemFrame.dispose();
            }
        }
    }

    private class ViewSalesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("View Sales Button Clicked");
        }
    }
    private class ViewReportsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("View Reports Button Clicked");
        }
    }
}
