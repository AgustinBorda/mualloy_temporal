var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop14_faulty { 
	always some f : Protected & Trash | f not in Protected'
}

run  prop14_faulty