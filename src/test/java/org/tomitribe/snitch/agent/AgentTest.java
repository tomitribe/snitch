/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch.agent;

import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.util.ASMifier;
import org.tomitribe.snitch.agent.colors.Blue;
import org.tomitribe.snitch.agent.colors.Color;
import org.tomitribe.snitch.agent.colors.Green;
import org.tomitribe.snitch.agent.colors.Main;
import org.tomitribe.snitch.agent.colors.Red;
import org.tomitribe.snitch.util.IO;

import java.io.File;
import java.io.OutputStream;
import java.util.Properties;

/**
 * @version $Revision$ $Date$
 */
public class AgentTest extends Assert {

    final File agentJar = Archive.archive()
            .manifest("Premain-Class", Agent.class)
            .manifest("Agent-Class", Agent.class)
            .manifest("Can-Redefine-Classes", true)
            .manifest("Can-Retransform-Classes", true)
            .addDir(JarLocation.jarLocation(Agent.class))
            .addJar(JarLocation.jarLocation(ASMifier.class))
            .asJar();

    final File testJar = Archive.archive()
            .add(Main.class)
            .add(Color.class)
            .add(Red.class)
            .add(Green.class)
            .add(Blue.class).asJar();

    @Test
    public void failNoConfigFiles() throws Exception {

        final Java.Result result = Java.java(
                "-javaagent:" + agentJar.getAbsolutePath(),
                "-classpath",
                testJar.getAbsolutePath(),
                Main.class.getName()
        );

        assertEquals(0, result.getExitCode());
        assertTrue(result.getErr().contains("No properties file specified"));
        assertTrue(result.getErr().contains("Tracker not installed"));
        assertFalse(result.getOut().contains("Tracker installed."));
    }

    @Test
    public void failInvalidConfFile() throws Exception {

        final Java.Result result = Java.java(
                "-javaagent:" + agentJar.getAbsolutePath()+"=foo.properties",
                "-classpath",
                testJar.getAbsolutePath(),
                Main.class.getName()
        );

        assertEquals(0, result.getExitCode());
        assertTrue(result.getErr().contains("Configuration file does not exist"));
        assertTrue(result.getErr().contains("Tracker not installed"));
        assertFalse(result.getOut().contains("Tracker installed."));
    }

    @Test
    public void singleConfFile() throws Exception {

        final File file = File.createTempFile("conf-", ".properties");
        final Properties properties = new Properties();

        properties.put("Red", Red.class.getMethod("red").toString());
        properties.put("Green", Green.class.getMethod("green").toString());
        properties.put("Blue", Blue.class.getMethod("blue").toString());
        properties.put("@Main", Main.class.getMethod("main", String[].class).toString());

        IO.writeProperties(properties, file);

        final Java.Result result = Java.java(
                "-javaagent:" + agentJar.getAbsolutePath() + "=" + file.getAbsolutePath(),
                "-classpath",
                testJar.getAbsolutePath(),
                Main.class.getName()
        );

        assertEquals(0, result.getExitCode());
        final String out = result.getOut();
        final String err = result.getErr();

        assertTrue(out.contains("Tracker installed."));
        assertTrue(out.contains("Tracking org/tomitribe/snitch/agent/colors/Main"));
        assertTrue(out.contains("Tracking org/tomitribe/snitch/agent/colors/Red"));
        assertTrue(out.contains("Tracking org/tomitribe/snitch/agent/colors/Green"));
        assertTrue(out.contains("Tracking org/tomitribe/snitch/agent/colors/Blue"));

        assertTrue(err.contains("Red{count=3"));
        assertTrue(err.contains("Blue{count=2"));
        assertTrue(err.contains("Green{count=1"));
        assertTrue(err.contains("Main{count=1"));
    }

    @Test
    public void multipleConfFile() throws Exception {

        final File fileA = File.createTempFile("conf-", ".properties");
        {
            final Properties properties = new Properties();
            properties.put("Red", Red.class.getMethod("red").toString());
            properties.put("Green", Green.class.getMethod("green").toString());
            IO.writeProperties(properties, fileA);
        }

        final File fileB = File.createTempFile("conf-", ".properties");
        {
            final Properties properties = new Properties();
            properties.put("Blue", Blue.class.getMethod("blue").toString());
            properties.put("@Main", Main.class.getMethod("main", String[].class).toString());
            IO.writeProperties(properties, fileB);
        }

        final Java.Result result = Java.java(
                "-javaagent:" + agentJar.getAbsolutePath() + "=" + fileA.getAbsolutePath() + "," + fileB.getAbsolutePath(),
                "-classpath",
                testJar.getAbsolutePath(),
                Main.class.getName()
        );

        assertEquals(0, result.getExitCode());
        final String out = result.getOut();
        final String err = result.getErr();

        assertTrue(out.contains("Tracker installed."));
        assertTrue(out.contains("Tracking org/tomitribe/snitch/agent/colors/Main"));
        assertTrue(out.contains("Tracking org/tomitribe/snitch/agent/colors/Red"));
        assertTrue(out.contains("Tracking org/tomitribe/snitch/agent/colors/Green"));
        assertTrue(out.contains("Tracking org/tomitribe/snitch/agent/colors/Blue"));

        assertTrue(err.contains("Red{count=3"));
        assertTrue(err.contains("Blue{count=2"));
        assertTrue(err.contains("Green{count=1"));
        assertTrue(err.contains("Main{count=1"));
    }


}