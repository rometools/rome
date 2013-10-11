/*
 * Copyright 2004 Sun Microsystems, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.rometools.fetcher;

/**
 * @author Nick Lothian
 *
 */
public class FetcherException extends Exception {
	private static final long serialVersionUID = -7479645796948092380L;

	int responseCode;
	
	public FetcherException(Throwable cause) {
		super();
		initCause(cause);
	}
	
	public FetcherException(String message, Throwable cause) {
		super(message);
		initCause(cause);
	}
	
	public FetcherException(String message) {
		super(message);
	}
	
	public FetcherException(int responseCode, String message) {
		this(message);		
		this.responseCode = responseCode;
	}

	public int getResponseCode() {
		return responseCode;
	}

}
