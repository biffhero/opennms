package org.opennms.netmgt.config.tester;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.opennms.netmgt.config.DataSourceFactory;
import org.opennms.test.ConfigurationTestUtils;
import org.opennms.test.DaoTestConfigBean;
import org.springframework.util.StringUtils;


public class ConfigTesterTest {
    private static Set<String> m_filesTested = new HashSet<String>();
    private static Set<String> m_filesIgnored = new HashSet<String>();
	//private ConfigTesterDataSource m_dataSource;

    @Before
	public void init() {
        DaoTestConfigBean daoTestConfig = new DaoTestConfigBean();
        daoTestConfig.afterPropertiesSet();
	}

    @After
    public void done() {
    	ConfigTesterDataSource dataSource = (ConfigTesterDataSource) DataSourceFactory.getDataSource();
    	
    	if (dataSource.getConnectionGetAttempts().size() > 0) {
    		StringWriter writer = new StringWriter();
    		PrintWriter printWriter = new PrintWriter(writer);
    		for (SQLException e : dataSource.getConnectionGetAttempts()) {
				e.printStackTrace(printWriter);
    		}
    		fail(dataSource.getConnectionGetAttempts().size()
    				+ " DataSource.getConnection attempts were made: \n"
    				+ writer.toString());
    	}
    }

	@Test
	public void testAckdConfiguration() {
        testConfigFile("ackd-configuration.xml");
	}

	@Test
	public void testActiondConfiguration() {
        testConfigFile("actiond-configuration.xml");
	}

	@Test
	public void testAmiConfig() {
        testConfigFile("ami-config.xml");
	}
	
	@Test
	/**
	 * FIXME: AsteriskConfig doesn't appear to be in our classpath.
	 */
	public void testAsteriskConfiguration() {
        ignoreConfigFile("asterisk-configuration.properties");
	}
	
	@Test
	public void testAvailabilityReports() {
        testConfigFile("availability-reports.xml");
	}
	
	@Test
	/**
	 * This file isn't read directly by OpenNMS.
	 */
	public void testC3p0Properties() {
        ignoreConfigFile("c3p0.properties");
	}
	
	@Test
	public void testCapsdConfiguration() {
        testConfigFile("capsd-configuration.xml");
	}
	
	@Test
	public void testCategories() {
        testConfigFile("categories.xml");
	}
	
	@Test
	public void testChartConfiguration() {
        testConfigFile("chart-configuration.xml");
	}
	
	@Test
	/**
	 * Database access.
	 */
	public void testCollectdConfiguration() {
        ignoreConfigFile("collectd-configuration.xml");
	}
	
	@Test
	public void testDatabaseReports() {
        testConfigFile("database-reports.xml");
	}
	
	@Test
	public void testDatabaseSchema() {
        testConfigFile("database-schema.xml");
	}
	
	@Test
	public void testDataCollectionConfig() {
        testConfigFile("datacollection-config.xml");
	}
	
	@Test
	public void testDestinationPaths() {
        testConfigFile("destinationPaths.xml");
	}
	
	@Test
	public void testDhcpdConfiguration() {
        testConfigFile("dhcpd-configuration.xml");
	}
	
	@Test
	public void testDiscoveryConfiguration() {
        testConfigFile("discovery-configuration.xml");
	}

	@Test
	public void testEventConf() {
        testConfigFile("eventconf.xml");
	}
    
	@Test
    public void testEventdConfiguration() {
        testConfigFile("eventd-configuration.xml");
    }
    
	@Test
    public void testEventsArchiverConfiguration() {
        testConfigFile("events-archiver-configuration.xml");
    }
    
	@Test
    public void testExcludeUeis() {
        testConfigFile("exclude-ueis.properties");
    }

	@Test
    public void testGroups() {
        testConfigFile("groups.xml");
    }
    
	@Test
    public void testHttpDatacollectionConfig() {
        testConfigFile("http-datacollection-config.xml");
    }

	@Test
    public void testJasperReports() {
        testConfigFile("jasper-reports.xml");
    }
    
	@Test
    public void testJavamailConfigurationProperties() {
        testConfigFile("javamail-configuration.properties");
    }
    
	@Test
    public void testJavamailConfigurationXml() {
        testConfigFile("javamail-configuration.xml");
    }

	@Test
    public void testJdbcDatacollectionConfig() {
        testConfigFile("jdbc-datacollection-config.xml");
    }
    
