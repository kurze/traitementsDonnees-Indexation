package fr.univtours.polytech.di.multimedia.parser;

// $ANTLR 3.1.2
// /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g
// 2009-04-02 11:19:52

import org.antlr.runtime.BitSet;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.TreeAdaptor;

public class BooleanExpressionParser extends Parser {
  public static class atom_return extends ParserRuleReturnScope {
    CommonTree tree;

    @Override
    public Object getTree() {
      return tree;
    }
  }

  public static class expr_return extends ParserRuleReturnScope {
    CommonTree tree;

    @Override
    public Object getTree() {
      return tree;
    }
  }

  public static class multExpr_return extends ParserRuleReturnScope {
    CommonTree tree;

    @Override
    public Object getTree() {
      return tree;
    }
  }

  public static class prog_return extends ParserRuleReturnScope {
    CommonTree tree;

    @Override
    public Object getTree() {
      return tree;
    }
  }

  public static final String[] tokenNames =
      new String[] { "<invalid>", "<EOR>", "<DOWN>", "<UP>", "OR", "AND",
          "SIGN", "OPENPARENTH", "CLOSEPARENTH", "NOT", "WS" };
  public static final int SIGN = 6;
  public static final int WS = 10;
  public static final int OR = 4;
  public static final int OPENPARENTH = 7;

  // delegates
  // delegators

  public static final int NOT = 9;
  public static final int AND = 5;

  public static final int EOF = -1;

  public static final int CLOSEPARENTH = 8;
  protected TreeAdaptor adaptor = new CommonTreeAdaptor();

  public static final BitSet FOLLOW_expr_in_prog35 =
      new BitSet(new long[] { 0x0000000000000002L });
  public static final BitSet FOLLOW_multExpr_in_expr47 =
      new BitSet(new long[] { 0x0000000000000012L });

  public static final BitSet FOLLOW_OR_in_expr50 =
      new BitSet(new long[] { 0x00000000000002C0L });;

  public static final BitSet FOLLOW_multExpr_in_expr53 =
      new BitSet(new long[] { 0x0000000000000012L });

  public static final BitSet FOLLOW_atom_in_multExpr75 =
      new BitSet(new long[] { 0x0000000000000022L });;

  public static final BitSet FOLLOW_AND_in_multExpr78 =
      new BitSet(new long[] { 0x00000000000002C0L });

  public static final BitSet FOLLOW_atom_in_multExpr81 =
      new BitSet(new long[] { 0x0000000000000022L });;

  public static final BitSet FOLLOW_SIGN_in_atom98 =
      new BitSet(new long[] { 0x0000000000000002L });

  public static final BitSet FOLLOW_OPENPARENTH_in_atom108 =
      new BitSet(new long[] { 0x00000000000002C0L });;

  public static final BitSet FOLLOW_expr_in_atom111 =
      new BitSet(new long[] { 0x0000000000000100L });

  // Delegated rules

  public static final BitSet FOLLOW_CLOSEPARENTH_in_atom113 =
      new BitSet(new long[] { 0x0000000000000002L });
  public static final BitSet FOLLOW_NOT_in_atom122 =
      new BitSet(new long[] { 0x0000000000000040L });
  public static final BitSet FOLLOW_SIGN_in_atom125 =
      new BitSet(new long[] { 0x0000000000000002L });
  public static final BitSet FOLLOW_NOT_in_atom133 =
      new BitSet(new long[] { 0x0000000000000080L });
  public static final BitSet FOLLOW_OPENPARENTH_in_atom136 =
      new BitSet(new long[] { 0x00000000000002C0L });
  public static final BitSet FOLLOW_expr_in_atom138 =
      new BitSet(new long[] { 0x0000000000000100L });
  public static final BitSet FOLLOW_CLOSEPARENTH_in_atom140 =
      new BitSet(new long[] { 0x0000000000000002L });

  public BooleanExpressionParser(final TokenStream input) {
    this(input, new RecognizerSharedState());
  }

