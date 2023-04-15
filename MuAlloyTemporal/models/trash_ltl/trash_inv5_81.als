var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop5_faulty { 
	some f : Trash | f in File until always f not in File
}

run  prop5_faulty