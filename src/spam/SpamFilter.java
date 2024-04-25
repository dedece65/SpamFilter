
package spam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpamFilter {

	String	spamWords;
	double	threshold;
	int		spamWordsCount;


	public SpamFilter(final String spamWords, final double threshold) {
		this.spamWords = spamWords;
		this.threshold = threshold;
	}

	public boolean isSpam(final String input) {
		final String removedMultiSpacing;
		final List<String> everyWordInInput;
		final List<String> listOfSpamMultiWords = new ArrayList<>();

		this.spamWordsCount = 0;

		removedMultiSpacing = input.toLowerCase().replaceAll("\\s+", " ");
		everyWordInInput = Arrays.asList(removedMultiSpacing.split(" "));

		final String spamWordsAux = this.spamWords.replaceAll("\\s+", " ").replaceAll(", ", ",");
		final List<String> listOfSpamWords = Arrays.asList(spamWordsAux.toLowerCase().split(","));

		for (String word : listOfSpamWords)
			if (word.contains(" "))
				listOfSpamMultiWords.add(word);

		//"one million", "you’ve won", "un millon", "has ganado"
		for (final String spamWord : listOfSpamWords)
			for (String inputWord : everyWordInInput)
				this.spamWordsCount += inputWord.equals(spamWord) ? 1 : 0;

		for (final String word : listOfSpamMultiWords)
			this.spamWordsCount += removedMultiSpacing.split(word, -1).length - 1;

		final double umbral = (double) this.spamWordsCount / everyWordInInput.size();
		return umbral > this.threshold;
	}

}
