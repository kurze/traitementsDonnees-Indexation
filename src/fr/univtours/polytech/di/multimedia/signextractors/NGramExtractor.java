package fr.univtours.polytech.di.multimedia.signextractors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe permettant d'extraite des N-grams � partir d'une cha�ne de caract�res.
 * @author S�bastien Aupetit
 */
public class NGramExtractor implements SignExtractor {

	/** pattern  pour le d�coupage */
	private Pattern nGramm;
	private Matcher matcher;
	private WordExtractor WE;
	private String word;

	public NGramExtractor(int n){
		WE = new WordExtractor();
		word = "";
		nGramm = Pattern.compile("[^ .,;:!?]{"+n+"}");
	}

  /**
   * {@inheritDoc}
   * @see fr.univtours.polytech.di.multimedia.signextractors.SignExtractor#nextToken()
   */
  @Override
  public String nextToken() {

	  while(true){// parcours tant que un ngram n'est pas extrait ou la fin atteint

		  // scan du mot retourn� par Word Extractor
		  matcher = nGramm.matcher(word);

		  if(matcher.find()){ // si un ngram est trouv�

			  // avance le parcours du mot en retirant un lettre au d�but
			  word = word.substring(1);

			  return matcher.group();
		  }else{ // si pas de ngram trouv�

			  // demande du mot suivant dans la chaine initiale
			  word = WE.nextToken();

			  if(word==null){ // si fin atteint
				  return null;
			  }
		  } 
	  }
  }

  /**
   * {@inheritDoc}
   * @see fr.univtours.polytech.di.multimedia.signextractors.SignExtractor#setContent(java.lang.String)
   */
  @Override
  public void setContent(final String content) {
	  WE.setContent(content);
	  matcher = null;
	  word  = "";
  }
}
