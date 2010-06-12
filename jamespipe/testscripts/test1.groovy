#!/usr/bin/env groovy 

import durbin.util.*;

plateMapFile = args[0]
dataFile = args[1]

// Read in the platemap file...
well2InfoMap = [:]
new File(plateMapFile).withReader{r->
  headings = r.readLine().split(",")
  r.splitEachLine(","){fields->
     well2InfoMap[fields[1]] = [fields[2],fields[3]]
  }
}


// Read in the data file.  The data file is basically a 3D 
// vector of features x compounds x titrations. 
data = new MultidimensionalMap()
name2ColumnMap = [:]
col2NameMap = [:]
new File(dataFile).withReader{r->

  // Read in and clean up the headings, which come full of special characters...
  headings = r.readLine().split("\t")
  headings = headings*.replaceAll("\"","") // Remove quotes
  headings = headings*.replaceAll(/\s/,"") // Remove spaces
  headings = headings*.replaceAll(/\(/,"") // Remove left braces
  headings = headings*.replaceAll(/\)/,"") // Remove right braces
  headings = headings*.replaceAll("%","Pct") // Remove % signs. 
  
  // Create a map to look up column from heading...
  headings.eachWithIndex{name,idx-> name2ColumnMap[name] = idx}
  headings.eachWithIndex{name,idx-> col2NameMap[idx] = name}
  
  // Go through each line of data file, match up the well ID with the 
  // compound and titration, then save the value for each feature 
  // in the 3D map...
  r.splitEachLine("\t"){fields->
    wellCol = name2ColumnMap['WellName']
    wellID = fields[wellCol].replaceAll("\"","")             
    compoundName = well2InfoMap[wellID][0]
    titration = well2InfoMap[wellID][1]
    
    fields.eachWithIndex{value,col->
      featureID = col2NameMap[col]
      
      if (value == "") value = "null"      
      // Skip per cell features (which is this processed file are all null)
      if (!featureID.contains("Cell:")){
        println "$wellID\t$featureID \t $compoundName\t$titration\t$value"
        data[featureID][compoundName][titration]=value;             
      }
    }
  }
}

println data['MicronucleiperCellMicronuclei']['Benomyl'][10]