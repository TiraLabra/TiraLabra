package Collections;

/**
 * A dictionary containing KeyValuePairs. Provides average O(1) add and remove
 * methods.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 */
public final class Dictionary<K, V> {

    private final HashSet<KeyValuePair<K, V>> set;

    /**
     * Create a new empty dictionary.
     */
    public Dictionary() {
        this.set = new HashSet<>(10);
    }

    /**
     * Add a new KeyValuePair to the dictionary.
     *
     * @param key KeyValuePair Key.
     * @param value KeyValuePair Value.
     */
    public void add(final K key, final V value) {
        if (key == null) {
            throw new NullPointerException("Dictionary key cannot be null");
        }
        set.add(new KeyValuePair<>(key, value));
    }

    /**
     * Does a key exist in the dictionary?
     *
     * @param key The key to check.
     * @return Does the key exist in the dictionary.
     */
    public boolean containsKey(final K key) {
        final KeyValuePair<K, V> kvp = new KeyValuePair<>(key, null);
        return set.contains(kvp);
    }

    /**
     * Get the value attached to the key, or null if key doesn't exist in the
     * dictionary.
     *
     * @param key The key.
     * @return The value attached to the key.
     */
    public V get(final K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        final KeyValuePair<K, V> kvp = new KeyValuePair<>(key, null);
        final KeyValuePair<K, V> found = set.get(kvp);
        if (found == null) {
            return null;
        }
        return found.getValue();
    }

    /**
     * Size of the dictionary.
     *
     * @return Size of the dictionary.
     */
    public int size() {
        return set.size();
    }

    public LinkedList<KeyValuePair<K, V>> pairs() {
        return set.values();
    }

    public static final class KeyValuePair<K, V> {

        private final K key;
        private final V value;

        private KeyValuePair(final K key, final V value) {
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj == null || !obj.getClass().equals(KeyValuePair.class)) {
                return false;
            }
            KeyValuePair kvp = (KeyValuePair) obj;
            return key.equals(kvp.key);
        }
    }
}
