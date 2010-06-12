#!/usr/bin/env groovy 


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
