package fr.univtours.polytech.di.multimedia.querymodels;

import java.util.ArrayList;
import java.util.List;

import fr.univtours.polytech.di.multimedia.database.Database;
import fr.univtours.polytech.di.multimedia.database.ValuedObject;

/**
 * Classe impl�mentant le mod�le d'interrogation bool�en simple.
 * @author S�bastien Aupetit
 */
public class SimpleBooleanQueryModel extends QueryModel {

  /**
   * Le constructeur.
   * @param database la base de donn�es
   */
  public SimpleBooleanQueryModel(final Database database) {
    super(database);
    // TOTO : A COMPLETER ICI
  }

  /**
   * {@inheritDoc}
   * @see fr.univtours.polytech.di.multimedia.querymodels.QueryModel#getAnswers(java.lang.String)
   */
  @Override
  public List < ValuedObject > getAnswers(final String question) {
    final List < ValuedObject > results = new ArrayList < ValuedObject >();

    // TODO : A COMPLETER ICI
    return results;
  }

}
