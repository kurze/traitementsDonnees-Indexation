package fr.univtours.polytech.di.multimedia.database;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fr.univtours.polytech.di.multimedia.MainFrame;
import fr.univtours.polytech.di.multimedia.filters.AccentFilter;
import fr.univtours.polytech.di.multimedia.filters.CaseFilter;
import fr.univtours.polytech.di.multimedia.filters.Filter;
import fr.univtours.polytech.di.multimedia.filters.StemmingFilter;
import fr.univtours.polytech.di.multimedia.filters.StopWordFilter;
import fr.univtours.polytech.di.multimedia.signextractors.SignExtractor;
import fr.univtours.polytech.di.multimedia.signextractors.WordExtractor;

/**
 * Classe repr�sentant la base de donn�es des documents.
 * @author S�bastien Aupetit
 */
public class Database {
  /** Compteur priv� statique pour s'assurer que les images cr�es sont uniques. */
  private static int counter = 0;

  /** La table de hashage des documents regroup�s par groupe. */
  private final Map < String, List < Document > > database =
      new HashMap < String, List < Document > >();

  /** La liste des documents de la base de donn�es. */
  private final List < Document > documents = new ArrayList < Document >();

  /** L'index invers� de la base de document. */
  private final InvertedIndex invertedIndex = new InvertedIndex();

  /**
   * Une r�f�rence vers la fen�tre principale pour l'affichage de la
   * progression.
   */
  private final MainFrame mainFrame;

  /** Une r�f�rence vers un extracteur de signes. */
  private final SignExtractor signExtractor;

  /** La liste des filtres � appliquer dans l'ordre sur les signes extraits. */
  private final List < Filter > filters;

  /**
   * Le constructeur.
   * @param mainFrame une r�f�rence vers la fenere principale de l'application
   * @param signExtractor un r�f�rence vers un extracteur de signes
   * @param filters la liste des filtres de signes � appliquer dans l'ordre
   */
  public Database(final MainFrame mainFrame, final SignExtractor signExtractor,
      final List < Filter > filters) {
    this.mainFrame = mainFrame;
    this.signExtractor = signExtractor;
    this.filters = filters;
  }

  /**
   * Applique tous les filtres sur un signes.
   * @param sign le signe � filtrer
   * @return le signe filtr� ou null si le signe doit �tre supprim�
   */
  public String filterSign(final String sign) {
    if (filters.size() == 0) {
      return sign;
    } else {
      String res = sign;
      for (int i = 0; i < filters.size(); ++i) {
        res = filters.get(i).filter(res);
        if (res == null) {
          break;
        }
      }
      return res;
    }
  }

  /**
   * Construit une image d�crivant la distribution des signes.
   * @return le composant � utiliser
   * @internal
   */
  public JScrollPane getDisplayableRankedSigns() {
    final List < ValuedObject > signs = new ArrayList < ValuedObject >();

    for (final String sign : invertedIndex.getSignSet()) {
      signs.add(new ValuedObject(sign, invertedIndex
          .getOverallWordOccurrences(sign)));
    }
    Collections.sort(signs, new DecreasingValuedObjectComparator());

    try {
      PrintStream out;
      out = new PrintStream("database-data.txt");
      for (int i = 0; i < signs.size(); ++i) {
        out.println("" + i + " " + Math.log10(signs.get(i).getScore()));
      }
      out.close();

      final PrintStream script = new PrintStream("database-script.plot");
      script.println("set terminal png enhanced size 1024,768 crop");
      script.println("set key below");
      script.println("set style data lines");
      script.println("set yrange [-0.1:*]");
      script.println("set xlabel 'Rang des signes (0=le plus fr�quent)'");
      script
          .println("set ylabel \"Logarithme (base 10) du nombre d'apparitions\"");
      script.println("set output 'database-" + Database.counter + ".png'");
      script
          .println("plot \"database-data.txt\" using 1:2 with lines lw 2 title \"\"");
      script.close();

      final ProcessBuilder pb =
          new ProcessBuilder("gnuplot", "database-script.plot");
      pb.start().waitFor();

      new File("database-data.txt").delete();
      new File("database-script.plot").delete();
    } catch (final FileNotFoundException e) {
      e.printStackTrace();
    } catch (final InterruptedException e) {
      e.printStackTrace();
    } catch (final IOException e) {
      e.printStackTrace();
    }

    final Icon icon = new ImageIcon("database-" + Database.counter + ".png");
    final JLabel zoom = new JLabel(icon);
    final JScrollPane scrollPane = new JScrollPane();
    scrollPane.getViewport().add(zoom);
    scrollPane.setMinimumSize(new Dimension(300, 300));

    new File("database-" + Database.counter + ".png").delete();
    Database.counter++;

    return scrollPane;
  }

