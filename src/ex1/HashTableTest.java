package ex1;

import org.junit.jupiter.api.Assertions;

class HashTableTest {

    HashTable hashTable = new HashTable();

    @org.junit.jupiter.api.Test
    void put() {


    }

    @org.junit.jupiter.api.Test
    void get() {

        // Probamos si hace bien el get del hashtable si le ponemos keys numéricas del 0 al 29
        HashTable h = new HashTable();
        for(int i=0; i<30; i++) {
            final String key = String.valueOf(i);
            h.put(key, key);
            Assertions.assertEquals(h.get(key), key);
        }


        // En otro hashtable vacio introducimos letras y numeros como keys y valores. Luego probamos a hacer el get de esos valores
        hashTable.put("j","22");
        hashTable.put("22","j");

        Assertions.assertEquals(hashTable.get("22"),"j");
        Assertions.assertEquals(hashTable.get("j"),"22");
        Assertions.assertEquals(hashTable.get("0"),"j");


    }

    @org.junit.jupiter.api.Test
    void drop() {

        // no borrar si esta vacio

        // no borrar si no encuentra key en esa posicion

        // borrar caso solitario

        // al borrar una entrada que este en medio, remplazar por la siguiente. hay que jugar con el .next y .previous
        // si es el ultimo, .previous.next = null
        // si es el primero, entries[i] =  .next, .next.previous = null

    }

    @org.junit.jupiter.api.Test
    void realSize() {

        for (int i = 0; i < 20; i++) {
            Assertions.assertEquals(i, i);

        }
        Assertions.assertEquals(1,16);
        Assertions.assertEquals(2,0);

    }

    @org.junit.jupiter.api.Test
    void size() {


    }
}