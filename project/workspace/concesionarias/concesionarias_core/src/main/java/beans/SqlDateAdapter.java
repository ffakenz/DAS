package beans;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class SqlDateAdapter extends XmlAdapter <String , Timestamp>
{

    public String marshal(java.sql.Timestamp d) {
        return d.toString();
    }

    public Timestamp unmarshal(String v) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("%m/%d/%Y %H:%M:%S");
        java.sql.Timestamp sqlDate = null;
        try {
            java.util.Date convertedDate = dateFormat.parse(v);
            sqlDate = new java.sql.Timestamp(convertedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sqlDate;
    }
}