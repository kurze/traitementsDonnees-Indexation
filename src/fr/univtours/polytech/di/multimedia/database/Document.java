package fr.univtours.polytech.di.multimedia.database;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Classe représentant un document.
 * @author Sébastien Aupetit
 */
public class Document {
  /** Le contenu textuel du document. */
  private final String content;

  /** Le nom de fichier du document. */
  private final String fileName;

  /** Le nom du groupe auquel appartient le document. */
  private final String group;

  /**
   * Le constructeur.
   * @param group le nom du groupe auquel appartient le document
   * @param fileName le nom de fichier du document
   */
  public Document(final String group, final String fileName) {
    String ligne;
    final StringBuilder sb = new StringBuilder();
    boolean lastIsSpace = true;

    try {
      final BufferedReader br =
          new BufferedReader(new InputStreamReader(
              new FileInputStream(fileName), "utf8"));

      while ((ligne = br.readLine()) != null) {
        for (int i = 0; i < ligne.length(); ++i) {
          final int code = ligne.codePointAt(i);
          if (Character.isLetter(code)) {
            sb.appendCodePoint(code);
            lastIsSpace = false;
          } else {
            if (!lastIsSpace) {
              sb.append(' ');
              lastIsSpace = true;
            }
          }
        }
        if (!lastIsSpace) {
          sb.append(' ');
          lastIsSpace = true;
        }
      }
      br.close();
    } catch (final FileNotFoundException e) {
      e.printStackTrace();
    } catch (final IOException e) {
      e.printStackTrace();
    }
    content = sb.toString();
    this.group = group;
    this.fileName = fileName;
  }

  /**
   * Permet d'obtenir le contenu du fichier sous la forme d'une chaîne de
   * caractères ne contenant que des lettres et des espaces. Il ne peut pas y
   * avoir deux espaces consécutifs.
   * @return le contenu
   */
  public String getContent() {
    return content;
  }

  /**
   * Permet d'obtenir le nom de fichier du document.
   * @return le nom de fichier
   */
  public String getFileName() {
    return fileName;
  }

  /**
   * Permet d'obtenir le nom du group auquel appartient le document.
   * @return le nom du groupe
   */
  public String getGroup() {
    return group;
  }

}
