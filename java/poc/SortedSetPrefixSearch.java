import java.util.SortedSet;
import java.util.TreeSet;

public class SortedSetPrefixSearch {
    public static void main(String[] args) {
        SortedSet<String> mySet = new TreeSet<>();
        mySet.add("apple");
        mySet.add("apricot");
        mySet.add("banana");
        mySet.add("apply");
        mySet.add("orange");

        String prefix = "ap";

        // Calculate the upper bound for the subSet method
        String fromElement = prefix;
        String toElement = prefix + Character.MAX_VALUE;

        // Get the subset of elements starting with the prefix
        SortedSet<String> result = mySet.subSet(fromElement, toElement);

        System.out.println("Entries starting with '" + prefix + "': " + result);
        // Expected output: Entries starting with 'ap': [apple, apply, apricot]
    }
}
