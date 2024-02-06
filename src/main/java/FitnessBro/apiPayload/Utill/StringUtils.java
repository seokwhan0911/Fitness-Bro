package FitnessBro.apiPayload.Utill;

/*
    StringUtils :   빈문자열이거나 기타 white space가 담긴 문자열에 대한 처리하는 메서드를 포함한 클래스
    isBlank     :   빈문자열이면 True
 */
public class StringUtils {
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }
}
