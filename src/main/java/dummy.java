import java.time.LocalTime;

public class dummy {
    public static void main(String[] args) {
        LocalTime currentTime = LocalTime.now();
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        System.out.println("Current time is " + currentTime);
        currentTime = LocalTime.parse("16:00:00");
        if((currentTime.isBefore(closingTime))){
            System.out.println("Restarant is open for Business");
        } else {
            System.out.println("Restaurant is closed for business");
        }
    }
}
