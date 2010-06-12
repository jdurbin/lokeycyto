#!/usr/bin/env groovy 

import durbin.stats.*;
import umontreal.iro.lecuyer.probdist.*;

//a = [1,2,3,4,5,7] as double[]
//b = [1,3.5,5,6,8] as double[]
//b = [1,3.5,5,6,8] as double[]

a = [1.1,2.2,3.3,4.2,5.2, 7.2, 7.2, 9.2,10.2,11.2,12.2,13.2,14.2,15.2] as double[]
b = [1.2,3.5,5.1,6.1,8.1,10.1,11.1,12.1,13.1,14.1,15.1,16.1,17.1,18.1] as double[]

//a = [1,2,3,4,5,6] as double[]
//b = [2,3,4,5,6,7] as double[]

d = Empirical.kolmogorovDistance(a,b)
p = Empirical.kolmogorovTest(a,b)

println "d:"+d+"\tp:"+p


// double d = kolmogorovDistance(a,b);
int rna = a.length;
int rnb = b.length;
  
double en = Math.sqrt(rna*rnb/(rna+rnb));
println "en = "+en
double z = d * Math.sqrt(rna*rnb/(rna+rnb));

println "z="+z  

//int n = Math.max(a.length,b.length);
//println "n:"+n

//KolmogorovSmirnovDistQuick kdist = new KolmogorovSmirnovDistQuick(n);
//double prob = kdist.cdf(n,z);
//double prob = kdist.cdf(n,d);
//println prob

