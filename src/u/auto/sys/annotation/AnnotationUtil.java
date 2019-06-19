package u.auto.sys.annotation;

import org.apache.commons.lang3.StringUtils;

import u.auto.util.CharUtil;

public class AnnotationUtil {

	public static String change(String str, String... msgs) {
		if (StringUtils.isEmpty(str) || msgs == null) {
			return str;
		}
		int s = -1, e = -1;
		for (int i = 0; i < msgs.length; i++) {
			String msg = msgs[i];
			if ((s = str.indexOf("{")) != -1 && (e = str.indexOf("}")) != -1) {
				str = str.replace(str.substring(s, e + 1), CharUtil.s + msg + CharUtil.s);
			}
		}
		return str;
	}

	public static String changeInt(String str, String msg, int... is) {
		if (StringUtils.isEmpty(str) || msg == null) {
			return str;
		}
		int s = -1, e = -1;
		// 替换数字
		for (int i = 0; i < is.length; i++) {
			int ii = is[i];
			if ((s = str.indexOf("{")) != -1 && (e = str.indexOf("}")) != -1) {
				str = str.replace(str.substring(s, e + 1), "" + ii);
			}
		}
		// 替换msg
		if ((s = str.indexOf("{")) != -1 && (e = str.indexOf("}")) != -1) {
			str = str.replace(str.substring(s, e + 1), CharUtil.s + msg + CharUtil.s);
		}
		return str;
	}

	public static String changeIntNumber(String str, String msg, int... is) {
		if (StringUtils.isEmpty(str) || msg == null) {
			return str;
		}
		int s = -1, e = -1;
		// 替换数字
		for (int i = 0; i < is.length; i++) {
			int ii = is[i];
			if ((s = str.indexOf("{")) != -1 && (e = str.indexOf("}")) != -1) {
				str = str.replace(str.substring(s, e + 1), getNumberByLength(ii));
			}
		}
		// 替换msg
		if ((s = str.indexOf("{")) != -1 && (e = str.indexOf("}")) != -1) {
			str = str.replace(str.substring(s, e + 1), CharUtil.s + msg + CharUtil.s);
		}
		return str;
	}

	public static String changeIntNumberMax(String str, String msg, int... is) {
		if (StringUtils.isEmpty(str) || msg == null) {
			return str;
		}
		int s = -1, e = -1;
		// 替换数字
		for (int i = 0; i < is.length; i++) {
			int ii = is[i];
			if ((s = str.indexOf("{")) != -1 && (e = str.indexOf("}")) != -1) {
				str = str.replace(str.substring(s, e + 1), getNumberByLength(ii));
			}
		}
		// 替换msg
		if ((s = str.indexOf("{")) != -1 && (e = str.indexOf("}")) != -1) {
			str = str.replace(str.substring(s, e + 1), CharUtil.s + msg + CharUtil.s);
		}
		return str;
	}

	public static String changeIntNumberMin(String str, String msg, int... is) {
		if (StringUtils.isEmpty(str) || msg == null) {
			return str;
		}
		int s = -1, e = -1;
		// 替换数字
		for (int i = 0; i < is.length; i++) {
			int ii = is[i];
			if ((s = str.indexOf("{")) != -1 && (e = str.indexOf("}")) != -1) {
				str = str.replace(str.substring(s, e + 1), getNumberByLength(ii));
			}
		}
		// 替换msg
		if ((s = str.indexOf("{")) != -1 && (e = str.indexOf("}")) != -1) {
			str = str.replace(str.substring(s, e + 1), CharUtil.s + msg + CharUtil.s);
		}
		return str;
	}

	/**
	 * 获得i个9
	 * **/
	public static String getNumberByLength(int i) {
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < i; j++) {
			sb.append("9");
		}
		if (i <= 0) {
			sb.append("0");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
//		System.out.println(change(Booelan.TRUE.toString(), "121"));
		System.out.println(Integer.MAX_VALUE);
		System.out.println((Integer.MAX_VALUE + "").length());
		System.out.println(Length.LENGTH.ch(0, 100, "123"));
//		System.out.println(AnnotationUtil.changeInt("@Length(min = {min}, max = {max}, message = {msg})", 0, 100, "123"));
	}

}
