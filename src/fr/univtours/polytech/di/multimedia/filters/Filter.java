package fr.univtours.polytech.di.multimedia.filters;

/**
 * Interface représentant un filtre pour les signes (suites de caractères).
 * @author Sébastien Aupetit
 */
public interface Filter {
  /**
   * Filtre le paramètre.
   * @param sign la suite de caractères à filtrer
   * @return la chaîne de caractères filtrée ou null si le signe doit être
   *         éliminé.
   */
  String filter(String sign);
}
