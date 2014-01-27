package fr.univtours.polytech.di.multimedia.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Classe implémentant un index inversé en mémoire. Cette implémentation n'est
 * pas optimale !!
 * @author Sébastien Aupetit
 */
public class InvertedIndex {

  /** La table de hashage. */
  private final Map < String, InvertedIndexElement > index =
      new HashMap < String, InvertedIndexElement >();

  /**
   * Permet d'obtenir la liste de tous les documents contenant un signe.
   * @param sign le signe
   * @return la liste des documents
   */
  public List < Document > getAllDocuments(final String sign) {
    final InvertedIndexElement element = index.get(sign);
    if (element == null) {
      return new ArrayList < Document >();
    } else {
      return element.getAllDocuments();
    }
  }

  /**
   * Permet d'obtenir le nombre total d'occurrences de signes présent dans
   * l'ensemble de la base de document.
   * @return le nombre total d'occurrences
   */
  public double getOverallWordOccurrences() {
    double sum = 0;
    for (final String sign : index.keySet()) {
      sum += getOverallWordOccurrences(sign);
    }
    return sum;
  }

  /**
   * Permet d'obtenir le nombre total d'occurrences d'un signe dans l'ensemble
   * des documents.
   * @param sign le signe
   * @return le nombre total d'occurrences du signe
   */
  public double getOverallWordOccurrences(final String sign) {
    return getWordOccurrences(sign).getSignOccurrence();
  }

  /**
   * Permet d'obtenir l'ensemble des signes distincts de la base de document.
   * @return l'ensemble des signes distincts
   */
  public Set < String > getSignSet() {
    return index.keySet();
  }

  /**
   * Permet d'accèder à la liste des documents et d'occurrences d'un signe.
   * @param sign le signe
   * @return la liste des documents et d'occurrences d'un signe.
   */
  public InvertedIndexElement getWordOccurrences(final String sign) {
    return index.get(sign);
  }

  /**
   * Permet d'obtenir le nombre d'occurrences d'un signe dans un document.
   * @param sign le ligne
   * @param document le document
   * @return le nombre d'occurrences
   */
  public double getWordOccurrences(final String sign, final Document document) {
    final InvertedIndexElement element = index.get(sign);
    if (element == null) {
      return 0;
    } else {
      return element.getSignOccurrenceInDocument(document);
    }
  }

  /**
   * Permet de définir le nombre d'occurrence d'un sign dans un document.
   * @param sign le signe
   * @param document le document
   * @param occurrence le nombre d'occurrences du sign dans le document
   */
  public void setWordOccurrence(final String sign, final Document document,
      final double occurrence) {
    InvertedIndexElement element = index.get(sign);
    if (element == null) {
      element = new InvertedIndexElement();
      index.put(sign, element);
    }
    element.setOccurence(document, occurrence);
  }
}
