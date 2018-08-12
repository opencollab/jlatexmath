# Generate a test from Geogebra examples

ltx = '<?xml version="1.0"?>\n'
ltx += '<tests name="geogebra">\n'
ltx += '<test>\n'
ltx += '<id name="1"/>\n'
ltx += '<code>\n'
ltx += '<![CDATA[\n'
ltx += '\\scalebox{3}{'
ltx += '\\begin{tabular}{|l|}\n'
ltx += '\\hline\n'

with open('scripts/geogebra.txt', 'r') as In:
    lines = In.readlines()

for line in lines:
    line = line[:-1] # remove '\n'
    if not line.startswith('{'):
        ltx += '{' + line + '}'
    else:
        ltx += line
    ltx += '\\cr\n\\hline\n'

ltx += '\\end{tabular}\n'
ltx += '}\n'
ltx += ']]>\n'
ltx += '</code>\n'
ltx += '<expected success="true"/>\n'
ltx += '</test>\n'
ltx += '</tests>\n'

with open('test_geogebra.xml', 'w') as Out:
    Out.write(ltx)    


