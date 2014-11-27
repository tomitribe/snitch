package com.tomitribe.snitch.sirona;

import com.tomitribe.snitch.agent.Archive;
import com.tomitribe.snitch.agent.colors.Blue;
import org.apache.sirona.javaagent.AfterFork;
import org.apache.sirona.javaagent.AgentArgs;
import org.apache.sirona.javaagent.BeforeFork;
import org.apache.sirona.javaagent.JavaAgentRunner;
import org.apache.sirona.repositories.Repository;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(JavaAgentRunner.class)
public class SironaBasedMonitoringTest {
    private static final String SNITCH = "target/snitch-test.jar";

    @BeforeFork
    public static void packageTargetClassesAsJar() throws IOException {
        final File snitch = new File(SNITCH);
        if (snitch.exists()) {
            return;
        }
        Archive.archive().addDir(new File("target/classes")).toJar(snitch);
    }

    @AfterFork
    public static void clean() {
        final File file = new File(SNITCH);
        if (!file.delete()) {
            file.deleteOnExit();
        }
    }

    @Test
    @AgentArgs(value = "libs=" + SNITCH + "|environment-debug=true")
    public void snitch() {
        new Blue().blue();
        assertEquals(1, Repository.INSTANCE.counters().size());
    }
}
