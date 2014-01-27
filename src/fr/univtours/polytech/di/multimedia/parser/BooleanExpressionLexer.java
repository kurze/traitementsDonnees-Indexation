package fr.univtours.polytech.di.multimedia.parser;

// $ANTLR 3.1.2
// /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g
// 2009-04-02 11:19:52

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;

public class BooleanExpressionLexer extends Lexer {
  public static final int SIGN = 6;
  public static final int WS = 10;
  public static final int OR = 4;
  public static final int OPENPARENTH = 7;
  public static final int NOT = 9;
  public static final int AND = 5;
  public static final int EOF = -1;
  public static final int CLOSEPARENTH = 8;

  // delegates
  // delegators

  public BooleanExpressionLexer() {
    ;
  }

  public BooleanExpressionLexer(final CharStream input) {
    this(input, new RecognizerSharedState());
  }

  public BooleanExpressionLexer(final CharStream input,
      final RecognizerSharedState state) {
    super(input, state);

  }

  @Override
  public String getGrammarFileName() {
    return "/home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g";
  }

  // $ANTLR start "AND"
  public final void mAND() throws RecognitionException {
    try {
      final int _type = BooleanExpressionLexer.AND;
      final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
      // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:31:2:
      // ( '*' )
      // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:31:4:
      // '*'
      {
        match('*');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "AND"

  // $ANTLR start "CLOSEPARENTH"
  public final void mCLOSEPARENTH() throws RecognitionException {
    try {
      final int _type = BooleanExpressionLexer.CLOSEPARENTH;
      final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
      // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:26:2:
      // ( ')' )
      // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:26:4:
      // ')'
      {
        match(')');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "CLOSEPARENTH"

  // $ANTLR start "NOT"
  public final void mNOT() throws RecognitionException {
    try {
      final int _type = BooleanExpressionLexer.NOT;
      final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
      // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:33:2:
      // ( '-' )
      // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:33:4:
      // '-'
      {
        match('-');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "NOT"

  // $ANTLR start "OPENPARENTH"
  public final void mOPENPARENTH() throws RecognitionException {
    try {
      final int _type = BooleanExpressionLexer.OPENPARENTH;
      final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
      // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:24:2:
      // ( '(' )
      // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:24:5:
      // '('
      {
        match('(');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "OPENPARENTH"

  // $ANTLR start "OR"
  public final void mOR() throws RecognitionException {
    try {
      final int _type = BooleanExpressionLexer.OR;
      final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
      // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:29:2:
      // ( '+' )
      // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:29:4:
      // '+'
      {
        match('+');

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "OR"

  // $ANTLR start "SIGN"
  public final void mSIGN() throws RecognitionException {
    try {
      final int _type = BooleanExpressionLexer.SIGN;
      final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
      // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:34:7:
      // ( ( 'a' .. 'z' | 'A' .. 'Z' )+ )
      // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:34:11:
      // ( 'a' .. 'z' | 'A' .. 'Z' )+
      {
        // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:34:11:
        // ( 'a' .. 'z' | 'A' .. 'Z' )+
        int cnt1 = 0;
        loop1: do {
          int alt1 = 2;
          final int LA1_0 = input.LA(1);

          if ((((LA1_0 >= 'A') && (LA1_0 <= 'Z')) || ((LA1_0 >= 'a') && (LA1_0 <= 'z')))) {
            alt1 = 1;
          }

          switch (alt1) {
            case 1:
              // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:
            {
              if (((input.LA(1) >= 'A') && (input.LA(1) <= 'Z'))
                  || ((input.LA(1) >= 'a') && (input.LA(1) <= 'z'))) {
                input.consume();

              } else {
                final MismatchedSetException mse =
                    new MismatchedSetException(null, input);
                recover(mse);
                throw mse;
              }

            }
              break;

            default:
              if (cnt1 >= 1) {
                break loop1;
              }
              final EarlyExitException eee = new EarlyExitException(1, input);
              throw eee;
          }
          cnt1++;
        } while (true);

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }

  // $ANTLR end "SIGN"

  @Override
  public void mTokens() throws RecognitionException {
    // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:1:8:
    // ( OPENPARENTH | CLOSEPARENTH | OR | AND | NOT | SIGN | WS )
    int alt3 = 7;
    switch (input.LA(1)) {
      case '(': {
        alt3 = 1;
      }
        break;
      case ')': {
        alt3 = 2;
      }
        break;
      case '+': {
        alt3 = 3;
      }
        break;
      case '*': {
        alt3 = 4;
      }
        break;
      case '-': {
        alt3 = 5;
      }
        break;
      case 'A':
      case 'B':
      case 'C':
      case 'D':
      case 'E':
      case 'F':
      case 'G':
      case 'H':
      case 'I':
      case 'J':
      case 'K':
      case 'L':
      case 'M':
      case 'N':
      case 'O':
      case 'P':
      case 'Q':
      case 'R':
      case 'S':
      case 'T':
      case 'U':
      case 'V':
      case 'W':
      case 'X':
      case 'Y':
      case 'Z':
      case 'a':
      case 'b':
      case 'c':
      case 'd':
      case 'e':
      case 'f':
      case 'g':
      case 'h':
      case 'i':
      case 'j':
      case 'k':
      case 'l':
      case 'm':
      case 'n':
      case 'o':
      case 'p':
      case 'q':
      case 'r':
      case 's':
      case 't':
      case 'u':
      case 'v':
      case 'w':
      case 'x':
      case 'y':
      case 'z': {
        alt3 = 6;
      }
        break;
      case '\t':
      case ' ': {
        alt3 = 7;
      }
        break;
      default:
        final NoViableAltException nvae =
            new NoViableAltException("", 3, 0, input);

        throw nvae;
    }

    switch (alt3) {
      case 1:
        // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:1:10:
        // OPENPARENTH
      {
        mOPENPARENTH();

      }
        break;
      case 2:
        // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:1:22:
        // CLOSEPARENTH
      {
        mCLOSEPARENTH();

      }
        break;
      case 3:
        // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:1:35:
        // OR
      {
        mOR();

      }
        break;
      case 4:
        // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:1:38:
        // AND
      {
        mAND();

      }
        break;
      case 5:
        // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:1:42:
        // NOT
      {
        mNOT();

      }
        break;
      case 6:
        // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:1:46:
        // SIGN
      {
        mSIGN();

      }
        break;
      case 7:
        // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:1:51:
        // WS
      {
        mWS();

      }
        break;

    }

  }

  // $ANTLR start "WS"
  public final void mWS() throws RecognitionException {
    try {
      final int _type = BooleanExpressionLexer.WS;
      final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
      // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:35:5:
      // ( ( ' ' | '\\t' )+ )
      // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:35:9:
      // ( ' ' | '\\t' )+
      {
        // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:35:9:
        // ( ' ' | '\\t' )+
        int cnt2 = 0;
        loop2: do {
          int alt2 = 2;
          final int LA2_0 = input.LA(1);

          if (((LA2_0 == '\t') || (LA2_0 == ' '))) {
            alt2 = 1;
          }

          switch (alt2) {
            case 1:
              // /home/seb/articles/Enseignements/DI-0809/DI5-OptionWM-TraitementDonnéesMultimedia-TP-MoteurIndexation-Complet/antlr/BooleanExpression.g:
            {
              if ((input.LA(1) == '\t') || (input.LA(1) == ' ')) {
                input.consume();

              } else {
                final MismatchedSetException mse =
                    new MismatchedSetException(null, input);
                recover(mse);
                throw mse;
              }

            }
              break;

            default:
              if (cnt2 >= 1) {
                break loop2;
              }
              final EarlyExitException eee = new EarlyExitException(2, input);
              throw eee;
          }
          cnt2++;
        } while (true);

        skip();

      }

      state.type = _type;
      state.channel = _channel;
    } finally {
    }
  }
  // $ANTLR end "WS"

}
