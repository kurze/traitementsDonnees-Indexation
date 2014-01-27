package fr.univtours.polytech.di.multimedia;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.univtours.polytech.di.multimedia.database.Database;
import fr.univtours.polytech.di.multimedia.database.ValuedObject;
import fr.univtours.polytech.di.multimedia.filters.AccentFilter;
import fr.univtours.polytech.di.multimedia.filters.CaseFilter;
import fr.univtours.polytech.di.multimedia.filters.Filter;
import fr.univtours.polytech.di.multimedia.filters.StemmingFilter;
import fr.univtours.polytech.di.multimedia.filters.StopWordFilter;
import fr.univtours.polytech.di.multimedia.querymodels.QueryModel;
import fr.univtours.polytech.di.multimedia.querymodels.SimpleBooleanQueryModel;
import fr.univtours.polytech.di.multimedia.querymodels.VectorQueryModel;
import fr.univtours.polytech.di.multimedia.querymodels.WeightedBooleanQueryModel;
import fr.univtours.polytech.di.multimedia.signextractors.NGramExtractor;
import fr.univtours.polytech.di.multimedia.signextractors.SignExtractor;
import fr.univtours.polytech.di.multimedia.signextractors.WordExtractor;

/**
 * La fen�tre principale de l'application.
 * @author S�bastien Aupetit
 */
public class MainFrame extends JFrame {

  private static final long serialVersionUID = 8131618408356111981L;

  /** La console. */
  private JTextArea console;

  /** La barre de progression. */
  private JProgressBar progressBar;

  /** Le message de progression. */
  private JLabel progressMessage;

  private JRadioButton wordSign;

  private JRadioButton troisGramSign;

  private JRadioButton quatreGramSign;

  private JRadioButton cinqGramSign;

  private JRadioButton sixGramSign;

  private JCheckBox checkCase;

  private JCheckBox checkAccent;

  private JCheckBox checkStopWord;

  private JCheckBox checkStemming;

  private JRadioButton vectorModel;

  private JRadioButton simpleBooleanModel;

  private JRadioButton weightedBooleanModel;

  private JTextField question;

  private JButton btnExecute;

  private JCheckBox checkTFIDF;

  private final JTabbedPane tabbedPane;

