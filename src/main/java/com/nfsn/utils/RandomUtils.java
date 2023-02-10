package com.nfsn.utils;
 
import java.util.Random;

/**    
 * <b>随机类工具</b>
 *    
 * @author Belen      
 * @version 1.0    
 * @since 2013-5-16  
 */    
public abstract class RandomUtils {
	public static final Integer ALL_NUMBER = 0;
	public static final Integer ALL_LITTLE_LETTER = 1;
	public static final Integer ALL_UPPER_LETTER = 2;
	public static final Integer ALL_LETTER_AND_NUMBER = 3;

	public static final char [] LETTER_AND_NUMBER = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
        'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
		'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	
	public static final char [] UPPER_LETTER = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
        'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

	public static final char [] LITTLE_LETTER = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
        'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	
	public static final char [] NUMBER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

	/**
	 * 获取指定位数的随机数
	 *
	 * @param generatorCode 生成方式，0纯数字，1纯小写字母，2纯大写字母，3混合[默认]
	 * @param t 位数
	 * @return
	 */
	public static String getRandom(Integer generatorCode, int t) {
		String randomNum = "";
		switch (generatorCode) {
			case 0://ALL_NUMBER
				randomNum = getRandomOfNumber(t);
				break;
			case 1://ALL_LITTLE_LETTER
				randomNum = getRandomOfLittleLetter(t);
				break;
			case 2://ALL_UPPER_LETTER
				randomNum = getRandomOfUpperLetter(t);
				break;
			case 3://ALL_LETTER_AND_NUMBER
				randomNum = getRandomOfLetterAndNumber(t);
			default:
				randomNum = getRandomOfLetterAndNumber(t);
				break;
		}
		return randomNum;
	}


	/**
	 * 按指定大小在<b>0-9</b>数字中生成随机数。
	 *
	 * @param t 生成的长度，t不能小于1或大于99，否则返回"0"
	 * @return 你想要的随机数
	 * @created 2013-5-16 下午02:40:05
	 * @author Belen
	 */
	public static String getRandomOfNumber(int t) {
		return get(NUMBER, t);
	}

	/** 
     * 按指定大小在<b>26个小写英文字母</b>中生成随机数。
     *  
     * @param t 
     *         生成的长度，t不能小于1或大于99，否则返回"0"
     * @return 你想要的随机数
     * @created 2013-5-16 下午02:40:05
     * @author Belen
     */  
    public static String getRandomOfUpperLetter(int t) {
       return get(UPPER_LETTER, t);
    }

	/**
     * 按指定大小在<b>26个大写英文字母</b>中生成随机数。
     *
     * @param t 生成的长度，t不能小于1或大于99，否则返回"0"
     * @return 你想要的随机数
     * @created 2013-5-16 下午02:40:05
     * @author Belen
     */
    public static String getRandomOfLittleLetter(int t) {
       return get(LITTLE_LETTER, t);
    }

    /** 
     * 按指定大小在<b>25个英文以及10个数字</b>中生成随机数。
     *  
     * @param t 
     *         生成的长度，t不能小于1或大于99，否则返回"0"
     * @return 你想要的随机数
     * @created 2013-5-16 下午02:40:05
     * @author Belen
     */  
    public static String getRandomOfLetterAndNumber(int t) {
       return get(LETTER_AND_NUMBER, t);
    } 
    
    /** 按指定数组生成数据。*/
	private static String get(char[] c, int t) {
		if(t < 1 || t > 99){
    		return "0";
    	}
		
		final int maxNum = 36;
		int i; // 生成的随机数  
		int count = 0; // 生成的长度  
 
		StringBuffer sb = new StringBuffer("");
		Random r = new Random();
		while (count < t) {
			// 生成随机数，取绝对值，防止生成负数，  
			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1  
			if (i >= 0 && i < c.length) {
				sb.append(c[i]);
				count++;
			}
		}
		return sb.toString();
	}
}