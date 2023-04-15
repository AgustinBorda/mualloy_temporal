var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop1_faulty { 
	no Trash and no (Protected & Trash) 
}

run  prop1_faulty