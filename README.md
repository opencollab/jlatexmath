<p><img src="docs/images/Logo.png"/></p>
<p>JLaTeXMath is a Java library. Its main purpose is to display mathematical formulas written in LaTeX. JLaTeXMath is the best Java library to display LaTeX code.</p>
<p>This library is used by numerous important projects like <a href="http://www.scilab.org/">Scilab</a>, <a href="http://www.geogebra.org/">Geogebra</a>, <a href="http://freeplane.sourceforge.net">Freeplane</a>, <a href="http://www.mathpiper.org/">Mathpiper</a>, <a href="http://db-maths.nuxit.net/CaRMetal/index_en.html">CaRMetal</a>, <a href="http://ultrastudio.org/">Ultrastudio</a>, etc.

<p> You can now follow the development of <i>JLaTeXMath</i> or ask for questions or requests in using the mailing-list <a href="mailto:jlatexmath@lists.forge.scilab.org">jlatexmath@lists.forge.scilab.org</a>. The releases are announced on it.
</p>
<p>You can subscribe to this mailing-list by checking <a href="http://lists.scilab.org/mailman/listinfo/jlatexmath">jlatexmath mailing list</a></p>
<p>The default encoding is UTF-8.</p>
<p>The most of LaTeX commands are available and :</p>
<ol type="i">
<li>macros from <i>amsmath</i> and symbols from <i>amssymb</i> and <i>stmaryrd</i>;</li>
<li><code>\includegraphics</code> (without options);</li>
<li>the TeX macro <code>\over</code>;</li>
<li>accents from <i>amsxtra</i> package;
<li>the macros <code>\definecolor</code>, <code>\textcolor</code>, <code>\colorbox</code> and <code>\fcolorbox</code> from the package <i>color</i>;</li>
<li>the macros <code>\rotatebox</code>, <code>\reflectbox</code> and <code>\scalebox</code> from the package <i>graphicx</i>;
<li>the most of latin unicode characters are available and cyrillic or greek characters are detected for the loading of the different fonts;</li>
<li>the commands <code>\newcommand</code> and <code>\newenvironment</code>;</li>
<li>the environments <code>array<\code>, <code>matrix</code>, <code>pmatrix</code>,..., <code>eqnarray</code>, <code>cases</code>;</li>
<li>the vertical and horizontal lines are handled in array environment;</li>
<li>the commands to change the size of the font are available : <code>\tiny</code>, <code>\small</code>,...,<code>\LARGE</code>, <code>\huge</code>, <code>\Huge</code>,
<li>the fonts are embedded in the jar file to be used by <a href="http://xmlgraphics.apache.org/fop/">fop 0.95</a> to generate PDF, PS or EPS (SVG export with shaped fonts works fine too). Since the version  0.9.5, the fop plugin is fully compatible with fop 1.0 and xmlgraphics 1.4;</li>
<li>and probably other things I forgot...</li>
</ol>
There is no dependency and no external programs to install : <i>JLaTeXMath</i> is fully functional by itself.</p>
<p>Few examples are available in the source distribution, they show how to use <i>JLaTeXMath</i> and for developpers how to write new commands in using Java.</p>
<p>A first example :</p>
<p><img src="docs/images/Formula1.png"/></p>
<p>a second one :</p>
<p><img src="docs/images/Formula2.png"/></p>
<p>and a third one :</p>
<p><img src="docs/images/Formula3.png"/></p>
<p><i>JLaTeXMath</i> is used by <a href="http://www.scilab.org">Scilab</a> to display formulas wrote in LaTeX in graphic windows :</p>
<p><img src="docs/images/ScilabScreenshot.png"/></p>
<p><i>JLaTeXMath</i> is a fork of the excellent project <a href="http://jmathtex.sourceforge.net/">JMathTeX</a>.</p>

To build the jlatexmath library just type ant...
The default task is "buildJar", the others are:
  - source;
  - plugin;
  - embedded;
  - src-all;
  - plugin-src;
  - clean;
  - minimal (for GeoGebra or web applications), dist/jlatexmath-minimal.zip will be created

Some examples are provided to show how to use easily the library.

Have fun and if you meet any problem, don't hesitate to create a new issue on github

<H1>License</H1>
Regarding JLaTeXMath’s Classpath Exception and JavaScript: If you use the Google Web Toolkit (GWT) to compile JLaTeXMath to an “executable” (in JavaScript) you can then include/link this “executable” JavaScript library on a website or inside another program. In this case the rest of the website/program need not be licensed under the GPL.
