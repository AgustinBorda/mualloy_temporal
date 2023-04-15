var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop10_faulty { 
	once File in Protected implies always File in Protected
}

run  prop10_faulty