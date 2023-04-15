var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop5_faulty { 
	eventually some f:File | f in File-Trash-Protected
}

run  prop5_faulty