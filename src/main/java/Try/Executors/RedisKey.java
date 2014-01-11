package Try.Executors;

public enum RedisKey {
    DOCID_ACTIONINDEX("docId:%s:actionIdx:index"),
    DOCID_ACTIONIDX("docId:%s:actionIdx:%s"),
    DOCID_ALL_KEYS("docId:%s:*"),

    DOCID_ACTIONLIST("docId:%s:actionList");

    private String key;

    RedisKey(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    public String formated(String... value) {
        return String.format(key, value);
    }
}
