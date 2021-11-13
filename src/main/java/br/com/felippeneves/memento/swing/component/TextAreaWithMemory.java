package br.com.felippeneves.memento.swing.component;

import javax.swing.JTextArea;

import br.com.felippeneves.memento.swing.memory.Memento;

public class TextAreaWithMemory extends JTextArea {

	public TextAreaWithMemory(int rows, int columns) {
		super(rows, columns);
	}

	public TextAreaMemento save() {
		return new TextAreaMemento(getText());
	}
	
	public void restore(TextAreaMemento memento) {
		setText(memento.getState());
	}
	
	public static class TextAreaMemento implements Memento {
		private String text;

		public TextAreaMemento(String text) {
			this.text = text;
		}

		private String getState() {
			return text;
		}
	}
}