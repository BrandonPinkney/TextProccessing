import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class TextProcessing {

    // main method create variables to tokenize and read stop words and put into
    // display output method
    public static void main(String[] args) {
        File file = new File("C:/Users/Pinkn/project1/TextProccessing/Proccessed/alice29.txt");
        File file2 = new File("C:/Users/Pinkn/project1/TextProccessing/Proccessed/stopwords.txt");
        File file3 = new File("C:/Users/Pinkn/project1/TextProccessing/output1.txt");
        List<String> tokens = tokenizetheText(file);
        Set<String> findStopwords = findAllStopWords(file2);
        Map<String, Integer> frequency = frequencyOfEachWord(file3);
        List<Map.Entry<String, Integer>> t = findTopNnumbers(frequency);
        getAllandRemoveStopWords(file2, findStopwords);
        writeTheContentsOut(tokens, "output1.txt");
        writeTheFrequencyOut(frequency, "output3.txt");
        writeTheTopNOut(t, "output4.txt");

    }

    // Preprocessing

    public static List<String> tokenizetheText(File file) {
        ArrayList<String> t = new ArrayList<>();
        BufferedReader br = null;
        try {
            String information;
            br = new BufferedReader(new FileReader(file));
            // iterate through and adds the words
            while ((information = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(information);
                while (st.hasMoreTokens()) {
                    String line = st.nextToken();
                    t.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return t;
    }

    // output file
    public static void writeTheContentsOut(List<String> token, String file) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            for (String tokens : token) {
                bw.write(tokens + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void getAllandRemoveStopWords(File file, Set<String> stopwords) {
        // initalize
        BufferedReader br = null;
        StringBuilder builder = new StringBuilder();
        String[] allWords = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String information;
            while ((information = br.readLine()) != null) {
                // case sensitivity
                allWords = information.toLowerCase().split(" ");
                for (String word : allWords) {
                    // if its not a stopword builder then appends word
                    if (!stopwords.contains(word)) {
                        builder.append(word);
                        builder.append(' ');
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // read stop words and put them into hashSet
    public static Set<String> findAllStopWords(File file) {
        HashSet<String> stopwords = new HashSet<>();
        BufferedReader br = null;
        try {
            String information;
            // read stopwords file
            br = new BufferedReader(new FileReader(file));
            // iterate through and adds the words
            while ((information = br.readLine()) != null) {
                stopwords.add(information.trim());
                if (!information.equals("")) {
                    stopwords.add(information);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return stopwords;
    }

    // Task 2

    public static Map<String, Integer> frequencyOfEachWord(File file) {
        HashMap<String, Integer> words = new HashMap<>();
        String[] words2 = null;
        BufferedReader br = null;
        try {
            String information;
            // read output1 file
            br = new BufferedReader(new FileReader(file));
            // iterate through and adds the words
            while ((information = br.readLine()) != null) {
                // Splitting the words of string
                // and storing them in the array.
                words2 = information.split(" ");
                for (String word : words2) {
                    // Asking whether the HashMap contains the
                    // key or not. Will return null if not.
                    Integer containsKey = words.get(word);

                    if (containsKey == null) {
                        words.put(word, 1);
                        // if the word is already here
                    } else {
                        words.put(word, containsKey + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return words;

    }

    public static void writeTheFrequencyOut(Map<String, Integer> frequency, String file) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            for (Map.Entry<String, Integer> freq : frequency.entrySet()) {
                String key = freq.getKey();
                int value = freq.getValue();
                bw.write("The word: " + key
                        + "the #"
                        + value + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    // Task 3

    public static List<Map.Entry<String, Integer>> findTopNnumbers(Map<String, Integer> topN) {

        Comparator<Map.Entry<String, Integer>> c = ((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        List<Map.Entry<String, Integer>> t = new ArrayList<>(topN.entrySet());
        t.sort(c);
        return t;
    }

    public static void writeTheTopNOut(List<Map.Entry<String, Integer>> frequency, String file) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            for (Map.Entry<String, Integer> freq : frequency) {
                String key = freq.getKey();
                int value = freq.getValue();
                bw.write(key + ": " + value + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public double findRatioOfData(Map<String, Integer> frequency) {
        int numberOfWords = 0;
        int numberOfTokens = 0;

        for (Map.Entry<String, Integer> grabber : frequency.entrySet()) {
            numberOfWords = numberOfWords + grabber.getValue();
            numberOfTokens = numberOfTokens + grabber.getValue();
        }
        return (double) numberOfWords / numberOfTokens;

    }

    // Task 4

    public static Map<String, Integer> sizeOfDataset(File file) {
        HashMap<String, Integer> t = new HashMap<>();
        BufferedReader br = null;

        try {
            String information;
            br = new BufferedReader(new FileReader(file));
            // iterate through and adds the words
            while ((information = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(information);
                while (st.hasMoreTokens()) {
                    String line = st.nextToken();
                    if (!t.containsKey(line)) {
                        t.put(line, 1);
                    } else {
                        t.put(line, t.get(line) + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return t;
    }

    public static int numberOfWords(File file) {
        String[] words2 = null;
        BufferedReader br = null;
        int counter = 0;
        try {
            String information;
            // read output1 file
            br = new BufferedReader(new FileReader(file));
            // iterate through and adds the words
            while ((information = br.readLine()) != null) {
                // Splitting the words of string
                // and storing them in the array.
                words2 = information.split(" ");
                // each time counter encounters a word in array counter increments giving us
                // number of words
                counter += words2.length;

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return counter;
    }

    public static Map<String, Integer> numberOfStopWords(File file) {
        HashMap<String, Integer> t = new HashMap<>();
        BufferedReader br = null;

        try {
            String information;
            br = new BufferedReader(new FileReader(file));
            // iterate through and adds the words
            while ((information = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(information);
                while (st.hasMoreTokens()) {
                    String line = st.nextToken();
                    if (!t.containsKey(line)) {
                        t.put(line, 1);
                    } else {
                        t.put(line, t.get(line) + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return t;
    }

    public static int numberOfPunctuation(File file) {
        int counter = 0;
        BufferedReader br = null;

        try {
            String information;
            br = new BufferedReader(new FileReader(file));
            // iterate through and adds the words
            while ((information = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(information);
                while (st.hasMoreTokens()) {
                    String line = st.nextToken();
                    // checks if character is not a letter or digit looking for puncuation\
                    // converts the line string into a char so it can be checked
                    for (char c : line.toCharArray()) {
                        if (c == '!' || c == ',' || c == ';' || c == '.'
                                || c == '?' || c == '-' ||
                                c == '\'' || c == '\"' || c == ':') {
                            counter++;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return counter;
    }
}
