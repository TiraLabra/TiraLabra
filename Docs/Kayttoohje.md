Usage: cmprsr (command) (archive_name) (file_name)

(command)
a - (a)dd to archive - creates new archive from file
e - (e)xtract from archive - extracts file from archive

Ohjelma vaatii aina kolme parametria, komennon, pakatun tiedoston nimen ja pakkaamattoman tiedoston nimen.
Ohjelma hyv‰ksyy mink‰ tahansa tiedoston pakattavaksi (ei kansioita).

Ohjelma k‰ytt‰‰ testaukseen kirjastoa cxxtest, se on lis‰tty git submodulena. Projekti hakemistossa tarvitsee kutsua
cd TiraLabra
git submodule init
git submodule update

K‰‰nt‰mist‰ varten tarvitsee cmake (v2.8.3 jos haluaa testit k‰‰nt‰‰ myˆs).
Tee hakemisto ohjelman buildille, siirry hakemistoon ja kutsu cmake [projektin hakemisto].
mkdir builders
cd builders
cmake ../Tiralabra
make
ctest
cmprsr a compressed [file]
cmprsr e compressed [other_file]

CMake luo makefilet ja sen j‰lkeen voi kutsua build kansiossa make.
Testit voi ajaa komennolla ctest.

Jos cmake kitisee testeist‰ eik‰ niit‰ saa toimimaan, testit voi laittaa pois projektin juuressa olevasta CMakeLists.txt kommentoimalla (#) enable_testing() ja add_subdirectory(test).
