package com.tomitribe.snitch.sirona;

import com.tomitribe.snitch.util.IO;
import org.apache.sirona.configuration.ConfigurationProvider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.util.Arrays.asList;

/**
 * - From classloader: snitch.properties
 * - From files:  ./snitch.properties,
 *              ./conf/snitch.properties,
 *              ${catalina.base}/snitch.properties,
 *              ${catalina.base}/conf/snitch.properties
 * - From system properties
 *
 * All are finally merged.
 */
public class SnitchConfigurationProvider implements ConfigurationProvider {
    @Override
    public int ordinal() {
        return 0;
    }

    @Override
    public Properties configuration() {
        final Properties props = new Properties();
        for (final String potentialName : asList("snitch.properties")) {
            for (final String prefix : asList("", "/")) {
                final InputStream is = SnitchConfigurationProvider.class.getClassLoader().getResourceAsStream(prefix + potentialName);
                if (is != null) {
                    try {
                        props.load(is);
                    } catch (final IOException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
            }

            for (final String parent : asList(".", System.getProperty("catalina.base"))) {
                if (parent == null) {
                    continue;
                }

                for (final String folder : asList(".", "conf")) {
                    final File f = new File(new File(new File(parent), folder), potentialName);
                    if (f.isFile()) {
                        try {
                            props.putAll(IO.readProperties(f));
                        } catch (final IOException e) {
                            throw new IllegalArgumentException(e);
                        }
                    }
                }
            }
        }
        props.putAll(System.getProperties()); // sirona supports it but put it before these ones so keep overriding
        return props;
    }
}
