package lz.cim.api.core.tool;

import junit.framework.TestCase;

import java.io.File;

public class ZipToolTest extends TestCase {

    public void testUnZip() {

        File file = new File("C:\\Users\\Administrator\\Desktop\\aaaa\\aaaa.zip");

        ZipTool.unZip(file, "C:\\Users\\Administrator\\Desktop\\aaaa\\bbbbb");
    }
}