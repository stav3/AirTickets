import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Κλάση MyGUI που θα υλοποιεί το main menu της εφαρμογής

public class MyGUI extends JFrame {

    private ArrayList<MyTicket> customerList;

    private JPanel mainPanel;
    private JMenuBar mainBar;
    private JMenu optionsMenu;
    private JMenuItem newTicketItem;
    private JMenuItem ticketListItem;
    private JMenuItem appInfoItem;
    private JMenuItem exitItem;

    private JButton newTicketBtn;
    private JButton ticketListBtn;
    private JButton aboutBtn;
    private JButton exitBtn;


    public MyGUI() {

        super();

        newTicketBtn = new JButton("New Ticket");
        ticketListBtn = new JButton("Issued Tickets");
        aboutBtn = new JButton("About");
        exitBtn = new JButton("Exit");


        mainBar = new JMenuBar();
        optionsMenu = new JMenu("Menu");
        newTicketItem = new JMenuItem("New Ticket");
        ticketListItem = new JMenuItem("Issued Tickets");
        appInfoItem = new JMenuItem("About");
        exitItem = new JMenuItem("Exit");


    }


    public void prepareGUI() {

        //Δημιουργία Panel και προσθήκη των στοιχείων του GUI

        mainPanel = new JPanel();

        mainPanel.setLayout(new GridLayout(9,2));

        optionsMenu.add(newTicketItem);
        optionsMenu.add(ticketListItem);
        optionsMenu.add(appInfoItem);
        optionsMenu.addSeparator();
        optionsMenu.add(exitItem);

        mainPanel.add(newTicketBtn);
        mainPanel.add(ticketListBtn);
        mainPanel.add(aboutBtn);
        mainPanel.add(exitBtn);

        this.add(mainPanel, BorderLayout.CENTER);

        mainBar.add(optionsMenu);
        this.setJMenuBar(mainBar);


        //Ανώνυμες κλάσεις που υλοποιούν την αλληλεπίδραση των κουμπιών
        //με τον χρήστη

        newTicketItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewTicket();
            }
        });

        ticketListItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTickerList();
            }
        });

        aboutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AboutFrame about= new AboutFrame();
                about.prepareGUI();
                dispose();
            }
        });

        appInfoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AboutFrame about= new AboutFrame();
                about.prepareGUI();
                dispose();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int choice = JOptionPane.showConfirmDialog(MyGUI.this, "Are you sure you want to exit?");
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(1);
                }
            }
        });

        newTicketBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewTicket();
            }
        });

        ticketListBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTickerList();
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(MyGUI.this, "Are you sure you want to exit?");
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(1);
                }
            }
        });


        this.setSize(780, 400);
        this.setLocationRelativeTo(null);
        this.setTitle("Air Tickets 171149");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    //Μέθοδος που ξεκινάει το καινούριο παράθυρο για την κλάση NewTicket
    //Κλάση η οποία δημιουργεί καινούριο εισιτήριο
    //Το παράθυρο του main menu κλείνει με το άνοιγμα της NewTicket
    //με την μέθοδο dispose()

    public void startNewTicket() {

        NewTicket newTicketFrame = new NewTicket();
        newTicketFrame.prepareGUI();
        dispose();
    }

    //Μέθοδος που ξεκινάει το καινούριο παράθυρο της TicketList
    //η οποία εμφανίζει τα εισιτήρια που έχουν ήδη εκδοθεί
    //όπως και στην startNewTicket(), το παράθυρο του main menu
    //κλείνει με την dispose()

    public void startTickerList() {

        TickerList ticketFrame = new TickerList();
        ticketFrame.prepareGUI();
        dispose();

    }
}
