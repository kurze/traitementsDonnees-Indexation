package fr.univtours.polytech.di.multimedia;

/**
 * La classe principale.
 * @author S�bastien Aupetit
 */
public class Main {

  /**
   * La fonction main.
   * @param args les arguments de la ligne de commande (non exploit�es)
   */
  public static void main(final String[] args) {
    // Cette variable indique s'il faut maximiser la fen�tre ou non
    final boolean maximizeFrame = true;
    final MainFrame mf = new MainFrame(maximizeFrame);
    mf.setVisible(true);
  }

  /**
   * Le constructeur est priv� car la classe est non instanciable.
   */
  private Main() {
    // rien � faire
  }
}
