import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args){
        String str = "2017-11-15";
        Date date1;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(str);
        }
        catch (ParseException e){
            date1 = new Date();
        }

    }
}
