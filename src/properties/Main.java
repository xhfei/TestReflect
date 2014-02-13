package properties;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long begin=System.currentTimeMillis();
		Object config=null;
		for(int i=0;i<10000;i++){
			config=ConfigManager.getInstance().getConfigItem("image_forum", "null");
		}
		System.out.println("====:"+(System.currentTimeMillis()-begin));
		System.out.println(config.toString());
	}

}
