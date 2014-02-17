package fr.univtours.polytech.di.multimedia.signextractors;

import java.util.regex.*;

/**
 * Classe permettant d'extraite des mots à partir d'une chaîne de caractères.
 * @author Sébastien Aupetit
 */
public class WordExtractor implements SignExtractor {

	private Pattern regexWord;
	private Matcher matcher;

  /**
   * {@inheritDoc}
   * @see fr.univtours.polytech.di.multimedia.signextractors.SignExtractor#nextToken()
   */
  @Override
  public String nextToken() {
	  // si un groupe est trouvé, on le renvoie, sinon on renvoie null
	  if(matcher.find()){
		  return matcher.group();
	  }else{
		  return null;
	  }
  }

  /**
   * {@inheritDoc}
   * @see fr.univtours.polytech.di.multimedia.signextractors.SignExtractor#setContent(java.lang.String)
   */
  @Override
  public void setContent(final String content) {
	  regexWord = Pattern.compile("[^ .,;:!?]+"); // liste des caractères pour séparer les tokens (ici des mots)
	  matcher = regexWord.matcher(content);
  }
}
