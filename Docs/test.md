Testing
=======

Testing is performed using JUnit unit tests. Testable input is written by hand and from that the output is calculated. If these match the algorithm is working correctly. Only exception to this is the ready algorithm, for which ready input and output are generated with outside software implementing DES (in this project, Java Cryptography Architecture). These should apply for this project also.  

Coverage
--------

Test coverage is nearly redundant for this project because of lacking code branches. Cobertura is used to chart line coverage where applicable.  

Performance
-----------

Performance is tested by running the complete program with different lengths of messages to encrypt and decrypt. These should produce a linear graph as a function of running time and length of message in blocks (message length divided by 64 bytes).

Results
-------

To be published...

Sources
-------
[Java Cryptography Architecture (JCA) Reference Guide] (http://docs.oracle.com/javase/8/docs/technotes/guides/security/crypto/CryptoSpec.html)
