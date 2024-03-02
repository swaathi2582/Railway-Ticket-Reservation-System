public class passenger {
    static int id=1;
    String passenger_name;
    int passenger_age;
    String berth_preference;
    int passenger_id;
    String alloted;
    int seat_number;

    public passenger(String name,int age,String s){
        this.passenger_name = name;
        this.passenger_age = age;
        this.berth_preference = s;
        passenger_id = id++;
        alloted = "";
        seat_number = -1;
    }
}
