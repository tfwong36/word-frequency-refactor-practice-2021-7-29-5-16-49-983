import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SpaceRegex = "\\s+";
    public static final String newLineDELIMITER = "\n";

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
        StringJoiner wordInfoStringJoiner = new StringJoiner(newLineDELIMITER);
        wordInfoList.forEach( wordInfo ->{
            wordInfoStringJoiner.add(wordInfo.getWord() + " " + wordInfo.getWordCount());
        });
        return wordInfoStringJoiner.toString();
    }

    public String getResult(String sentence){
        if (sentence == null)
            return "Calculate Error";
        List<WordInfo> wordInfoList = calculateWordFrequency(sentence);
        wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
        return joinWordInfos(wordInfoList);

    }
}
