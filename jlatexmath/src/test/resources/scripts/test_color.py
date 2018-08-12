# Generate a test for colors

ltx = '<?xml version="1.0"?>\n'
ltx += '<tests name="colors_xxx">\n'
ltx += '<test>\n'
ltx += '<id name="1"/>\n'
ltx += '<code>\n'
ltx += '<![CDATA[\n'
ltx += '\\begin{tabular}{|' + 'c|' * 64 + '}\n'
ltx += '\\hline\n'
for i in range(4096):
    color = "{0:#0{1}x}".format(i, 5)[2:].upper()
    ltx += '\\cellcolor{#' + color + '}\\nbsp'
    if (i + 1) % 64 == 0:
        ltx += '\\cr\n\\hline\n'
    else:
        ltx += '&'
ltx += '\\end{tabular}\n'
ltx += ']]>\n'
ltx += '</code>\n'
ltx += '<expected success="true"/>\n'
ltx += '</test>\n'
ltx += '</tests>\n'

with open('test_colors_xxx.xml', 'w') as Out:
    Out.write(ltx)

ltx = '<?xml version="1.0"?>\n'
ltx += '<tests name="colors_rgb">\n'
ltx += '<test>\n'
ltx += '<id name="1"/>\n'
ltx += '<code>\n'
ltx += '<![CDATA[\n'
ltx += '\\begin{tabular}{|' + 'c|' * 128 + '}\n'
ltx += '\\hline\n'
i = 0
for r in range(0, 256, 8):
    for g in range(0, 256, 8):
        for b in range(0, 256, 8):
            ltx += '\\cellcolor{{rgb({}, {}, {})}}\\nbsp'.format(r, g, b)
            if (i + 1) % 128 == 0:
                ltx += '\\cr\n\\hline\n'
            else:
                ltx += '&'
            i += 1
ltx += '\\end{tabular}\n'
ltx += ']]>\n'
ltx += '</code>\n'
ltx += '<expected success="true"/>\n'
ltx += '</test>\n'
ltx += '</tests>\n'

with open('test_colors_rgb.xml', 'w') as Out:
    Out.write(ltx)
