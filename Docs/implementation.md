Implementation
==============
This project uses Java as it's language. Because of the nature of Java many extra operations that would be much simpler in, for example, C need to be implemented to the software. This brings unnecessary complexity and adds an additional challenge. It hinders it's performance slowing the algorithm down, meaning this implementation will be very slow compared to many other, lower level language implementations.

Performance
-----------
Time and space complexities remain what specified in the definition document (O(n) for time and O(1) for space). One algorithm's time complexity is O(n^2) (nested for-loops), but this has no practical difference to performance since input is always the standard 64 bits repeated n times.

Room for Improvements
---------------------
Several aspects of this project could have been done better. Mainly different modes of block ciphers could have been implemented (for example Electronic Code Book) and more attention to algorithm efficiency would have helped performance. In addition a natural progression from DES is Advanced Encryption Standard AES, the follower of DES and sufficient by today's standards.

Number one priority for the application as of April 28th, 2014  however is finishing it so that it works and correctly implements the DES standard. Intention is to finish this in May 2014.
