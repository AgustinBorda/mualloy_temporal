var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop2_faulty { 
	historically no (File+Trash+Protected) 
}

run  prop2_faulty