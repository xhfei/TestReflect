package monitor;

public class PerformanceMonitor {
	 //①通过一个ThreadLocal保存调用线程相关的性能监视信息  
    private static ThreadLocal<MethodPerformace> performanceRecord =          
                                new ThreadLocal<MethodPerformace>();  
      
    //②启动对某一目标方法的性能监视  
     public static void begin(String method) {  
        System.out.println("begin monitor...");  
        MethodPerformace mp = new MethodPerformace(method);  
        performanceRecord.set(mp);  
    }  
    public static void end() {  
        System.out.println("end monitor...");  
        MethodPerformace mp = performanceRecord.get();  
  
         //③打印出方法性能监视的结果信息。  
        mp.printPerformance();  
    }  
}
