package br.com.aoc2.cache.simulator.util;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class UtilTest extends TestCase {
    @Test
    public void testParseStringToBinarie() {
        var bits = "000";
        Assert.assertEquals(0, Util.parseBinarieToDecimal(bits));
    }
}