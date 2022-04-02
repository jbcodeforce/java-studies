package jbcodeforce.fun;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestStringDecoder {
    
    @Test
    public void testPile(){
        StringDecoder  decoder = new StringDecoder(); 
        StringDecoder.Node n = decoder.new Node(3,"a");
        StringDecoder.Pile p = decoder.new Pile();
        p.push(n);
        p.push(decoder.new Node(2,"ab"));
        StringDecoder.Node n1 = p.pop();
        Assertions.assertEquals(3,n1.occurence);
        Assertions.assertEquals("a",n1.pattern);
        Assertions.assertEquals("ab",p.pop().pattern);
    }

    @Test
    public void testSimpleParser(){
        StringDecoder  decoder = new StringDecoder(); 
        Assertions.assertTrue(decoder.buildNode(null) == null);
        StringDecoder.Node n = decoder.buildNode("3[a]");
        Assertions.assertEquals(3,n.occurence);
        Assertions.assertEquals("a",n.pattern);
        StringDecoder.Node n2 = decoder.buildNode("44[abc]");
        Assertions.assertEquals(44,n2.occurence);
        Assertions.assertEquals("abc",n2.pattern);
        Assertions.assertEquals("bdc",decoder.buildNode("44[bdc").pattern);
    }

    @Test
    public void testSplit(){
        String[] c = "3[a]5[bd]".split("]");
        Assertions.assertEquals("3[a",c[0]);
        Assertions.assertEquals("5[bd",c[1]);
     }

    @Test
    public void simplest(){
        StringDecoder  decoder = new StringDecoder(); 
        String codedString = "3[a]";
        Assertions.assertEquals("aaa",decoder.decode(codedString));  

    }
    @Test
    public void testDecoderCreation() {
        StringDecoder  decoder = new StringDecoder(); 
        String codedString = "3[a]2[bc]";
        String decodedString = decoder.decode(codedString);   
        Assertions.assertEquals("aaabcbc",decodedString);
    }
}
