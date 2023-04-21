import java.util.Scanner;

public class Admin {
    final private String PASSWORD = "35165165";
    final private String USERNAME = "admin1415";
    private Flight[] flights = new Flight[100];

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public Flight[] getFlights() {
        return flights;
    }

    public void setFlights(Flight[] flights) {
        this.flights = flights;
    }

    public void addFlight() {
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter fields of flight");
        String flightId = input.next();
        String origin = input.next();
        String destination = input.next();
        String date = input.next();
        String time = input.next();
        int price = input.nextInt();
        int seats = input.nextInt();

        int i = 0;
        while (flights[i] != null)
            i++;

         flights[i] = new Flight(flightId, origin, destination, date, time, price, seats);
    }

    public void updateFlight() {
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter the row of flight in table that be must update");
        int i = input.nextInt() - 1;

        System.out.println("which of these (flight id, origin, destination and...) must be update?");
        String update = input.next();

        if (update.equals("flight id"))
            flights[i].setFlightId(input.next());

        if (update.equals("origin"))
            flights[i].setOrigin(input.next());

        if (update.equals("destination"))
            flights[i].setDestination(input.next());

        if (update.equals("date"))
            flights[i].setDate(input.next());

        if (update.equals("time"))
            flights[i].setTime(input.next());

        if (update.equals("price"))
            flights[i].setPrice(input.nextInt());

        if (update.equals("seats"))
            flights[i].setSeats(input.nextInt());
    }

    public void removeFlight() {
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter the flight id to remove");
        String flightId = input.next();
        int i;
        for (i = 0; flights[i] != null; i++)
            if (flightId.equals(flights[i].getFlightId())) {
                flights[i] = null;
                break;
            }


        while (flights[i + 1] != null) {
            flights[i] = flights[i + 1];
            i++;
        }

        flights[i] = null;

    }


    public void flightSchedules() {
        System.out.println("|Row|Flight Id  |Origin      |Destination |Date      |Time |price    |Seats|");
        System.out.println("............................................................................");

        for (int i = 0; flights[i] != null; i++) {
            System.out.printf("|%-3d|%-11s|%-12s|%-12s|%-10s|%-5s|%-,9d|%-5s|%n", i + 1, flights[i].getFlightId(),
                    flights[i].getOrigin(), flights[i].getDestination(), flights[i].getDate(), flights[i].getTime(),
                    flights[i].getPrice(), flights[i].getSeats());
            System.out.print("............................................................................\n");
        }
    }
}
