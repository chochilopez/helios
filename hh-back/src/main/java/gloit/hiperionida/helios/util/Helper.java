package gloit.hiperionida.helios.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Helper {
    public static HttpHeaders httpHeaders (String mensaje) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "mensaje");
        headers.set("mensaje", mensaje);

        return headers;
    }

    public static String localDateTimeToString(LocalDateTime localDateTime, String format) {
        try {
            if (format.isEmpty())
                format = "HH:mm:ss dd-MM-yyyy";
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);

            return dateTimeFormatter.format(ZonedDateTime.of(localDateTime, ZoneId.of("America/Argentina/Buenos_Aires")));
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDateTime zonedDateTimeToLocalDateTime(ZonedDateTime date) {
        try {
            ZoneId zoneId = ZoneId.of("America/Argentina/Buenos_Aires");
            ZonedDateTime zonedDateTime = date.withZoneSameInstant(zoneId);
            return zonedDateTime.toLocalDateTime();
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDateTime stringToLocalDateTime(String date, String format) {
        try {
            if (format.isEmpty())
                format = "HH:mm:ss dd-MM-yyyy";
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
            System.out.println(LocalDateTime.parse(date, dateTimeFormatter));

            return LocalDateTime.parse(date, dateTimeFormatter);
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDateTime getNow(String zone){
        if (zone.isEmpty())
            zone = "America/Argentina/Buenos_Aires";
        ZoneId zoneId = ZoneId.of(zone);

        return LocalDateTime.now(zoneId);
    }

    public static Double getNDecimal(Double number, int decimals){
        BigDecimal bigDecimal = new BigDecimal(number).setScale(decimals, RoundingMode.UP);
        return bigDecimal.doubleValue();
    }

    public static Boolean checkCellphone(String phoneNumber){
        // regex for e.164 phone number
        Pattern pattern = Pattern.compile("^\\+[1-9]\\d{1,14}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.find();
    }

    public static Integer getInteger(String stringNumber){
        try {
            if (stringNumber.isEmpty())
                return null;
            return Integer.parseInt(stringNumber);
        }catch(Exception e) {
            return null;
        }
    }

    public static Long getLong(String stringNumber){
        try {
            if (stringNumber.isEmpty())
                return null;
            return Long.parseLong(stringNumber);
        }catch(Exception e) {
            return null;
        }
    }

    public static Double getDecimal(String stringNumber){
        try {
            if (stringNumber.isEmpty())
                return null;
            return Double.parseDouble(stringNumber);
        }catch(Exception e) {
            return null;
        }
    }

    public static Boolean getBoolean(String bool){
        try {
            if (bool.isEmpty())
                return null;
            return Boolean.valueOf(bool);
        }catch(Exception e) {
            return null;
        }
    }

    public static Boolean isNullString (String aString) {
        return aString == null;
    }

    public static Boolean isEmptyString (String aString) {
        if (isNullString(aString)) return true;
        return aString.isEmpty();
    }
}
