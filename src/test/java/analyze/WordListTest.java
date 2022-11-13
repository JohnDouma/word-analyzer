package analyze;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class WordListTest {
    private WordList wordList;

    @Before
    public void setup() throws IOException {
        wordList = WordList.getInstance();
    }

    @Test
    public void testGetClosestLexically() {
        String word = wordList.getLexicallyClosestWord("abc");
        Assert.assertEquals("carrot", word);

        word = wordList.getLexicallyClosestWord("Tuesday");
        Assert.assertEquals("today", word);

        Assert.assertEquals("carrot", wordList.getLexicallyClosestWord(""));

        word = wordList.getLexicallyClosestWord("EGG");
        Assert.assertEquals("forest", word);

        word = wordList.getLexicallyClosestWord("hello");
        Assert.assertEquals("hello", word);
    }

    @Test
    public void testGetClosestNumerically() {
        String word = wordList.getNumericallyClosestWord("abc");
        Assert.assertEquals("john", word);

        word = wordList.getNumericallyClosestWord("Tuesday");
        Assert.assertEquals("forest", word);

        Assert.assertEquals("john", wordList.getNumericallyClosestWord(""));

        word = wordList.getNumericallyClosestWord("EGG");
        Assert.assertEquals("john", word);

        word = wordList.getNumericallyClosestWord("hello");
        Assert.assertEquals("hello", word);
    }
}
