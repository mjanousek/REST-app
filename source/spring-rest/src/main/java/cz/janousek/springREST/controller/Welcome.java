/**
 * author: Martin Janousek
 */
package cz.janousek.springREST.controller;

public class Welcome {

	private final long inc;
	private final String content;

	public Welcome(long inc, String content) {
		this.inc = inc;
		this.content = content;
	}

	public long getInc() {
		return inc;
	}

	public String getContent() {
		return content;
	}
}
