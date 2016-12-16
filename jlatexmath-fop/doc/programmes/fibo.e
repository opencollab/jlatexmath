indexing
	description :
		"{
		Le terme de rang `n' de la suite de Fibonacci (Version récursive 
		terminale).
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
	url           : "$HeadURL: svn://localhost/jlatexmath/programmes/fibo.e $"
	modifications : "$Author: haro $"	
		
...

feature

	fibonacci(n : INTEGER) : INTEGER is
			-- Le terme de rang `n'
		require
			n > 0
			
		do
			Result := fibo(n, 1, 1)
			
		ensure
			"{ Result = le terme de rang `n'}"
		end
		
feature {}

	fibo(n, F1, Resultat : INTEGER) : INTEGER is
	
		do
			if n < 3 then
				Result := Resultat
			else
				Result := fibo(n-1, Resultat, Resultat + F1)
			end
		end
		
... 
		
