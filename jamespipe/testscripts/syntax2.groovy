#!/usr/bin/env groovy

import groovy.sql.Sql
import org.h2.Driver

list = [0.078125,0.15625,0.3125,0.625,1.25,10,2.5,5]
//list = ["A","B","C","D","E","F","G","H"]
//list = ["A","B"]

println "list.size()="+list.size()

list = list.collect{"\"$it\" DOUBLE"}.join(",")

def db = Sql.newInstance("jdbc:h2:mem:data","org.h2.Driver")

db.execute("create table results($list);" as String)

query = """
select column_name from information_schema.columns where table_name = 'RESULTS';"""
columnNames = query(db,query)
println "columnNames: "+columnNames


rowList = [0.2421875,0.703125,0.69921875,0.69921875,0.69921875,0.69921875,0.69921875,0.69921875]
//rowList = [1.0,2.0]
println "rowList.size = rowList.size()"
row = rowList.join(",")
println "row: "+row

//query = """insert into results values(1,2);""" 
query = """insert into results values($row);""" as String
db.execute(query)

def query(db,query){
  rlist = []
  db.eachRow(query){
    if ((it[0] == null) || (it[0] == 'null')) return(null)
    rlist << it[0]
  }
  return(rlist)
}