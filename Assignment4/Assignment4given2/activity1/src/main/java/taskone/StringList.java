package taskone;

import java.util.List;
import java.util.ArrayList;

class StringList {
    
    List<String> strings = new ArrayList<String>();
    String data;
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
        boolean flag = true;
        while(flag == true) {
            try {
                data = strings.get(index);
                strings.remove(index);
                flag =false;
            }catch(Exception e) {
                if(index > strings.size() || index < 0) {
                       System.out.println("Must be within bounds");
                }
            }
        }
        return data;
    }

    public void reverse(int index) {
        
        StringBuilder reversedWord = new StringBuilder();
        reversedWord.append(strings.get(index));
        reversedWord.reverse();
        strings.set(index, reversedWord.toString());
    }
    
    
}