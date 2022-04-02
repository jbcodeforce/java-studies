package jbcodeforce.fun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Decode a string using a particular encoding rule
 * This problem is to build a syntaxic tree to extract the semantic from the input encoded string
 * 3[a]2[bc]  becomes aaabcbc
 * need to parse the string to build a pile: fifo
 * node is string and a number 
 */
public class StringDecoder {
    public static int MAX_CHAR_SET = 256;

    // k is the encoded key, v is the origin value
    HashMap<Character,Character> encodedSet = new HashMap<Character,Character>(MAX_CHAR_SET); 
    
    public String decode(String codedString) {
        Pile p = new Pile();
        String[] c = codedString.split("]");
        for (String s : c) {
            Node n =  buildNode(s);
            p.push(n);
        }
        return buildStringFromPile(p);
    }
    
    /**
     * parser the following syntax string with nn[cccccc] 
     * @param string
     * @return
     */
    public Node buildNode(String string) {
        if (string == null) return null;
        int first = string.indexOf("[");
        int last = string.indexOf("]");
        if (last == -1) {
            last = string.length();
        }
        int o = Integer.parseInt(string.substring(0,first));
        String p = string.substring(first+1,last);
        return new Node(o,p);
    }

    /**
     * From the pile of node
     * @param p
     * @return
     */
    public String buildStringFromPile(Pile p) {
        return "";
    }

    public class Pile {
        ArrayList<Node> thePile = new ArrayList<Node>();

        public void push(Node n) {
            thePile.add(n);
        }

        public Node pop() {
            if (thePile.isEmpty()) {
                return null;
            } else {
                return thePile.remove(0);
            }
        }
    }

    public class Node {
        int occurence;
        String pattern;

        public Node(int occurence,String pattern) {
            this.occurence = occurence;
            this.pattern = pattern;
        }
    }

   
}