	@Test
    public void testJmxDatacollectionConfig() {
        testConfigFile("jmx-datacollection-config.xml");
    }

    @Test
	public void testKscPerformanceReports() {
        testConfigFile("ksc-performance-reports.xml");
	}

    @Test
    /**
     * FIXME: Database access.
     */
    public void testLinkdConfiguration() {
        ignoreConfigFile("linkd-configuration.xml");
    }

    @Test
    public void testMagicUsers() {
        testConfigFile("magic-users.properties");
    }

    @Test
    public void testMap() {
        testConfigFile("map.properties");
    }
    
    @Test
    /**
     * FIXME: Database access.
     */
    public void testMapsadapterConfiguration() {
        ignoreConfigFile("mapsadapter-configuration.xml");
    }
    
    @Test
    public void testMicroblogConfiguration() {
        testConfigFile("microblog-configuration.xml");
    }
    
    @Test
    public void testModelImporter() {
        testConfigFile("model-importer.properties");
    }
    
    @Test
    /**
     * FIXME: Don't know why this is ignored.
     * 
     * See GatewayGroupLoader for the code that we'd need to call in the ConfigTester.
     */
    public void testModemConfig() {
        ignoreConfigFile("modemConfig.properties");
    }
    
    @Test
    public void testMonitoringLocations() {
        testConfigFile("monitoring-locations.xml");
    }
    
    @Test
    public void testNotifdConfiguration() {
        testConfigFile("notifd-configuration.xml");
    }

    @Test
    public void testNotificationCommands() {
        testConfigFile("notificationCommands.xml");
    }
    
    @Test
    /**
     * FIXME: Database access.
     */
    public void testNotifications() {
        ignoreConfigFile("notifications.xml");
    }
    
    @Test
    public void testNsclientConfig() {
        testConfigFile("nsclient-config.xml");
    }
    
    @Test
    public void testNsclientDatacollectionConfig() {
        testConfigFile("nsclient-datacollection-config.xml");
    }
    
    @Test
    public void testOpennmsDatasources() {
        testConfigFile("opennms-datasources.xml");
    }
    
    @Test
    public void testOpennmsServer() {
        testConfigFile("opennms-server.xml");
    }
    
    @Test
    /**
     * FIXME: Don't know why this is off.
     */
    public void testOpennms() {
        ignoreConfigFile("opennms.properties");
    }
    
    @Test
    public void testOtrs() {
        testConfigFile("otrs.properties");
    }
    
    @Test
    public void testPollOutages() {
        testConfigFile("poll-outages.xml");
    }
    
    @Test
    public void testPollerConfig() {
        testConfigFile("poller-config.properties");
    }
    
    @Test
    /**
     * FIXME: Database access.
     */
    public void testPollerConfiguration() {
        ignoreConfigFile("poller-configuration.xml");
    }
    
    @Test
    public void testProvisiondConfiguration() {
        testConfigFile("provisiond-configuration.xml");
    }

    @Test
    /**
     * FIXME: Not part of the standard build?
     */
    public void testRancidConfiguration() {
        ignoreConfigFile("rancid-configuration.xml");
    }

    @Test
    public void testReportdConfiguration() {
        testConfigFile("reportd-configuration.xml");
    }
	
	@Test
	public void testResponseAdhocGraph() {
        testConfigFile("response-adhoc-graph.properties");
	}
	
	@Test
	public void testResponsePrefabGraph() {
        testConfigFile("response-graph.properties");
	}

	@Test
    public void testRrdConfiguration() {
        testConfigFile("rrd-configuration.properties");
    }

	@Test
    public void testRt() {
        testConfigFile("rt.properties");
    }

	@Test
    public void testRtcConfiguration() {
        testConfigFile("rtc-configuration.xml");
    }

	@Test
    public void testRwsConfiguration() {
        testConfigFile("rws-configuration.xml");
    }

	@Test
    public void testScriptdConfiguration() {
        testConfigFile("scriptd-configuration.xml");
    }

	@Test
    public void testServiceConfiguration() {
        testConfigFile("service-configuration.xml");
    }

	@Test
    public void testSiteStatusViews() {
        testConfigFile("site-status-views.xml");
    }

	@Test
    public void testSmsPhonebook() {
        testConfigFile("smsPhonebook.properties");
    }

	@Test
	public void testSnmpAdhocGraph() {
        testConfigFile("snmp-adhoc-graph.properties");
	}

