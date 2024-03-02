import java.util.Scanner;

public class index{

    public static void bookTicket(passenger p){
        if(TicketBooker.availableWaitingList == 0){
            System.out.println("------------------NO TICKETS AVAILABLE-----------------");
            return;
        }

        if((p.berth_preference.equals("L"))&&(TicketBooker.availableLowerBerth > 0)){
            System.out.println("--1--");
            TicketBooker.bookTicket(p,"L",TicketBooker.listLowerBerth.get(0));
            TicketBooker.listLowerBerth.remove(0);
            TicketBooker.availableLowerBerth--;
        }else if((p.berth_preference.equals("M"))&&(TicketBooker.availableMiddleBerth > 0)){
            System.out.println("--2--");
            TicketBooker.bookTicket(p,"M",TicketBooker.listMiddleBerth.get(0));
            TicketBooker.listMiddleBerth.remove(0);
            TicketBooker.availableMiddleBerth--;
        }else if((p.berth_preference.equals("U"))&&(TicketBooker.availableUpperBerth > 0)) {
            System.out.println("--3--");
            TicketBooker.bookTicket(p,"U",TicketBooker.listUpperBerth.get(0));
            TicketBooker.listUpperBerth.remove(0);
            TicketBooker.availableUpperBerth--;
        }else if(TicketBooker.availableLowerBerth > 0){
            System.out.println("--4--");
            TicketBooker.bookTicket(p,"L",TicketBooker.listLowerBerth.get(0));
            TicketBooker.listLowerBerth.remove(0);
            TicketBooker.availableLowerBerth--;
        }else if(TicketBooker.availableMiddleBerth > 0){
            System.out.println("--5--");
            TicketBooker.bookTicket(p,"M",TicketBooker.listMiddleBerth.get(0));
            TicketBooker.listMiddleBerth.remove(0);
            TicketBooker.availableMiddleBerth--;
        }else if(TicketBooker.availableUpperBerth > 0){
            System.out.println("--6--");
            TicketBooker.bookTicket(p,"U",TicketBooker.listUpperBerth.get(0));
            TicketBooker.listUpperBerth.remove(0);
            TicketBooker.availableUpperBerth--;
        }else if(TicketBooker.availableRacTickets > 0){
            System.out.println("--7--");
            TicketBooker.racTicket(p);
        }else{
            System.out.println("--8--");
            TicketBooker.addtowaitinglist(p);
        }
    }

    public static void cancelTicket(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter passenger id:");
        int id = sc.nextInt();

        //check for validity of the passenger
        if(!TicketBooker.passengerobj.containsKey(id)){
              System.out.println("Invalid passenger");
              return;
        }

        passenger p = TicketBooker.passengerobj.get(id);
        TicketBooker.cancelTicket(p);

    }

    public static void showTickets(){
        System.out.println("Available Tickets : ");
        System.out.println("Upper Berth : "+TicketBooker.availableUpperBerth);
        System.out.println("Middle Berth : "+TicketBooker.availableMiddleBerth);
        System.out.println("Lower Berth : "+TicketBooker.availableLowerBerth);
        System.out.println("RAC : "+TicketBooker.availableRacTickets);
        System.out.println("Waiting List : "+TicketBooker.availableWaitingList);
    }

    public static void passengerDetails(){
        for(passenger pobj:TicketBooker.passengerobj.values()){
            System.out.println("Passenger Id : "+pobj.passenger_id);
            System.out.println("Passenger Name : "+pobj.passenger_name);
            System.out.println("Passenger allotment : "+pobj.alloted);
            System.out.println("Number : \n"+pobj.seat_number);
        }
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        boolean loop = true;

        while(loop){
            System.out.println("Enter your choice:\n");
            System.out.println("1.Book Your Ticket");
            System.out.println("2.Cancel Your Ticket");
            System.out.println("3.Show Available Tickets");
            System.out.println("4.Show all the passenger details");
            System.out.println("5.Exit");
            int choice = sc.nextInt();

            switch(choice){
                case 1:
                System.out.println("------------Ticket Booking---------------");
                System.out.println("Enter your name:");
                String name = sc.next();
                System.out.println("Enter your age:");
                int age = sc.nextInt();
                System.out.println("Enter your berth preference(L,M or U):");
                String preference = sc.next();
                passenger pobj = new passenger(name,age,preference);
                bookTicket(pobj);
                break;

                case 2:
                System.out.println("------------Cancel Ticket---------------");
                cancelTicket();
                break;

                case 3:
                System.out.println("------------Available Tickets---------------");
                showTickets();
                break;

                case 4:
                System.out.println("------------Displaying Passenger Details---------------");
                passengerDetails();
                break;

                case 5:
                loop = false;
                System.out.println("------------Thanks For Your Time---------------");
                break;
            }
        }
        

    }
}