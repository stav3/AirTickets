import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

//H κλάση που αντιπροσωπεύει το εισιτήριο
//Η 4 προσωπικές προσθήκες μου είναι:
//flightID = κωδικός πτήσης
//boardTime= ώρα επιβίβασης
//gate= πύλη αεροδρομίου
//seat= η θέση στο αεροπλάνο

public class MyTicket {

    private String ticketID;
    private String issueDate;
    private String clientName;
    private String itinerary;
    private float price;

    private String flightID;
    private String boardTime;
    private String gate;
    private String seat;


    public MyTicket(){}

    public MyTicket(String newTickerId, String newIssueDate, String newClientName, String newItinerary,
                    float newPrice, String newFlightId, String newBoardTime, String newGate, String newSeat) {

        ticketID = newTickerId;
        issueDate = newIssueDate;
        clientName = newClientName;
        itinerary = newItinerary;
        price = newPrice;
        flightID = newFlightId;
        boardTime = newBoardTime;
        gate = newGate;
        seat = newSeat;

    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getItinerary() {
        return itinerary;
    }

    public void setItinerary(String itinerary) {
        this.itinerary = itinerary;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public String getBoardTime() {
        return boardTime;
    }

    public void setBoardTime(String boardTime) {
        this.boardTime = boardTime;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String toString() {
        return ticketID + "," + issueDate + "," + clientName + "," +
                itinerary + "," + price + "," + flightID + "," + boardTime + "," + gate + "," +
                seat;
    }


}
