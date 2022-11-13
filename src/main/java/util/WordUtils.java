package util;

/**
 * Static computation methods
 */
public class WordUtils {

    /**
     * Returns the numerical value of a word where the value is equal to the sum of the values
     * of the letters. We assign 'a'=1, 'b'=2, etc.
     * @param word - input, can be null
     * @return sum of values of letters in word
     */
    public static int getValue(String word) {
        if (word == null) {
            return 0;
        }
        word = word.toLowerCase();

        int retval = 0;

        for (int i = 0; i < word.length(); i++) {
            retval += word.charAt(i) - 'a' + 1;
        }

        return retval;
    }

    /**
     * Returns closest number to a target number where closest is defined to be the number n
     * such that Math.abs(n-target) is the smallest. In case of a tie there is no guarantee which will
     * be returned.
     *
     * @param num1 first input
     * @param num2 second input
     * @param target the number being compared against
     * @return either num1 or num2, whichever is closest to target
     */
    public static int getClosest(int num1, int num2, int target) {
        int first = Math.abs(num1-target);
        int second = Math.abs(num2-target);

        return (first <= second) ? num1 : num2;
    }

    /**
     * Returns lexically closest word to target, given two input words
     *
     * @param word1 first input
     * @param word2 second input
     * @param target word to be compared to
     * @return either word1 or word2, whichever is closest lexically to target
     *
     */
    public static String getClosest(String word1, String word2, String target) {
        if (word1 == null) {
            return word2;
        }
        if (word2 == null) {
            return word1;
        }
        if (target.isEmpty()) {
            return word1.compareTo(word2) < 0 ? word1 : word2;
        }

        return Math.abs(target.compareTo(word1)) < Math.abs(target.compareTo(word2)) ? word1 : word2;
    }
}
