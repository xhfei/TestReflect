package test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonTest {
	public static void main(String...args){
		String jsonStr="{'uploadid':'1542','code':0,'msg':'','files':[{'code':0,'msg':'','filename':'IMG_1481.JPG','filesize':316178,'width':1200,'height':1600,'exifs':[{'BrightnessValue':'0/1024'},{'ColorSpace':'1'},{'ComponentsConfiguration':'1 2 3 0'},{'Contrast':'0'},{'CustomRendered':'1'},{'DateTimeDigitized':'2012:10:05 05:42:11'},{'DateTimeOriginal':'2012:10:05 05:42:11'},{'DigitalZoomRatio':'0/0'},{'ExifImageLength':'1600'},{'ExifImageWidth':'1200'},{'ExifVersion':'48 50 50 48'},{'ExposureMode':'0'},{'ExposureProgram':'2'},{'FileSource':'3'},{'FlashpixVersion':'48 49 48 48'},{'InteroperabilityIndex':'R98'},{'InteroperabilityVersion':'48 49 48 48'},{'Make':'HUAWEI TECHNOLOGY'},{'MeteringMode':'2'},{'Model':'U1270'},{'Orientation':'1'},{'PrintImageMatching':'80 114 105 110 116 73 77 0 48 51 48 48 0 0 0 0'},{'ResolutionUnit':'2'},{'Saturation':'0'},{'SceneType':'1'},{'Sharpness':'0'},{'Software':'APP-U1270V100R001CHNC17B724SP01'},{'WhiteBalance':'0'},{'XResolution':'300/1'},{'YCbCrPositioning':'1'},{'YResolution':'300/1'}],'images':[{'url':'fmn064/20121217/1545/large_LNrX_3ea20000004c7a01.jpg','type':'large','width':540,'height':720},{'url':'fmn064/20121217/1545/main_LNrX_3ea20000004c7a01.jpg','type':'main','width':200,'height':266},{'url':'fmn064/20121217/1545/tiny_LNrX_3ea20000004c7a01.jpg','type':'tiny','width':50,'height':50},{'url':'fmn064/20121217/1545/head_LNrX_3ea20000004c7a01.jpg','type':'head','width':100,'height':133},{'url':'fmn064/20121217/1545/xlarge_LNrX_3ea20000004c7a01.jpg','type':'xlarge','width':1024,'height':1365}]}]}";
		JSONObject json=JSONObject.fromObject(jsonStr);
		System.out.println(json.get("code"));
		JSONArray fileJson=(JSONArray)json.get("files");
		JSONObject imgarr=JSONObject.fromObject(fileJson.get(0));
		JSONArray images=(JSONArray)imgarr.get("images");
		JSONObject imgJson=JSONObject.fromObject(images.get(0));
		System.out.println(imgJson.get("url"));
	}
}
