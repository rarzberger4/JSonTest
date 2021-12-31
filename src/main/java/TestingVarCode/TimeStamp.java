package TestingVarCode;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimeStamp {

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Timestamp(System.currentTimeMillis())));
    }

}
