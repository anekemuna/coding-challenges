class TimeMap {
    private Map<String, TreeMap<Integer, String>> map;

    public TimeMap() {
        this.map = new HashMap <> ();
    }
    
    public void set(String key, String value, int timestamp) {
        TreeMap <Integer, String> valuePair;

        if (!map.containsKey(key)) {
            valuePair = new TreeMap <> ();
        } else {
            valuePair = map.get(key); 
        }

        valuePair.put(timestamp, value);
        map.put(key, valuePair);
    }
    
    public String get(String key, int timestamp) {
        if(!map.containsKey(key)) return "";

        TreeMap<Integer, String> timePair = map.get(key);

        Map.Entry<Integer, String> entry = timePair.floorEntry(timestamp);
        
        if (entry == null) {
            return "";
        }

        return entry.getValue();
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */