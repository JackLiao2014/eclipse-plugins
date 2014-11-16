/*******************************************************************************
 * Copyright (c) 2013 Liviu Ionescu.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Liviu Ionescu - initial version 
 *     		(based on Ningareddy Modase contribution)
 *******************************************************************************/

package ilg.gnuarmeclipse.debug.gdbjtag.jlink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

public class StreamReader extends Thread {

	// ------------------------------------------------------------------------

	private BufferedReader fReader;
	private StringWriter fWriter = new StringWriter();
	private String fKey;

	// ------------------------------------------------------------------------

	public StreamReader(InputStream is, String key) {
		fReader = new BufferedReader(new InputStreamReader(is));
		fKey = key;
	}

	// ------------------------------------------------------------------------

	public void run() {
		try {
			String line = fReader.readLine();
			while (line != null) {
				line = fReader.readLine();
				if (line != null && line.contains(fKey)) {
					fWriter.append(line.trim());
				}
			}
			fReader.close();
		} catch (IOException e) {
		}
	}

	public String getResult() {
		return fWriter.toString();
	}

	// ------------------------------------------------------------------------
}