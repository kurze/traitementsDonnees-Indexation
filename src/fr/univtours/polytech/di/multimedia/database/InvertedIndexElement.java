/**
 *
 */
package fr.univtours.polytech.di.multimedia.database;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe gérant l'index inversé pour un signe.
 * @author Sébastien Aupetit
 */
class InvertedIndexElement {
  /**
   * Classe interne enregistrant le nombre d'occurrence du signe dans le
   * document.
   * @author Sébastien Aupetit
   * @internal
   */
  private class Pair {
    /** Le nombre d'occurrence. */
    private double signOccurrence;

    /** Le document. */
    private final Document document;

    /**
     * Le constructeur.
     * @param document le document
     * @param signOccurrence le nombre d'occurrence
     */
    public Pair(final Document document, final double signOccurrence) {
      this.signOccurrence = signOccurrence;
      this.document = document;
    }

    /**
     * Permet d'obtenir le document.
     * @return le document
     */
    public Document getDocument() {
      return document;
    }

    /**
     * Permet d'obtenir le nombre d'occurrences du signe.
     * @return le nombre d'occurrences
     */
    public double getSignOccurrence() {
      return signOccurrence;
    }

    /**
     * Permet de définir le nombre d'occurrences du signe.
     * @param signOccurrence le nombre d'occurrences
     */
    public void setWordOccurrence(final double signOccurrence) {
      this.signOccurrence = signOccurrence;
    }
  }

  /** La liste des paires (document,nombre d'occurrences). */
  private final List < Pair > documents = new ArrayList < Pair >();

  /** Le nombre total d'occurrences du signe dans l'ensemble des documents. */
  private int signOccurrence = 0;

  /**
   * Recherche l'indice du document dans la liste.
   * @param document le document
   * @return l'indice du document ou -1 si le document n'est pas dans la liste
   */
  private int findDocument(final Document document) {
    for (int i = 0; i < documents.size(); ++i) {
      if (documents.get(i).getDocument() == document) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Permet d'obtenir la liste des documents contenant le signe.
   * @return la liste des documents
   */
  public List < Document > getAllDocuments() {
    final List < Document > docs = new ArrayList < Document >();
    for (final Pair pair : documents) {
      docs.add(pair.getDocument());
    }
    return docs;
  }

  /**
   * Permet d'obtenir le nombre d'occurrences total du signe.
   * @return le nombre total du signe
   */
  public double getSignOccurrence() {
    return signOccurrence;
  }

  /**
   * Permet d'obtenir le nombre d'occurrences du signe dans un document.
   * @param document le document
   * @return le nombre d'occurrences
   */
  public double getSignOccurrenceInDocument(final Document document) {
    final int index = findDocument(document);
    if (index != -1) {
      return documents.get(index).getSignOccurrence();
    } else {
      return 0;
    }
  }

  /**
   * Permet d'obtenir le nombre d'éléments dans la liste.
   * @return le nombre d'éléments de la liste
   */
  public int getSize() {
    return documents.size();
  }

  /**
   * Permet de définir le nombre d'occurrences du signe dans un document.
   * <p/>
   * Si le nombre d'occurrences est nulle, le document est retiré de la liste.
   * Si le nombre d'occurrences est non nulle et si le document n'est pas déjà
   * dans la liste, il est ajouté.
   * @param document le document
   * @param signOccurrence le nombre d'occurrences du signe
   */
  public void setOccurence(final Document document, final double signOccurrence) {
    if (Math.abs(signOccurrence) < 1.e-10) {
      final int index = findDocument(document);
      if (index != 0) {
        documents.remove(index);
      }
    } else {
      final int index = findDocument(document);
      Pair pair;

      if (index != -1) {
        pair = documents.get(index);
        this.signOccurrence -= pair.getSignOccurrence();
      } else {
        pair = new Pair(document, signOccurrence);
        documents.add(pair);
      }
      this.signOccurrence += signOccurrence;
      pair.setWordOccurrence(signOccurrence);
    }
  }
}
