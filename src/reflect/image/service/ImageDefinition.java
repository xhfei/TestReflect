package reflect.image.service;

public class ImageDefinition {
	private String url;
	private String localFile;
	private String methodType;
	public ImageDefinition(){
		
	}
	public ImageDefinition(String url,String localFile,String methodType){
		this.url=url;
		this.localFile=localFile;
		this.methodType=methodType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLocalFile() {
		return localFile;
	}
	public void setLocalFile(String localFile) {
		this.localFile = localFile;
	}
	public String getMethodType() {
		return methodType;
	}
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}
	
}
