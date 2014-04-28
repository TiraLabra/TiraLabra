Testing
=======

Testing is performed using JUnit unit tests. Testable input is written by hand and from that the output is calculated. If these match the algorithm is working correctly. Only exception to this is the ready algorithm, for which ready input and output are generated with outside software implementing DES (in this project, Java Cryptography Architecture API). These should apply for this project also.  

Example input-output can also be found in [sample tests](http://orlingrabbe.com/des.htm) and guides for testing different errors in implementation of algorithm from Testing Implementation of DES.

Coverage
--------

Test coverage is nearly redundant for this project because of lacking code branches. Cobertura is used to chart line coverage where applicable.  

Performance
-----------

Performance is tested by running the complete program with different lengths of messages to encrypt and decrypt. These should produce a linear graph as a function of running time and length of message in blocks (message length divided by 64 bits).

Results
-------

To be published...

Sources
-------
[Java Cryptography Architecture (JCA) Reference Guide] (http://docs.oracle.com/javase/8/docs/technotes/guides/security/crypto/CryptoSpec.html)

[Testing Implementation of DES] (http://people.csail.mit.edu/rivest/pubs/Riv85.txt): Rivest, Ronald R., MIT Laboratory for Computer Science, Feb. 1985 (fetched 28.4.2014).
