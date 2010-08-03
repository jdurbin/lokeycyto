#!/bin/bash 

# Shellscript version of the pipeline... 
# process rawFile PlateMap histogramBins stattype
# 
# For example: 
# ./scripts/process.sh IXM490 PlateMaps/SP40002.csv 20 histdiff

# convert windows crlf to just lf
echo "gunzip -c $1.zip | tr '\r' '\n' > $1.lf.tab"
gunzip -c $1.zip | tr '\r' '\n' > $1.lf.tab

# convert tab file to csv file. Also, fix headers to have no special characters. 
echo "./scripts/tab2CSVAndFixHeader $1.lf.tab > $1.lf.csv"
./scripts/tab2CSVAndFixHeader $1.lf.tab > $1.lf.csv

# Sort the file by CellID so we can do an inline merge of cell rows...
echo "./scripts/sortNamed  $1.lf.csv CellID > $1.sorted.csv "
./scripts/sortNamed  $1.lf.csv CellID > $1.sorted.csv 

# Merge cell rows.  merged.csv is the file that should probably be saved
# from this preprocessing. 
echo "./scripts/mergeSortedCellRows $1.sorted.csv  > $1.merged.csv"
./scripts/mergeSortedCellRows $1.sorted.csv  > $1.merged.csv

# Compute a features by compound_molarity table, where each cell in the 
# table is a measure of the distance between the control (blank) and experimental
# distributions of values. 
echo "./scripts/computeFeatureByCompoundTable $1.merged.csv $2 $3 $4 > $1.$4.dirty.csv"
./scripts/computeFeatureByCompoundTable $1.merged.csv $2 $3 $4 > $1.$4.dirty.csv

# Remove uninformative rows from table...
echo "./scripts/cleanupCompoundByFeatureTable $1.$4.csv > $1.$4.csv"
./scripts/cleanupCompoundByFeatureTable $1.$4.dirty.csv > $1.$4.csv

echo "./scripts/featureByCompoundHeatmap $1.$4.csv $1 heatmap_pdf/$1.pdf heatmap_cdt/$1.cdt"
./scripts/featureByCompoundHeatmap $1.$4.csv $1 heatmap_pdf/$1.pdf heatmap_cdt/$1.cdt

echo "removing temporary files..."

# Clean up the temporary files.  Merged is kept as the 
# new "primary" input file for future runs.  
rm -f $1.lf.tab
rm -f $1.lf.csv 
rm -f $1.sorted.csv 
rm -f $1.$4.dirty.csv
# rm -f $1.merged.csv

echo "done." 