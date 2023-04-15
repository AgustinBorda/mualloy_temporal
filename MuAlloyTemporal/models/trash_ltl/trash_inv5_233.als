var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop5_faulty { 
	all f : File | f not in Trash and eventually f in Trash
}

run  prop5_faulty