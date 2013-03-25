/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2012 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2012 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.features.topology.ssh.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.vaadin.server.LegacyApplication;
import com.vaadin.ui.LegacyWindow;

public class AuthWindowTest {



	String testHost = "debian.opennms.org";
	String emptyHost = "";
	int testPort = 22;
	int emptyPort = 0;
	String invalidPort = "-1";  // passed in to test for invalid port
	String validPort = "22";
	String invalidPortString = "abcd"; // passed in to test for error checking
	String invalidHost = "philip";
	String testPassword = "password";
	String testUser = "usr";

	AuthWindow normalWindow;
	AuthWindow noPortWindow; 
	AuthWindow noHostWindow; 
	AuthWindow emptyWindow;
	AuthWindow invalidHostWindow;
	LegacyWindow mainWindow;
	LegacyApplication app;

	@SuppressWarnings("serial")
	@Before
	public void setup (){     
		normalWindow = new AuthWindow(testHost, testPort);
		noPortWindow = new AuthWindow(testHost, emptyPort);
		noHostWindow = new AuthWindow(emptyHost, testPort);
		emptyWindow = new AuthWindow(emptyHost, emptyPort);
		invalidHostWindow = new AuthWindow(invalidHost, testPort);

		mainWindow = new LegacyWindow();
		app = new LegacyApplication() { //Empty Application
			@Override
			public void init() {}
		};
		app.setMainWindow(mainWindow);
		app.getMainWindow().addWindow(normalWindow);
		app.getMainWindow().addWindow(noHostWindow);
		app.getMainWindow().addWindow(noPortWindow);
		app.getMainWindow().addWindow(emptyWindow);
		app.getMainWindow().addWindow(invalidHostWindow);

	}

	@Test
	public void testButtonClick() {        
		normalWindow.buttonClick(null);
		assertEquals("Failed to log in", normalWindow.testString);

		noPortWindow.portField.setText(invalidPort);
		noPortWindow.buttonClick(null);
		assertEquals("Port must be between 1 and 65535", noPortWindow.testString);
		
		invalidHostWindow.buttonClick(null);
		assertEquals("Failed to connect to host", invalidHostWindow.testString);
		
		emptyWindow.portField.setText(invalidPortString);
		emptyWindow.buttonClick(null);
		assertEquals("Port must be an integer", emptyWindow.testString);
		
		emptyWindow.portField.setText(validPort);
		emptyWindow.hostField.setText(invalidHost);
		emptyWindow.buttonClick(null);
		assertEquals("Failed to connect to host", emptyWindow.testString);
	}

	@Test
	public void testAttach(){
		assertTrue(app.getMainWindow().getWindows().contains(normalWindow));
		app.getMainWindow().removeWindow(normalWindow);
		assertFalse(app.getMainWindow().getWindows().contains(normalWindow));
	}
	
	@Test
	public void testShowSSHWindow() {
		normalWindow.showSSHWindow();
		assertFalse(app.getMainWindow().getWindows().contains(normalWindow));
	}

}
