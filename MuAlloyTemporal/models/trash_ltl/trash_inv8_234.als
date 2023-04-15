var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop8_faulty { 
	eventually all f:File | one f.link implies eventually f in Trash
}

run  prop8_faulty