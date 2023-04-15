var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop15_faulty { 
	eventually (all f:File | f in Trash)
}

run  prop15_faulty