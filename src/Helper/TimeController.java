package Helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeController {


    public static String[] getTOptions(){
        String[] options = new String[15];
        options[0] = "8:00 AM";
        options[1] = "9:00 AM";
        options[2] = "10:00 AM";
        options[3] = "11:00 AM";
        options[4] = "12:00 PM";
        options[5] = "1:00 PM";
        options[6] = "2:00 PM";
        options[7] = "3:00 PM";
        options[8] = "4:00 PM";
        options[9] = "5:00 PM";
        options[10] = "6:00 PM";
        options[11] = "7:00 PM";
        options[12] = "8:00 PM";
        options[13] = "9:00 PM";
        options[14] = "10:00 PM";
        return options;
    }

    public static String splitDateTimeReturnTime(String dateTime){
       String time = dateTime.split(" ")[1];
       int hour =  Integer.parseInt(time.substring(0,2));
       if (hour > 12){
           hour = hour - 12;
           return String.valueOf(hour) + time.substring(2,5) + " PM";
       } else if (hour == 12){
           return time.substring(0, 5) + " PM";
       } else if (hour > 9) {
           return time.substring(0, 5) + " AM";
       } else {
           return time.substring(1, 5) + " AM";
       }
    }

    public static LocalDate splitDateTimeReturnDate(String dateTime){
        LocalDate localDate = LocalDate.parse(dateTime.substring(0, 10));
        return localDate;
    }

}
