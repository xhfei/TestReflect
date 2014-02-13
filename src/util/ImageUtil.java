package util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class ImageUtil {
	private static final String img_prefix = "<img src=\"";
	private static final String fullImg = "<img src=\"http://[^\"]*(jpg|jpeg|png|gif|bmp)";
//	private static final String regImg = "http://[^\"]*(jpg|jpeg|png|gif|bmp)";
	private static final String regEmotions = "http://s.xnimg.cn";
	private static final String suffixImg = "[\\w]+.(jpg|jpeg|png|gif|bmp)";
	/**
	 * 判断一段内容里是否包含图片
	 * @param replyContent
	 * @return
	 */
	public static boolean isHaveImage(String replyContent) {
		if (StringUtils.isBlank(replyContent)) {
			return false;
		}
		Pattern p = Pattern.compile(fullImg,Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(replyContent);
		while (m.find()) {
			return true;
		}
		return false;
	}
	/**
	 * 从内容里获取图片url
	 * @param replyContent
	 * @return
	 */
	/**
	 * 从内容里获取图片url
	 * 
	 * @param replyContent
	 * @return
	 */
	public static List<String> getImageFromContent(String content) {
		List<String> res = new ArrayList<String>();
		if (StringUtils.isBlank(content)) {
			return res;
		}
		Pattern fullImgPattern = Pattern.compile(fullImg, Pattern.CASE_INSENSITIVE);
		Pattern emoPattern = Pattern.compile(regEmotions, Pattern.CASE_INSENSITIVE);
		Matcher m = fullImgPattern.matcher(content);
		while (m.find()) {
			String imageUrl = m.group().replace(img_prefix, "");
			Matcher me = emoPattern.matcher(imageUrl);
			if (!me.find()) {
				res.add(imageUrl);
			}
		}
		return res;
	}
	public static String getFileNameFromUrl(String url){
		Pattern urlPattern = Pattern.compile(suffixImg,Pattern.CASE_INSENSITIVE);
		Matcher m = urlPattern.matcher(url);
		String res=null;
		while (m.find()) {
			res=m.group();
		}
		return res;
	}
	public static void main(String...args){
		
		String content=" <p>        <img src=\"http://img2081.poco.cn/mypoco/myphoto/20130106/00/173223716201301060118573626179292545_005.jpg\" border=\"0\"></p><p>        <br>        <br>        <img src=\"http://img2081.poco.cn/mypoco/myphoto/20130106/00/173223716201301060118573626179292545_004.jpg\" border=\"0\" width=\"475\" height=\"600\"> <br>        <br>        <img src=\"http://img2081.poco.cn/mypoco/myphoto/20130106/00/173223716201301060118573626179292545_006.jpg\" border=\"0\" width=\"368\" height=\"600\"> <br>        <br>        <br>◎译　　名　飓风营救2(未分级加长版)/救参96小时2(港)/即刻救援2(台) <br>◎片　　名　Taken&nbsp;2 <br>◎年　　代　2012 <br>◎国　　家　美国 <br>◎类　　别　动作/犯罪/剧情 <br>◎语　　言　英语 <br>◎字　　幕　中文 <br>◎IMDB评分&nbsp;&nbsp;6.4/10&nbsp;from&nbsp;59,717&nbsp;users <br>◎IMDB链接&nbsp;&nbsp;http://www.imdb.com/title/tt1397280/ <br>◎文件格式　高清蓝光720P版BD-RMVB <br>◎视频尺寸　1280&nbsp;x&nbsp;720 <br>◎文件大小　1CD&nbsp;0.98GB <br>◎片　　长　1h&nbsp;38m&nbsp;18s <br>◎导　　演　奥利维尔&middot;米加顿&nbsp;Olivier&nbsp;Megaton <br>◎主　　演　连姆&middot;尼森&nbsp;Liam&nbsp;Neeson&nbsp;....Bryan&nbsp;Mills <br>　　　　　　玛姬&middot;格蕾斯&nbsp;Maggie&nbsp;Grace&nbsp;....Kim <br>　　　　　　法米克&middot;詹森&nbsp;Famke&nbsp;Janssen&nbsp;....Lenore <br>　　　　　　拉德&middot;舍博德兹加&nbsp;Rade&nbsp;Serbedzija&nbsp;....Murad <br>　　　　　　卢克&middot;葛莱姆斯&nbsp;Luke&nbsp;Grimes <br>　　　　　　勒兰德&middot;奥瑟&nbsp;Leland&nbsp;Orser&nbsp;....Sam <br>　　　　　　强&middot;格瑞斯&nbsp;Jon&nbsp;Gries&nbsp;....Casey&nbsp;(uncredited) <br>　　　　　　Luenell&nbsp;Campbell&nbsp;....Bertha <br>　　　　　　Laura&nbsp;Beth&nbsp;McIlroy&nbsp;....Sunset&nbsp;Surfer <br>　　　　　　Kevork&nbsp;Malikyan&nbsp;....Durmaz <br>　　　　　　Aclan&nbsp;Bates&nbsp;....Sheik's&nbsp;Aide <br>　　　　　　Ryan&nbsp;Butcher&nbsp;....Shooter&nbsp;(uncredited) <br>　　　　　　D&middot;B&middot;斯威尼&nbsp;D.B.&nbsp;Sweeney&nbsp;....Bernie <br>　　　　　　Alain&nbsp;Figlarz&nbsp;....Suko <br>　　　　　　Frank&nbsp;Alvarez&nbsp;....Car&nbsp;Wash&nbsp;Attendant <br>　　　　　　Ali&nbsp;Yildirim&nbsp;....Imam <br>　　　　　　Ergun&nbsp;Kuyucu&nbsp;....Mirko <br>　　　　　　Cengiz&nbsp;Bozkurt&nbsp;....Border&nbsp;Guard&nbsp;#1 <br>　　　　　　Hakan&nbsp;Karahan&nbsp;....Reception&nbsp;Clerk <br>　　　　　　Naci&nbsp;Adig&uuml;zel&nbsp;....Cheikh <br>　　　　　　Mehmet&nbsp;Polat&nbsp;....Hotel&nbsp;Driver <br>　　　　　　Luran&nbsp;Ahmeti&nbsp;....Hotel&nbsp;Thug&nbsp;#3 <br>　　　　　　Erkan&nbsp;&amp;Uuml;&amp;ccedil;&uuml;nc&uuml;&nbsp;....Barber <br>　　　　　　Ugur&nbsp;Ugural&nbsp;....Man&nbsp;in&nbsp;Street <br>　　　　　　Alex&nbsp;Dawe&nbsp;....Johnson <br>　　　　　　Olivier&nbsp;Rabourdin&nbsp;....Jean-Claude <br>　　　　　　Micha&amp;euml;l&nbsp;Vander-Meiren&nbsp;....Jean-Claude's&nbsp;Driver <br>　　　　　　Rochelle&nbsp;Gregorie&nbsp;....Jean-Claude's&nbsp;Concierge <br>　　　　　　Emre&nbsp;Melemez&nbsp;....Hammam&nbsp;Attendant <br>　　　　　　Ilkay&nbsp;Akdagli&nbsp;....Albanian&nbsp;Intelligence&nbsp;Officer <br>　　　　　　Mylene&nbsp;Pilutik&nbsp;....Waitress <br>　　　　　　Nathan&nbsp;Rippy&nbsp;....Newsreader <br>　　　　　　Bekir&nbsp;Aslantas&nbsp;....Coffin&nbsp;Puller <br>　　　　　　Tamer&nbsp;Avkapan&nbsp;....Hammam&nbsp;Cop <br>　　　　　　Baris&nbsp;Aydin&nbsp;....Pool&nbsp;Man&nbsp;Newspaper <br>　　　　　　Adil&nbsp;Sak&nbsp;....Coffin&nbsp;Puller <br>　　　　　　Murat&nbsp;Tuncelli&nbsp;....Custom&nbsp;Officer&nbsp;Albania <br>　　　　　　Saruhan&nbsp;Sari&nbsp;....Waiter <br>        <br>        <br>        <br>        <br>◎简　　介　 <br>        <br>　　在上一集中，前中情局探员布莱恩为了拯救被绑架的女儿小金，惹上了冷血无情的阿尔巴尼亚黑帮分子，而他们誓言复仇，趁布莱恩全家至伊斯坦布尔度假时，设下了天罗地网，准备捉拿活口以彻底复仇，而第一个受害者，就是布莱恩的妻子，并以她的生命作要挟，逼他就范；不过，他们却错估了布莱恩的能力，身怀绝技的布莱恩在这个危急的当下，迅速地展开了一连串惊险刺激的救援行动，这一次，因为小金的介入，反而使得整个情况更加复杂、状况不断，面对更多的敌人，更强大的火力，布莱恩是否能再度施展浑身绝技，让家人脱困呢？一场枪林弹雨的生死决战即将展开！ <br>        <br>        <br>        <br>        <br>        <br>◎影片截图 <br>        <br>        <img src=\"http://img2081.poco.cn/mypoco/myphoto/20130106/00/173223716201301060118573626179292545_003.jpg\" border=\"0\" width=\"575\" height=\"323\"> <br>        <br>        <img src=\"http://img2081.poco.cn/mypoco/myphoto/20130106/00/173223716201301060118573626179292545_002.jpg\" border=\"0\" width=\"575\" height=\"323\"> <br>        <br>        <img src=\"http://img2081.poco.cn/mypoco/myphoto/20130106/00/173223716201301060118573626179292545_001.jpg\" border=\"0\" width=\"575\" height=\"323\"> <br>        <br>        <img src=\"http://img2081.poco.cn/mypoco/myphoto/20130106/00/173223716201301060118573626179292545_000.jpg\" border=\"0\" width=\"575\" height=\"323\"> <br>        <br>        <br>        <br>        <img src=\"http://img04.taobaocdn.com/imgextra/i4/229823360/T2NldIXghXXXXXXXXX_!!229823360.jpg\" border=\"0\" width=\"200\" height=\"75\">&nbsp;</p><p>        <span style=\"background-color: rgb(255,0,0);\">第一个是小组官方论坛地址</span><strong>注册</strong>就可以<strong>免费</strong>下载，每天更新N多最新大片，无毒无害无副作用，其他几个是网盘地址，<a href=\"/xiaozu/201817/357615346\" target=\"_blank\"><img class=\"mceItem emotion\" src=\"http://s.xnimg.cn//imgpro/icons/statusface/zy.gif\" border=\"0\" alt=\"最右\"> 下载教程点击查看<img class=\"mceItem emotion\" src=\"http://s.xnimg.cn//imgpro/icons/statusface/shangbuqi.gif\" border=\"0\" alt=\"伤不起\"></a></p><p>        <a href=\"http://www.52nanxiao.com/bbs/thread-28815-1-1.html\">http://www.52nanxiao.com/bbs/thread-28815-1-1.html</a></p><p>        <a href=\"http://yfdisk.com/file/sjnsjf/0dd0bf98/\">http://yfdisk.com/file/sjnsjf/0dd0bf98/</a></p><p>        <a href=\"http://ref.so/my/sjnsjf/3/http://sjnsjf.qjwm.com/down_6467809.html\">http://ref.so/my/sjnsjf/3/http://sjnsjf.qjwm.com/down_6467809.html</a></p>";
//		String content="http://s.xnimg.cn//imgpro/emotions/jiongjiong/1.gif<img src=\"http://FMN.rrimg.com/fmn056/20121128/0805/b_large_iXzg_690b000001241262.JPEG\" border=\"0\"></p><p></p><p><img src=\"http://fmn.rrfmn.com/fmn058/20121128/0805/b_large_Jyna_0d66000002e61263.jpg\" border=\"0\"></p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2选1&nbsp; （说实在的你看不大出来。。。）</p><p>        <img src=\"http://fmn.rrimg.com/fmn061/20121128/0805/b_large_XbvK_39f8000011f91261.jpg\" border=\"0\"></p>";
//		long begin=System.currentTimeMillis();
		List<String> list=getImageFromContent(content);
//		System.out.println("--------"+(System.currentTimeMillis()-begin));
		for(String url:getImageFromContent(content)){
			System.out.println(url);
		}
//		boolean isHaveImage=isHaveImage(content);
//		System.out.println(isHaveImage);
		System.out.println(getFileNameFromUrl(content));
		
	}
	
}
