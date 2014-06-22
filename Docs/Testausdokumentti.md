
### Unit testing

* LinkedList

The list object is tested for addition and printing the string stored within.

* Binary string conversion

The module method is verified with a more strict test to create a binary representation string without using list comprehension.

* Padding

Padding is tested so it gives the correct length.

### Performance testing

Python's `timeit` is a useful tool for performance testing, and provides an easy to use API. In this document, `setup` implies the following setup: 

```
setup = '''
import nmd5
m = nmd5.new()
'''

setup = '''
import hashlib
import nmd5
m = nmd5.new()
n = hashlib.md5()
string = "a"*200
string2 = string.encode('utf-8')
'''
```
#### Digests
We begin by analysing digests. This requires a different setup. Calculating digests is in constant time and space complexity (notice increasing input sizes to update function):
```
In [13]: setup = '''
   ....: import nmd5
   ....: m = nmd5.new()
   ....: m.update("a")
   ....: '''

In [14]: print(min(timeit.Timer('m.digest()', setup=setup).repeat(7, 10000)))
0.22652548499172553

In [15]: print(min(timeit.Timer('m.hexdigest()', setup=setup).repeat(7, 10000)))
0.33181199099635705

In [16]: setup = '''
   ....: import nmd5
   ....: m = nmd5.new()
   ....: m.update("a"*5000)
   ....: '''

In [17]: print(min(timeit.Timer('m.digest()', setup=setup).repeat(7, 10000)))
0.22879273700527847

In [18]: print(min(timeit.Timer('m.hexdigest()', setup=setup).repeat(7, 10000)))
0.33244859499973245
```
A digest is always 16 bytes in size, and always calculates the result with registers A, B, C, D which are purposefully kept as 32-bit values. Conversion to digest and hexdigest only ever does the same list iteration for a fixed-length element. This makes the time and space complexity of creating digests `O(1)`.


#### Hashing function
By calling `update()` we can  evaluate the hashing function. Judging by how it actually cycles through 512-bit blocks, we will assume it's time complexity is linear. By testing it with steps of adding 100 more to input length per step, we can see that it is actually very slowly increasing.
```
In [3]: print(min(timeit.Timer('m.update("a"*51)', setup=setup).repeat(7, 10)))
0.011394502012990415

...

In [5]: print(min(timeit.Timer('m.update("a"*151)', setup=setup).repeat(7, 10)))
0.02944470100919716 

...

In [2]: print(min(timeit.Timer('m.update("a"*251)', setup=setup).repeat(7, 10)))
0.05227168099372648

...

In [2]: 0.011394502012990415*100
Out[2]: 1.1394502012990415

In [3]: 0.011394502012990415*200
Out[3]: 2.278900402598083


```
If `O(n)`were a tight bound for the hashing algorithm, it would have been expected to see a 100 * increase in elapsed time - however it was only a fraction of it (0.011 -> 0.029 -> 0.052). This has to do with the fact that the running time only increases when you reach a message length that is goes over `n % 448` - that is, we need to append an extra `448 - (n % 448)` bits to the message, thus introducing an extra round of calculating the words and one extra round for the main hashing function. Similarly, the space complexity of the algorithm jumps at 512-bit intervals, because only when you run out of bits in 512 bits you have to add another set of 512. 

We can also see that by dividing the expected elapsed time by four you get close to the actual running time:
```
In [14]: 1.1394502012990415/4
Out[14]: 0.2848625503247604

In [15]: 2.278900402598083/4
Out[15]: 0.5697251006495208
```
This would suggest that `O(n/4)` is the actual complexity which indeed makes it `O(n)`.

### Optimisations
While the RFC clearly states the MD5 algorithm, there are some optimisations we can make. Python is not very suited for "heavy lifting" algorithms, and this project certainly does not aim to make it close to the actual `hashlib` implementation (which is written in C), but we can look into a few cases.

#### Hex digest optimisation:
We can further clarify hex digest generation by using Python list comprehensions.

