Manual
======

The program is run as a command prompt application. As parameters it accepts the flags '-e' for encryption and '-d' for decryption, 'des' or '3des' for modes of encryption and decryption and, in order, the filepaths and -names for key, input data and output data. If the parameters are entered correctly the program runs the algorithm silently, exceptiong errors, and writes the output data to file.

To summarize, the command should be in the form  
```
%s -d mode key input output
```
or  
```
%s -e mode key input output
```
where %s is the program name.

If the program paramters are insufficient, nonexistent or simply wrong it prints a short guide to proper formatting of parameters.


Keyfile
-------

The keyfile is a normal plaintext file that contains either a string of 56 bits for DES and three strings of 56 bits for triple DES. If this requirement is not met the program generates necessary padding to fill the 56 bits. The program also generates necessary subkeys from these keys.
