package fr.univtours.polytech.di.multimedia.filters;


/**
 * Classe de filtrage implémentant un filtre de mots vides.
 * @author Sébastien Aupetit
 */
public class StopWordFilter implements Filter {

  /**
   * Le constructeur.
   * @param caseFilterApplied indique si les signes ont été filtrés en minuscule
   * @param accentFilterApplied indique si les signes ont été filtrés sans
   *          accent et sans caractères spéciaux
   */
  public StopWordFilter(final boolean caseFilterApplied,
      final boolean accentFilterApplied) {
    // TODO : A COMPLETER ICI
  }

  /**
   * {@inheritDoc}
   * @see fr.univtours.polytech.di.multimedia.filters.Filter#filter(java.lang.String)
   */
  @Override
  public String filter(final String sign) {
    // TODO : A COMPLETER ICI
    return sign;
  }

}
