package pattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

public class FilterTest {
	public static void main(String args[]) {
		MutiPatternParser filterEngine = new MutiPatternParser();
		BufferedReader brKeyword = TxtReader
				.keywordReader("file/illegalkeyword.txt");
		BufferedReader brArticle = TxtReader.keywordReader("file/article.txt");
		String keyword = null;
		String article = null;
		long startTime = System.currentTimeMillis();
		StringBuffer buffer = new StringBuffer();
		Vector<Integer> levelSet = new Vector<Integer>();
		try {
			while ((keyword = brKeyword.readLine()) != null) {
				filterEngine.addFilterKeyWord(keyword, 1);
			}
			while ((article = brArticle.readLine()) != null) {
				buffer.append(article);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("读取文件IO异常!!!");
			e.printStackTrace();
		}
		String content = filterEngine.parse(buffer.toString(), levelSet);
		System.out.println("article.txt test:levelSet.size=" + levelSet.size()
				+ "\n this article's content: " + content);

		long endTime = System.currentTimeMillis();

		System.out.println("Cost:" + (endTime - startTime) + "ms");

		/* 清除过滤算法引擎，带来的后果是引擎中将找不到任何的字符，测试代码，用来重载关键字时使用！ */
		filterEngine.clear();
		levelSet.clear();
		filterEngine.parse("我我我 你你你 他他他", levelSet);
		System.out.println("test:levelSet.size=" + levelSet.size());// 测试正确结果是找不到匹配字符
		levelSet.clear();
		filterEngine.parse("a'b'c a b c", levelSet);
		System.out.println("test:tz levelSet.size=" + levelSet.size());// 测试正确结果是找不到匹配字符*/
	}
}
