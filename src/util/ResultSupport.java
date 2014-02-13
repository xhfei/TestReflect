package util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ResultSupport implements IResult {
    private boolean isSuccess = false;
    private Map<String, Object> resultMap = new HashMap<String, Object>(4);

    public void setSuccess(boolean isSuccess) {
    	this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
	return isSuccess;
    }

    public void setResult(String paramKey, Object paramValue) {
		if (resultMap == null) {
		    resultMap = new HashMap<String, Object>();
		}
		if (paramKey == null || paramKey.equals("")) {
		    paramKey = DEFAULT_RESULT_KEY;
		}
		resultMap.put(paramKey, paramValue);
    }

    public void setDefaultResult(Object paramValue) {
    	resultMap.put(DEFAULT_RESULT_KEY, paramValue);
    }

    public Object getResult(Object paramKey) {
    	if (resultMap == null) {
			return null;
		}
		return resultMap.get(paramKey);
    }

    public Map<String, Object> getResults() {
    	return resultMap;
    }

    public Object getDefaultResult() {
    	return resultMap.get(DEFAULT_RESULT_KEY);
    }

    public boolean containsResult(Object paramKey) {
    	if (resultMap == null) {
    		return false;
    	}
    	return resultMap.containsKey(paramKey);
    }

    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}