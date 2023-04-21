import java.util.Scanner;

public class Menu {
        private Passenger[] passengers = new Passenger[1000];
        private Passenger enteredPassenger;
        private Admin admin = new Admin();


        public void loginMenu() {
                while(true) {
                        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                        System.out.println("         WELCOME TO AIRLINE RESERVATION SYSTEM         ");
                        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                        System.out.println("   ...................MENU OPTIONS..................   ");
                        System.out.println("\t<1> Sign in\n\t<2> Sign up");

                        Scanner input = new Scanner(System.in);
                        int enter = input.nextInt();

                        if (enter == 1)
                                signIn();
                        else if (enter == 2)
                                signUp();
                }
        }

        public void adminMenu() {
                Scanner input = new Scanner(System.in);

                while (true) {
                        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                        System.out.println("                   ADMIN MENU OPTIONS                  ");
                        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                        System.out.println("   .................................................   ");
                        System.out.println("\t<1> Add\n\t<2> update\n\t<3> Remove\n\t<4> Flight schedules\n\t<0> Sign out");


                        int adminAction = input.nextInt();
                        switch (adminAction) {
                                case 1:
                                        admin.addFlight();
                                        break;
                                case 2:
                                        admin.updateFlight();
                                        break;
                                case 3:
                                        admin.removeFlight();
                                        break;
                                case 4:
                                        admin.flightSchedules();
                                        break;
                                case 0:
                                        return;
                                default:

                        }

                }
        }

        public void passengerMenu() {
                Scanner input = new Scanner(System.in);

                while (true) {
                        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                        System.out.println("                 PASSENGER MENU OPTIONS                 ");
                        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                        System.out.println("   ..................................................   ");
                        System.out.println("\t<1> Change password\n\t<2> Search flight tickets\n\t<3> Booking ticket\n\t<4> Ticket cancellation" +
                                "\n\t<5> Booked tickets\n\t<6> Add charge\n\t<0> Sign out");

                        int passengerAction = input.nextInt();

                        switch (passengerAction) {
                                case 1:
                                        enteredPassenger.changePassword();
                                        break;
                                case 2:
                                        enteredPassenger.searchFlight(admin.getFlights());
                                        break;
                                case 3:
                                        enteredPassenger.bookingTicket(admin.getFlights());
                                        break;
                                case 4:
                                        enteredPassenger.ticketCancellation(admin.getFlights());
                                        break;
                                case 5:
                                        enteredPassenger.bookedTickets(admin.getFlights());
                                        break;
                                case 6:
                                        enteredPassenger.addCharge();
                                        break;
                                case 0:
                                        return;
                                default:
                        }
                }
        }






        public void signUp() {
                Scanner input = new Scanner(System.in);
                Passenger passenger = new Passenger();
                int flag = 0;
                while(true) {
                        System.out.println("Please choose your username and password :");
                        String user = input.next();
                        String pass = input.next();

                        int i = 0;
                        while (passengers[i] != null)
                                i++;

                        passengers[i] = passenger;
                        passengers[i].setUsername(user);
                        passengers[i].setPassword(pass);

                        if (user.equals(admin.getUSERNAME()) || pass.equals(admin.getPASSWORD())) {
                                System.out.println("Sorry,This username or password already have used");
                                flag = 1;
                        }

                        for (int j = i - 1; j >= 0; j--) {
                                if (user.equals(passengers[j].getUsername()) || pass.equals(passengers[j].getPassword())) {
                                        System.out.println("Sorry,This username or password already have used");
                                        flag = 1;
                                }
                        }

                        if (flag == 0) {
                                break;
                        }
                }
        }


        public void signIn() {
                Scanner input = new Scanner(System.in);

                int flag = 0;
                while (true) {
                        System.out.println("Please enter your username and password");
                        String user = input.next();
                        String pass = input.next();

                        if (user.equals(admin.getUSERNAME()) && pass.equals(admin.getPASSWORD())) {
                                adminMenu();
                                flag = 1;
                        } else {
                                for (int i = 0; passengers[i] != null; i++)
                                        if (user.equals(passengers[i].getUsername()) && pass.equals(passengers[i].getPassword())) {
                                                enteredPassenger = passengers[i];
                                                passengerMenu();
                                                flag = 1;
                                        }

                        } if (flag == 0)
                                System.out.println("The User not found!");
                        else
                                break;
                }
        }
}
