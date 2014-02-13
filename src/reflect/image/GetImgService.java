package reflect.image;

import reflect.image.annotation.GetPic;
import reflect.image.annotation.PicService;

@PicService("com.hongfei.xu")
public interface GetImgService {

	@GetPic(value="renren",type="get")
	public void downloadImage(String url,String localFile);

}
