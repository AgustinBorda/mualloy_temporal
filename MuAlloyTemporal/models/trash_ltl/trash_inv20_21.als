var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop20_faulty { 
	always all f : File | f in Trash triggered f not in Protected 
}

run  prop20_faulty