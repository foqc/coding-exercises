package map;

public class MyMap<K, V> {
    private Entry<K, V>[] buckets;
    private static final int INITIAL_CAPACITY = 1 << 4; // 16

    public MyMap() {
        this(INITIAL_CAPACITY);
    }

    public MyMap(int capacity) {
        this.buckets = new Entry[capacity];
    }

    private int getBucketSize() {
        return buckets.length;
    }

    private int getHash(K key) {
        return key == null ? 0 : Math.abs(key.hashCode());
    }

    public void put(K key, V value) {
        Entry<K, V> entry = new Entry<>(key, value, null);
        int bucket = getHash(key) % getBucketSize();
        Entry<K, V> existing = buckets[bucket];

        if (existing == null) {
            buckets[bucket] = entry;
        } else {
            while (existing.getNext() != null) {
                if (existing.getKey().equals(key)) {
                    existing.setValue(value);
                    return;
                } else existing = existing.getNext();
            }
            if (existing.getKey().equals(key)) existing.setValue(value);
            existing.setNext(entry);
        }
    }

    public V get(K key) {
        int bucket = getHash(key) % getBucketSize();
        Entry<K, V> existing = buckets[bucket];

        while (existing != null) {
            if (existing.getKey().equals(key)) {
                return existing.getValue();
            }
            existing = existing.getNext();
        }
        return null;
    }
}
