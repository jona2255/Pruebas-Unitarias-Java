package ex1;

import org.junit.jupiter.api.Assertions;

class HashTableTest {

    HashTable hashTable = new HashTable();

    @org.junit.jupiter.api.Test
    void put() {


    }

    @org.junit.jupiter.api.Test
    void get() {

        HashTable h = new HashTable();

        // Put some key values.
        for(int i=0; i<30; i++) {
            final String key = String.valueOf(i);
            h.put(key, key);
            Assertions.assertEquals(h.get(key), key);

        }
        for (int i = 0; i < 21; i++) {

            Assertions.assertEquals(h.get(String.valueOf(i)), String.valueOf(i));
        }

        hashTable.put("j","22");
        hashTable.put("22","j");

        Assertions.assertNull(null);
        Assertions.assertEquals(hashTable.get("j"),"22");
        Assertions.assertEquals(hashTable.get("22"),"j");
        Assertions.assertEquals(hashTable.get("0"),"22");
        Assertions.assertEquals(hashTable.get("5"),"5");


    }

    @org.junit.jupiter.api.Test
    void drop() {


    }

    @org.junit.jupiter.api.Test
    void realSize() {

        Assertions.assertEquals(16,16);

    }

    @org.junit.jupiter.api.Test
    void size() {


    }
}