
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Κλάση του παραθύρου About με τα προσωπικά μου δεδομένα


public class AboutFrame extends JFrame {

    private JTextArea mainArea;

    private JLabel iconLb;

    private JMenuBar mainBar;
    private JMenu optionsMenu;
    private JMenuItem newTicketItem;
    private JMenuItem ticketListItem;

    private JMenuItem aboutItem;
    private JMenuItem exitItem;


    public AboutFrame() {

        mainArea = new JTextArea(10, 30);
        mainArea.setEnabled(false);

        mainArea.setForeground(Color.magenta);
        mainBar = new JMenuBar();
        optionsMenu = new JMenu("Menu");
        newTicketItem = new JMenuItem("New Ticket");
        ticketListItem = new JMenuItem("Issued Tickets");
        aboutItem = new JMenuItem("About");
        exitItem = new JMenuItem("Exit");

    }

    public void prepareGUI() {


        JPanel panel = new JPanel();

        setLayout(new FlowLayout(FlowLayout.CENTER));

        mainArea.append("\tAirTickets 171149\n\n");
        mainArea.append("Author:\tMoysoglou Stavros\n");
        mainArea.append("A.M:\t171149\n");
        mainArea.append("Finished on:\t9/6/2021");

        optionsMenu.add(newTicketItem);
        optionsMenu.add(ticketListItem);
        optionsMenu.add(aboutItem);
        optionsMenu.addSeparator();
        optionsMenu.add(exitItem);

        panel.add(mainArea,BorderLayout.CENTER);

        mainBar.add(optionsMenu);
        this.setJMenuBar(mainBar);
        this.add(panel);

        newTicketItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewTicket ticket= new NewTicket();
                ticket.prepareGUI();
                dispose();
            }
        });

        ticketListItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TickerList ticketList= new TickerList();
                ticketList.prepareGUI();
                dispose();
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AboutFrame.this,
                        "You are already in the About page :)",
                        "Just to make sure",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int choice = JOptionPane.showConfirmDialog(AboutFrame.this, "Are you sure you want to exit?");
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(1);
                }
            }
        });

        this.setSize(780, 400);
        this.setLocationRelativeTo(null);
        this.setTitle("About");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


    }
}
