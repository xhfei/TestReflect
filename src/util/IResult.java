package util;

import java.util.Map;

public interface IResult {

    public static final String DEFAULT_RESULT_KEY = "_defaultResult";

    public abstract void setSuccess(boolean isSuccess);

    public abstract boolean isSuccess();

    public abstract void setDefaultResult(Object paramValue);

    public abstract void setResult(String paramKey, Object paramValue);

    public abstract Object getResult(Object paramKey);

    public abstract Map<String, Object> getResults();

    public abstract Object getDefaultResult();

    public abstract boolean containsResult(Object paramKey);
}