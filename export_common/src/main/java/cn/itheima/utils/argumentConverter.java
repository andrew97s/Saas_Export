package cn.itheima.utils;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * <h3>export_parent</h3>
 * <p>to convert the argument to required type from jsp/html to Controller</p>
 *
 * @author : Andrew
 * @date : 2020-06-20 15:52
 **/
public class argumentConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            java.util.Date parse = dateFormat.parse(s);
            date = new Date(parse.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

}
