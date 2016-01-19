package Controller;

import java.text.SimpleDateFormat;
import java.util.Date;


public class UtilityController {

    //This is to convert Java DateTime String into SQL readable String Format (YYYY-MM-DD h:m:s) e.g:2016-01-04 23:34:09
    public static String convertSQLDateTimeString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

}
