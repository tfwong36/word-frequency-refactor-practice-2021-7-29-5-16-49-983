import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SpaceRegex = "\\s+";

    private List<WordInfo> calculateWordFrequency(String sentence){
        List<String> words = Arrays.asList(sentence.split(SpaceRegex));
        List<String> distinctWords = words.stream()
                                            .distinct()
                                            .collect(Collectors.toList());
        List<WordInfo> wordInfos = new ArrayList<>();
        distinctWords.forEach(distinctWord -> {
            int frequency = (int) words.stream()
                                        .filter(word->word.equals(distinctWord))
                                        .count();
            wordInfos.add(new WordInfo(distinctWord, frequency));
        });
        return wordInfos;
    }
    private String joinWordInfos(List<WordInfo> wordInfoList){
        StringJoiner wordJoiner = new StringJoiner("\n");
        for (WordInfo word : wordInfoList) {
            String s = word.getValue() + " " +word.getWordCount();
            wordJoiner.add(s);
        }
        return wordJoiner.toString();
    }
    public String getResult(String sentence){
        if (sentence.split(SpaceRegex).length==1) {
            return sentence + " 1";
        } else {
            try {
                List<WordInfo> wordInfoList = calculateWordFrequency(sentence);
                wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
                return joinWordInfos(wordInfoList);

            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }
}