	@Test
    /**
     * FIXME: Not part of the standard build?
     */
    public void testSnmpAssetAdapterConfiguration() {
        ignoreConfigFile("snmp-asset-adapter-configuration.xml");
    }

	@Test
    public void testSnmpConfig() {
        testConfigFile("snmp-config.xml");
    }

	@Test
	public void testSnmpPrefabGraph() {
        testConfigFile("snmp-graph.properties");
	}

	@Test
	/**
	 * FIXME: Database access.
	 */
    public void testSnmpInterfacePollerConfiguration() {
        ignoreConfigFile("snmp-interface-poller-configuration.xml");
    }

	@Test
    public void testStatsdConfiguration() {
        testConfigFile("statsd-configuration.xml");
    }

	@Test
    public void testSurveillanceViews() {
        testConfigFile("surveillance-views.xml");
    }

	@Test
    public void testSyslogdConfiguration() {
        testConfigFile("syslogd-configuration.xml");
    }

	@Test
	/**
	 * FIXME: Database access.
	 */
    public void testThreshdConfiguration() {
		ignoreConfigFile("threshd-configuration.xml");
    }

	@Test
    public void testThresholds() {
        testConfigFile("thresholds.xml");
    }

	@Test
    public void testTl1dConfiguration() {
        testConfigFile("tl1d-configuration.xml");
    }

	@Test
    public void testTranslatorConfiguration() {
        testConfigFile("translator-configuration.xml");
    }

	@Test
    public void testTrapdConfiguration() {
        testConfigFile("trapd-configuration.xml");
    }

	@Test
    public void testUsers() {
        testConfigFile("users.xml");
    }

	@Test
    public void testVacuumdConfiguration() {
        testConfigFile("vacuumd-configuration.xml");
    }

	@Test
    public void testViewsdisplay() {
        testConfigFile("viewsdisplay.xml");
    }

	@Test
    public void testVulnscandConfiguration() {
        testConfigFile("vulnscand-configuration.xml");
    }

	@Test
    public void testWmiConfig() {
        testConfigFile("wmi-config.xml");
    }

	@Test
    public void testWmiDatacollectionConfig() {
        testConfigFile("wmi-datacollection-config.xml");
    }

	@Test
    public void testXmlrpcdConfiguration() {
        testConfigFile("xmlrpcd-configuration.xml");
    }

	@Test
    public void testXmpConfig() {
        testConfigFile("xmp-config.xml");
    }

	@Test
    public void testXmpDatacollectionConfig() {
        testConfigFile("xmp-datacollection-config.xml");
    }

	@Test
	/**
	 * FIXME: Configuration code is not in its own class.
	 * 
	 * It's embedded in XMPPNotificationManager's constructor.
	 */
    public void testXmppConfiguration() {
        ignoreConfigFile("xmpp-configuration.properties");
    }
	
	@Test
	public void testAllConfigs() {
		ConfigTester.main(new String[] { "-a" });
	}

	private void testConfigFile(String file) {
		/*
		 * Add to the tested list first, so if we get a test failure
		 * for a specific file test, we don't also make 
         * testCheckAllDaemonXmlConfigFilesTested fail.
		 */
        m_filesTested.add(file);
        
		ConfigTester.main(new String[] { file });
	}
	
	private void ignoreConfigFile(String file) {
        m_filesIgnored.add(file);
	}
	
    @Test
    public void testCheckAllDaemonXmlConfigFilesTested() {
        File someConfigFile = ConfigurationTestUtils.getFileForConfigFile("discovery-configuration.xml");
        File configDir = someConfigFile.getParentFile();
        assertTrue("daemon configuration directory exists at " + configDir.getAbsolutePath(), configDir.exists());
        assertTrue("daemon configuration directory is a directory at " + configDir.getAbsolutePath(), configDir.isDirectory());

        String[] configFiles = configDir.list(new FilenameFilter() {
            public boolean accept(File file, String name) {
                return name.endsWith(".xml");
            } });
        
        Set<String> allXml = new HashSet<String>(Arrays.asList(configFiles));
        
        allXml.removeAll(m_filesTested);
        allXml.removeAll(m_filesIgnored);
        
        if (allXml.size() > 0) {
            List<String> files = new ArrayList<String>(allXml);
            Collections.sort(files);
            fail("These files in " + configDir.getAbsolutePath() + " were not tested: \n\t" + StringUtils.collectionToDelimitedString(files, "\n\t"));
        }
    }
}
