#!/usr/bin/env groovy 



x = [1.0,2.0,3.2,4.2] as double[]


sum = 0
x.each{sum+=it}
y = x.collect{it/sum}

//y = x*.multiply(1/sum)

println x
println y 

sum = 0;
y.each{sum+=it}

println sum