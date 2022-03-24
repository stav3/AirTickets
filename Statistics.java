import java.util.ArrayList;

//Η κλάση Statistics θα κάνει όλους τους υπολογισμούς
//των στατιστικών δεδομένων των εισητηρίων και θα αποθηκεύονται
//σε ένα στιγμιότυπο στην κλάση TicketList ως stats


public class Statistics {

    private int numOfTickets;
    private float costOfTickets;
    private String minTicId;            //Ticket ID του φθηνότερου εισητηρίου
    private float minTic;
    private String maxTicId;            //Ticket ID του ακριβότερου εισητηρίου
    private float maxTic;

    public Statistics() {

        numOfTickets = 0;
        costOfTickets = 0;

    }

    public int getNumOfTickets() {
        return numOfTickets;
    }

    public void setNumOfTickets(int numOfTickets) {
        this.numOfTickets = numOfTickets;
    }

    public float getCostOfTickets() {
        return costOfTickets;
    }

    public void setCostOfTickets(int costOfTickets) {
        this.costOfTickets = costOfTickets;
    }

    public String getMinTicId() {
        return minTicId;
    }

    public void setMinTicId(String minTicId) {
        this.minTicId = minTicId;
    }

    public float getMinTic() {
        return minTic;
    }

    public void setMinTic(float minTic) {
        this.minTic = minTic;
    }

    public String getMaxTicId() {
        return maxTicId;
    }

    public void setMaxTicId(String maxTicId) {
        this.maxTicId = maxTicId;
    }

    public float getMaxTic() {
        return maxTic;
    }

    public void setMaxTic(float maxTic) {
        this.maxTic = maxTic;
    }

    public void generateStats(ArrayList<MyTicket> obj) {

        //Ο αριθμός των ήδη εκτυπωμένων εισιτηρίων έχει ήδη υπολογιστεί
        //από την μέθοδο readCustomers() της TickerList με την count

        maxTic = obj.get(0).getPrice();
        minTic = obj.get(0).getPrice();
        maxTicId = obj.get(0).getTicketID();
        minTicId = obj.get(0).getTicketID();
        costOfTickets = 0;

        for (int i = 0; i < obj.size(); i++) {

            costOfTickets = costOfTickets + obj.get(i).getPrice();
            if (obj.get(i).getPrice() < minTic) {
                minTic = obj.get(i).getPrice();
                minTicId = obj.get(i).getTicketID();

            }

            if (obj.get(i).getPrice() > maxTic) {
                maxTic = obj.get(i).getPrice();
                maxTicId = obj.get(i).getTicketID();

            }
        }

    }
}


