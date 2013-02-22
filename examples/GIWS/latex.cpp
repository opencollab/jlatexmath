/*
 * JLaTeXMath ( http://forge.scilab.org/jlatexmath ) - This file is part of JLaTeXMath
 *
 * Copyright (C) 2012 - Calixte DENIZET & Sylvestre LEDRU
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
 */

#include "LaTeXGenerator.hxx"
#include <jni.h>

JavaVM* create_vm() {
    JavaVM* jvm; JNIEnv* env;

    JavaVMInitArgs args;
    JavaVMOption options[2];

    args.version = JNI_VERSION_1_4;

    args.nOptions = 2;
    options[0].optionString = const_cast<char*>("-Djava.class.path=./dist/GiwsExample.jar:/usr/share/java/jlatexmath.jar");
    options[1].optionString = const_cast<char*>("-Xcheck:jni");
    args.options = options;

    args.ignoreUnrecognized = JNI_FALSE;

    JNI_CreateJavaVM(&jvm, (void **)&env, &args);

    return jvm;
}

int main(int argc, char ** argv)
{
    if (argc == 3)
    {
	JavaVM* jvm = create_vm();
	org_scilab_forge_example_giws::LaTeXGenerator * latex = new org_scilab_forge_example_giws::LaTeXGenerator(jvm);
	latex->generate(argv[1], argv[2]);
	
	delete latex;
    }

    return 0;
}
