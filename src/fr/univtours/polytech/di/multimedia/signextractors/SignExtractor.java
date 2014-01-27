package fr.univtours.polytech.di.multimedia.signextractors;

/**
 * Interface définissant comment extraire des signes à partir d'une chaîne de
 * caractères.
 * @author Sébastien Aupetit
 */
public interface SignExtractor {
  /**
   * Permet d'obtenir le signe suivant.
   * @return le signe ou null s'il n'y a plus de signes à extraire
   */
  public String nextToken();

  /**
   * Permet de définir le chaîne de caractères à partir de laquelle les signes
   * seront extraits.
   * @param content la chaîne de caractère
   */
  public void setContent(String content);
}