  public BooleanExpressionParser(final TokenStream input,
      final RecognizerSharedState state) {
    super(input, state);

  }

  // $ANTLR start "atom"
  // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:17:1:
  // atom : ( SIGN | OPENPARENTH expr CLOSEPARENTH | NOT SIGN | NOT OPENPARENTH
  // expr CLOSEPARENTH );
  public final BooleanExpressionParser.atom_return atom()
      throws RecognitionException {
    final BooleanExpressionParser.atom_return retval =
        new BooleanExpressionParser.atom_return();
    retval.start = input.LT(1);

    CommonTree root_0 = null;

    Token SIGN8 = null;
    Token OPENPARENTH9 = null;
    Token CLOSEPARENTH11 = null;
    Token NOT12 = null;
    Token SIGN13 = null;
    Token NOT14 = null;
    Token OPENPARENTH15 = null;
    Token CLOSEPARENTH17 = null;
    BooleanExpressionParser.expr_return expr10 = null;

    BooleanExpressionParser.expr_return expr16 = null;

    CommonTree SIGN8_tree = null;
    final CommonTree OPENPARENTH9_tree = null;
    final CommonTree CLOSEPARENTH11_tree = null;
    CommonTree NOT12_tree = null;
    CommonTree SIGN13_tree = null;
    CommonTree NOT14_tree = null;
    CommonTree OPENPARENTH15_tree = null;
    CommonTree CLOSEPARENTH17_tree = null;

    try {
      // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:17:5:
      // ( SIGN | OPENPARENTH expr CLOSEPARENTH | NOT SIGN | NOT OPENPARENTH
      // expr CLOSEPARENTH )
      int alt3 = 4;
      switch (input.LA(1)) {
        case SIGN: {
          alt3 = 1;
        }
          break;
        case OPENPARENTH: {
          alt3 = 2;
        }
          break;
        case NOT: {
          final int LA3_3 = input.LA(2);

          if ((LA3_3 == BooleanExpressionParser.SIGN)) {
            alt3 = 3;
          } else if ((LA3_3 == BooleanExpressionParser.OPENPARENTH)) {
            alt3 = 4;
          } else {
            final NoViableAltException nvae =
                new NoViableAltException("", 3, 3, input);

            throw nvae;
          }
        }
          break;
        default:
          final NoViableAltException nvae =
              new NoViableAltException("", 3, 0, input);

          throw nvae;
      }

      switch (alt3) {
        case 1:
          // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:17:9:
          // SIGN
        {
          root_0 = (CommonTree) adaptor.nil();

          SIGN8 =
              (Token) match(input, BooleanExpressionParser.SIGN,
                  BooleanExpressionParser.FOLLOW_SIGN_in_atom98);
          SIGN8_tree = (CommonTree) adaptor.create(SIGN8);
          adaptor.addChild(root_0, SIGN8_tree);

        }
          break;
        case 2:
          // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:18:9:
          // OPENPARENTH expr CLOSEPARENTH
        {
          root_0 = (CommonTree) adaptor.nil();

          OPENPARENTH9 =
              (Token) match(input, BooleanExpressionParser.OPENPARENTH,
                  BooleanExpressionParser.FOLLOW_OPENPARENTH_in_atom108);
          pushFollow(BooleanExpressionParser.FOLLOW_expr_in_atom111);
          expr10 = expr();

          state._fsp--;

          adaptor.addChild(root_0, expr10.getTree());
          CLOSEPARENTH11 =
              (Token) match(input, BooleanExpressionParser.CLOSEPARENTH,
                  BooleanExpressionParser.FOLLOW_CLOSEPARENTH_in_atom113);

        }
          break;
        case 3:
          // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:19:7:
          // NOT SIGN
        {
          root_0 = (CommonTree) adaptor.nil();

          NOT12 =
              (Token) match(input, BooleanExpressionParser.NOT,
                  BooleanExpressionParser.FOLLOW_NOT_in_atom122);
          NOT12_tree = (CommonTree) adaptor.create(NOT12);
          root_0 = (CommonTree) adaptor.becomeRoot(NOT12_tree, root_0);

          SIGN13 =
              (Token) match(input, BooleanExpressionParser.SIGN,
                  BooleanExpressionParser.FOLLOW_SIGN_in_atom125);
          SIGN13_tree = (CommonTree) adaptor.create(SIGN13);
          adaptor.addChild(root_0, SIGN13_tree);

        }
          break;
        case 4:
          // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:20:7:
          // NOT OPENPARENTH expr CLOSEPARENTH
        {
          root_0 = (CommonTree) adaptor.nil();

          NOT14 =
              (Token) match(input, BooleanExpressionParser.NOT,
                  BooleanExpressionParser.FOLLOW_NOT_in_atom133);
          NOT14_tree = (CommonTree) adaptor.create(NOT14);
          root_0 = (CommonTree) adaptor.becomeRoot(NOT14_tree, root_0);

          OPENPARENTH15 =
              (Token) match(input, BooleanExpressionParser.OPENPARENTH,
                  BooleanExpressionParser.FOLLOW_OPENPARENTH_in_atom136);
          OPENPARENTH15_tree = (CommonTree) adaptor.create(OPENPARENTH15);
          adaptor.addChild(root_0, OPENPARENTH15_tree);

          pushFollow(BooleanExpressionParser.FOLLOW_expr_in_atom138);
          expr16 = expr();

          state._fsp--;

          adaptor.addChild(root_0, expr16.getTree());
          CLOSEPARENTH17 =
              (Token) match(input, BooleanExpressionParser.CLOSEPARENTH,
                  BooleanExpressionParser.FOLLOW_CLOSEPARENTH_in_atom140);
          CLOSEPARENTH17_tree = (CommonTree) adaptor.create(CLOSEPARENTH17);
          adaptor.addChild(root_0, CLOSEPARENTH17_tree);

        }
          break;

      }
      retval.stop = input.LT(-1);

      retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
      adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

    } catch (final RecognitionException re) {
      reportError(re);
      recover(input, re);
      retval.tree =
          (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

    } finally {
    }
    return retval;
  }

  // $ANTLR end "atom"
  // $ANTLR start "expr"
  // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:10:1:
  // expr : multExpr ( OR multExpr )* ;
  public final BooleanExpressionParser.expr_return expr()
      throws RecognitionException {
    final BooleanExpressionParser.expr_return retval =
        new BooleanExpressionParser.expr_return();
    retval.start = input.LT(1);

    CommonTree root_0 = null;

    Token OR3 = null;
    BooleanExpressionParser.multExpr_return multExpr2 = null;

    BooleanExpressionParser.multExpr_return multExpr4 = null;

    CommonTree OR3_tree = null;

    try {
      // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:10:5:
      // ( multExpr ( OR multExpr )* )
      // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:10:9:
      // multExpr ( OR multExpr )*
      {
        root_0 = (CommonTree) adaptor.nil();

        pushFollow(BooleanExpressionParser.FOLLOW_multExpr_in_expr47);
        multExpr2 = multExpr();

        state._fsp--;

        adaptor.addChild(root_0, multExpr2.getTree());
        // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:10:18:
        // ( OR multExpr )*
        loop1: do {
          int alt1 = 2;
          final int LA1_0 = input.LA(1);

          if ((LA1_0 == BooleanExpressionParser.OR)) {
            alt1 = 1;
          }

          switch (alt1) {
            case 1:
              // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:10:19:
              // OR multExpr
            {
              OR3 =
                  (Token) match(input, BooleanExpressionParser.OR,
                      BooleanExpressionParser.FOLLOW_OR_in_expr50);
              OR3_tree = (CommonTree) adaptor.create(OR3);
              root_0 = (CommonTree) adaptor.becomeRoot(OR3_tree, root_0);

              pushFollow(BooleanExpressionParser.FOLLOW_multExpr_in_expr53);
              multExpr4 = multExpr();

              state._fsp--;

              adaptor.addChild(root_0, multExpr4.getTree());

            }
              break;

            default:
              break loop1;
          }
        } while (true);

      }

      retval.stop = input.LT(-1);

      retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
      adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

    } catch (final RecognitionException re) {
      reportError(re);
      recover(input, re);
      retval.tree =
          (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

    } finally {
    }
    return retval;
  }

  // $ANTLR end "expr"
  @Override
  public String getGrammarFileName() {
    return "/home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g";
  }

  @Override
  public String[] getTokenNames() {
    return BooleanExpressionParser.tokenNames;
  }

  public TreeAdaptor getTreeAdaptor() {
    return adaptor;
  }

  // $ANTLR start "multExpr"
  // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:13:1:
  // multExpr : atom ( AND atom )* ;
  public final BooleanExpressionParser.multExpr_return multExpr()
      throws RecognitionException {
    final BooleanExpressionParser.multExpr_return retval =
        new BooleanExpressionParser.multExpr_return();
    retval.start = input.LT(1);

    CommonTree root_0 = null;

    Token AND6 = null;
    BooleanExpressionParser.atom_return atom5 = null;

    BooleanExpressionParser.atom_return atom7 = null;

    CommonTree AND6_tree = null;

    try {
      // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:14:5:
      // ( atom ( AND atom )* )
      // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:14:9:
      // atom ( AND atom )*
      {
        root_0 = (CommonTree) adaptor.nil();

        pushFollow(BooleanExpressionParser.FOLLOW_atom_in_multExpr75);
        atom5 = atom();

        state._fsp--;

        adaptor.addChild(root_0, atom5.getTree());
        // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:14:14:
        // ( AND atom )*
        loop2: do {
          int alt2 = 2;
          final int LA2_0 = input.LA(1);

          if ((LA2_0 == BooleanExpressionParser.AND)) {
            alt2 = 1;
          }

          switch (alt2) {
            case 1:
              // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:14:15:
              // AND atom
            {
              AND6 =
                  (Token) match(input, BooleanExpressionParser.AND,
                      BooleanExpressionParser.FOLLOW_AND_in_multExpr78);
              AND6_tree = (CommonTree) adaptor.create(AND6);
              root_0 = (CommonTree) adaptor.becomeRoot(AND6_tree, root_0);

              pushFollow(BooleanExpressionParser.FOLLOW_atom_in_multExpr81);
              atom7 = atom();

              state._fsp--;

              adaptor.addChild(root_0, atom7.getTree());

            }
              break;

            default:
              break loop2;
          }
        } while (true);

      }

      retval.stop = input.LT(-1);

      retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
      adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

    } catch (final RecognitionException re) {
      reportError(re);
      recover(input, re);
      retval.tree =
          (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

    } finally {
    }
    return retval;
  }

  // $ANTLR end "multExpr"
  // $ANTLR start "prog"
  // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:7:1:
  // prog : expr ;
  public final BooleanExpressionParser.prog_return prog()
      throws RecognitionException {
    final BooleanExpressionParser.prog_return retval =
        new BooleanExpressionParser.prog_return();
    retval.start = input.LT(1);

    CommonTree root_0 = null;

    BooleanExpressionParser.expr_return expr1 = null;

    try {
      // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:8:2:
      // ( expr )
      // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:8:5:
      // expr
      {
        root_0 = (CommonTree) adaptor.nil();

        pushFollow(BooleanExpressionParser.FOLLOW_expr_in_prog35);
        expr1 = expr();

        state._fsp--;

        adaptor.addChild(root_0, expr1.getTree());
        /*
         * System.out.println((expr1!=null?((CommonTree)expr1.tree):null).toStringTree
         * ());
         */

      }

      retval.stop = input.LT(-1);

      retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
      adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

    } catch (final RecognitionException re) {
      reportError(re);
      recover(input, re);
      retval.tree =
          (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

    } finally {
    }
    return retval;
  }

  // $ANTLR end "prog"
  public void setTreeAdaptor(final TreeAdaptor adaptor) {
    this.adaptor = adaptor;
  }

}
