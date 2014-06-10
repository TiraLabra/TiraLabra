# Notes
- use md5.hash("") to calculate a hash for any string, this is yet to be standardized for easy testing
- hexdigest() now works

# Use
```
import nmd5
m = nmd5.new()
m.hash("Hello World!")
m.hexdigest() // 'ed076287532e86365e841e92bfc50d8c'
```