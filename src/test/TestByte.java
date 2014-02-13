package test;

import java.math.BigInteger;
import java.nio.ByteBuffer;

import org.bson.types.ObjectId;


public class TestByte {
	public static void main(String... args) {
		byte tb=-8;
		int ti=tb&255;
		System.out.println(ti);
		BigInteger bigBari= new BigInteger("11111000", 2);
		System.out.println(bigBari);
		BigInteger bigInteger= new BigInteger("529ede72", 16);
		System.out.println(bigInteger);
		System.out.println("--"+Integer.toBinaryString(-97));
		System.out.println("--"+Integer.toBinaryString(159));
		ObjectId oid=new ObjectId();
		int time=oid.getTimeSecond();
		int machine=oid.getMachine();
		int inc=oid.getInc();
		System.out.println("time:"+time);
		System.out.println("machine:"+machine);
		System.out.println("inc:"+inc);
		byte b[] = new byte[12];
        ByteBuffer bb = ByteBuffer.wrap(b);
        bb.putInt(time);
        bb.putInt(machine);
        bb.putInt(inc);
        System.out.println(bb);
        String mongoStr=toStringMongod(b);
        System.out.println("mongoStr:"+mongoStr);
		/*
		String od="529eee6219e08797be2af99f";
		System.out.println(od.getBytes().length);
		byte f=(byte)10000;
		System.out.println(f);
		Integer a=127;
		System.out.println(System.currentTimeMillis());
		System.out.println(a);
		System.out.println(2*2*2*2*2*2*2);
		System.out.println(Integer.toBinaryString(15));
		System.out.println(Integer.toBinaryString(127));
		System.out.println(Integer.toHexString(127));
		System.out.println(Integer.toOctalString(127));
		String str="529d87655006f267b8cd06ed";
		System.out.println("======="+str.getBytes().length);
		for(int i=0;i<10;i++){
			ObjectId oid=new ObjectId();
			System.out.print(oid.getCurrentInc());
			System.out.print("--");
			System.out.println(oid.toStringMongod());
		}*/
	}
	public static String toStringMongod(byte b[])
    {
        StringBuilder buf = new StringBuilder(24);
        for(int i = 0; i < b.length; i++)
        {
            int x = b[i] & 255;
            System.out.println("b["+i+"]:"+b[i]);
            System.out.println("x:"+x);
            String s = Integer.toHexString(x);
            System.out.println("s:"+s);
            if(s.length() == 1)
                buf.append("0");
            buf.append(s);
            System.out.println("----------");
        }

        return buf.toString();
    }
}
