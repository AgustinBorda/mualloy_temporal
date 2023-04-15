var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop13_faulty { 
	always some f : File | always f in Trash implies once f in Trash
}

run  prop13_faulty