# Use
```
import nmd5
m = nmd5.new()
m.update("Hello World!")
m.hexdigest() #'ed076287532e86365e841e92bfc50d8c'
m.digest() #b'\xed\x07b\x87S.\x866^\x84\x1e\x92\xbf\xc5\r\x8c'
```