var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop8_faulty { 
	eventually all f:File | f in f.link implies f.link in Trash
}

run  prop8_faulty