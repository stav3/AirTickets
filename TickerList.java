import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

//Κλάση TicketList για την εμφάνιση των ήδη τυπωμένων εισιτηρίων

public class TickerList extends JFrame {

    private ArrayList<MyTicket> customerList;
    private JTextArea txtArea, topArea;
    private Statistics stats;
    private JButton newTicketBtn, refreshBtn, exitBtn;
    private JMenuBar mainBar;
    private JMenu optionsMenu;
    private JMenuItem newTicketItem;
    private JMenuItem ticketListItem;
    private JMenuItem appInfoItem;
    private JMenuItem exitItem;


    public TickerList() {

        //Τα δεδομένα των των εισιτηρίων θα αποθηκέυονται στην ArrayList customerList
        //τύπου MyTicket που αποτελεί την κλάση του αντικειμένου "εισιτήριο"
        //στο στιγμιότυπο της κλάσης Statistics, stats, θα αποθηκέυονται όλα τα στατιστικά
        //δεδομένα που θα προσφέρονται στον χρήστη

        customerList = new ArrayList<>();
        stats = new Statistics();

        txtArea = new JTextArea(10, 70);
        txtArea.setEditable(false);

        topArea = new JTextArea();
        topArea.setEnabled(false);

        newTicketBtn = new JButton("New Ticket");
        refreshBtn = new JButton("Refresh");
        exitBtn = new JButton("Exit");
//        UIManager.getDefaults().put("TextArea.font", UIManager.getFont("TextField.font"));

        mainBar = new JMenuBar();
        optionsMenu = new JMenu("Menu");
        newTicketItem = new JMenuItem("New Ticket");
        ticketListItem = new JMenuItem("Issued Tickets");
        appInfoItem = new JMenuItem("About");
        exitItem = new JMenuItem("Exit");

    }

