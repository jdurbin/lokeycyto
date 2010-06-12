#!/usr/bin/env groovy 

import groovy.sql.Sql
import org.h2.Driver

database = "CP2DB"

// connect to the file-based database specified...
def db = Sql.newInstance("jdbc:h2:$database","org.h2.Driver")


query = """select distinct(pm.Compound) from pm;"""
compounds = query(db,query)
println compounds

query = "select distinct(pm.molarity) from pm;"
molarities = query(db,query)
println molarities


// Oddly, table_name is case sensitive. 
query = """
select column_name from information_schema.columns where table_name = 'DATA';"""
features = query(db,query)
println "${features.size()}"

feature = features[0]
println "one feature: $feature"

query = """
  select data.$feature from pm,data
  where pm.WellName = data.WellName and pm.molarity like '%null%';
"""
nullresults = query(db,query)





/**
* Perform the sql query and return the results as a list. 
*/ 
def query(db,query){
  rlist = []
  db.eachRow(query as String){
    if ((it[0] == null) || (it[0] == 'null')) return(null)
    rlist << it[0]
  }
  return(rlist)
}