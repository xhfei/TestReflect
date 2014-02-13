package pattern;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

public class TxtReader implements Serializable {

	public TxtReader() {
		super();
	}

	public static BufferedReader keywordReader(String fileName) {
		File file = new File(fileName);
		BufferedReader br = null;
		try {
			FileInputStream in = new FileInputStream(file);
			InputStreamReader inReader = new InputStreamReader(in, "UTF-8");

			br = new BufferedReader(inReader);

		} catch (FileNotFoundException e) {
			System.out.println("你想加载的文件没有找到！！！");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("你指定的编码类型不支持哦！！！");
			e.printStackTrace();
		}
		return br;

	}
}
