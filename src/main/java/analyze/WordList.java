package analyze;

import util.FileUtils;
import util.WordUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Contains the collection of words in two separate trees.
 * One is keyed lexically and the other is keyed by integers.
 */
public class WordList {
    private static final TreeMap<Integer, String> treeMap = new TreeMap<>();
    private static final TreeSet<String> treeSet = new TreeSet<>();
    private static WordList wordList;
    private static final String wordFilename = "words.txt";

    public synchronized static WordList getInstance() throws IOException {
        if (wordList == null) {
            wordList = new WordList();
        }

        return wordList;
    }

    private WordList() throws IOException {
        initializeWords();
    }

    /**
     * Return lexically closest word in our collection to the input word
     *
     * @param word String to which return value should be closest
     * @return String in our word list that is closest to the input word, null if word list is empty
     */
    public synchronized String getLexicallyClosestWord(String word) {
        if (treeSet.isEmpty()) {
            return null;
        }

        if (word.isEmpty()) {
            return treeSet.first();
        }

        word = word.toLowerCase();
        String less = treeSet.floor(word);
        String more = treeSet.ceiling(word);
        return WordUtils.getClosest(less, more, word);
    }

    /**
     * Return numerically closest string to the input word
     *
     * @param word String to which the word should be closest
     * @return String in our word list that is closest to the inmput word, null if word list is empty
     */
    public synchronized String getNumericallyClosestWord(String word) {
        if (treeMap.isEmpty()) {
            return null;
        }

        if (word.isEmpty()) {
            return treeMap.get(treeMap.firstKey());
        }

        word = word.toLowerCase();
        int valueOfWord = WordUtils.getValue(word);

        Integer less = treeMap.floorKey(valueOfWord);
        Integer more = treeMap.ceilingKey(valueOfWord);
        if (less == null) {
            return treeMap.get(more);
        }
        if (more == null) {
            return treeMap.get(less);
        }
        int key = WordUtils.getClosest(less, more, valueOfWord);

        return treeMap.get(key);
    }

    /**
     * Adds word to word list
     */
    public synchronized void addWord(String word) throws FileNotFoundException, URISyntaxException {
        if (treeSet.contains(word.toLowerCase())) {
            return;
        }
        treeSet.add(word.toLowerCase());
        treeMap.put(WordUtils.getValue(word), word.toLowerCase());
        FileUtils.addWordToFile(word, wordFilename);
    }

    /*
     *  Loads the words from words.txt, if it exists.
     */
    private void initializeWords() throws IOException {
        BufferedReader bufferedReader = FileUtils.getReader(wordFilename);
        String line;
        while (bufferedReader.ready()) {
            line = bufferedReader.readLine().toLowerCase();
            treeSet.add(line);
            treeMap.put(WordUtils.getValue(line), line);
        }

        bufferedReader.close();
    }
}