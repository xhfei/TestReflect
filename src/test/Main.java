package test;

import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bson.types.ObjectId;

import util.SimpleDesEncrypter;

public class Main {
	public static void main(String... args) throws Exception {
		String password="mobiledevcenter@20131203";
		String key1="uEjvTiLoNgt/01lqwSJjzDo2eIAe0x4k";
		String key2="XiBugLyDp98=";
		SecretKeySpec key=getkey(key2);
		SimpleDesEncrypter encrypter2 = new SimpleDesEncrypter(key);
		String decrypted = encrypter2.decrypt(key1);
		String enc = encrypter2.encrypt(password);
		String pw = encrypter2.decrypt(enc);
		System.out.println("decrypted:"+decrypted);
		System.out.println("enc:"+enc);
		System.out.println("pw:"+pw);
	}
	public static SecretKeySpec getkey(String key2) {
        byte[] dec = null;
        try {
            dec = new Base64().decode(key2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SecretKeySpec(dec, "DES");
    }
	public void out(String str){
		System.out.println(str);
	}
	// public static void main(String... args) {
	// Date GUIDE_TIME=new Date(1365577622000L);
	// SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// System.out.println("111:"+sdf.format(GUIDE_TIME));
	// Calendar calendar=Calendar.getInstance();
	// calendar.set(Calendar.DAY_OF_MONTH,12);
	// calendar.set(Calendar.HOUR_OF_DAY,23);
	// calendar.set(Calendar.MINUTE,59);
	// calendar.set(Calendar.SECOND,59);
	// Date d=calendar.getTime();
	// System.out.println(sdf.format(d));
	// System.out.println(d.getTime());
	// Date popDate=new Date(1358006399296L);
	// System.out.println(sdf.format(popDate));
	// System.out.println(new Date().before(popDate));
	// System.out.println(sdf.format(GUIDE_TIME));
	// Integer integer=Integer.valueOf(1);
	// System.out.println(integer.signum(integer));
	// String key="3G_COMMEND_GAME_392571713_2";
	// System.out.println(key+".length:"+key.getBytes().length);
	// Date date=new Date(1365577621000L);
	// System.out.println(date.toString()+":"+date.toString().getBytes().length);
	// System.out.println(date.before(GUIDE_TIME));
	// ObjectOutputStream oos=null;
	// try {
	// File file=new File("E:\\str.bak");
	// if(!file.exists()){
	// file.createNewFile();
	// }
	// oos=new ObjectOutputStream(new FileOutputStream(file));
	// oos.writeObject(key);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }finally{
	// try {
	// oos.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// System.out.println("=======");
	// }
}
