#!/usr/bin/env python

from scipy import stats
import numpy as np
from numpy import array, asarray, dot, ma, zeros, sum
import scipy.special as special
import scipy.linalg as linalg
import numpy as np

from scipy.stats import ks_2samp
from scipy.stats import ksprob

#b = array([1,2  ,3,4,5, 7, 8, 9,10,11,12,13,14,15])
#a = array([1,3.5,5,6,8,10,11,12,13,14,15,16,17,18])

a = array([1.1,2.2,3.3,4.2,5.2, 7.2, 8.2, 9.2,10.2,11.2,12.2,13.2,14.2,15.2])
b = array([1.2,3.5,5.1,6.1,8.1,10.1,11.1,12.1,13.1,14.1,15.1,16.1,17.1,18.1])

a = np.sort(a)
b = np.sort(b)

na = len(a)
nb = len(b)

dall =  np.concatenate([a,b])

print dall

cdfa = np.searchsorted(a,dall,side='right')/(1.0*na)
cdfb = np.searchsorted(b,dall,side='right')/(1.0*nb)

#cdfa = np.searchsorted(a,dall,side='right')
#cdfb = np.searchsorted(b,dall,side='right')

print "cdfa:"
print cdfa
print "cdfb:"
print cdfb

print "cdfa-cdfb"
print cdfa - cdfb

d = np.max(np.absolute(cdfa-cdfb))
#Note: d absolute not signed distance

en = np.sqrt(na*nb/float(na+nb))

print en

try:
    #prob = ksprob((en+0.12+0.11/en)*d)
    prob = ksprob(d*en)
except:
    prob = 1.0

print d, prob


print ksprob(0.2)
print ksprob(0.4)
print ksprob(0.6)
print ksprob(0.8)

