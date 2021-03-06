package ex4;
import java.util.ArrayList;
// Original source code: https://gist.github.com/amadamala/3cdd53cb5a6b1c1df540981ab0245479
// Modified by Fernando Porrino Serrano for academic purposes.


// REFACTOR: He hecho una extracción de clase en el método main por que ese método no tiene que estar en esta clase. Lo he puesto en la clase Main
// REFACTOR: He hecho un extracción de método en las clases get y drop, el cual repetía el bucle que recorría la tabla


/**
 * The type Hash table.
 */
public class HashTable {
    private int INITIAL_SIZE = 16;
    private int size = 0;
    private HashEntry[] entries = new HashEntry[INITIAL_SIZE];

    /**
     * Size int. Devuelve el tamaño de la tabla, indicando el numero de entradas en la tabla
     *
     * @return the int
     */
    public int size(){
        return this.size;
    }

    /**
     * Real size int. Devulve el tamaño inicial de la tabla.
     *
     * @return the int
     */
    public int realSize(){
        return this.INITIAL_SIZE;
    }

    /**
     * Put. Introduce los datos en la tabla.
     *
     * @param key   the key
     * @param value the value
     */
    public void put(String key, String value) {
        int hash = getHash(key);
        final HashEntry hashEntry = new HashEntry(key, value);

        if(entries[hash] == null) {
            entries[hash] = hashEntry;
        }
        else {
            HashEntry temp = entries[hash];
            while(temp.next != null)
                temp = temp.next;
            temp.next = hashEntry;
            hashEntry.prev = temp;
        }
        size++;     // Hacía falta aumentar el size cada vez que añadimos una entrada
    }

    /**
     * Returns 'null' if the element is not found. Devuelve el valor de la posición de la tabla que buscas
     *
     * @param key the key
     * @return the value
     */
    public String get(String key) {
        int hash = getHash(key);
        if(entries[hash] != null) {
            HashEntry temp = entries[hash];

            temp = getHashEntry(key, temp);  // Llama al método getHashEntry, creado por el Refactor

            return temp.value;
        }

        return null;
    }

    /**
     * Recorre la a tabla y te devuelve el HashEntry que buscaba
     * @param key
     * @param temp
     * @return
     */
    // Método creado por el Refactor
    private HashEntry getHashEntry(String key, HashEntry temp) {
        while (!temp.key.equals(key) && temp.next != null)
            temp = temp.next;
        return temp;
    }

    /**
     * Drop. Borra una entrada de la tabla
     *
     * @param key the key
     */
    public void drop(String key) {
        int hash = getHash(key);

        if(entries[hash] != null) {
            HashEntry temp = entries[hash];

            temp = getHashEntry(key, temp); // Llama al método getHashEntry, creado por el Refactor

            if(temp.prev == null && temp.next == null) entries[hash] = null;     //esborrar element únic (no col·lissió)
            else{
                if(temp.next != null) temp.next.prev = temp.prev;   //esborrem temp, per tant actualitzem l'anterior al següent
                temp.prev.next = temp.next;                         //esborrem temp, per tant actualitzem el següent de l'anterior
            }
        }
    }

    private int getHash(String key) {
        // piggy backing on java string
        // hashcode implementation.
        return key.hashCode() % INITIAL_SIZE;
    }

    private class HashEntry {

        String key;

        String value;


// Linked list of same hash entries.
        HashEntry next;

        HashEntry prev;

        /**
         * Instantiates a new Hash entry.
         *
         * @param key   the key
         * @param value the value
         */
        public HashEntry(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }

        @Override
        public String toString() {
            return "[" + key + ", " + value + "]";
        }
    }


    @Override
    public String toString() {
        int bucket = 0;
        StringBuilder hashTableStr = new StringBuilder();
        for (HashEntry entry : entries) {
            if(entry == null) {
                bucket++;
                continue;
            }

            hashTableStr.append("\n bucket[")
                    .append(bucket)
                    .append("] = ")
                    .append(entry.toString());
            bucket++;
            HashEntry temp = entry.next;
            while(temp != null) {
                hashTableStr.append(" -> ");
                hashTableStr.append(temp.toString());
                temp = temp.next;
            }
        }
        return hashTableStr.toString();
    }

    /**
     * Gets collisions for key.
     *
     * @param key the key
     * @return the collisions for key
     */
    public ArrayList<String> getCollisionsForKey(String key) {
        return getCollisionsForKey(key, 1);
    }

    /**
     * Get collisions for key array list.
     *
     * @param key      the key
     * @param quantity the quantity
     * @return the array list
     */
    public ArrayList<String> getCollisionsForKey(String key, int quantity){
        /*
          Main idea:
          alphabet = {0, 1, 2}

          Step 1: "000"
          Step 2: "001"
          Step 3: "002"
          Step 4: "010"
          Step 5: "011"
           ...
          Step N: "222"

          All those keys will be hashed and checking if collides with the given one.
        * */

        final char[] alphabet = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        ArrayList<Integer> newKey = new ArrayList();
        ArrayList<String> foundKeys = new ArrayList();

        newKey.add(0);
        int collision = getHash(key);
        int current = newKey.size() -1;

        while (foundKeys.size() < quantity){
            //building current key
            String currentKey = "";
            for(int i = 0; i < newKey.size(); i++)
                currentKey += alphabet[newKey.get(i)];

            if(!currentKey.equals(key) && getHash(currentKey) == collision)
                foundKeys.add(currentKey);

            //increasing the current alphabet key
            newKey.set(current, newKey.get(current)+1);

            //overflow over the alphabet on current!
            if(newKey.get(current) == alphabet.length){
                int previous = current;
                do{
                    //increasing the previous to current alphabet key
                    previous--;
                    if(previous >= 0)  newKey.set(previous, newKey.get(previous) + 1);
                }
                while (previous >= 0 && newKey.get(previous) == alphabet.length);

                //cleaning
                for(int i = previous + 1; i < newKey.size(); i++)
                    newKey.set(i, 0);

                //increasing size on underflow over the key size
                if(previous < 0) newKey.add(0);

                current = newKey.size() -1;
            }
        }

        return  foundKeys;
    }

    /**
     * Log.
     *
     * @param msg the msg
     */
    static void log(String msg) {
        System.out.println(msg);
    }
}