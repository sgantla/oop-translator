package oop.printer; 

import java.io.*; 

import java.text.BreakIterator;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import xtc.Constants;

import xtc.util.Pair;
import xtc.util.Utilities;

import xtc.tree.*; 

public class PodPrinter extends Utility {

	protected PrintWriter out; 
	protected PrintWriter directOut; 

	protected FileWriter o; 
	/** The current indentation level. */
	protected int indent = 0;

	/** The current column. */
	protected int column = Constants.FIRST_COLUMN;

	/** The current line. */
	protected long line = Constants.FIRST_LINE;
	
	public PodPrinter(FileWriter out) {
		this.o = out; 
	}

	public PodPrinter(OutputStream out) {
		this(new PrintWriter(out, false)); 
	}

	public PodPrinter(Writer out) {
		this(new PrintWriter(out, false)); 
	}

	public PodPrinter(PrintWriter out) {
		this.out = out; 
		directOut = out; 
	}

	public PodPrinter reset() {
		indent = 0; 
		column = Constants.FIRST_COLUMN; 
		line = Constants.FIRST_LINE; 

		return this; 
	}

	/* Get current column number */ 
	public int column() {
		return column; 
	}

	/* Set the current column to the specified number */ 
	public PodPrinter column(int column) {
		this.column = column; 
		return this; 
	}

	/* Get the current line number */
	public long line() {
		return line; 
	}

	public PodPrinter line(long line) {
		this.line = line;
		return this;
	}


	/**
	 * Increase the current indentation level.
	 *
	 * @return This printer.
	 */
	public PodPrinter incr() {
		indent += Constants.INDENTATION;
		return this;
	}

	/**
	 * Decrease the current indentation level.
	 *
	 * @return This printer.
	 */
	public PodPrinter decr() {
		indent -= Constants.INDENTATION;
		return this;
	}


	/* Get the current indentation level */
	public int level() {
		return indent / Constants.INDENTATION;
	}

	/* Indent */
	public PodPrinter indent() {
		for (int i = 0; i < indent; i ++) {
			out.print(' '); 
		}

		column += indent; 
		return this; 
	}

	/* Print the specified character */ 
	public PodPrinter p(char c) {
		out.print(c); 
		column += 1; 
		return this; 
	}

	/* Print the specified integer */
	public PodPrinter p(int i) {
		return p(Integer.toString(i)); 
	}

	/* Print the specified double */ 
	public PodPrinter p(double d) {
		return p(Double.toString(d)); 
	}

	/* Print the specified string */
	public PodPrinter p(String s) {
		out.print(s); 
		column += s.length(); 
		return this; 
	}

	/* Print the specified string followed by a newlline */
	public PodPrinter pln(String s) {
		out.println(s); 
		column = Constants.FIRST_COLUMN; 
		line ++; 
		return this; 
	}

	/* Print the specified character followed by a newline */
	public PodPrinter pln(char c) {
		out.println(c); 
		column = Constants.FIRST_COLUMN; 
		line ++; 
		return this; 
	}

	/* Print out the specified integer followed by a newline */
	public PodPrinter pln(int i) {
		return pln(Integer.toString(i)); 
	}

	/* Print the specified long followed by a newline */ 
	public PodPrinter pln(long l) {
		return pln(Long.toString(l)); 
	}

	/* Print the specified doubled followed by a newline */
	public PodPrinter pln(double d) {
		return pln(Double.toString(d)); 
	}


	/* Print a newline */
	public PodPrinter pln() {
		out.println(); 
		column = Constants.FIRST_COLUMN; 
		line ++; 
		return this; 
	}

	/* Print the specified node; if it is null, nothing is printed */
	public PodPrinter p(Node node) {
		new Visitor() {
		}.dispatch(node); 
		return(this); 
	}

	/* Print the specified comment */
	//public PodPrinter p(Comment comment) not supported

	public PodPrinter lineUp(Locatable locatable) {
		return lineUp(locatable, 0);
	}

	public PodPrinter lineUp(Locatable locatable, int before) {
		if (! locatable.hasLocation()) {
			throw new IllegalArgumentException("Locatable without location " +
					locatable);
		}

		final Location loc = locatable.getLocation();

		if (0 > loc.column - before) {
			throw new IllegalArgumentException("Invalid character distance " + before);
		}

		if (loc.line > line) {
			for (int i=0; i<loc.line-line; i++) pln();
			for (int i=0; i<loc.column-before; i++) p(' ');

		} else if ((loc.line == line) && (loc.column-before >= column)) {
			for (int i=0; i<loc.column-before-column; i++) p(' ');

		} else {
			p(' ');
		}

		return this;
	}

	/* Flush the underlying print writer */
	public PodPrinter flush() {
		out.flush(); 
		return this; 
	}

	/* Close the printer */
	public void close() {
		out.close(); 
	}
}
