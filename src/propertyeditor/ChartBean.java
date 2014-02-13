package propertyeditor;

import javax.swing.JPanel;

public class ChartBean extends JPanel{
	private int titlePosition = 1;  
	   private boolean inverse;  
	   //省略get/setter方法  
	public int getTitlePosition() {
		return titlePosition;
	}
	public void setTitlePosition(int titlePosition) {
		this.titlePosition = titlePosition;
	}
	public boolean isInverse() {
		return inverse;
	}
	public void setInverse(boolean inverse) {
		this.inverse = inverse;
	}
	   
}
