package fr.univtours.polytech.di.multimedia.filters;


/**
 * Classe de filtrage impl�mentant un filtre de mots vides.
 * @author S�bastien Aupetit
 */
public class StopWordFilter implements Filter {

  /**
   * Le constructeur.
   * @param caseFilterApplied indique si les signes ont �t� filtr�s en minuscule
   * @param accentFilterApplied indique si les signes ont �t� filtr�s sans
   *          accent et sans caract�res sp�ciaux
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
