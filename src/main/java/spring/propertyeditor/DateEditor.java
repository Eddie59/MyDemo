package spring.propertyeditor;

import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * DateEditor class
 *
 * @author Administrator
 * @date
 */
public class DateEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isNoneEmpty(text)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = format.parse(text);
                //给bean  set值
                super.setValue(date);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
