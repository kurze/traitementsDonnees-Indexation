/**
 *
 */
package fr.univtours.polytech.di.multimedia.database;

/**
 * Classe utilitaire contenant un objet et un score pour l'objet.
 * @author Sébastien Aupetit
 */
public class ValuedObject {

  /** Le score. */
  private final double score;

  /** L'objet. */
  private final Object object;

  /**
   * Le constructeur.
   * @param object l'objet
   * @param score le score de l'objet
   */
  public ValuedObject(final Object object, final double score) {
    this.object = object;
    this.score = score;
  }

  /**
   * Permet d'obtenir l'objet.
   * @return l'objet
   */
  public Object getObject() {
    return object;
  }

  /**
   * Permet d'obtenir le score.
   * @return le score
   */
  public double getScore() {
    return score;
  }
}