  /**
   * Le constructeur.
   * @param maximizeFrame Indique si la fen�tre est initialement maximis�e
   *          (true) ou non (false).
   */
  public MainFrame(final boolean maximizeFrame) {
    super();
    setTitle("Moteur d'indexation de contenus");
    setMinimumSize(getSize());
    setResizable(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBackground(Color.gray);

    tabbedPane = new JTabbedPane();
    tabbedPane.setTabPlacement(SwingConstants.TOP);
    getContentPane().add(tabbedPane);

    final JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.add(createControlPanel());
    mainPanel.add(createConsolePanel());

    tabbedPane.addTab("Configuration", mainPanel);

    // auto organisation de la fen�tre
    pack();

    // doit-on maximiser la fen�tre ?
    if (maximizeFrame) {
      setExtendedState(Frame.MAXIMIZED_BOTH);
    }
  }

  /**
   * Cr�e la zone de configuration.
   * @return le panel
   */
  private JPanel createConfigurePanel() {
    final JPanel configurePanel = new JPanel();
    configurePanel.setLayout(new BoxLayout(configurePanel, BoxLayout.Y_AXIS));
    configurePanel.setBorder(BorderFactory
        .createTitledBorder("Configuration :"));

    {
      final JPanel panel = new JPanel();
      final JLabel label = new JLabel("Signes descripteurs :");
      panel.add(label);

      wordSign = new JRadioButton("Mot");
      troisGramSign = new JRadioButton("3-gram");
      quatreGramSign = new JRadioButton("4-gram");
      cinqGramSign = new JRadioButton("5-gram");
      sixGramSign = new JRadioButton("6-gram");

      panel.add(wordSign);
      panel.add(troisGramSign);
      panel.add(quatreGramSign);
      panel.add(cinqGramSign);
      panel.add(sixGramSign);

      final ButtonGroup group = new ButtonGroup();
      group.add(wordSign);
      group.add(troisGramSign);
      group.add(quatreGramSign);
      group.add(cinqGramSign);
      group.add(sixGramSign);

      wordSign.setSelected(true);
      configurePanel.add(panel);
    }

    {
      final JPanel panel = new JPanel();
      final JLabel label = new JLabel("Transformantion des caract�res :");
      panel.add(label);

      checkCase = new JCheckBox("Convertir la casse", false);
      panel.add(checkCase);

      checkAccent = new JCheckBox("Eliminer les accents", false);
      panel.add(checkAccent);

      configurePanel.add(panel);
    }

    {
      final JPanel panel = new JPanel();
      final JLabel label = new JLabel("Transformation des signes :");
      panel.add(label);

      checkStopWord = new JCheckBox("Eliminer les mots vides", false);
      panel.add(checkStopWord);

      checkStemming = new JCheckBox("Radicaliser les mots", false);
      panel.add(checkStemming);

      configurePanel.add(panel);
    }

    {
      final JPanel panel = new JPanel();
      final JLabel label = new JLabel("Mod�le d'indexation :");
      panel.add(label);

      vectorModel = new JRadioButton("Mod�le vectoriel");
      simpleBooleanModel = new JRadioButton("Mod�le bool�en simple");
      weightedBooleanModel = new JRadioButton("Mod�le bool�en pond�r�");

      panel.add(vectorModel);
      panel.add(simpleBooleanModel);
      panel.add(weightedBooleanModel);

      final ButtonGroup group = new ButtonGroup();
      group.add(vectorModel);
      group.add(simpleBooleanModel);
      group.add(weightedBooleanModel);

      vectorModel.setSelected(true);
      configurePanel.add(panel);
    }

    {
      final JPanel panel = new JPanel();
      final JLabel label = new JLabel("Pond�ration :");
      panel.add(label);

      checkTFIDF = new JCheckBox("tf-idf", false);
      panel.add(checkTFIDF);

      configurePanel.add(panel);
    }

    {
      final JPanel panel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
      final JLabel label = new JLabel("Question :");
      panel.add(label);

      question = new JTextField("");
      panel.add(question);

      configurePanel.add(panel);
    }

    wordSign.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(final ChangeEvent e) {
        checkStemming.setEnabled(wordSign.isSelected());
        checkStopWord.setEnabled(wordSign.isSelected());
        simpleBooleanModel.setEnabled(wordSign.isSelected());
        weightedBooleanModel.setEnabled(wordSign.isSelected());
        vectorModel.setSelected(vectorModel.isSelected()
            || !wordSign.isSelected());
      }
    });

