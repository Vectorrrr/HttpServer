package model;

/**
 * @author Gladush Ivan
 * @since 29.03.16.
 */
public class Bean {
    private String url;
    private String classPath;

    public Bean(String url, String classPath) {
        this.url = url;
        this.classPath = classPath;
    }

    public String getUrl() {
        return url;
    }

    public String getClassPath() {
        return classPath;
    }
}
