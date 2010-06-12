#!/usr/bin/env python

from scipy import stats
import numpy as np
from numpy import array, asarray, dot, ma, zeros, sum
import scipy.special as special
import scipy.linalg as linalg
import numpy as np

from scipy.stats import ks_2samp
from scipy.stats import ksprob


def ks_2samp2(data1, data2):
    """ Computes the Kolmogorov-Smirnof statistic on 2 samples.
    """
    data1, data2 = map(asarray, (data1, data2))
    
    print data1
    
    n1 = data1.shape[0]
    n2 = data2.shape[0]
    
    print n1,n2
    
    n1 = len(data1)
    n2 = len(data2)
    
    print n1,n2
    
    data1 = np.sort(data1)
    data2 = np.sort(data2)
    
    print data1
    
    data_all = np.concatenate([data1,data2])
    
    #print data_all
    
    cdf1 = np.searchsorted(data1,data_all,side='right')/(1.0*n1)
    
    print cdf1
    
    cdf2 = (np.searchsorted(data2,data_all,side='right'))/(1.0*n2)
    d = np.max(np.absolute(cdf1-cdf2))
    #Note: d absolute not signed distance
    en = np.sqrt(n1*n2/float(n1+n2))
    try:
        prob = ksprob((en+0.12+0.11/en)*d)
    except:
        prob = 1.0
    return d, prob
    
#################################################


#fix random seed to get the same result
np.random.seed(12345678);

n1 = 200  # size of first sample
n2 = 300  # size of second sample

#different distribution we can reject the null hypothesis since the pvalue is below 1%

rvs1 = stats.norm.rvs(size=n1,loc=0.,scale=1);
rvs2 = stats.norm.rvs(size=n2,loc=0.5,scale=1.5)
a = ks_2samp2(rvs1,rvs2)
print a

#(0.20833333333333337, 4.6674975515806989e-005)

#slightly different distribution we cannot reject the null hypothesis at a 10% or lower alpha since the pvalue at 0.144 is higher than 10%

#rvs3 = stats.norm.rvs(size=n2,loc=0.01,scale=1.0)
#b = ks_2samp2(rvs1,rvs3)
#print b

#(0.10333333333333333, 0.14498781825751686)

#identical distribution we cannot reject the null hypothesis since the pvalue is high, 41%

#rvs4 = stats.norm.rvs(size=n2,loc=0.0,scale=1.0)
#c = ks_2samp2(rvs1,rvs4)
#print c
#(0.07999999999999996, 0.41126949729859719)