  /**
   * Construit un composant permettant d'afficher des statistiques sur la base
   * de donn�es
   * @return le composant � utiliser
   * @internal
   */
  public JPanel getDisplayableStatistics() {
    final JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    panel.add(new JLabel("Nombre de documents : " + documents.size()));
    panel.add(new JLabel("Nombre de signes diff�rents : "
        + invertedIndex.getSignSet().size()));
    panel.add(new JLabel("Nombre moyen de signes par document : "
        + (invertedIndex.getOverallWordOccurrences() / documents.size())));

    final JTextArea area = new JTextArea();
    area.setEditable(false);
    area.append("Les 100 signes les plus fr�quents sont :");
    panel.add(area);
    area.setAlignmentX(0);
    area.setLineWrap(true);

    final List < ValuedObject > signs = new ArrayList < ValuedObject >();

    for (final String sign : invertedIndex.getSignSet()) {
      signs.add(new ValuedObject(sign, invertedIndex
          .getOverallWordOccurrences(sign)));
    }
    Collections.sort(signs, new DecreasingValuedObjectComparator());

    for (int i = 0; i < Math.min(100, signs.size()); ++i) {
      area.append((String) signs.get(i).getObject() + " ");
    }

    return panel;
  }

  /**
   * Permet d'obtenir la liste des documents de la base de donn�es
   * @return la liste des documents
   */
  public List < Document > getDocuments() {
    return documents;
  }

  /**
   * Permet d'obtenir la liste des groupes de documents
   * @return la liste des groupes
   */
  public List < String > getGroups() {
    final List < String > result = new ArrayList < String >();
    result.addAll(database.keySet());
    return result;
  }

  /**
   * Permet d'obtenir le nombre de documents pr�sent dans un groupe
   * @param group le nom du groupe
   * @return le nombre de documet du groupe
   */
  public int getGroupSize(final String group) {
    return database.get(group).size();
  }

  /**
   * Permet d'obtenir l'index invers�.
   * @return l'index invers�
   */
  public InvertedIndex getInvertedIndex() {
    return invertedIndex;
  }

  /**
   * Permet d'obtenir l'extracteur de signes associ�s � la base de donn�es.
   * @return l'extracteur de signes
   */
  public SignExtractor getSignExtractor() {
    return signExtractor;
  }

  /**
   * Permet de construire l'index invers� en indexant les documents
   */
  public final void indexAllDocuments() {
    for (int i = 0; i < getDocuments().size(); ++i) {
      mainFrame.setProgress("Indexation des documents", i, getDocuments()
          .size());
      indexDocument(getDocuments().get(i));
    }
    mainFrame.setProgress("Indexation des documents termin�es", getDocuments()
        .size(), getDocuments().size());
  }

  /**
   * Indexe un document
   * @param document le document � indexer
   */
  private void indexDocument(final Document document) {
	  signExtractor.setContent(document.getContent());

	  List<Filter> filter = new Vector<Filter>();
	  filter.add(new AccentFilter());
	  filter.add(new CaseFilter());

	  if(signExtractor.getClass()==WordExtractor.class){
		  filter.add(new StopWordFilter(true, true));
		  filter.add(new StemmingFilter());
	  }

	  String sign=null;
	  while((sign = signExtractor.nextToken()) != null){
		  for (Filter f : filter) {
			  sign = f.filter(sign);
			  if(sign != null){
				  break;
			  }
			  invertedIndex.setWordOccurrence(
					  sign, 
					  document, 
					  invertedIndex.getWordOccurrences(sign, document)+1.0);
		  }
	  }
  }

  /**
   * Charge les documents du sous r�pertoire pages.
   */
  public void load() {
    final File pagesDirectory = new File("pages");
    final File[] groups = pagesDirectory.listFiles();

    for (int i = 0; i < groups.length; ++i) {
      final File group = groups[i];
      mainFrame.setProgress("Chargement du groupe de documents : "
          + group.getName(), i, groups.length);

      final List < Document > listeDocuments = new ArrayList < Document >();
      for (final String filename : group.list()) {
        final Document document =
            new Document(group.getName(), group.getAbsolutePath()
                + File.separatorChar + filename);
        listeDocuments.add(document);
      }
      database.put(group.getName(), listeDocuments);
      documents.addAll(listeDocuments);
    }
    mainFrame.setProgress("Chargement termin�", groups.length, groups.length);
  }

}
