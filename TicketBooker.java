import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TicketBooker {
    static int availableLowerBerth  = 1; //21 lower berth
    static int availableMiddleBerth = 1; //21 middle berth
    static int availableUpperBerth = 1; //21 upper berth
    static int availableRacTickets = 1; //18 rac
    static int availableWaitingList = 1; //10 waiting list

    static Queue<Integer> waitingList = new LinkedList<>();
    static Queue<Integer> racList = new LinkedList<>();
    static List<Integer> passengerList = new ArrayList<>();

    //available seats numbers in each list
    static List<Integer> listLowerBerth = new ArrayList<>(Arrays.asList(1));
    static List<Integer> listMiddleBerth = new ArrayList<>(Arrays.asList(1));
    static List<Integer> listUpperBerth = new ArrayList<>(Arrays.asList(1));
    static List<Integer> listRacList = new ArrayList<>(Arrays.asList(1));
    static List<Integer> listWaitingList = new ArrayList<>(Arrays.asList(1));

    static Map<Integer,passenger> passengerobj = new HashMap<>();

    public static void bookTicket(passenger p,String berthPosition,int number){
        p.alloted = berthPosition;
        p.seat_number = number;
        passengerobj.put(p.passenger_id,p);
        System.out.println("------------Ticket Booked Successfully--------------");
        System.out.println("Berth confirmation : "+p.alloted);
        System.out.println("Seat Number: "+p.seat_number);
    }

    public static void racTicket(passenger p){
        p.alloted = "rac";
        p.seat_number = listRacList.get(0);
        listRacList.remove(0);
        availableRacTickets--;
        racList.add(p.passenger_id);
        passengerobj.put(p.passenger_id,p);
        System.out.println("-----------------Added to RAC-----------------");
        System.out.println("RAC Number: "+p.seat_number);
    }

    public static void addtowaitinglist(passenger p){
        p.alloted = "waiting list";
        p.seat_number = listWaitingList.get(0);
        listWaitingList.remove(0);
        availableWaitingList--;
        waitingList.add(p.passenger_id);
        passengerobj.put(p.passenger_id,p);
        System.out.println("------------------Added to Waiting List---------------------");
    }

    public static void cancelTicket(passenger p){

       // System.out.println("Here");

        if(!p.alloted.equals("waiting list")){
            if(p.alloted.equals("L")){
                availableLowerBerth++;
                listLowerBerth.add(p.seat_number);
            }else if(p.alloted.equals("M")){
                availableMiddleBerth++;
                listMiddleBerth.add(p.seat_number);
            }else if(p.alloted.equals("U")){
                availableUpperBerth++;
                listUpperBerth.add(p.seat_number);
            }else if(p.alloted.equals("rac")){
                availableRacTickets++;
                listRacList.add(p.seat_number);
            }

            passengerobj.remove(p.passenger_id);

            if(racList.size() > 0){
                int passengerIdFromRac = racList.poll();
                passenger passengerObjFromRac = passengerobj.get(passengerIdFromRac);
                listRacList.add(passengerObjFromRac.seat_number);
                availableRacTickets++;

                if(waitingList.size()>0){
                    int passengerIdFromWL = waitingList.poll();
                    passenger passengerObjFromWL = passengerobj.get(passengerIdFromWL);
                    listWaitingList.add(passengerObjFromWL.seat_number);
                    availableWaitingList++;

                    passengerObjFromWL.alloted = "rac";
                    passengerObjFromWL.seat_number = listRacList.get(0);
                    listRacList.remove(0);
                    racList.add(passengerIdFromWL);
                    availableRacTickets--;

                }

                index.bookTicket(passengerObjFromRac);
            }

        }
        

        

    }

}
