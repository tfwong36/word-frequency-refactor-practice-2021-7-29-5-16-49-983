import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SpaceRegex = "\\s+";

    private List<WordInfo> calculateWordFrequency(String sentence){
        List<String> words = Arrays.asList(sentence.split(SpaceRegex));
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());
        List<WordInfo> wordInfos = new ArrayList<>();
        distinctWords.forEach(distinctWord -> {
            int frequency = (int) words.stream()
                                        .filter(word->word.equals(distinctWord))
                                        .count();
            wordInfos.add(new WordInfo(distinctWord, frequency));
        });
        return wordInfos;
    }

    public String getResult(String sentence){
        if (sentence.split(SpaceRegex).length==1) {
            return sentence + " 1";
        } else {

            try {
                List<WordInfo> wordInfoList = calculateWordFrequency(sentence);

                wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordInfo word : wordInfoList) {
                    String s = word.getValue() + " " +word.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }


    private Map<String,List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordInfo.getValue())){
                ArrayList<WordInfo> words = new ArrayList<>();
                words.add(wordInfo);
                map.put(wordInfo.getValue(), words);
            }

            else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }


        return map;
    }


}
