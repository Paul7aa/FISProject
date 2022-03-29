# FISProject
## Managementul productiei unei firme de asamblat calculatoare

Se cere sa se proiecteze un sistem de management al productiei unei firme de asamblat calculatoare care sa indeplineasca urmatoarele cerinte:

a.componentele de intrare (placi de baza, microprocesoare, hdd, carcase, monitoare, etc) au numele generic de componente. Firma face comenzi la furnizori pentru componente  şi fiecare comanda este inregistrata cu data, inventar de componente, furnizor, status (în asteptare, livrata), situate plata (platita, în plata) şi observatii

b.firma produce sisteme (laptopuri, desktorpuri…), care fac parte dintr-o anumita familie de produse, fiecare familie avand mai multi membrii. Printre familii sunt: laptopuri mini, desktopuri office, desktopuri gaming, laptopuri „desktop replacement”, etc

c.pe langa sistemele individuale, firma mai are şi pachete promotionale, de exemplu calculator + imprimanta, la care se ofera anumite reduceri

d.pentru fiecare sistem în baza de date exista urmatoarele date: cantitatea în inventar, cantitatea comandata şi cantitatea deja livrata. Livrarile catre beneficiari au statusul de: platit, neplatit

e.pentru partea de garantie a produselor, la fiecare sistem care intra în garantie, se specifica componentele care au avut probleme, pentru a se evita pe viitor folosirea acestora în noi sisteme

f.la cerere sistemul poate prezenta urmatoarele grafice:

1.pentru un cod de componeta, situatia stocurilor în timp
2.pentru un cod de sistem, situatia stocului în timp, a vanzarilor şi a produselor returnate pentru garantie
3.pentru un furnizori şi pentru beneficiari, sitautia platilor în timp

g.în afara familiilor standard, firma accepta comenzi de sisteme personalizate, atunci când comenzile depasesc o anumita valoare, sistemele respective fiind incadrate în baza de date cu un cod separat
