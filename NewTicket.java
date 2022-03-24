import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

//Η κλάση που θα υλοποιεί το παράθυρο για καινούρια εισιτήρια


public class NewTicket extends JFrame {


    private JLabel ticketIDLb;
    private JLabel issueDateLb;
    private JLabel clientNameLb;
    private JLabel itineraryLb;
    private JLabel priceLb;
    private JLabel flightIdLb;
    private JLabel boardTimeLb;
    private JLabel gateLb;
    private JLabel seatLb;

    private JTextField ticketIDTf;
    private JTextField issueDateTf;
    private JTextField clientNameTf;
    private JTextField itineraryTf;
    private JTextField priceTf;
    private JTextField flightIdTf;
    private JTextField boardTimeTf;
    private JTextField gateTf;
    private JTextField seatTf;

    private JTextArea area;

    private JButton saveBtn;

    private JMenuBar mainBar;
    private JMenu optionsMenu;
    private JMenuItem newTicketItem;
    private JMenuItem ticketListItem;
    private JMenuItem appInfoItem;
    private JMenuItem exitItem;


    public NewTicket() {

        super();



        ticketIDLb = new JLabel("Ticket ID:");
        issueDateLb = new JLabel("Issue Date:");
        clientNameLb = new JLabel("Full Name:");
        itineraryLb = new JLabel("Itinerary:");
        priceLb = new JLabel("Price:");
        flightIdLb = new JLabel("Flight ID:");
        boardTimeLb = new JLabel("Boarding Time:");
        gateLb = new JLabel("Gate:");
        seatLb = new JLabel("Seat:");

        ticketIDTf = new JTextField(10);
        issueDateTf = new JTextField(10);
        clientNameTf = new JTextField(10);
        itineraryTf = new JTextField(10);
        priceTf = new JTextField(10);
        flightIdTf = new JTextField(10);
        boardTimeTf = new JTextField(10);
        gateTf = new JTextField(10);
        seatTf = new JTextField(10);


        saveBtn = new JButton("Save");

        area = new JTextArea();
        area.setEditable(false);

        mainBar = new JMenuBar();
        optionsMenu = new JMenu("Menu");
        newTicketItem = new JMenuItem("New Ticket");
        ticketListItem = new JMenuItem("Issued Tickets");
        appInfoItem = new JMenuItem("About");
        exitItem = new JMenuItem("Exit");


    }

    public void prepareGUI() {


        JPanel mainPanel = new JPanel();
        JPanel btnPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(10,2));
        String text = null;

        mainPanel.add(ticketIDLb);
        mainPanel.add(ticketIDTf);
        mainPanel.add(issueDateLb);
        mainPanel.add(issueDateTf);
        mainPanel.add(clientNameLb);
        mainPanel.add(clientNameTf);
        mainPanel.add(itineraryLb);
        mainPanel.add(itineraryTf);
        mainPanel.add(priceLb);
        mainPanel.add(priceTf);
        mainPanel.add(flightIdLb);
        mainPanel.add(flightIdTf);
        mainPanel.add(boardTimeLb);
        mainPanel.add(boardTimeTf);
        mainPanel.add(gateLb);
        mainPanel.add(gateTf);
        mainPanel.add(seatLb);
        mainPanel.add(seatTf);

        mainPanel.add(saveBtn);

        this.add(mainPanel, BorderLayout.CENTER);

        this.add(area, BorderLayout.AFTER_LAST_LINE);

        optionsMenu.add(newTicketItem);
        optionsMenu.add(ticketListItem);
        optionsMenu.add(appInfoItem);
        optionsMenu.addSeparator();
        optionsMenu.add(exitItem);


        mainBar.add(optionsMenu);
        this.setJMenuBar(mainBar);

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                MyTicket customer = new MyTicket();

                customer.setTicketID(ticketIDTf.getText());
                customer.setIssueDate(issueDateTf.getText());
                customer.setClientName(clientNameTf.getText());
                customer.setItinerary(itineraryTf.getText());
                customer.setPrice(Float.parseFloat(priceTf.getText()));
                customer.setFlightID(flightIdTf.getText());
                customer.setGate(gateTf.getText());
                customer.setSeat(seatTf.getText());
                customer.setBoardTime(boardTimeTf.getText());

                area.setText(customer.toString());


                    saveCustomer(customer);
                    JOptionPane.showMessageDialog(NewTicket.this,
                            "Saved Successfully!",
                            "Save Message",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(NewTicket.this,
                            "Could not save customer",
                            "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                }catch(IllegalArgumentException nullCells){

                    JOptionPane.showMessageDialog(NewTicket.this,
                            "Empty cells or Wrong input error",
                            "Error Message",
                            JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        newTicketItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(NewTicket.this,
                        "You are already in New Ticket Window :)",
                        "Just to make sure",
                        JOptionPane.INFORMATION_MESSAGE);

            }

        });

        ticketListItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TickerList list = new TickerList();
                list.prepareGUI();
                dispose();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int choice = JOptionPane.showConfirmDialog(NewTicket.this, "Are you sure you want to exit?");
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(1);
                }
            }
        });

        this.setSize(780, 400);
        this.setLocationRelativeTo(null);
        this.setTitle("New Ticket");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);


    }

    //Η μέθοδος saveCustomer() θα αποθηκέυει στο αρχείο customerList.txt και θα προσθέτει καινούρια
    //χωρίς να διαγράφει τα παλιά. Στο τέλος, κλείνει το παράθυρο και ανοίγει το main menu

    public void saveCustomer(MyTicket obj) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("customerList.txt", true));

        writer.write(obj.toString());
        writer.newLine();

        writer.close();

        MyGUI mainTab= new MyGUI();
        mainTab.prepareGUI();
        dispose();

    }


}
