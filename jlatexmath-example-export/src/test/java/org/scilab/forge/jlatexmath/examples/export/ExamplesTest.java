/* Main.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://jlatexmath.sourceforge.net
 *
 * Copyright (C) 2009 DENIZET Calixte
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * A copy of the GNU General Public License can be found in the file
 * LICENSE.txt provided with the source distribution of this program (see
 * the META-INF directory in the source jar). This license can also be
 * found on the GNU website at http://www.gnu.org/licenses/gpl.html.
 *
 * If you did not receive a copy of the GNU General Public License along
 * with this program, contact the lead developer, or write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 *
 * Linking this library statically or dynamically with other modules
 * is making a combined work based on this library. Thus, the terms
 * and conditions of the GNU General Public License cover the whole
 * combination.
 *
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce
 * an executable, regardless of the license terms of these independent
 * modules, and to copy and distribute the resulting executable under terms
 * of your choice, provided that you also meet, for each linked independent
 * module, the terms and conditions of the license of that module.
 * An independent module is a module which is not derived from or based
 * on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obliged to do so.
 * If you do not wish to do so, delete this exception statement from your
 * version.
 *
 */
package org.scilab.forge.jlatexmath.examples.export;

import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.scilab.forge.jlatexmath.internal.util.Images;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExamplesTest {

    @Test
    public void testExample1() throws FileNotFoundException, TranscoderException, IOException {
        Example1.main(new String[0]);
        saveSvgAsPngAndCheck("Example1");
    }

    @Test
    public void testExample1Shaped()
    throws FileNotFoundException, TranscoderException, IOException {
        Example1.main(new String[0]);
        saveSvgAsPngAndCheck("Example1_shaped");
    }

    @Test
    @Ignore
    public void testExample2() throws FileNotFoundException, TranscoderException, IOException {
        // TODO get this working (copy relevant fonts to some
        Example2.main(new String[0]);
        saveSvgAsPngAndCheck("Example2");
    }

    @Test
    public void testExample2Shaped()
    throws FileNotFoundException, TranscoderException, IOException {
        Example2.main(new String[0]);
        saveSvgAsPngAndCheck("Example2_shaped");
    }

    @Test
    @Ignore
    public void testExample3() throws FileNotFoundException, TranscoderException, IOException {
        // TODO get this working
        Example3.main(new String[0]);
        saveSvgAsPngAndCheck("Example3");
    }

    @Test
    public void testExample3Shaped()
    throws FileNotFoundException, TranscoderException, IOException {
        Example3.main(new String[0]);
        saveSvgAsPngAndCheck("Example3_shaped");
    }

    @Test
    public void testExample4() throws TranscoderException, IOException {
        Example4.main(new String[0]);
        saveSvgAsPngAndCheck("Example4");
    }

    @Test
    public void testExample4Shaped() throws TranscoderException, IOException {
        Example4.main(new String[0]);
        saveSvgAsPngAndCheck("Example4_shaped");
    }

    private static void saveSvgAsPngAndCheck(String name)
    throws FileNotFoundException, TranscoderException, IOException {
        saveSvgAsPng(name);
        check(name + ".png");
    }

    private static void saveSvgAsPng(String name)
    throws FileNotFoundException, TranscoderException, IOException {
        TranscoderInput ti = new TranscoderInput(new FileInputStream("target/" + name + ".svg"));
        FileOutputStream os = new FileOutputStream("target/" + name + ".png");
        TranscoderOutput to = new TranscoderOutput(os);
        PNGTranscoder pt = new PNGTranscoder();
        pt.transcode(ti, to);
        os.flush();
        os.close();
    }

    @Test
    public void testExample5() {
        Example5.main(new String[0]);
    }

    @Test
    public void testURI() {
        String s = "jar:file:/C:/Users/david/.m2/repository/org/scilab/forge/jlatexmath/1.0.5-SNAPSHOT/jlatexmath-1.0.5-SNAPSHOT.jar!/org/scilab/forge/jlatexmath/fonts/latin/optional/jlm_cmss10.ttf";
        File f = new File(s);
        System.out.println(f.exists());
    }

    private static void check(String filename) {
        try {
            System.out.println("checking image " + filename);
            BufferedImage a = ImageIO.read(new File("src/test/resources/expected/" + filename));
            BufferedImage b = ImageIO.read(new File("target/" + filename));
            double distance = Images.distance(a, b);
            System.out.println("distance=" + distance);
            // TODO establish a reasonable threshold after running the tests on
            // different platforms (windows, osx, linux, others?)
            final double THRESHOLD = Images.DISTANCE_THRESHOLD;
            assertTrue("actual and expected images for " + filename + " are different sizes!",
                       distance >= 0);
            assertTrue("distance is above threshold, images are probably significantly different, distance="
                       + distance,
                       distance <= THRESHOLD);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
