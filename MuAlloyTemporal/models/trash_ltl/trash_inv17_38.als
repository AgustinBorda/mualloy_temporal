var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop17_faulty { 
	always all f : File | f in Trash implies f not in File
}

run  prop17_faulty