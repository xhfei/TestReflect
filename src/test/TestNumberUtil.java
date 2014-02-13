package test;



/**
 * 主要是对数字的一些操作，优化
 * 
 * @author <a href="mailto:yijiu.chen@renren-inc.com">陈一九</a>
 * @version 2012-1-30下午03:43:35
 */
public class TestNumberUtil {

    /**
     * 将两个int数字合并成一个long
     * 
     * @param high 高位
     * @param low 低位
     * @return
     */
    public static long combineInt2Long(int high, int low) {
        return (((long) high << 32) & 0xFFFFFFFF00000000L) | ((long) low & 0xFFFFFFFFL);
    }

    /**
     * 根据合并的long提取对应的高位或地位整数
     * 
     * @param val
     * @param isLow
     * @return
     */
    public static int separateLong2int(long val, boolean isLow) {
        if (isLow) {
            return (int) (val & 0xFFFFFFFFL);
        } else {
            return (int) ((val & 0xFFFFFFFF00000000L) >> 32);
        }
    }
    
    public static void main(String args[]){
    	System.out.println(TestNumberUtil.combineInt2Long(222188852,0));
    	System.out.println(TestNumberUtil.combineInt2Long(222188852,Integer.MAX_VALUE));
    	System.out.println(TestNumberUtil.separateLong2int(989436009731063922L,false));
    	System.out.println(TestNumberUtil.separateLong2int(989436009731063922L,true));
    	System.out.println(TestNumberUtil.combineInt2Long(222188852,2081143));
    }
}