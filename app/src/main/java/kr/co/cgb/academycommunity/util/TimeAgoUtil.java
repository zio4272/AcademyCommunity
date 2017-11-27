package kr.co.cgb.academycommunity.util;

import java.util.Locale;

/**
 * Created by user on 2017-08-08.
 */

public class TimeAgoUtil {

    public static String getTimeAgoString(int minuteAgo) {

        String minuteStr = "";
        
        if (minuteAgo <= 2) {
            minuteStr = "방금 전";
        }
        else if (minuteAgo <= 40) {
            // 2보다는 크고, 40보다는 작은 상황임.

            minuteStr = String.format(Locale.KOREA, "%d분 전", minuteAgo);

        }
        else if (minuteAgo <= 90) {
            minuteStr = "한시간 전";
        }
        else if (minuteAgo <= 510) {
            int hour = (minuteAgo + 29) / 60;
            minuteStr = String.format(Locale.KOREA, "%d시간 전", hour);

        }
        else  {
            minuteStr = "오래 전";
        }

        return minuteStr;
    }

}
