package fr.univtours.polytech.di.multimedia.querymodels;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import fr.univtours.polytech.di.multimedia.database.Database;
import fr.univtours.polytech.di.multimedia.database.Document;
import fr.univtours.polytech.di.multimedia.database.ValuedObject;

/**
 * Classe abstraite repr�sentant un mod�le d'interrogation de l'index invers�.
 * @author S�bastien Aupetit
 */
public abstract class QueryModel {

  /**
   * Un compteur priv� permettant de s'assurer que les images cr�es sont uniques
   * pour le processus.
   */
  private static int counter = 0;

  /** La base de donn�es. */
  private final Database database;

  /**
   * Le constructeur.
   * @param database la base de donn�es � utiliser
   */
  public QueryModel(final Database database) {
    this.database = database;
  }

  /**
   * Permet d'afficher les premiers r�sultats de l'interrogation.
   * @param results la liste des documents �valu�s
   * @param limit le nombre de documents � afficher
   */
  public void displayAnswers(final List < ValuedObject > results,
      final int limit) {
    if (results.size() > database.getDocuments().size()) {
      System.out.println(" ***** ERREUR ***** : trop de documents en r�ponse");
    }

    System.out.println("Nombre de documents trouv�s : " + results.size());
    System.out.println("Voici les " + limit + " premi�res r�ponses :");
    for (int i = 0; i < Math.min(limit, results.size()); ++i) {
      System.out.println("" + results.get(i).getScore() + " => "
          + ((Document) results.get(i).getObject()).getFileName());
    }
  }

  /**
   * Calcul l'ensemble des documents r�pondant � la question.
   * @param question la question
   * @return la liste tri�e par pertinence d�croissante des documents r�pondant
   *         � la question
   */
  public abstract List < ValuedObject > getAnswers(final String question);

  /**
   * Permet d'obtenir la base de donn�es utilis�e.
   * @return la base de donn�es
   */
  public Database getDatabase() {
    return database;
  }

  /**
   * Construit une repr�sentation graphique des r�sultats de l'interrogation
   * @param results la liste des documents tri�s et valu�s
   * @return le composant d'affichage
   */
  public JScrollPane getDisplayableAnswers(final List < ValuedObject > results) {
    if (results.size() > database.getDocuments().size()) {
      System.out.println(" ***** ERREUR ***** : trop de documents en r�ponse");
    }

    PrintStream out;
    try {
      out = new PrintStream("results-data.txt");
      final List < String > groups = database.getGroups();
      final Map < String, Integer > counters =
          new HashMap < String, Integer >();

      for (final String group : groups) {
        counters.put(group, 0);
      }

      for (int i = 0; i < results.size(); ++i) {
        out.print(i);
        final String grp = ((Document) results.get(i).getObject()).getGroup();
        counters.put(grp, counters.get(grp) + 1);
        for (int j = 0; j < groups.size(); ++j) {
          final String group = groups.get(j);
          out.print(" "
              + (counters.get(group) / (double) getDatabase().getGroupSize(
                  group)/* / (i + 1.) */));
        }
        out.println("");
      }
      for (int i = results.size(); i < database.getDocuments().size(); ++i) {
        out.print(i);
        for (int j = 0; j < groups.size(); ++j) {
          final String group = groups.get(j);
          out.print(" "
              + (counters.get(group) / (double) getDatabase().getGroupSize(
                  group) /* / (i + 1.) */));
        }
        out.println("");
      }

      out.close();

      final PrintStream script = new PrintStream("results-script.plot");
      script.println("set terminal png enhanced size 1024,768 crop");
      script.println("set key below");
      script.println("set style data lines");
      script.println("set xlabel 'Nombre de documents r�ponses'");
      script.println("set ylabel 'Proportion des documents du groupe'");
      script.println("set yrange [-0.1:1.1]");
      script.println("set output 'results-" + QueryModel.counter + ".png'");
      script.print("plot ");
      for (int j = 0; j < groups.size(); ++j) {
        final String group = groups.get(j);
        script.print("\"results-data.txt\" using 1:" + (j + 2)
            + " with lines lw 2 title \"" + group + "\"");
        if (j + 1 < groups.size()) {
          script.print(", ");
        }
      }
      script.close();

      final ProcessBuilder pb =
          new ProcessBuilder("gnuplot", "results-script.plot");
      pb.start().waitFor();

      new File("results-data.txt").delete();
      new File("results-script.plot").delete();
    } catch (final FileNotFoundException e) {
      e.printStackTrace();
    } catch (final InterruptedException e) {
      e.printStackTrace();
    } catch (final IOException e) {
      e.printStackTrace();
    }

    final Icon icon = new ImageIcon("results-" + QueryModel.counter + ".png");
    final JLabel zoom = new JLabel(icon);
    final JScrollPane scrollPane = new JScrollPane();
    scrollPane.getViewport().add(zoom);
    scrollPane.setMinimumSize(new Dimension(300, 300));

    new File("results-" + QueryModel.counter + ".png").delete();
    QueryModel.counter++;

    return scrollPane;
  }

  /**
   * Construit une repr�sentation graphique des scores des r�sultats de
   * l'interrogation
   * @param results la liste des documents tri�s et valu�s
   * @return le composant d'affichage
   */
  public JScrollPane getDisplayableScores(final List < ValuedObject > results) {

    if (results.size() > database.getDocuments().size()) {
      System.out.println(" ***** ERREUR ***** : trop de documents en r�ponse");
    }

    PrintStream out;
    try {
      double max = 1.;
      out = new PrintStream("scores-data.txt");

      for (int i = 0; i < results.size(); ++i) {
        out.println("" + i + " " + results.get(i).getScore());
        max = Math.max(max, results.get(i).getScore());
      }
      for (int i = results.size(); i < database.getDocuments().size(); ++i) {
        out.println("" + i + " " + 0.0);
      }

      out.close();

      final PrintStream script = new PrintStream("scores-script.plot");
      script.println("set terminal png enhanced size 1024,768 crop");
      script.println("set key below");
      script.println("set style data lines");
      script.println("set xlabel 'Rang du document'");
      script.println("set ylabel 'Score'");
      script.println("set yrange [-0.1:" + (max + 0.1) + "]");
      script.println("set output 'scores-" + QueryModel.counter + ".png'");
      script
          .println("plot \"scores-data.txt\" using 1:2 with lines lw 2 title \"\"");
      script.close();

      final ProcessBuilder pb =
          new ProcessBuilder("gnuplot", "scores-script.plot");
      pb.start().waitFor();

      new File("scores-data.txt").delete();
      new File("scores-script.plot").delete();
    } catch (final FileNotFoundException e) {
      e.printStackTrace();
    } catch (final InterruptedException e) {
      e.printStackTrace();
    } catch (final IOException e) {
      e.printStackTrace();
    }

    final Icon icon = new ImageIcon("scores-" + QueryModel.counter + ".png");
    final JLabel zoom = new JLabel(icon);
    final JScrollPane scrollPane = new JScrollPane();
    scrollPane.getViewport().add(zoom);
    scrollPane.setMinimumSize(new Dimension(300, 300));

    new File("scores-" + QueryModel.counter + ".png").delete();
    QueryModel.counter++;

    return scrollPane;
  }
}
