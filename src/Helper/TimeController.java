package Helper;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static java.time.ZoneId.systemDefault;
import static java.time.ZoneOffset.UTC;

public class TimeController {

    /**
     *
     * @return An array of appointment time options
     */
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

    /**
     *  takes in a date time and returns the time to populate appointment combobox
     * @param dateTime
     * @return
     */
    public String splitDateTimeReturnTime(String dateTime) {
        String local = convertToLocal(dateTime);
        String test = local.substring(11, 12);
        String retVal = "";
        if (test.equals("0")) {
            retVal = local.substring(12, 19);
        } else {
            retVal = local.substring(11, 19);
        }
        return retVal;
    }

    /**
     * takes in a date time and returns the date
     * @param dateTime
     * @return
     */
    public LocalDate splitDateTimeReturnDate(String dateTime){
        String local = this.convertToLocal(dateTime);
        LocalDate localDate = LocalDate.parse(local.substring(6, 10) + "-" + local.substring(0, 5));
        return localDate;
    }

    /**
     *
     * @param localDatetime is converted to UTC time
     * @return
     */
    public String convertToUTC(LocalDateTime localDatetime) {
        if (localDatetime != null) {
            ZonedDateTime ldtZoned = localDatetime.atZone(ZoneId.systemDefault());
            ZonedDateTime utcZoned = ldtZoned.withZoneSameInstant(ZoneId.of("UTC"));
        return utcZoned.toString().substring(0, 16);
        } else {
            return null;
        }
    }

    /**
     *
     * @param date is converted to local date time from utc.
     * @return
     */
    public String convertToLocal(String date){
        if (date != null) {
            date = date.replaceAll("\\.0*$", "");
            String dateFormat = "yyyy-MM-dd HH:mm:ss";
            DateTimeFormatter formatAM = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a");
            String zoneString = ZoneId.systemDefault().toString();

            LocalDateTime ldt = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(dateFormat));
            ZonedDateTime utcZone = ldt.atZone(UTC);

            LocalDateTime srtL = LocalDateTime.ofInstant(utcZone.toInstant(), ZoneId.of(zoneString));
            return srtL.format(formatAM);
        } else {
            return null;
        }
    }

    public String removeTFromTime(String date){
        if (date != null){
            return date.replace("T", " ");
        }
        else {
        return  null;
        }
    }

}
