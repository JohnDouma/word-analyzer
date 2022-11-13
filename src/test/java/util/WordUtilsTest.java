package util;

import org.junit.Test;
import org.junit.Assert;

public class WordUtilsTest {
    @Test
    public void testGetValue_validInput() {
        String word = "abc";
        int value = WordUtils.getValue(word);
        Assert.assertEquals(6, value);

        word = "hello";
        value = WordUtils.getValue(word);
        Assert.assertEquals(52, value);
    }

    @Test
    public void testGetValue_nullOrEmptyInput() {
        String word = null;
        Assert.assertEquals(0, WordUtils.getValue(word));

        word = "";
        Assert.assertEquals(0, WordUtils.getValue(word));
    }

    @Test
    public void testGetClosestNumber() {
        int closest = WordUtils.getClosest(0,2, 5);
        Assert.assertEquals(2, closest);

        closest = WordUtils.getClosest(3,5,4);
        Assert.assertEquals(3, closest);

        closest = WordUtils.getClosest(3,5,5);
        Assert.assertEquals(5, closest);

        closest = WordUtils.getClosest(5,5,5);
        Assert.assertEquals(5, closest);
    }

    @Test
    public void testGetClosestWord() {
        String closest = WordUtils.getClosest("abs", "absconded", "abscond");
        Assert.assertEquals("absconded", closest);

        closest = WordUtils.getClosest("abs", "absconded", "");
        Assert.assertEquals("abs", closest);
    }
}
