package reflect.test;

import reflect.test.annotation.PicService;
import reflect.test.annotation.GetPic;

@PicService("white dog")
public class Dog implements IAnimal{

	@Override
	@GetPic(value="loudy",type="post")
	public void info() {
		System.out.println("I am a dog!");
	}

}
