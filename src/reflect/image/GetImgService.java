package reflect.image;

import reflect.image.annotation.GetPic;
import reflect.image.annotation.PicService;

@PicService("com.fly.test")
public interface GetImgService {

	@GetPic(value="renren",type="get")
	public void downloadImage(String url,String localFile);
	
	@GetPic(value="renren",type="get")
	public void downloadImage2(String url,String localFile);

}
