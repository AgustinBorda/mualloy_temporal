var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop10_faulty { 
	always (all f:File&Protected | always after f in Protected)
}

run  prop10_faulty