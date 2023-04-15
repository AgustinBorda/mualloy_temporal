var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop8_faulty { 
	eventually some a,b:File | a->b in link implies a in Trash
}

run  prop8_faulty