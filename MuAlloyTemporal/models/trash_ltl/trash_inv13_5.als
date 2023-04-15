var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop13_faulty { 
	always all f : File | (f in Trash) until (f not in Trash)
}

run  prop13_faulty