    simpleBooleanModel.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(final ChangeEvent e) {
        checkTFIDF.setEnabled(!simpleBooleanModel.isSelected());
      }
    });

    return configurePanel;
  }

  /**
   * Cr�e la console.
   * @return le panel
   */
  private JPanel createConsolePanel() {
    final JPanel consolePanel = new JPanel();
    consolePanel.setBorder(BorderFactory.createTitledBorder("Console :"));
    consolePanel.setLayout(new BoxLayout(consolePanel, BoxLayout.Y_AXIS));

    console = new JTextArea();
    console.setEditable(false);
    final JScrollPane consoleScrollpane = new JScrollPane();
    consoleScrollpane.getViewport().add(console);
    consoleScrollpane.setPreferredSize(new Dimension(1000, 600));
    consolePanel.add(consoleScrollpane);

    // Redirection de stdout et stderr vers notre console
    final JTextAreaOutputStream out = new JTextAreaOutputStream(console);
    System.setOut(new PrintStream(out));
    System.setErr(new PrintStream(out));
    return consolePanel;
  }

  /**
   * Cr�e la zone de contr�le.
   * @return la panel
   */
  private JPanel createControlPanel() {
    final JPanel controlPanel = new JPanel();
    controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

    controlPanel.add(createConfigurePanel());
    controlPanel.add(createProgressPanel());

    btnExecute = new JButton("Ex�cuter");
    btnExecute.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        executeIndexation();
      }
    });
    btnExecute.setAlignmentX(Component.CENTER_ALIGNMENT);
    controlPanel.add(btnExecute);

    return controlPanel;
  }

  /**
   * Cr�e la zone de la barre de progression.
   * @return le panel
   */
  private Component createProgressPanel() {
    final JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createTitledBorder("Progresssion :"));
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    progressMessage = new JLabel(" ");
    progressMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(progressMessage);

    progressBar = new JProgressBar(SwingConstants.HORIZONTAL);
    panel.add(progressBar);

    return panel;
  }

  /**
   * Ex�cute l'ensemble des actions d'indexation.
   */
  private void executeIndexation() {
    btnExecute.setEnabled(false);
    while (tabbedPane.getTabCount() > 1) {
      tabbedPane.removeTabAt(1);
    }

    final MainFrame me = this;
    final Runnable runnable = new Runnable() {
      @Override
      public void run() {

        // l'extracteur de signes
        SignExtractor signExtractor;
        if (wordSign.isSelected()) {
          signExtractor = new WordExtractor();
        } else if (troisGramSign.isSelected()) {
          signExtractor = new NGramExtractor(3);
        } else if (quatreGramSign.isSelected()) {
          signExtractor = new NGramExtractor(4);
        } else if (cinqGramSign.isSelected()) {
          signExtractor = new NGramExtractor(5);
        } else {
          signExtractor = new NGramExtractor(6);
        }

        final List < Filter > filters = new ArrayList < Filter >();

        if (checkCase.isSelected()) {
          filters.add(new CaseFilter());
        }
        if (checkAccent.isSelected()) {
          filters.add(new AccentFilter());
        }
        if (wordSign.isSelected() && checkStopWord.isSelected()) {
          filters.add(new StopWordFilter(checkCase.isSelected(), checkAccent
              .isSelected()));
        }
        if (wordSign.isSelected() && checkStemming.isSelected()) {
          filters.add(new StemmingFilter());
        }

        final Database db = new Database(me, signExtractor, filters);

        db.load();

        db.indexAllDocuments();

        QueryModel queryModel;
        if (vectorModel.isSelected()) {
          queryModel = new VectorQueryModel(db, checkTFIDF.isSelected());
        } else if (simpleBooleanModel.isSelected()) {
          queryModel = new SimpleBooleanQueryModel(db);
        } else {
          queryModel =
              new WeightedBooleanQueryModel(db, checkTFIDF.isSelected());
        }

        setProgress("Calcul de la r�ponse � la question", 0, 1);
        System.out.println("Calcul des r�ponses � la question: "
            + question.getText());
        List < ValuedObject > results =
            queryModel.getAnswers(question.getText());
        if (results == null) {
          results = new ArrayList < ValuedObject >();
        }
        setProgress("Calcul de la r�ponse � la question termin�", 1, 1);

        setProgress("Calcul des repr�sentations graphiques", 0, 5);
        queryModel.displayAnswers(results, 50);

        setProgress("Calcul des repr�sentations graphiques", 1, 5);
        tabbedPane.addTab("Statistiques", db.getDisplayableStatistics());

        setProgress("Calcul des repr�sentations graphiques", 2, 5);
        tabbedPane.addTab("Analyse des signes", db.getDisplayableRankedSigns());

        setProgress("Calcul des repr�sentations graphiques", 3, 5);
        tabbedPane.addTab("R�partition des r�ponses", queryModel
            .getDisplayableAnswers(results));

        setProgress("Calcul des repr�sentations graphiques", 5, 5);
        tabbedPane.addTab("Scores des r�ponses", queryModel
            .getDisplayableScores(results));

        setProgress("Calcul des repr�sentations graphiques termin�", 5, 5);

        btnExecute.setEnabled(true);
      }
    };
    new Thread(runnable).start();
  }

  /**
   * Met � jour la zone de progression.
   * @param message le message
   * @param cur la position du curseur de progression
   * @param total la valeur maximale du curseur de progression
   */
  public void setProgress(final String message, final int cur, final int total) {
    progressMessage.setText(message);
    progressBar.setMaximum(total);
    progressBar.setMinimum(0);
    progressBar.setValue(cur);
  }
}
