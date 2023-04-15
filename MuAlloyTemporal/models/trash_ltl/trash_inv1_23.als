var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop1_faulty { 
	before no Trash + Protected
}

run  prop1_faulty