    public void prepareGUI() {


        JPanel panel = new JPanel();
        JPanel btmPanel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        panel.add(topArea, BorderLayout.PAGE_START);
        panel.add(txtArea, BorderLayout.CENTER);
        panel.add(btmPanel, BorderLayout.PAGE_END);

        btmPanel.setLayout(new FlowLayout());

        btmPanel.add(newTicketBtn);
        btmPanel.add(refreshBtn);
        btmPanel.add(exitBtn);

        JScrollPane scrpanel = new JScrollPane(panel);

        readCustomers();
        sortCustomers();
        stats.generateStats(customerList);
        displayCustomers();
        displayStatistics();

        optionsMenu.add(newTicketItem);
        optionsMenu.add(ticketListItem);
        optionsMenu.add(appInfoItem);
        optionsMenu.addSeparator();
        optionsMenu.add(exitItem);
        mainBar.add(optionsMenu);
        this.setJMenuBar(mainBar);

        this.add(scrpanel);

        newTicketItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                NewTicket ticket = new NewTicket();
                ticket.prepareGUI();
                dispose();
            }

        });

        //Όλες οι ανώνυμες κλάσεις, όπως και στην κλάση MyGUI, καθόδηγούν τον
        //χρήστη σε οποιοδήποτε παράθυρο θελήση. Επιπλέον με βάση των προδιαγραφών της
        //εργασίας, προσφέρεται και η δυνατότητα της ανανέωσης των δεδομένων με την
        //μέθοδο refresh()

        ticketListItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(TickerList.this,
                        "You are already in Ticker List Window :)",
                        "Just to make sure",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int choice = JOptionPane.showConfirmDialog(TickerList.this, "Are you sure you want to exit?");
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(1);
                }
            }
        });

        newTicketBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewTicket ticket = new NewTicket();
                ticket.prepareGUI();
            }
        });

        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshCustomers();
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int choice = JOptionPane.showConfirmDialog(TickerList.this, "Are you sure you want to exit?");
                if (choice == JOptionPane.YES_OPTION) {
                    MyGUI mainMenu = new MyGUI();
                    mainMenu.prepareGUI();
                    dispose();
                }


            }
        });

        this.setSize(780, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    //Mέθοδος εκτύπωσης των δεδομένων στην txtArea ( στο πλαίσιο που εμφανίζονται τα δεδομένα )

    public void displayCustomers() {

        txtArea.append("Ticket ID\tIssue Date\tFull Name\tItinerary\tPrice\tFlight ID\tBoard Time\tGate\tSeat");
        txtArea.append("\n\n");


        for (int i = 0; i < customerList.size(); i++) {


            txtArea.append(customerList.get(i).getTicketID());
            txtArea.append("\t");
            txtArea.append(customerList.get(i).getIssueDate());
            txtArea.append("\t");
            txtArea.append(customerList.get(i).getClientName());
            txtArea.append("\t");
            txtArea.append(customerList.get(i).getItinerary());
            txtArea.append("\t");
            txtArea.append(String.valueOf(customerList.get(i).getPrice()));
            txtArea.append("\t");
            txtArea.append(customerList.get(i).getFlightID());
            txtArea.append("\t");
            txtArea.append(customerList.get(i).getBoardTime());
            txtArea.append("\t");
            txtArea.append(customerList.get(i).getGate());
            txtArea.append("\t");
            txtArea.append(customerList.get(i).getSeat());
            txtArea.append("\n");
        }
    }

    //Ανάγνωση των επικυρωμένων εισιτηρίων από το αρχείο customerList.txt
    //Διευκρίνηση: στο πλαίσιο της συλλογής των στατιστικών δεδομένων
    //με κάθε readLine() αποθηκεύεται το πλήθος των γραμμών
    //για την εκχώρηση της τιμής στην stats
    //κάθε εισιτήριο αποθηκεύεται και στην ArrayList customerList

    public void readCustomers() {

        customerList.clear();
        try {

            int count = 0;
            String line = null;
            String[] token;


            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\stavr\\Desktop\\AirTickets\\customerList.txt"));
            while (reader.ready()) {
                line = reader.readLine();
                count++;


                token = line.split(",", 9);
                if (token.length == 9) {
                    MyTicket customer = new MyTicket(token[0], token[1], token[2], token[3], Float.parseFloat(token[4]),
                            token[5], token[6], token[7], token[8]);

                    customerList.add(customer);

                }
            }
            stats.setNumOfTickets(count - 1);

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(TickerList.this,
                    "Could not Find Source File for Customers",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(TickerList.this,
                    "An error has occurred while reading customer list",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
        }


    }

    //Ταξινόμηση της ArrayList κατα αύξουσα σειρά με αλγόριθμο bubble sort
    //

    public void sortCustomers() {

        boolean sorted = false;

        while (!sorted) {
            sorted = true;
            for (int i = 0; i < customerList.size() - 1; i++) {

                if (customerList.get(i).getPrice() > customerList.get(i + 1).getPrice()) {

                    MyTicket temp = (new MyTicket(customerList.get(i).getTicketID(), customerList.get(i).getIssueDate(),
                            customerList.get(i).getClientName(), customerList.get(i).getItinerary(), customerList.get(i).getPrice()
                            , customerList.get(i).getFlightID(), customerList.get(i).getBoardTime(), customerList.get(i).getGate(),
                            customerList.get(i).getSeat()));

                    customerList.remove(i);
                    customerList.add(i + 1, temp);
                    sorted = false;
                }
            }
        }
    }


    //Εκτύπωση των στατιστικών δεδομένων στην πρώτη σειρά του παραθύρου με
    //βάση το BorderLayout

    public void displayStatistics() {

        topArea.append("Issued Tickets: ");
        topArea.append(String.valueOf(stats.getNumOfTickets()));
        topArea.append("\t");
        topArea.append("Total Cost: ");
        topArea.append(String.valueOf(stats.getCostOfTickets()) + "€");
        topArea.append("\t");
        topArea.append("Cheapest Ticket: ");
        topArea.append(stats.getMinTicId() + ", " + stats.getMinTic() + "€");
        topArea.append("\t");
        topArea.append("Most Expensive Ticket: " + stats.getMaxTicId() + ", " + stats.getMaxTic() + "€");
    }

    //Λειτουργία refresh κουμπιού. Αφού προστεθεί καινούριο εισιτήριο,
    //διαβάζεται από την αρχείο το .txt αρχείο με ολόκληρο το σύνολο
    //και εκτυπώνεται στα κατάλληλα πεδία του GUI μαζί με τα καινούρια
    //στατιστικά δεδομένα

    public void refreshCustomers() {

        txtArea.setText(" ");
        topArea.setText(" ");

        readCustomers();
        sortCustomers();
        stats.generateStats(customerList);
        displayStatistics();
        displayCustomers();

    }

}
