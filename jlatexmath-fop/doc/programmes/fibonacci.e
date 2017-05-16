indexing
	 description :
	 "{
	 Retourne l'élément de rang `n' de la suite de fibonacci (Version itérative)
	 La suite de Fibonacci est définie par :
	 <xi:include 
	     xmlns:xi="http://www.w3.org/2001/XInclude"
		  href="&equations;eq-2dim.xml"
		  parse="xml"/>
	 }"
	auteurs       : "Christophe HARO"
	copyright_auteur  : "Auteur  : (c) Christophe HARO, 2010"
	licenses_url  : "http://www.gnu.org/licences/gpl-3-0.html"
	licence       : "GPL"
	projet        : "JLATEXMATH"
	dossier       : "${DOCBOOK}/jlatexmath/"
	fichier       : "fibo.e"
	date          : "$Date: 2010-04-16 09:28:46 +0200 (Ven 16 avr 2010) $"
	revision      : "$Revision: 13 $"
	url           : "$HeadURL: svn://localhost/jlatexmath/programmes/fibonacci.e $"
	modifications : "$Author: haro $"

...

feature
	
	 fibonacci(n : INTEGER) : INTEGER is
				-- L'élément de rang `n'
	 
		  require
				n > 0
			
		  local
				     i : INTEGER -- Le rang du dernier élément calculé
				f1, f2 : INTEGER -- Les deux éléments qui le précèdent
		  do
				from
					     f1 := 1 -- fibonacci(i-1) : terme de rang 1
					 Result := 1 -- fibonacci(i)   : terme de rang 2
					      i := 2 -- Rang du dernier terme calculé
				
				invariant
					 Result = fibonacci(i)
					    f1  = fibonacci(i-1)
				variant
					 n - i
				
				until
					 i >= n
					 
				loop
					 f2 := f1
					 f1 := Result
					 Result := f1 + f2
					 i := i + 1
				end

		  ensure
				--"{ Result est l'élément de rang `n' de la suite }"
				
		  end
...
	
				
