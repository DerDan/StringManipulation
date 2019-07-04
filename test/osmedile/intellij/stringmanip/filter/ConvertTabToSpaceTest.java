package osmedile.intellij.stringmanip.filter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConvertTabToSpaceTest {
    @Test
    public void testConvertTabToSpaces() throws Exception {
        ConvertTabToSpaces convert = new ConvertTabToSpaces(false);
        String a = convert.transformByLine("\t\tABCD");
        assertEquals("      ABCD", convert.transformByLine("\t\tABCD"));
        assertEquals("   A  BCD", convert.transformByLine("\tA\tBCD"));
        assertEquals("   AB CD", convert.transformByLine("\tAB\tCD"));
        assertEquals("   ABC   D  ", convert.transformByLine("\tABC\tD\t"));
        assertEquals("   A  BCD", convert.transformByLine(" \tA\tBCD"));
        assertEquals("   A  BCD", convert.transformByLine("  \tA\tBCD"));
        String ref = convert.transformByLine("\t\tABCD", 0);
        assertEquals("      ABCD", ref);
        assertEquals("     ABCD", convert.transformByLine("\t\tABCD", 1));
        assertEquals("    ABCD", convert.transformByLine("\t\tABCD", 2));
        assertEquals("      ABCD", convert.transformByLine("\t\tABCD", 3));
    }
}