Without list comprehension:
```
In [1]: print(min(timeit.Timer('m.hexdigest', setup=setup).repeat(7, 10000)))
0.0006872160010971129

In [2]: print(min(timeit.Timer('m.hexdigest', setup=setup).repeat(7, 10000)))
0.0006698640063405037

In [3]: print(min(timeit.Timer('m.hexdigest', setup=setup).repeat(7, 10000)))
0.0006723010155837983

In [4]: print(min(timeit.Timer('m.hexdigest', setup=setup).repeat(7, 10000)))
0.0006706719868816435
```

With list comprehension:
```
In [1]: print(min(timeit.Timer('m.hexdigest', setup=setup).repeat(7, 10000)))
0.0005399159854277968

In [2]: print(min(timeit.Timer('m.hexdigest', setup=setup).repeat(7, 10000)))
0.0006523950141854584

In [3]: print(min(timeit.Timer('m.hexdigest', setup=setup).repeat(7, 10000)))
0.0006520340102724731

In [4]: print(min(timeit.Timer('m.hexdigest', setup=setup).repeat(7, 10000)))
0.0006523980118799955
```

Turns out this did not give any noticeable effect, althought it is now more readable and concise.


#### Conversion to binary

Converting to binary strings is perhaps not the optimal method, but in this case it's a quite powerful abstraction to see how the algorithm works. Conversion always requires us to iterate over the string, which makes it linear in time complexity. 
We can see this by comparing 1000 iterations to 10000 iterations:
```
In [1]: print(min(timeit.Timer('m.toBinaryString("a"*8)', setup=setup).repeat(7, 1000)))
0.007961135997902602

In [2]: print(min(timeit.Timer('m.toBinaryString("a"*8)', setup=setup).repeat(7, 10000)))
0.07431591398199089

```

We can try optimising by removing now unnecessary checks which were written at the beginning of this project.
By changing the function to a list comprehension (suggested by rikuu):

```
In [1]: print(min(timeit.Timer('m.toBinaryString("a"*8)', setup=setup).repeat(7, 1000)))
0.005867065017810091

In [2]: print(min(timeit.Timer('m.toBinaryString("a"*8)', setup=setup).repeat(7, 10000)))
0.05759257302270271
```
we can see a slight improvement in performance.


#### Round function wrapper

The code was quite messy, because every round had its own function. Since functions are first class objects in Python, We can create a function R which takes a function as the first argument, so we don't have to explicitly create the round functions, but instead give the function F/G/H/I to R which it has to run. However, this doesn't impact running time in any meaningful way.

All functions declared explicitly (taken from the top of the page):
```
In [3]: print(min(timeit.Timer('m.update("a"*51)', setup=setup).repeat(7, 10)))
0.011394502012990415

In [5]: print(min(timeit.Timer('m.update("a"*151)', setup=setup).repeat(7, 10)))
0.02944470100919716 

In [2]: print(min(timeit.Timer('m.update("a"*251)', setup=setup).repeat(7, 10)))
0.05227168099372648
```

Using a wrapper function R:
```
In [3]: print(min(timeit.Timer('m.update("a"*51)', setup=setup).repeat(7, 10)))
0.01181726501090452

In [4]: print(min(timeit.Timer('m.update("a"*151)', setup=setup).repeat(7, 10)))
0.029189961001975462

In [5]: print(min(timeit.Timer('m.update("a"*251)', setup=setup).repeat(7, 10)))
0.04830886000127066
```

#### Constants moved to initialisation

The round shift amount constant initialisation were still left to be calculated on every block. By moving this outside the loop. This does not affect running time.

With every block:
```
In [3]: print(min(timeit.Timer('m.update("a"*15)', setup=setup).repeat(7, 100)))
0.24752386000182014
```
Only in the __hash function:
```
In [3]: print(min(timeit.Timer('m.update("a"*15)', setup=setup).repeat(7, 100)))
0.2515371789922938
```

#### Running time compared to hashlib

All in all, this implementation is very slow compared to `hashlib.md5`, which is written in C.

hashlib:
```

```
nmd5:
```

```
