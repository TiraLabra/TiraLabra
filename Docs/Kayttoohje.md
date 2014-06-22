# Use
Either install it in a Python project you use, or open up a Python shell in the directory where nmd5.py is.
From there, you can import the class and use it. Give any input string to the methods listed below to use this module.

```
import nmd5
m = nmd5.new()
m.update("Hello World!")
m.hexdigest() #'ed076287532e86365e841e92bfc50d8c'
m.digest() #b'\xed\x07b\x87S.\x866^\x84\x1e\x92\xbf\xc5\r\x8c'
```

or:

```
import nmd5
m = nmd5.new("Hello World!")
m.hexdigest() #'ed076287532e86365e841e92bfc50d8c'
m.digest() #b'\xed\x07b\x87S.\x866^\x84\x1e\x92\xbf\xc5\r\x8c'
```

or:

```
import nmd5
m = nmd5.md5("Hello World!")
m.hexdigest() #'ed076287532e86365e841e92bfc50d8c'
m.digest() #b'\xed\x07b\x87S.\x866^\x84\x1e\x92\xbf\xc5\r\x8c'
```

Use the copy method to create a copy from the nmd5 object.

# Testing

You can run tests simply by running:
```
python3.4 test.py
```
in the project root. You don't have to specify your Python version if it is 3.x by default.