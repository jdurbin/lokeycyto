#!/usr/bin/env groovy 

import durbin.util.*


t = new Table("CP2.allLines.csv",",")

println "Done!"

// 495592 x 232 
// time ./testscripts/syntaxTable.groovy 
// Reading 495592 x 232 table...Caught: java.lang.OutOfMemoryError: GC overhead limit exceeded
// 	at syntaxTable.run(syntaxTable.groovy:6)
// 
// real	7m7.678s
// user	5m45.547s
// sys	0m26.184s
