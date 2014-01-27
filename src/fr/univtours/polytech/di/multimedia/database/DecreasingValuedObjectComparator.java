package fr.univtours.polytech.di.multimedia.database;

import java.util.Comparator;

/**
 * Classe comparateur permettant de trier des {@link ValuedObject} en ordre
 * décroissant de score.
 * @author Sébastien Aupetit
 */
public class DecreasingValuedObjectComparator implements
    Comparator < ValuedObject > {

  /**
   * {@inheritDoc}
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(final ValuedObject o1, final ValuedObject o2) {
    if (o1.getScore() > o2.getScore()) {
      return -1;
    }
    if (o1.getScore() < o2.getScore()) {
      return 1;
    } else {
      return 0;
    }
  }
}
