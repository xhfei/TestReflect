package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SubList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list=new ArrayList<Integer>(11);
		for(int i=1;i<=11;i++){
			list.add(i);
		}
		int i=0;
		for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();) {
			Integer it=iterator.next();
			if(i%2==0){
				iterator.remove();
			}
		}
		System.out.println(list.size());
	}

}
