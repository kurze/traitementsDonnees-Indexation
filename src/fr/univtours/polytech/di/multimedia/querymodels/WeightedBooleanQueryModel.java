package fr.univtours.polytech.di.multimedia.querymodels;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.tree.Tree;

import fr.univtours.polytech.di.multimedia.database.Database;
import fr.univtours.polytech.di.multimedia.database.ValuedObject;
import fr.univtours.polytech.di.multimedia.parser.ExpressionParser;

/**
 * Classe impl�mentant le mod�le d'interrogation bool�en pond�r�.
 * @author S�bastien Aupetit
 */
public class WeightedBooleanQueryModel extends QueryModel {

  /** Indique s'il faut utiliser une pond�ration TF-IDF. */
  private final boolean useTFIDF;

  /**
   * Le constructeur.
   * @param database la base de donn�es
   * @param useTFIDF indique s'il faut utiliser une pond�ration TF-IDF
   */
  public WeightedBooleanQueryModel(final Database database,
      final boolean useTFIDF) {
    super(database);
    this.useTFIDF = useTFIDF;
  }

  /**
   * {@inheritDoc}
   * @see fr.univtours.polytech.di.multimedia.querymodels.QueryModel#getAnswers(java.lang.String)
   */
  @Override
  public List < ValuedObject > getAnswers(final String question) {
    final List < ValuedObject > results = new ArrayList < ValuedObject >();

    final Tree tree = ExpressionParser.parseQuery(question);

    // TODO : A COMPLETER ICI
    return results;
  }
}
