import java.util.Scanner;

public class Passenger {
    private String username;
    private String password;
    private Ticket[] tickets = new Ticket[10];
    private int charge = 0;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Ticket[] getTickets() {
        return tickets;
    }

    public void setTickets(Ticket[] tickets) {
        this.tickets = tickets;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }





    public void changePassword() {
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter your new password");
        password = input.next();
    }



    public void searchFlight(Flight[] filteredFlights) {
        Scanner input = new Scanner(System.in);

        int initialLength = 0;
        while (filteredFlights[initialLength] != null)
            initialLength++;

        System.out.println("please primarily specialize fields(origin,destination,date,max price) and then filter the flights" +
                "\nand enter any more sth to see result");

        while (true) {
            String filter = input.next();

            switch (filter) {
                case "origin":
                    String origin = input.next();
                    for (int i = 0; i < initialLength; i++)
                        if (!(origin.equals(filteredFlights[i].getOrigin())))
                            filteredFlights[i] = null;
                    break;

                case "destination":
                    String destination = input.next();
                    for (int i = 0; i < initialLength; i++)
                        if (filteredFlights[i] != null && !(destination.equals(filteredFlights[i].getDestination())))
                            filteredFlights[i] = null;
                    break;

                case "date":
                    String date = input.next();
                    for (int i = 0; i < initialLength; i++)
                        if (filteredFlights[i] != null && !(date.equals(filteredFlights[i].getDate())))
                            filteredFlights[i] = null;
                    break;

                case "max price":
                    int price = input.nextInt();
                    for (int i = 0; i < initialLength; i++)
                        if (filteredFlights[i] != null && price < filteredFlights[i].getPrice())
                            filteredFlights[i] = null;
                    break;

                default:
                    int check = -1;
                    for (int i = 0; i < initialLength; i++) {
                        if (filteredFlights[i] != null) {
                            check++;
                            filteredFlights[check] = filteredFlights[i];
                        }
                    }
                    System.out.println("|Row|Flight Id  |Origin      |Destination |Date      |Time |Price    |Seats|");
                    System.out.println("............................................................................");
                    for (int i = 0; filteredFlights[i] != null; i++) {
                        System.out.printf("|%-3s|%-11s|%-12s|%-12s|%-10s|%-5s|%-,9d|%-5d|%n", i + 1,
                                filteredFlights[i].getFlightId(), filteredFlights[i].getOrigin(), filteredFlights[i].getDestination(),
                                filteredFlights[i].getDate(), filteredFlights[i].getTime(), filteredFlights[i].getPrice(),
                                filteredFlights[i].getSeats());
                        System.out.println("............................................................................\n");
                    }
                    return;

            }
        }
    }


    public void bookingTicket(Flight[] flights) {
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter flightId for booking ticket");

        String flightId = input.next();
        int i;
        for (i = 0; flights[i] != null; i++) {
            if (flightId.equals(flights[i].getFlightId())) {
                if (flights[i].getSeats() == 0) {
                    System.out.println("Sorry,The capacity fill!");
                    return;
                } else if (charge < flights[i].getPrice()) {
                    System.out.println("Sorry,Your charge is less than price!");
                    return;
                } else
                    break;
            }
        }

        charge -= flights[i].getPrice();
        flights[i].setSeats(flights[i].getSeats() - 1);

        int j = 0;
        while (tickets[j] != null)
            j++;

        tickets[j] = new Ticket();
        tickets[j].setFlightId(flightId);

        System.out.println("ticket id:"+tickets[j].makeTicketId());
    }


    public void ticketCancellation(Flight[] flights) {
        Scanner input = new Scanner(System.in);

        System.out.println("please enter the ticket id for cancellation");

        String ticketId = input.next();

        int i;
        for (i = 0; tickets[i] != null; i++) {
            if (ticketId.equals(tickets[i].getTicketId())) {
                for (int j = 0; flights[j] != null; j++) {
                    if (tickets[i].getFlightId().equals(flights[j].getFlightId())) {
                        flights[j].setSeats(flights[j].getSeats() + 1);
                        charge += flights[j].getPrice();
                    }
                }
                tickets[i] = null;
            }
        }

        while (tickets[i] != null) {
            tickets[i] = tickets[i + 1];
            i++;
        }
        tickets[i] = null;

    }



    public void bookedTickets(Flight[] flights) {
        for (int i = 0; tickets[i] != null; i++)
            for (int j = 0; flights[j] != null; j++)
                if (tickets[i].getFlightId().equals(flights[j].getFlightId()))
                    System.out.printf("%nTicketId:%-12s FlightId:%-9s Origin:%-12s Destination:%-12s Date:%-10s Time:%-5s" +
                            "Price:%-,9d Seats:%-5d%n", tickets[i].getTicketId(), flights[j].getFlightId(), flights[j].getOrigin(),
                            flights[j].getDestination(), flights[j].getDate(), flights[j].getTime(), flights[j].getPrice(),
                            flights[j].getSeats());
    }




    public void addCharge() {
        Scanner input = new Scanner(System.in);

        charge += input.nextInt();
    }


}
