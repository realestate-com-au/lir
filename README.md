License
-------

Copyright (C) 2014 REA Group Ltd.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.



LIR (Lucene Index Reader)
==========================

`lir` is a Lucene Index reading utility. Right now it can do two things. One, it can read through all the documents available in a index directory and print them out. Secondly, it can search through the index for a field name and value combination, specified by the user,  and retrive all the documents that contain the combination. 

Setup
=====
Clone the repo to your workstation

	git clone https://github.com/satyaavasarala/lir

Set the LIR_HOME in ~/.profile if on Linux or ENV variables if on Windows

	export LIR_HOME=<<your clone location>>

For access to 'lir' from anywhere on your workstation add the following to the PATH

	export PATH="$LIR_HOME/bin:$PATH"


Build
=====
Build the `lir` by using

	build-lir

Usage
=====
To list all the documents available in the Lucene Index

	lir -i $LIR_HOME/test-index-small/ -listall

To search for a specific field name and value across all the documents

	lir -i $LIR_HOME/test-index-small/ -search firstname,firstName500
