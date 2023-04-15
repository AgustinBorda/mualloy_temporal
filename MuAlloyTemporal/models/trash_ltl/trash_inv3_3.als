var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop3_faulty { 
	always some f:File | f in Trash + Protected
}

run  prop3_faulty