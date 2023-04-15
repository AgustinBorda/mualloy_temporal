var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop20_faulty { 
	always all f : File | f not in Protected until f in Trash 
}

run  prop20_faulty