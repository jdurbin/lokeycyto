#!/usr/bin/env Rscript

#x = c(1,2,3,4,5)
#y = c(1,2,3,4,6)


#x = c(1,2  ,3,4,5, 7, 8, 9,10,11,12,13,14,15)
#y = c(1,3.5,5,6,8,10,11,12,13,14,15,16,17,18)

x = c(1.1,2.2,3.3,4.2,5.2, 7.2, 8.2, 9.2,10.2,11.2,12.2,13.2,14.2,15.2)
y = c(1.2,3.5,5.1,6.1,8.1,10.1,11.1,12.1,13.1,14.1,15.1,16.1,17.1,18.1)


ks.test(x,y)

