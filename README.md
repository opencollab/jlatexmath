<img src="docs/images/Logo.png"/>

<a href="https://travis-ci.org/opencollab/jlatexmath"><img src="https://travis-ci.org/opencollab/jlatexmath.svg"/></a><br/>
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.scilab.forge/jlatexmath/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/org.scilab.forge/jlatexmath)<br/>
[![Coverage Status](https://coveralls.io/repos/github/opencollab/jlatexmath/badge.svg?branch=master)](https://coveralls.io/github/opencollab/jlatexmath?branch=master)

JLaTeXMath is a Java library. Its main purpose is to display mathematical formulas written in LaTeX. JLaTeXMath is the best Java library to display LaTeX code.

This library is used by numerous important projects like <a href="http://www.scilab.org/">Scilab</a>, <a href="http://www.geogebra.org/">Geogebra</a>, <a href="http://freeplane.sourceforge.net">Freeplane</a>, <a href="http://www.mathpiper.org/">Mathpiper</a>, <a href="http://db-maths.nuxit.net/CaRMetal/index_en.html">CaRMetal</a>, <a href="http://ultrastudio.org/">Ultrastudio</a>, etc.

The default encoding is UTF-8.

Most LaTeX commands are available including:

* macros from <i>amsmath</i> and symbols from <i>amssymb</i> and <i>stmaryrd</i>
* `\includegraphics` (without options)
* TeX macro `\over`
* accents from <i>amsxtra</i> package
* macros `\definecolor`, `\textcolor`, `\colorbox` and `\fcolorbox` from the package <i>color</i>
* macros `\rotatebox`, `\reflectbox` and `\scalebox` from the package <i>graphicx</i>
* most latin unicode characters are available and cyrillic or greek characters are in the artifacts <i>jlatexmath-font-cyrillic</i> and <i>jlatexmath-font-greek</i>
* commands `\newcommand` and `\newenvironment`
* environments `array`, `matrix`, `pmatrix`,..., `eqnarray`, `cases`
* vertical and horizontal lines are handled in array environment
* commands to change the size of the font are available : `\tiny`, `\small`,...,`\LARGE`, `\huge`, `\Huge`
* fonts which are embedded in the jlatexmath jar file for use by <a href="http://xmlgraphics.apache.org/fop/">fop 1.0</a> to generate PDF, PS or EPS (SVG export with shaped fonts works fine too). Since jlatexmath version  0.9.5, the fop plugin is fully compatible with fop 1.0 and xmlgraphics 1.4
* and probably other things I forgot...

A few examples are available in the source distribution, they show how to use <i>JLaTeXMath</i> and how to write new commands using Java.

A first example :

&nbsp;&nbsp;&nbsp;&nbsp;<img src="docs/images/Formula1.png"/>

a second one :

&nbsp;&nbsp;&nbsp;&nbsp;<img src="docs/images/Formula2.png"/>

and a third one :

&nbsp;&nbsp;&nbsp;&nbsp;<img src="docs/images/Formula3.png"/>

<i>JLaTeXMath</i> is used by <a href="http://www.scilab.org">Scilab</a> to display formulas written in LaTeX in graphic windows:

<img src="docs/images/ScilabScreenshot.png"/>

<i>JLaTeXMath</i> is a fork of the excellent project <a href="http://jmathtex.sourceforge.net/">JMathTeX</a>.

To build the jlatexmath artifacts just type
    
    mvn clean install

Some examples are provided to show how to use easily the library.

Have fun and if you meet any problem, don't hesitate to create a new issue on github.

## License
Regarding JLaTeXMath’s Classpath Exception and JavaScript: If you use the Google Web Toolkit (GWT) to compile JLaTeXMath to an “executable” (in JavaScript) you can then include/link this “executable” JavaScript library on a website or inside another program. In this case the rest of the website/program need not be licensed under the GPL.

## Benchmarks
To run jmh benchmarks (measuring parse and render performance):

```bash
# benchmarks are in core module
cd jlatexmath
mvn clean install -P benchmark
```

## Code coverage
To run code coverage

```bash
mvn -DrepoToken=TOKEN clean cobertura:cobertura coveralls:report
```
