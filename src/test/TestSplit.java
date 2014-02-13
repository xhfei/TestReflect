package test;

import org.apache.commons.lang.StringUtils;

public class TestSplit {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="/root/hotCommend";
		String[] tss=str.split("/");
		for(String ts:tss){
			if(StringUtils.isEmpty(ts)){
				continue;
			}
			System.out.println(ts);
		}
	}

}
