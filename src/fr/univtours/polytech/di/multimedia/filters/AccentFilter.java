package fr.univtours.polytech.di.multimedia.filters;

import java.text.Normalizer;

/**
 * Classe de filtre permettant d'éliminer les accents et les caractères spéciaux
 * d'une chaîne de caractères.
 * @author Sébastien Aupetit
 */
public class AccentFilter implements Filter {

  /**
   * {@inheritDoc}
   * @see fr.univtours.polytech.di.multimedia.filters.Filter#filter(java.lang.String)
   */
  @Override
  public String filter(final String sign) {
    final String result = Normalizer.normalize(sign, Normalizer.Form.NFD);
    return result.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
  }

}
