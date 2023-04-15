var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop19_faulty { 
	eventually (all f:File | f in Protected implies f in Protected&Trash)
}

run  prop19_faulty