#!/usr/bin/env python
# encoding: utf-8
import sys, string
ip="0xA/0xA/0xA"
date=string.spilt(ip, "/")
print date

"""
bintodec.py

Created by Pushkar Modi on 2010-10-03.
"""

def sets():
	a = set()
	b = set()
	a.add("Pushkar")
	a.add("Modi")
	b.add("Roopa")
	b.add("Modi")
	a.update(b)
	print a

def dicts():
	a = {"pushkar" : 0, "modi" : 0}
	b = {"roopa" : 0}
	a.update(b)
	print a


def bintodec():
	ip = int("00111110101100111010110000000011", 2)
	print ip


if __name__ == '__main__':
	main()