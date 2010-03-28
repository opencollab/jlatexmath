package org.scilab.forge.jlatexmath;

import java.lang.Character.UnicodeBlock;
import java.io.InputStream;

public interface AlphabetRegistration {
    
    public Character.UnicodeBlock getUnicodeBlock();

    public Object getPackage();

    public String getTeXFontFileName();
}