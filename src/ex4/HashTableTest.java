package ex4;

import org.junit.jupiter.api.Assertions;

class HashTableTest {

    HashTable hashTable = new HashTable();

    @org.junit.jupiter.api.Test
    void put() {

        // Añadimos 4 valores en posiciones diferentes y comprobamos que se han introducido
        hashTable.put("0","13");
        hashTable.put("1","22");
        hashTable.put("2","45");
        hashTable.put("3","9");
        Assertions.assertEquals(hashTable.get("0"),"13");
        Assertions.assertEquals(hashTable.get("1"),"22");
        Assertions.assertEquals(hashTable.get("2"),"45");
        Assertions.assertEquals(hashTable.get("3"),"9");

        Assertions.assertEquals("\n bucket[0] = [0, 13]\n" +
                " bucket[1] = [1, 22]\n" +
                " bucket[2] = [2, 45]\n" +
                " bucket[3] = [3, 9]", hashTable.toString());


        // Añadimos 4 valores en las mismas posiciones que antes, en teoría se sobreescriben,
        // en este caso no porque no se ha corregido el error
        hashTable.put("0","0");
        hashTable.put("1","1");
        hashTable.put("2","2");
        hashTable.put("3","3");
        Assertions.assertEquals(hashTable.get("0"),"0");
        Assertions.assertEquals(hashTable.get("1"),"1");
        Assertions.assertEquals(hashTable.get("2"),"2");
        Assertions.assertEquals(hashTable.get("3"),"3");

        Assertions.assertEquals("\n bucket[0] = [0, 0]\n" +
                " bucket[1] = [1, 1]\n" +
                " bucket[2] = [2, 2]\n" +
                " bucket[3] = [3, 3]", hashTable.toString());

    }

    @org.junit.jupiter.api.Test
    void get() {

        // Probamos si hace bien el get si le ponemos keys numéricas del 0 al 29
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

        // También probamos en hacer el get de una key que no hemos introducido

        Assertions.assertNull(hashTable.get("h"));


    }

    @org.junit.jupiter.api.Test
    void drop() {

        // Añadimos 4 entradas y hacemos el drop con 2, también comparamos si se han borrado
        hashTable.put("0","0");
        hashTable.put("1","1");
        hashTable.put("2","2");
        hashTable.put("3","3");
        hashTable.drop("2");
        hashTable.drop("3");

        Assertions.assertEquals("\n bucket[0] = [0, 0]\n" +
                " bucket[1] = [1, 1]", hashTable.toString());

    }

    @org.junit.jupiter.api.Test
    void realSize() {

        // Probamos si el tamaño del realSize es 16
        Assertions.assertEquals(hashTable.realSize(), 16);
        Assertions.assertEquals(16, hashTable.realSize());

        // Probamos a añadir un elemento para ver si cambia el realSize
        hashTable.put("j","22");
        Assertions.assertEquals(hashTable.realSize(), 16);
        Assertions.assertEquals(16, hashTable.realSize());

    }

    /**
     *  Modificado el put para que funcione
     */
    @org.junit.jupiter.api.Test
    void size() {

        // Primero probamos si el tamaño es 0
        Assertions.assertEquals(hashTable.size(), 0);
        Assertions.assertEquals(0, hashTable.size());

        // Introducimos un valor para que el size aumente a 1 y lo probamos
        hashTable.put("j","22");
        Assertions.assertEquals(hashTable.size(), 1);
        Assertions.assertEquals(1, hashTable.size());

        // Introducimos un valor para que el size aumente a 2 y lo probamos
        hashTable.put("2","22");
        Assertions.assertEquals(hashTable.size(), 2);
        Assertions.assertEquals(2, hashTable.size());

        // Introducimos un valor con el mismo hash
        hashTable.put("2","13");
        Assertions.assertEquals(hashTable.size(), 3);
        Assertions.assertEquals(3, hashTable.size());

    }
}