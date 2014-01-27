package fr.univtours.polytech.di.multimedia;

import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;

import javax.swing.JTextArea;

/**
 * Un flux OutputStream pour écrire dans un JTextArea.
 * @author Sébastien Aupetit
 */
class JTextAreaOutputStream extends FilterOutputStream {

  /** Le JTextArea. */
  private final JTextArea textArea;

  /**
   * Instantie un nouveau flux connecté au JTextArea.
   * @param area the JTextArea to connect to.
   */
  public JTextAreaOutputStream(final JTextArea area) {
    super(new ByteArrayOutputStream());
    this.textArea = area;
  }

  /**
   * {@inheritDoc}
   * @see java.io.FilterOutputStream#write(byte[])
   */
  @Override
  public void write(final byte b[]) throws IOException {
    textArea.append(new String(b));
    textArea.setCaretPosition(textArea.getDocument().getLength());

  }

  /**
   * {@inheritDoc}
   * @see java.io.FilterOutputStream#write(byte[], int, int)
   */
  @Override
  public void write(final byte b[], final int off, final int len)
      throws IOException {
    final String s = new String(b, off, len);
    textArea.append(s);
    textArea.setCaretPosition(textArea.getDocument().getLength());

  }
}
