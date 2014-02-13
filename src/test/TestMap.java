package test;

import java.util.HashMap;
import java.util.Map;

public class TestMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int appId=1;
		Map<Integer,Integer> map=new HashMap<Integer, Integer>();
		map.put(Integer.valueOf(1), Integer.valueOf(1));
		System.out.println(map.containsKey(appId));
	}

}
