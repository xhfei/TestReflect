package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class TestComparator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list=new ArrayList<Integer>();
		list.add(0);
		list.add(9);
		list.add(5);
		list.add(7);
		Collections.sort(list, new Comparator<Integer>(){
			@Override
			public int compare(Integer i1, Integer i2){
				int result = i1.compareTo(i2); 
				return result;
			}
		});
		for(Integer i:list){
			System.err.println(i);
		}
	}

}
