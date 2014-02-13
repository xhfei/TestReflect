package testfuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MyTest implements Callable<Integer>{
	private static ExecutorService executor = Executors.newCachedThreadPool();
	private Integer max; 
	public MyTest(Integer max){
		this.max=max;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args){
		System.out.println("aaaaaaaaaaa");
		MyTest test=new MyTest(5);
		System.out.println("bbbbbbbbb");
		Future<Integer> future=executor.submit(test);
		System.out.println("ccccccccc");
		try {
			future.get(2,TimeUnit.SECONDS);
			System.out.println("dddddddd");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0;i<5;i++){
			System.out.println("==========="+i);
		}
		Future<?> runnable1 = executor.submit(new Runnable() {  
             @Override  
             public void run() {  
                 System.out.println("runnable1 running.");  
             }  
        });  
		System.out.println("dddddddd");
		try{
			 System.out.println("Runnable1:" + runnable1.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Integer call() throws Exception {
		int total=0;
		for(int i=0;i<max;i++){
			total+=i;
			Thread.sleep(1000);
		}
		System.out.println("+++++++++++");
		return total;
	}

}
