import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SpaceRegex = "\\s+";
    public static final String NEW_LINE_DELIMITER = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";

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
        return wordInfoList.stream()
                .map(wordInfo -> wordInfo.getWord() + " " + wordInfo.getWordCount())
                .collect(Collectors.joining(NEW_LINE_DELIMITER));
    }

    public String getResult(String sentence){
        if (sentence == null)
            return CALCULATE_ERROR;
        List<WordInfo> wordInfoList = calculateWordFrequency(sentence);
        wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
        return joinWordInfos(wordInfoList);

    }
}
