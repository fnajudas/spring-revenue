package Revenue.Using.Springboot.utilities;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
    public static Timestamp getTimeNow(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new Timestamp(date.getTime());
    }
}
