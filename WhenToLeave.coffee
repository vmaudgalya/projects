###
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
###

'use strict'

https = require 'https'
util = require 'util'
origin_address = 'Sunnyvale'
destination_address = 'San+Francisco'
mode = 'driving'


options =
  hostname: 'maps.googleapis.com',
  path: "/maps/api/distancematrix/json?origins=#{origin_address}&destinations=#{destination_address}&mode=#{mode}&language=en-US&key=#{MAPS_KEY}"

https.get options, (res) ->

  res.on 'data', (chunk) ->
    duration = JSON.parse(chunk).rows[0].elements[0].duration.text;
    console.log "Currently, it will take you approximately #{duration} if you are #{mode} from #{origin_address} to #{destination_address.replace('+', ' ')}"

  res.on 'error', (error) ->
    console.log "Got an error: #{error.message}"

