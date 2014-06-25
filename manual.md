Manual
=========

####IDE
CodeBlocks has been used to develop the project.

####Installation
To start using the library you have to link the static library file to your project/file. You can do that in CodeBlocks by going to 
`Settings -> Compiler -> Linker settings -> add button`
Then you have to select the static library which can be found in the folder `release/static_lib`

When the library is linked, then you just have to include the headers to your project by writing
`#include "release/includes/string_slib.hpp`. You just have to include the `string_slib.hpp`.


####Documentation
Documentation is generated using Doxygen. The documentaion is in html format and is available in the `documentation` folder. You have to open the `index.html` file with the browser.
All working algorithms are documented.

If you believe that the html generated documentation is deprecated, then you can generate it yourself by copying the doxygen executable into the "Code Blocks project" folder and then running `./doxygen`. This will generate folders `html` and `latex` which will contain the newly generated documentation.
