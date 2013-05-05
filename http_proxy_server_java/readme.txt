######################################################
#
# CSci 5131: Advanced Internet Programming, Spring 2009
#
######################################################

Assignment 1: Building a HTTP Proxy server in Java
Due Date: Feb 11, 2009

Team Members:  
=============
Ankur Kath (Mime Handling, File Parsing, Testing)
Atul Katiyar (Architecture and integration, Caching, Testing)
Pushkar Modi (Basic proxy, Architecture and integration, Testing)


PROJECT DETAILS:
================

Main folder: src
Sub-packages: Caching, MimeContent

Assumptions:
----------

1. jre 1.6 is required to run the proxy.

2. The URL has to start with "http" or "www" in the configuration file.

3. The URI's "www.google.com" and "google.com" are treated independently.

4. We support filtering of only two mime types : images and text. The proxy server ignores any other mime types mentioned as blocked in the configuration file.

5. Filtering can be provided at the domain (www.google.com) or subdomain level  (www.images.google.com). Each must be specified individually in the configuation file.

6. Filtering is not provided at the directory level (www.google.com/news).

7. The proxy services only single client at a time (single-threaded).

8. It is assumed that the requests being made are of "genuine" websites. 

9. Handling Status codes: No explicit consideration is given for non-OK (status code = 200) status code. The response (including the response header) as sent by the server is sent to the client. For example, when a request is made for "www.gmail.com", a response code of 301 (Moved) is received which is then passed to the client. Firefox correctly interprets the response and shows appropriate error msg whereas IE (7.0) shows blank page. We concluded from this that there is some ambiguity wrt to http client implementation by the web browser.

10. While determining the MIME content type for the requested URI, if MIME type could not be determined from the HEAD request, a conservative approach (wrt blocking the content based on config.txt configuration file) is followed by blocking the content.


Other:
-----
1. Prioritizing of mime types is implemented. This means that if we have the following entries in the configuration file, the third entry will supercede the first two:

www.microsoft.com image/jpeg
www.microsoft.com image/*
www.microsoft.com *

2. The cached data goes in a folder called "temp_cache" (in the pwd) of the Proxy Server.

3. Gives error " Server returned HTTP response code: 403 for URL.." with images on google webpage.

4. Logging is provided in XML format for debugging purposes. File <date>.xml is created in the pwd.

Steps to execute (in Linux):
============================

1. On client machine set the settings in the web browser for the proxy server to point to server machine

2. On the server machine (loaded with jre 1.6):
$ cd src
$ javac Proxy.java
$ java Proxy config.txt 50000

3. Try to access webpages from the web-browser on the client machine.


