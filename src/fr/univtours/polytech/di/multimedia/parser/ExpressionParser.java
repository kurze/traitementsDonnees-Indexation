package fr.univtours.polytech.di.multimedia.parser;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.Tree;


/**
 * Classe utilitaire encapsulant l'analyse d'une expression booléenne.
 * @author Sébastien Aupetit
 */
public class ExpressionParser {
  /**
   * Affiche l'arbre construit à partir de l'expression analysée
   * @param tree l'arbre de l'expression
   * @internal
   */
  private static void display(final Tree tree) {
    if (tree.getType() != BooleanExpressionParser.SIGN) {
      System.out.print("(");
    }
    System.out.print(tree.getText());
    for (int i = 0; i < tree.getChildCount(); ++i) {
      ExpressionParser.display(tree.getChild(i));
      if (i + 1 < tree.getChildCount()) {
        System.out.print(" ");
      }
    }
    if (tree.getType() != BooleanExpressionParser.SIGN) {
      System.out.print(") ");
    }
  }

  /**
   * Construit un arbre à partir de l'expression.
   * <p/>
   * <b>Opérateurs :</b>
   * <ul>
   * <li>OU : mot+mot</li>
   * <li>ET : mot*mot</li>
   * <li>NON : -mot</li>
   * <li>les priorités usuelles s'appliquent comme pour une expression
   * arithmétique</li>
   * <li>les parenthèses peuvent être utilisées pour faire des regroupements</li>
   * <li>les expressions sont évaluées de la gauche vers la droite</li
   * </ul>
   * <b>Exemples d'expressions valides :</b>
   * <ul>
   * <li>mot1+mot2+mot3 => mot1 OU mot2 OU mot3</li>
   * <li>mot1+mot2*mot3 => mot1 OU (mot2 ET mot3)</li>
   * <li>mot1*-mot2 => mot1 ET (NON mot2)</li>
   * </ul>
   * <b>Analyse de l'arbre construit :</b>
   * <ul>
   * <li>Tree getChild(index) est le sous arbre enfant n°index</li>
   * <li>getText() est la contenu du noeud de l'arbre</li>
   * <li>getType() est :
   * <ul>
   * <li>BooleanExpressionParser.SIGN si c'est un signe, getText() est le signe</li>
   * <li>BooleanExpressionParser.AND si c'est un ET, getChild(0) et getChild(1)
   * sont les deux sous-expressions</li>
   * <li>BooleanExpressionParser.OR si c'est un ET, getChild(0) et getChild(1)
   * sont les deux sous-expressions</li>
   * <li>BooleanExpressionParser.NOT si c'est un NON, getChild(0) est la
   * sous-expression</li></li>
   * </ul>
   * @param query l'expression booléenne à analyser
   * @return l'arbre représentant l'expression
   */
  public static Tree parseQuery(final String query) {
    final BooleanExpressionLexer lex =
        new BooleanExpressionLexer(new ANTLRStringStream(query));
    final CommonTokenStream tokens = new CommonTokenStream(lex);
    final BooleanExpressionParser g = new BooleanExpressionParser(tokens);
    try {
      final Tree result = (Tree) g.prog().getTree();

      System.out.print("Analyse de la question : " + query + " => ");
      ExpressionParser.display(result);
      System.out.println("");
      return result;
    } catch (final RecognitionException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Le constructeur privé pour empècher toute instanciation.
   */
  private ExpressionParser() {
    // rien à faire
  }
}
