#!/usr/bin/env groovy 


//   1    1     5    1    3    5
//  ?10  014   454
//   1   1+1   5-1-1  412      232                     0.25 * deltas from either side. 
//   1    2     3     1+1+1/2  -1/2 + 3 + 1/2  
// 
//   s0 = x0 + alpha*(x1-x0)
//   s1 = alpha*(x0-x1) + x1 + alpha*(x2-x1)
// 

import  hep.aida.ref.Histogram1D

numbins = 10
h = new Histogram1D("bob",numbins,1.0,5.0,)
x= [0.9,1.9,2.9,2.9,2.9,2.9,2.9,3.9,4.9,4.9,4.9,5.9,5.9,5.9,5.9,5.9,5.9]
x.each{h.fill(it)}

(0..<numbins).each{
  println "$it ${h.binHeight(it)}"
}


hx = (0..<numbins).collect{h.binHeight(it)}  as double[]

println hx

y = exponentialSmoothing(hx,0.25)
println y




//      0   1   2   3   4   5
//x = [1.0,1.0,5.0,1.0,3.0,5.0] as double[]



//y = exponentialSmoothing(x,0.25)





/**
* Performs exponential smoothing on the given array. 
* 
* x0,x1,x2,x3,x4 input. 
*
*   s0 = x0 + alpha*(x1-x0)
*   s1 = alpha*(x0-x1) + x1 + alpha*(x2-x1)
* 
*/
def exponentialSmoothing(double[] x,double alpha){
  def n = x.length
  double[] s = new double[n]
  s[0] = x[0] + alpha*(x[1]-x[0])  
  (1..< (n-1)).each{i->
    s[i] = alpha*(x[i-1]-x[i])+x[i]+alpha*(x[i+1]-x[i])
  }
  s[n-1] = alpha*(x[n-2]-x[n-1])+x[n-1]
     
  return(s)
}


/**
* Performs exponential smoothing on the given array. 
* 
* x0,x1,x2,x3,x4 input. 
* 
* s0 = x0
* s1 = alpha*x0+(1-alpha)*s0  = s0+ alpha*(x0-s0) 
* 
*/
def exponentialSmoothing2(double[] x,double alpha){
  double[] s = new double[x.length]
  
  s[0] = x[0];  
  (1..< x.length).each{i->
    s[i] = s[i-1]+alpha*(x[i-1]-s[i-1])
  }   
  return(s)
}