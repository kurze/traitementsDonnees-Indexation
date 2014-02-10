package fr.univtours.polytech.di.multimedia.filters;

/**
 * Classe de filtre réalisant un stemming.
 * @author Sébastien Aupetit
 */
public class StemmingFilter implements Filter {

    
    String[] suffixes = {
	    "ance",
	    "ique",
	    "isme",
	    "able",
	    "iste",
	    "eux",
	    "ances",
	    "iques",
	    "ismes",
	    "ables",
	    "istes",
	    "atrice",
	    "ateur",
	    "ation",
	    "atrices",
	    "ateurs",
	    "ations"
    };
  /**
   * {@inheritDoc}
   * @see fr.univtours.polytech.di.multimedia.filters.Filter#filter(java.lang.String)
   */
  @Override
  public String filter(final String sign) {
    String newSign = sign;
      for (String suffixe : suffixes) {
	if(newSign.endsWith(suffixe))
	{
	    newSign = (String) sign.subSequence(0, sign.length() - suffixe.length());
	    break;
	}
    }
    return newSign;
  }

}
