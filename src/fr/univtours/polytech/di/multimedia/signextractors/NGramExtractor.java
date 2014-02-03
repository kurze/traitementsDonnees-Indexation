package fr.univtours.polytech.di.multimedia.signextractors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe permettant d'extraite des N-grams à partir d'une chaîne de caractères.
 * @author Sébastien Aupetit
 */
public class NGramExtractor implements SignExtractor {

	private Pattern nGramm;
	private Matcher matcher;
	private String content;
	private WordExtractor WE;
	private int n;
	private String word;

	public NGramExtractor(int n){
		this.n = n;
		WE = new WordExtractor();
		word = "";
	}

  /**
   * {@inheritDoc}
   * @see fr.univtours.polytech.di.multimedia.signextractors.SignExtractor#nextToken()
   */
  @Override
  public String nextToken() {
	  
	  boolean dernierMot=false;
	  
	  while(!dernierMot){
		  matcher = nGramm.matcher(word);
		  if(matcher.find()){
			  word = word.substring(1);
			  return matcher.group();
		  }else{
			  word = WE.nextToken();
			  if(word==""){
				  dernierMot = true;
				  return "";
			  }
		  } 
	  }
	  return "";
  }

  /**
   * {@inheritDoc}
   * @see fr.univtours.polytech.di.multimedia.signextractors.SignExtractor#setContent(java.lang.String)
   */
  @Override
  public void setContent(final String content) {
	  nGramm = Pattern.compile("[^ .,;:!?]{"+n+"}");
	  this.content = content;
	  WE.setContent(content);
  }
}
