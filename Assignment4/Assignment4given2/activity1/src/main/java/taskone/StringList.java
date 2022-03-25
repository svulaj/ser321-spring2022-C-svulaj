package taskone;

import java.util.List;
import java.util.ArrayList;

class StringList {
    
    List<String> strings = new ArrayList<String>();

    public void add(String str) {
        int pos = strings.indexOf(str);
        if (pos < 0) {
            strings.add(str);
        }
    }

    public boolean contains(String str) {
        return strings.indexOf(str) >= 0;
    }

    public int size() {
        return strings.size();
    }

    public String toString() {
        return strings.toString();
    }

    public String remove(int index) {
        String data = strings.get(index);
        strings.remove(index);
        return data;
    }

    public void reverse(int index) {
        StringBuilder reversedWord = new StringBuilder();
        reversedWord.append(strings.get(index));
        reversedWord.reverse();
        strings.set(index, reversedWord.toString());
    }
}