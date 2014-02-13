package properties;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

/**
 * <li>;Project: test <li>File Name: ConfigManager.java <li>Title: ConfigManager
 * <li>Description: 从《Java 与 模式》中找到的一个可以管理properties文件的管理器，
 * 可以处理文件在系统运行期间的改动。采用了单例模式 <li>Copyright: Copyright (c) 2000-2005 <li>Company:
 * 
 * @author no Author 访问方式示例： Object
 *         o=ConfigManager.getInstance().getConfigItem(key,null); if(o==null){
 *         throw new Exception("System 获取字符串" + key + "失败！"); }
 * 
 * 
 */
public class ConfigManager {
	private static ConfigManager m_instance;
	private static String PFILE;

	synchronized public static ConfigManager getInstance() {
		if (m_instance == null) {
			if (PFILE == null) {
				URL url = ConfigManager.class
						.getResource("./imageforum.properties");
				PFILE = url.getPath();
				// PFILE = StringUtils.replaceAll(PFILE, "%20", " ");
			}
			m_instance = new ConfigManager();
		}
		return m_instance;
	}

	private File m_file = null;
	private long m_lastModifiedTime = 0;
	private Properties m_props = null;

	private ConfigManager() {
		m_file = new File(PFILE);
		m_lastModifiedTime = m_file.lastModified();

		if (m_lastModifiedTime == 0) {
			System.err.println(PFILE + " file does not exist!");
		}

		m_props = new Properties();

		try {
			m_props.load(new FileInputStream(PFILE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	final public Object getConfigItem(String name, Object defaultVal) {
		long newTime = m_file.lastModified();
		if (newTime == 0) {
			if (m_lastModifiedTime == 0) {
				System.err.println(PFILE + " file does not exist!");
			} else {
				System.err.println(PFILE + " file was deleted!!");
			}
			return defaultVal;
		} else if (newTime > m_lastModifiedTime) {
			m_props.clear();
			try {
				m_props.load(new FileInputStream(PFILE));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		m_lastModifiedTime = newTime;

		Object val = m_props.getProperty(name);
		if (val == null) {
			return defaultVal;
		} else {
			return val;
		}
	}

}
