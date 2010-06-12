#!/usr/bin/env groovy 



(v1,v2) = myFun(5)

println v1
println v2




def myFun(x){
  return([x+1,x+5])
}