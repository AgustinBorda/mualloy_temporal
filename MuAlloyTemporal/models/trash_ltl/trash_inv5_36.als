var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop5_faulty { 
 	all f : File | always f in Trash => eventually f not in File
}

run  prop5_faulty