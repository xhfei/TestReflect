package reflect.image.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface GetPic {

	public static final String METHOD_TYPE_POST = "post";

	public static final String METHOD_TYPE_GET = "get";

	/**
	 * @return 要操作的资源的URI
	 */
	String value();

	String type() default METHOD_TYPE_POST;

}
