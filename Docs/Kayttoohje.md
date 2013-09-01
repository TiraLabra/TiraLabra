# Käyttöohje

## Laitteistovaatimukset

Javan versio 7 on pakollinen, koska JUnit-testeissä on käytetty vasta 7:ssa tullutta binäärisyntaksia.

## Käynnistys

Ohjelma käynnistetään seuraavasti:

```
cd testaus/
java -jar Tiralabra_maven.jar
```

## Käyttö

Ohjelman päävalikko näyttää tältä:
```
Welcome to a Huffman/Steganographer encoder/decoder program
Choose next action:
1) Huffman-Stegano encode
2) Stegano-Huffman decode
q) Quit
>>
```

### Koodaus

```
# Luodaan syöte
$ echo "Hello world, Huffman" >> testi.txt

$ java -jar Tiralabra_maven.jar

Welcome to a Huffman/Steganographer encoder/decoder program
Choose next action:
1) Huffman-Stegano encode
2) Stegano-Huffman decode
q) Quit
>> 1
Give path to plain text file> testi.txt
2013-09-01 13:16:37 >> Starting Huffman encoding
2013-09-01 13:16:37 >> Frequency analysis starting
2013-09-01 13:16:37 >> 	calculate frequencies
2013-09-01 13:16:37 >> 		Message length: 21
2013-09-01 13:16:37 >> 10%
2013-09-01 13:16:37 >> 20%
2013-09-01 13:16:37 >> 30%
2013-09-01 13:16:37 >> 40%
2013-09-01 13:16:37 >> 50%
2013-09-01 13:16:37 >> 100%
2013-09-01 13:16:37 >> 	create weighted nodes
2013-09-01 13:16:37 >> Frequency analysis complete. Building tree
2013-09-01 13:16:37 >> Tree built. Assigning codes
2013-09-01 13:16:37 >> Encoding message
2013-09-01 13:16:37 >> 		Message length: 21
2013-09-01 13:16:37 >> Encoding complete
Original message length: 168 bits
Compressed message length: 81 bits
Compressed size: 48 %
Give path to image to inject> input.jpg
Encoded image saved as encoded-input.jpg.png
Huffman map saved as encoded-input.jpg.png.huff
``` 

### Purku 

```
Choose next action:
1) Huffman-Stegano encode
2) Stegano-Huffman decode
q) Quit
>> 2
Give path to image to decode> encoded-input.jpg.png
2013-09-01 13:16:59 >> Stegano decoding bits
Steganographic decoding done. Beginning Huffman decoding
2013-09-01 13:16:59 >> Huffman decoding started. Building reverse tree
2013-09-01 13:16:59 >> Decoding message
Hello world, Huffman
```

### Lopetus

```
Choose next action:
1) Huffman-Stegano encode
2) Stegano-Huffman decode
q) Quit
>> q
Goodbye.
```

## Potentiaalisia ongelmatapauksia

* Tekstitiedosto sisältää muita kuin 8-bittisiä merkkejä => koodaus ei onnistu
* ```tiedostonimi.png.huff``` ei ole samassa kansiossa kuin ```tiedostonimi.png``` => purku ei onnistu ilman Huffman-karttaa
* Yrität tallentaa ison tekstin pieneen kuvaan => koodaus ei onnistu
